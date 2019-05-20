import java.util.ArrayList;
import java.util.Random;
import java.util.Scanner;

/*
 * TurnTailReinforcementLearning - Reinforcement Learning (RL) approaches to the game of Turn Tail by Todd Neller.

In the game of Turn Tail, you are given a certain number of dollar coins and begin with no winnings.
Each turn, you may choose to keep all of your winnings and end the game, or flip all of your coins at once to potentially win more.
If all of your flipped coins come up tails, you lose all of your winnings and the game is over.
If any of your flipped coins come up heads, you win as many dollars as the total number of coins you flip.  
Then, you take all coins that came up tails, and these "turn tail and run", i.e. they are removed from the game for your next turn.
Finally, your winnings can never exceed some maximum winning limit of dollars.  
If at any point your winnings would exceed this limit, your winnings are capped at the limit and the game is over.
For a given initial number of coins and a given winnings limit, 
(1) how should one play, and
(2) what is the expected gain?

Author: Todd Neller
Date: October 31st, 2015
 */
public class TurnTailReinforcementLearning {
	static final int INITIAL_COINS, WINNINGS_LIMIT; 
	static final int HOLD = 0, FLIP = 1; // actions
	static final int[][] choose;
	static final Random random = new Random();
	static final Scanner in = new Scanner(System.in);

	static {
		System.out.print("Initial coins for analysis (default: 10)? ");
		String line = in.nextLine();
		if (line.length() == 0)
			line = "10";
		INITIAL_COINS = Integer.parseInt(line);
		System.out.print("Winnings limit for analysis (default: 50)? ");
		line = in.nextLine();
		if (line.length() == 0)
			line = "50";
		WINNINGS_LIMIT = Integer.parseInt(line);
		
		choose = new int[INITIAL_COINS + 1][INITIAL_COINS + 1];
		for (int i = 0; i <= INITIAL_COINS; i++) {
			choose[i][0] = choose[i][i] = 1;
			for (int j = 1; j < i; j++)
				choose[i][j] = choose[i - 1][j - 1] + choose[i - 1][j];
		}
	}

	int play() {
		System.out.println("Welcome to the Neller Turn Tail Casino!");
		System.out.println("We have but one game here, and that's Turn Tail.  Here are the rules:");
		int initialBankroll = 100;
		int bankroll = initialBankroll;
		int initCoins = 10;
		int pricePerGame = 16;
		int winningLimit = 100;
		System.out.printf("In the game of Turn Tail, you are initially given %d dollar coins and have no winnings.\n"
				+ "Each turn, you may choose to keep all of your winnings and end the game,\n" 
				+ "or flip all of your coins at once to potentially win more.\n"
				+ "If all of your flipped coins come up tails, you lose all of your winnings and the game is over.\n"
				+ "If _any_ of your flipped coins come up heads, you win as many dollars as the total number of coins you flip.\n"
				+ "Then, you take all coins that came up tails, and these \"turn tail and run\", \n"
				+ "i.e. they are removed from the game for your next turn.\n"
				+ "Finally, your winnings can never exceed some maximum winning limit of dollars.\n"
				+ "If at any point your winnings would exceed this limit, your winnings are capped at the limit and the game is over.\n",
				initCoins);
		while (true) {
			System.out.printf("Your current bankroll is $%d.\n", bankroll);
			if (bankroll < 16) {
				System.out.println("You cannot afford another game.  Thanks for playing!  Goodbye!");
				break;
			}
			char response = '?';
			while (response != 'y' && response != 'n') {
				System.out.print("Do you wish to play a game of Turn Tail (\"y\" or Enter) or leave the casino (\"n\")? ");
				String input = in.nextLine().trim().toLowerCase();
				if (input.length() == 0)
					input = "y";
				response = input.charAt(0);
			}
			if (response == 'n')
				break;

			// play a game
			bankroll -= pricePerGame;
			int coins = initCoins;
			System.out.printf("You pay $%d.  Your current bankroll is now $%d.\n", pricePerGame, bankroll);
			System.out.println("To flip, just press Enter.  To hold, enter any character.");
			int winnings = 0;
			while (true) {
				System.out.printf("Coins: %2d  Winnings: %3d  Would you like to flip or hold? ", coins, winnings);
				String input = in.nextLine();
				if (input.length() > 0)
					break;
				winnings += coins;
				int numHeads = 0;
				System.out.print("Flips:");
				for (int i = 0; i < coins; i++) {
					boolean flip = random.nextBoolean();
					if (flip)
						numHeads++;
					System.out.print(flip ? " Head" : " Tail");
				}
				System.out.println();
				coins = numHeads;
				if (coins == 0) {
					winnings = 0;
					System.out.println("No Heads!  You lose your winnings and the game is over.");
					break;
				}
				if (winnings >= winningLimit) {
					winnings = winningLimit;
					System.out.println("You've reached the winning limit of $" + winningLimit + "!  Congratulations!!!");
					break;
				}
			}
			System.out.println("You won " + winnings + " dollars" + (winnings > pricePerGame ? "!!!  Yes!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!" : "."));
			bankroll += winnings;
		}
		System.out.println("You leave the casino with $" + bankroll + (bankroll > initialBankroll ? "!!!  Yes!" : "."));
		System.out.print("Press Enter to continue. ");
		in.nextLine();
		System.out.println();
		return bankroll;
	}


