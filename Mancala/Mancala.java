import java.util.Scanner;

/**
 * Mancala - Timed mancala game manager.  To play two programs
 * against each * other, change the classes at (*1*) and (*2*).
 * Each player has a maximum of 2.5 minutes of game time, so each
 * game will last at most 5 minutes.  In your testing, you may
 * choose to compete against your program.  Or you may play your
 * program against itself.  In the end, your program will compete
 * as both MAX and MIN against other programs.
 *
 * @author: Todd Neller */

public class Mancala {

	/**
	 * <code>main</code> - manage a timed mancala game
	 *
	 * @param args a <code>String[]</code> value - unused
	 */
	public static void main(String[] args) {
		// Create players
		MancalaPlayer[] player = new MancalaPlayer[2];

		// (*1*) put player one class here
		player[GameNode.MAX] = new HumanMancalaPlayer(); 

		// (*2*) put player two class here
		Scanner in = new Scanner(System.in);
		int defaultLimit = 11;
		System.out.print("Search depth limit (default " + defaultLimit + ")? ");
		String input = in.nextLine().trim();
		int depthLimit = input.equals("") ? defaultLimit : Integer.parseInt(input);
		player[GameNode.MIN] = new DepthLimitedMancalaPlayer(depthLimit); 

		// Select whether or not playing as player 1:
		System.out.print("Play first (default y)? ");
		input = in.nextLine().trim().toLowerCase();
		if (!input.equals("") && input.charAt(0) == 'n') {
			// swap players
			MancalaPlayer tmp = player[GameNode.MAX];
			player[GameNode.MAX] = player[GameNode.MIN];
			player[GameNode.MIN] = tmp;
		}

		// Create times
		System.out.print("How many minutes for player 1 (default 2.5)? ");
		input = in.nextLine().trim();
		long ms1 = (long) (60000 * (input.equals("") ? 2.5 : Double.parseDouble(input)));
		System.out.print("How many minutes for player 2 (default 2.5)? ");
		input = in.nextLine().trim();
		long ms2 = (long) (60000 * (input.equals("") ? 2.5 : Double.parseDouble(input)));
		long[] playerMillisRemaining = {ms1, ms2};

		// Create a clock
		Stopwatch clock = new Stopwatch();
		long timeTaken;

		// Create an initial node
		MancalaNode node = new ScoreDiffMancalaNode();
		System.out.println(node);

		// While game is on...
		int move;
		String winner = "DRAW";
		while (!node.gameOver()) {
			// Request move from current player
			long timeRemaining = playerMillisRemaining[node.player];
			clock.reset();
			clock.start();
			move = player[node.player].chooseMove(node, timeRemaining);
			timeTaken = clock.stop();

			// Duduct time taken
			playerMillisRemaining[node.player] -= timeTaken;
			if (playerMillisRemaining[node.player] < 0) {
				if (node.player == GameNode.MAX) {
					System.out.println("Player 1 game timer expired.");
					winner = "PLAYER 2 WINS";
				} else {
					System.out.println("Player 2 game timer expired.");
					winner = "PLAYER 1 WINS";
				}
				break;
			}

			// Update game state
			System.out.println("Player " 
					+ ((node.player == GameNode.MAX) ? "1" : "2") 
					+ " makes move " 
					+ MancalaNode.moveToString(move) + ".");
			node.makeMove(move);

			// Display Progress
			System.out.println(node);
		}
		in.close();

		// Display winner and statistics
		if (node.gameOver())
			if (node.utility() > 0)
				winner = "PLAYER 1 WINS";
			else if (node.utility() < 0)
				winner = "PLAYER 2 WINS";
			else
				winner = "DRAW";
		System.out.println("Time Taken (ms): ");
		System.out.println("Player 1: " + (ms1 - playerMillisRemaining[GameNode.MAX]));
		System.out.println("Player 2: " + (ms2 - playerMillisRemaining[GameNode.MIN]));
		System.out.println(winner);
	}

}

