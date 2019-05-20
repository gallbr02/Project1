import java.io.File;
import java.io.FileInputStream;
import java.io.FileOutputStream;
import java.io.IOException;
import java.io.ObjectInputStream;
import java.io.ObjectOutputStream;
import java.util.ArrayList;
import java.util.HashSet;
import java.util.Random;
import java.util.Scanner;

/**
 * PigDataCollector.java - Facilitates data collection of human versus optimal computer Pig play
 *
 * @author Todd Neller
 * @version 1.0
 *
Copyright (C) 2015 Todd Neller
EXPERIMENTAL CODE - NOT FOR REDISTRIBUTION
 */

public class {
	int goal = 100;
	double epsilon = 1e-9;
	double[][][] p;
	boolean[][][] roll;
	String solutionFile = "Pig.dat";
	String playFile = "PigPlay.dat";
	long playerID = System.currentTimeMillis();
	long gameID = System.currentTimeMillis();
	ArrayList<PigEvent> history = new ArrayList<PigEvent>();
	HashSet<Long> gameIDSet = new HashSet<Long>();

	PigDataCollector() {
		initOptimalPolicy();
		loadHistory();
	}

	void initOptimalPolicy() {
		try {
			File f = new File(solutionFile);
			if (f.exists()) {
				System.out.print("Reading optimal play policy ... ");
				ObjectInputStream in = new ObjectInputStream(new FileInputStream(f));
				p = (double[][][]) in.readObject();
				roll = (boolean[][][]) in.readObject();
				in.close();
				System.out.println("Done.");
			}
			else {
				System.out.print("Computing optimal play policy ... ");
				p = new double[goal][goal][];
				roll = new boolean[goal][goal][];
				for (int i = 0; i < goal; i++)
					for (int j = 0; j < goal; j++) { 
						p[i][j] = new double[goal - i];
						roll[i][j] = new boolean[goal - i];
					}
				valueIterate();
				System.out.println("Done.");
				System.out.print("Saving optimal play policy ... ");
				ObjectOutputStream out = new ObjectOutputStream(new FileOutputStream(f));
				out.writeObject(p);
				out.writeObject(roll);
				out.close();
				System.out.println("Done.");
			}
		}
		catch (IOException e) {
			e.printStackTrace();
			System.exit(1);
		} catch (ClassNotFoundException e) {
			e.printStackTrace();
			System.exit(2);
		}
	}

	void valueIterate() {
		double maxChange;
		do {
			maxChange = 0.0;
			for (int i = 0; i < goal; i++) // for all i
			for (int j = 0; j < goal; j++) // for all j
			for (int k = 0; k < goal - i; k++) { // for all k
				double oldProb = p[i][j][k];

				// Compute the probability of winning with a roll
				double pRoll = 1.0 - pWin(j, i, 0);
				for (int roll = 2; roll <= 6; roll++)
					pRoll += pWin(i, j, k + roll);
				pRoll /= 6.0;

				// Compute the probability of winning with a hold
				double pHold = 1 - pWin(j, i + k, 0);

				roll[i][j][k] = pRoll > pHold;
				if (roll[i][j][k])
					p[i][j][k] = pRoll;
				else
					p[i][j][k] = pHold;
				double change = Math.abs(p[i][j][k] - oldProb);
				maxChange = Math.max(maxChange, change);
			}
		} while (maxChange >= epsilon);
	}

	public double pWin(int i, int j, int k) { 
		if (i + k >= goal)
			return 1.0;
		else if (j >= goal)
			return 0.0;
		else return p[i][j][k];
	}

	@SuppressWarnings("unchecked")
	void loadHistory() {
		try {
			File f = new File(playFile);
			if (f.exists()) {
				ObjectInputStream in = new ObjectInputStream(new FileInputStream(f));
				playerID = in.readLong();
				history = (ArrayList<PigEvent>) in.readObject();
				in.close();
				gameIDSet = new HashSet<Long>();
				for (PigEvent event : history)
					gameIDSet.add(event.gameID);
				System.out.printf("Data from %d game(s) recorded.\n", gameIDSet.size());
			}
		}
		catch (IOException e) {
			e.printStackTrace();
			System.exit(3);
		} catch (ClassNotFoundException e) {
			e.printStackTrace();
			System.exit(4);
		}
	}
	
	void play() {
		Random random = new Random();
		Scanner in = new Scanner(System.in);
		while (true) {
			gameID = System.currentTimeMillis();
			int[] scores = new int[3];
			int player = 1;
			int humanPlayer = (gameIDSet.size() % 2 == 0) ? 1 : 2;
			System.out.printf("You will be player %d.\n", humanPlayer);
			System.out.println("Enter nothing to roll; enter anything to hold.");
			int score = 0; 
			boolean gameOver = false;
			while (!gameOver) {
				for (int p = 1; p <= 2; p++)
					System.out.printf("Player %d score: %d\n", p, scores[p]);
				System.out.printf("It is player %d's turn.\n", player);
				score = scores[player];
				long currentPlayerID = (player == humanPlayer) ? playerID : 0;
				int otherPlayer = 3 - player;
				int turnTotal = 0;
				boolean turnOver = false;
				int r = random.nextInt(6) + 1;
				while (!turnOver) {
					history.add(new PigEvent(gameID, currentPlayerID, scores[player], scores[otherPlayer], turnTotal, r));
					System.out.printf("Roll: %d\n", r);
					if (r == 1) {
						turnTotal = 0;
						turnOver = true;
					}
					else {
						turnTotal = turnTotal + r;
						System.out.printf("Your score: %2d  Opponent score: %2d  Turn total: %2d  Roll/Hold? ", score, scores[otherPlayer], turnTotal);
						if (player == humanPlayer) { // human decision
							if (score + turnTotal >= goal) {
								System.out.println("hold (You win!)");
								turnOver = true;
							}
							else
								turnOver = (!in.nextLine().equals(""));
						}
						else  { // computer decision
							turnOver = (score + turnTotal >= goal || !roll[score][scores[otherPlayer]][turnTotal]);
							System.out.println(turnOver ? "hold" : "");
						}
						if (turnOver)
							history.add(new PigEvent(gameID, currentPlayerID, scores[player], scores[otherPlayer], turnTotal, 0));
						else
							r = random.nextInt(6) + 1;
					}
				}
				System.out.printf("Turn total: %d\n", turnTotal);
				score = score + turnTotal;
				System.out.printf("New score: %d\n", score);
				if (score >= 100)
					gameOver = true;
				scores[player] = score;
				player = (player == 1) ? 2 : 1;
			}
			
			// Game over, write out history
			System.out.print("Saving history ... ");
			gameIDSet.add(gameID);
			try {
				File f = new File(playFile);
				ObjectOutputStream out = new ObjectOutputStream(new FileOutputStream(f));
				out.writeLong(playerID);
				out.writeObject(history);
				out.close();
			}
			catch (IOException e) {
				e.printStackTrace();
				System.exit(5);
			}
			System.out.println("Done.");
			
			// Query whether or not to play again?
			boolean playingAgain = false;
			while (true) {
				System.out.printf("You have played %d games. Would you like to play again (yes/no)? ", gameIDSet.size());
				String response = in.nextLine().trim().toLowerCase();
				if (response.length() > 0 && response.charAt(0) == 'y')
					playingAgain = true;
				else if (response.length() > 0 && response.charAt(0) == 'n')
					playingAgain = false;
				else continue;
				break;
			}
			
			if (!playingAgain)
				break;
		}
		in.close();
	}

	public static void main(String[] args){
		new PigDataCollector().play();
	}
}