	double[][][] expectedWinnings = new double[INITIAL_COINS + 1][WINNINGS_LIMIT + 1][2];

	double getExpectedWinnings(int coins, int winnings) {
		if (winnings >= WINNINGS_LIMIT)
			return WINNINGS_LIMIT;
		if (coins == 0)
			return 0;
		return Math.max(expectedWinnings[coins][winnings][HOLD], expectedWinnings[coins][winnings][FLIP]);
	}

	void valueIteration() {
		for (int coins = 1; coins <= INITIAL_COINS; coins++)
			for (int winnings = 0; winnings <= WINNINGS_LIMIT; winnings++)
				expectedWinnings[coins][winnings][HOLD] = (double) winnings;
		double epsilon = 1e-9;
		double maxChange = 0;
		do {
			maxChange = 0;
			for (int coins = 1; coins <= INITIAL_COINS; coins++)
				for (int winnings = WINNINGS_LIMIT; winnings >= 0; winnings--) {
					// store old expected winnings
					double oldExpectedWinnings = expectedWinnings[coins][winnings][FLIP];

					// compute new expected winnings
					// begin with all tails outcome
					int totalOutcomes = 1;
					expectedWinnings[coins][winnings][FLIP] = 0;
					// continue with every immediate scoring outcome
					for (int numHeads = 1; numHeads <= coins; numHeads++) {
						expectedWinnings[coins][winnings][FLIP] += 
								choose[coins][numHeads] * getExpectedWinnings(numHeads, winnings + coins);
						totalOutcomes += choose[coins][numHeads];
					}
					expectedWinnings[coins][winnings][FLIP] /= totalOutcomes;

					// see if greatest change
					double change = Math.abs(oldExpectedWinnings - expectedWinnings[coins][winnings][FLIP]);
					if (change > maxChange)
						maxChange = change;
				}
			//			System.out.println("Maximum Change: " + maxChange);
		} while (maxChange >= epsilon); 


		// Summarize expected final winnings
		System.out.println("Expected final winnings computed via value iteration:");
		System.out.println("        Coins = ");
		System.out.print("Winnings");
		for (int coins = 1; coins <= INITIAL_COINS; coins++)
			System.out.printf("\t%4d", coins);
		System.out.println();
		for (int winnings = 0; winnings <= WINNINGS_LIMIT; winnings++) {
			System.out.printf("%4d\t", winnings);
			for (int coins = 1; coins <= INITIAL_COINS; coins++)
				System.out.printf("\t%6.2f", getExpectedWinnings(coins, winnings));
			System.out.println();
		}
		System.out.println();
		// Summarize expected final winnings when flipping: 
		System.out.println("Expected final winnings when flipping:");
		System.out.println("        Coins = ");
		System.out.print("Winnings");
		for (int coins = 1; coins <= INITIAL_COINS; coins++)
			System.out.printf("\t%4d", coins);
		System.out.println();
		for (int winnings = 0; winnings <= WINNINGS_LIMIT; winnings++) {
			System.out.printf("%4d\t", winnings);
			for (int coins = 1; coins <= INITIAL_COINS; coins++)
				System.out.printf("\t%6.2f", expectedWinnings[coins][winnings][FLIP]);
			System.out.println();
		}
		System.out.println();

		// Summarize policy
		System.out.println("Optimal policy:");
		System.out.println("        Coins = ");
		System.out.print("Winnings");
		for (int coins = 1; coins <= INITIAL_COINS; coins++)
			System.out.printf("\t%4d", coins);
		System.out.println();
		for (int winnings = 0; winnings <= WINNINGS_LIMIT; winnings++) {
			System.out.printf("%4d\t", winnings);
			for (int coins = 1; coins <= INITIAL_COINS; coins++)
				System.out.print("\t" + (expectedWinnings[coins][winnings][FLIP] > expectedWinnings[coins][winnings][HOLD] ? "FLIP" : "HOLD"));
			System.out.println();
		}
		System.out.println();
	}

	public void monteCarloExploringStarts() {
		double[][][] q = new double[INITIAL_COINS + 1][WINNINGS_LIMIT + 1][2];
		int[][][] selections = new int[INITIAL_COINS + 1][WINNINGS_LIMIT + 1][2];
		double[][][] rewards = new double[INITIAL_COINS + 1][WINNINGS_LIMIT + 1][2];
		while (true) {
			// Get user iterations
			int numIter = 0;
			while (true) {
				System.out.print("Number of iterations of Monte Carlo with Exploring Starts (0 to end)? ");
				try {
					String line = in.nextLine();
					numIter = Integer.parseInt(line);
					break;
				}
				catch (Exception e) {
					System.out.println("Illegal input - try again.");
				}
			}
			if (numIter == 0) break;

			for (int iter = 0; iter < numIter; iter++) {
				ArrayList<Integer> stateCoins = new ArrayList<Integer>();
				ArrayList<Integer> stateWinnings = new ArrayList<Integer>();
				ArrayList<Integer> actions = new ArrayList<Integer>();

				//Choose exploring start
				int coins = random.nextInt(INITIAL_COINS) + 1;
				int winnings = random.nextInt(WINNINGS_LIMIT + 1);
				int action = random.nextInt(2);
				if (selections[coins][winnings][HOLD] == 0)
					action = HOLD;
				else if (selections[coins][winnings][FLIP] == 0)
					action = FLIP;
				stateCoins.add(coins);
				stateWinnings.add(winnings);
				actions.add(action);

				//Play game
				while (action == FLIP) {					
					int numHeads = 0;
					for (int i = 0; i < coins; i++) {
						boolean flip = random.nextBoolean();
						if (flip)
							numHeads++;
					}
					winnings += coins;
					coins = numHeads;
					if (coins == 0) {
						winnings = 0;
						break;
					}
					if (winnings >= WINNINGS_LIMIT) {
						winnings = WINNINGS_LIMIT;
						break;
					}

					if (selections[coins][winnings][HOLD] == 0)
						action = HOLD;
					else if (selections[coins][winnings][FLIP] == 0)
						action = FLIP;
					else
						action = q[coins][winnings][FLIP] > q[coins][winnings][HOLD] ? FLIP : HOLD;
						stateCoins.add(coins);
						stateWinnings.add(winnings);
						actions.add(action);
				}

				double reward = (coins > 0) ? winnings : 0;

				//Add Rewards to (state,action) pairs
				for (int i = 0; i < stateCoins.size(); i++) {
					coins = stateCoins.get(i);
					winnings = stateWinnings.get(i);
					action = actions.get(i);
					selections[coins][winnings][action]++;
					rewards[coins][winnings][action] += reward;
					q[coins][winnings][action] = rewards[coins][winnings][action] / selections[coins][winnings][action];
				}		
			}

			// Summarize policy
			System.out.println("Current policy:");
			System.out.println("        Coins = ");
			System.out.print("Winnings");
			for (int coins = 1; coins <= INITIAL_COINS; coins++)
				System.out.printf("\t%4d", coins);
			System.out.println();
			for (int winnings = 0; winnings <= WINNINGS_LIMIT; winnings++) {
				System.out.printf("%4d\t", winnings);
				for (int coins = 1; coins <= INITIAL_COINS; coins++)
					System.out.print("\t" + (q[coins][winnings][FLIP] > q[coins][winnings][HOLD] ? "FLIP" : "HOLD"));
				System.out.println();
			}
			System.out.println();

			System.out.println("Current Q:");
			System.out.println("        Coins = ");
			System.out.print("Winnings");
			for (int coins = 1; coins <= INITIAL_COINS; coins++)
				System.out.printf("\t%4d", coins);
			System.out.println();
			for (int winnings = 0; winnings <= WINNINGS_LIMIT; winnings++) {
				System.out.printf("%4d\t", winnings);
				for (int coins = 1; coins <= INITIAL_COINS; coins++)
					System.out.printf("\t%5.2f", Math.max(q[coins][winnings][FLIP], q[coins][winnings][HOLD]));
				System.out.println();
			}
			System.out.println();
		}
	}

	static double EXPLORATION_BOOST = .1;
	
	public static int UCB1(double[] q, int[] selections) {
		int totalSelections = 0;
		for (int i = 0; i < selections.length; i++) {
			if (selections[i] == 0)
				return i;
			totalSelections += selections[i];
		}
		int bestAction = 0;
		double bestValue = Double.NEGATIVE_INFINITY;
		for (int i = 0; i < selections.length; i++) {
			double value = q[i] + EXPLORATION_BOOST * Math.sqrt(2 * Math.log(totalSelections) / selections[i]);
			if (value > bestValue) {
				bestValue = value;
				bestAction = i;
			}
		}
		return bestAction;		
	}

	public void monteCarloOnPolicyUCB1() {
		while (true) {
			System.out.print("UCB exploration boost (default: 1.0)? ");
			try {
				String line = in.nextLine();
				if (line.length() == 0)
					line = "1.0";
				EXPLORATION_BOOST = Double.parseDouble(line);
				break;
			}
			catch (Exception e) {
				System.out.println("Illegal input - try again.");
			}
		}	
		
		double[][][] q = new double[INITIAL_COINS + 1][WINNINGS_LIMIT + 1][2];
		int[][][] selections = new int[INITIAL_COINS + 1][WINNINGS_LIMIT + 1][2];
		double[][][] rewards = new double[INITIAL_COINS + 1][WINNINGS_LIMIT + 1][2];
		while (true) {
			// Get user iterations
			int numIter = 0;
			while (true) {
				System.out.print("Number of iterations of On-Policy Monte Carlo with UCB1 (0 to end)? ");
				try {
					String line = in.nextLine();
					numIter = Integer.parseInt(line);
					break;
				}
				catch (Exception e) {
					System.out.println("Illegal input - try again.");
				}
			}
			if (numIter == 0) break;

			for (int iter = 0; iter < numIter; iter++) {
				ArrayList<Integer> stateCoins = new ArrayList<Integer>();
				ArrayList<Integer> stateWinnings = new ArrayList<Integer>();
				ArrayList<Integer> actions = new ArrayList<Integer>();

				//Choose start of game with 10 coins and 0 winnings
				int coins = INITIAL_COINS;
				int winnings = 0;
				int action = UCB1(q[coins][winnings], selections[coins][winnings]);
				stateCoins.add(coins);
				stateWinnings.add(winnings);
				actions.add(action);

				//Play game
				while (action == FLIP) {					
					int numHeads = 0;
					for (int i = 0; i < coins; i++) {
						boolean flip = random.nextBoolean();
						if (flip)
							numHeads++;
					}
					winnings += coins;
					coins = numHeads;
					if (coins == 0) {
						winnings = 0;
						break;
					}
					if (winnings >= WINNINGS_LIMIT) {
						winnings = WINNINGS_LIMIT;
						break;
					}

					action = UCB1(q[coins][winnings], selections[coins][winnings]);
					stateCoins.add(coins);
					stateWinnings.add(winnings);
					actions.add(action);
				}

				double reward = (coins > 0) ? winnings : 0;

				//Add Rewards to (state,action) pairs
				for (int i = 0; i < stateCoins.size(); i++) {
					coins = stateCoins.get(i);
					winnings = stateWinnings.get(i);
					action = actions.get(i);
					selections[coins][winnings][action]++;
					rewards[coins][winnings][action] += reward;
					q[coins][winnings][action] = rewards[coins][winnings][action] / selections[coins][winnings][action];
				}		
			}

			// Summarize policy
			System.out.println("Current policy:");
			System.out.println("        Coins = ");
			System.out.print("Winnings");
			for (int coins = 1; coins <= INITIAL_COINS; coins++)
				System.out.printf("\t%4d", coins);
			System.out.println();
			for (int winnings = 0; winnings <= WINNINGS_LIMIT; winnings++) {
				System.out.printf("%4d\t", winnings);
				for (int coins = 1; coins <= INITIAL_COINS; coins++)
					System.out.print("\t" + (q[coins][winnings][FLIP] > q[coins][winnings][HOLD] ? "FLIP" : "HOLD"));
				System.out.println();
			}
			System.out.println();

			System.out.println("Current Q:");
			System.out.println("        Coins = ");
			System.out.print("Winnings");
			for (int coins = 1; coins <= INITIAL_COINS; coins++)
				System.out.printf("\t%4d", coins);
			System.out.println();
			for (int winnings = 0; winnings <= WINNINGS_LIMIT; winnings++) {
				System.out.printf("%4d\t", winnings);
				for (int coins = 1; coins <= INITIAL_COINS; coins++)
					System.out.printf("\t%5.2f", Math.max(q[coins][winnings][FLIP], q[coins][winnings][HOLD]));
				System.out.println();
			}
			System.out.println();
			
			System.out.println("Current Q with Flip (Selections):");
			System.out.println("        Coins = ");
			System.out.print("Winnings");
			for (int coins = 1; coins <= INITIAL_COINS; coins++)
				System.out.printf("\t%4d\t", coins);
			System.out.println();
			for (int winnings = 0; winnings <= WINNINGS_LIMIT; winnings++) {
				System.out.printf("%4d\t", winnings);
				for (int coins = 1; coins <= INITIAL_COINS; coins++)
					System.out.printf("\t%5.2f(%d)", q[coins][winnings][FLIP], selections[coins][winnings][FLIP]);
				System.out.println();
			}
			System.out.println();
		}
	}

	public void monteCarloOnPolicyEpsilonGreedy() {
		double epsilon = 0;
		while (true) {
			System.out.print("Epsilon for epsilon-greedy policy (default: 0.1)? ");
			try {
				String line = in.nextLine();
				if (line.length() == 0)
					line = "0.1";
				epsilon = Double.parseDouble(line);
				break;
			}
			catch (Exception e) {
				System.out.println("Illegal input - try again.");
			}
		}
		
		double[][][] q = new double[INITIAL_COINS + 1][WINNINGS_LIMIT + 1][2];
		int[][][] selections = new int[INITIAL_COINS + 1][WINNINGS_LIMIT + 1][2];
		double[][][] rewards = new double[INITIAL_COINS + 1][WINNINGS_LIMIT + 1][2];
		
		while (true) {
			// Get user iterations
			int numIter = 0;
			while (true) {
				System.out.print("Number of iterations of On-Policy Monte Carlo with epsilon-Greedy (0 to end)? ");
				try {
					String line = in.nextLine();
					numIter = Integer.parseInt(line);
					break;
				}
				catch (Exception e) {
					System.out.println("Illegal input - try again.");
				}
			}
			if (numIter == 0) break;

			for (int iter = 0; iter < numIter; iter++) {
				ArrayList<Integer> stateCoins = new ArrayList<Integer>();
				ArrayList<Integer> stateWinnings = new ArrayList<Integer>();
				ArrayList<Integer> actions = new ArrayList<Integer>();

				//Choose start of game with 10 coins and 0 winnings
				int coins = INITIAL_COINS;
				int winnings = 0;
				int action = HOLD;
				if (selections[coins][winnings][HOLD] == 0)
					action = HOLD;
				else if (selections[coins][winnings][FLIP] == 0)
					action = FLIP;
				else if (random.nextDouble() < epsilon || q[coins][winnings][FLIP] == q[coins][winnings][HOLD])
					action = random.nextInt(2);
				else 
					action = q[coins][winnings][FLIP] > q[coins][winnings][HOLD] ? FLIP : HOLD;
				stateCoins.add(coins);
				stateWinnings.add(winnings);
				actions.add(action);

				//Play game
				while (action == FLIP) {					
					int numHeads = 0;
					for (int i = 0; i < coins; i++) {
						boolean flip = random.nextBoolean();
						if (flip)
							numHeads++;
					}
					winnings += coins;
					coins = numHeads;
					if (coins == 0) {
						winnings = 0;
						break;
					}
					if (winnings >= WINNINGS_LIMIT) {
						winnings = WINNINGS_LIMIT;
						break;
					}

					if (selections[coins][winnings][HOLD] == 0)
						action = HOLD;
					else if (selections[coins][winnings][FLIP] == 0)
						action = FLIP;
					else if (random.nextDouble() < epsilon || q[coins][winnings][FLIP] == q[coins][winnings][HOLD])
						action = random.nextInt(2);
					else
						action = q[coins][winnings][FLIP] >= q[coins][winnings][HOLD] ? FLIP : HOLD;
					stateCoins.add(coins);
					stateWinnings.add(winnings);
					actions.add(action);
				}

				double reward = (coins > 0) ? winnings : 0;

				//Add Rewards to (state,action) pairs
				for (int i = 0; i < stateCoins.size(); i++) {
					coins = stateCoins.get(i);
					winnings = stateWinnings.get(i);
					action = actions.get(i);
					selections[coins][winnings][action]++;
					rewards[coins][winnings][action] += reward;
					q[coins][winnings][action] = rewards[coins][winnings][action] / selections[coins][winnings][action];
				}		
			}

			// Summarize policy
			System.out.println("Current policy:");
			System.out.println("        Coins = ");
			System.out.print("Winnings");
			for (int coins = 1; coins <= INITIAL_COINS; coins++)
				System.out.printf("\t%4d", coins);
			System.out.println();
			for (int winnings = 0; winnings <= WINNINGS_LIMIT; winnings++) {
				System.out.printf("%4d\t", winnings);
				for (int coins = 1; coins <= INITIAL_COINS; coins++)
					System.out.print("\t" + (q[coins][winnings][FLIP] > q[coins][winnings][HOLD] ? "FLIP" : "HOLD"));
				System.out.println();
			}
			System.out.println();

			System.out.println("Current Q:");
			System.out.println("        Coins = ");
			System.out.print("Winnings");
			for (int coins = 1; coins <= INITIAL_COINS; coins++)
				System.out.printf("\t%4d", coins);
			System.out.println();
			for (int winnings = 0; winnings <= WINNINGS_LIMIT; winnings++) {
				System.out.printf("%4d\t", winnings);
				for (int coins = 1; coins <= INITIAL_COINS; coins++)
					System.out.printf("\t%5.2f", Math.max(q[coins][winnings][FLIP], q[coins][winnings][HOLD]));
				System.out.println();
			}
			System.out.println();
			
			System.out.println("Current Q(Selections) with Flip :");
			System.out.println("        Coins = ");
			System.out.print("Winnings");
			for (int coins = 1; coins <= INITIAL_COINS; coins++)
				System.out.printf("\t%4d\t", coins);
			System.out.println();
			for (int winnings = 0; winnings <= WINNINGS_LIMIT; winnings++) {
				System.out.printf("%4d\t", winnings);
				for (int coins = 1; coins <= INITIAL_COINS; coins++)
					System.out.printf("\t%5.2f(%d)", q[coins][winnings][FLIP], selections[coins][winnings][FLIP]);
				System.out.println();
			}
			System.out.println();
		}
	}

	public static void main(String[] args) {
		TurnTailReinforcementLearning ttrl = new TurnTailReinforcementLearning();
		ttrl.play();
		ttrl.valueIteration();
		ttrl.monteCarloExploringStarts();
		ttrl.monteCarloOnPolicyUCB1();
		ttrl.monteCarloOnPolicyEpsilonGreedy();
	}

}
