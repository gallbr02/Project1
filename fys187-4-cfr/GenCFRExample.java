import java.util.Random;


public class GenCFRExample {
	Random random = new Random();
	
	void shuffle(int[] a) {
		for (int i = a.length - 1; i >= 1; i--) {
			int j = random.nextInt(i + 1);
			int tmp = a[i];
			a[i] = a[j];
			a[j] = tmp;
		}
	}
	
	
	void generate() {
		int player = random.nextInt(2) + 1;
		int other = 3 - player;
		int[] rwd = new int[] {2, 4, 5, 10}; // realization weight denominators
		shuffle(rwd);
		int firstColWidth = 40;
		String colFormat = "%" + firstColWidth + "s\t";
		System.out.printf(colFormat + "Player 1\tPlayer 2\n", " ");
		System.out.printf(colFormat + "%5.3f\t\t%5.3f\n", "Realization weights:", 1.0/rwd[1], 1.0/rwd[2]);
		System.out.println();
		System.out.printf(colFormat + "\n", "Player " + player + " node:");
		System.out.printf(colFormat + "a1\t\ta2\t\ta3\n", "Action:");
		int[] cumRegret = new int[] {100, 200, 300, 400, 500, 600, 700, 800, 900};
		shuffle(cumRegret);
		int[] indices = new int[] {0, 1, 2};
		shuffle(indices);
		final int NEG = 0, FIRST = 1, SECOND = 2; 
		cumRegret[indices[NEG]] *= -1;
		cumRegret[indices[SECOND]] = 1000 - cumRegret[indices[FIRST]];
		System.out.printf(colFormat + "%5d\t\t%5d\t\t%5d\n", "Cumulative Regret:", cumRegret[0], cumRegret[1], cumRegret[2]);
		int[] posRegret = cumRegret.clone();
		posRegret[indices[NEG]] = 0;
		System.out.printf(colFormat + "%5d\t\t%5d\t\t%5d\n", "Positive Regret:", posRegret[0], posRegret[1], posRegret[2]);
		double[] strategy = new double[] {posRegret[0]/1000.0, posRegret[1]/1000.0, posRegret[2]/1000.0};
		System.out.printf(colFormat + "%5.3f\t\t%5.3f\t\t%5.3f\n", "Strategy:", strategy[0], strategy[1], strategy[2]);
		double[][] playerRealization = new double[3][];
		playerRealization[player] = strategy.clone();
		for (int i = 0; i < 3; i++)
			playerRealization[player][i] /= rwd[player];
		playerRealization[other] = new double[] {1.0/rwd[other], 1.0/rwd[other], 1.0/rwd[other]};
 		System.out.printf(colFormat + "%5.3f\t\t%5.3f\t\t%5.3f\n", "To Cumulative Strategy add:", playerRealization[player][0], playerRealization[player][1], playerRealization[player][2]);
		System.out.println();
		System.out.printf(colFormat + "\n", "Recursive CFR actions evaluations:");
 		System.out.printf(colFormat + "%5.3f\t\t%5.3f\t\t%5.3f\n", "Player 1 Realization Weights:", playerRealization[1][0], playerRealization[1][1], playerRealization[1][2]);
 		System.out.printf(colFormat + "%5.3f\t\t%5.3f\t\t%5.3f\n", "Player 2 Realization Weights:", playerRealization[2][0], playerRealization[2][1], playerRealization[2][2]);
		int[] actionValue = {-9, -8, -7, -6, -5, -4, -3, -2, -1, 1, 2, 3, 4, 5, 6, 7, 8, 9};
		shuffle(actionValue);
		for (int i = 0; i < 3; i++)
			actionValue[i] *= 10 * rwd[other];
		System.out.printf(colFormat + "%5d\t\t%5d\t\t%5d\n", "Action values for player " + player + ":", actionValue[0], actionValue[1], actionValue[2]);
		System.out.println();
		int nodeValue = (int) Math.round(strategy[0] * actionValue[0] + strategy[1] * actionValue[1] + strategy[2] * actionValue[2]);
		System.out.printf(colFormat + "%5d\n", "Node value for player " + player + ":", nodeValue);
		int[] actionRegret = new int[] {actionValue[0], actionValue[1], actionValue[2]};
		for (int i = 0; i < 3; i++)
			actionRegret[i] -= nodeValue;
 		System.out.printf(colFormat + "%5d\t\t%5d\t\t%5d\n", "Action regrets for player " + player + ":", actionRegret[0], actionRegret[1], actionRegret[2]);
 		int[] cfRegret = actionRegret.clone();
		for (int i = 0; i < 3; i++)
			cfRegret[i] /= rwd[other];
 		System.out.printf(colFormat + "%5d\t\t%5d\t\t%5d\n", "Counterfactual regrets for player " + player + ":", cfRegret[0], cfRegret[1], cfRegret[2]);
		System.out.printf(colFormat + "%5d\t\t%5d\t\t%5d\n", "Old Cumulative Regret:", cumRegret[0], cumRegret[1], cumRegret[2]);
		int[] newCumRegret = cumRegret.clone();
		for (int i = 0; i < 3; i++)
			newCumRegret[i] += cfRegret[i];
		System.out.printf(colFormat + "%5d\t\t%5d\t\t%5d\n", "New Cumulative Regret:", newCumRegret[0], newCumRegret[1], newCumRegret[2]);

		System.out.println();
		System.out.println();
		System.out.println();
		System.out.println("Exercise (Fill in the missing values; answers above):");
		
		System.out.printf(colFormat + "Player 1\tPlayer 2\n", " ");
		System.out.printf(colFormat + "%5.3f\t\t%5.3f\n", "Realization weights:", 1.0/rwd[1], 1.0/rwd[2]);
		System.out.println();
		System.out.printf(colFormat + "\n", "Player " + player + " node:");
		System.out.printf(colFormat + "a1\t\ta2\t\ta3\n", "Action:");
		System.out.printf(colFormat + "%5d\t\t%5d\t\t%5d\n", "Cumulative Regret:", cumRegret[0], cumRegret[1], cumRegret[2]);
		System.out.printf(colFormat + "\n", "Positive Regret:");
		System.out.printf(colFormat + "\n", "Strategy:");
		System.out.printf(colFormat + "\n", "To Cumulative Strategy add:");
		System.out.println();
		System.out.printf(colFormat + "\n", "Recursive CFR actions evaluations:");
 		System.out.printf(colFormat + "\n", "Player 1 Realization Weights:");
 		System.out.printf(colFormat + "\n", "Player 2 Realization Weights:");
		System.out.printf(colFormat + "%5d\t\t%5d\t\t%5d\n", "Action values for player " + player + ":", actionValue[0], actionValue[1], actionValue[2]);
		System.out.println();
		System.out.printf(colFormat + "\n", "Node value for player " + player + ":");
		System.out.printf(colFormat + "\n", "Action regrets for player " + player + ":");
 		System.out.printf(colFormat + "\n", "Counterfactual regrets for player " + player + ":");
		System.out.printf(colFormat + "\n", "Old Cumulative Regret:");
		System.out.printf(colFormat + "\n", "New Cumulative Regret:");

		System.out.println();
		System.out.println();
		System.out.println();
		System.out.println("Concise word problem (answers above):");
		System.out.printf("For CFR on a player %d information set node, assume that realization weights are\n%5.3f and %5.3f for players 1 and 2, respectively.\n", player, 1.0/rwd[1], 1.0/rwd[2]);
		System.out.printf("Further assume that, for that information set node, cumulative regrets are\n%d, %d, and %d for actions a1, a2, and a3, respectively.\n", cumRegret[0], cumRegret[1], cumRegret[2]);
		System.out.printf("If recursive calls to CFR for each of these actions yield action value %d, %d, and %d, respectively,\nwhat will be the new cumulative regrets for actions a1, a2, and a3, respectively?\n", actionValue[0], actionValue[1], actionValue[2]);

		

		
		
 
	}
	
	public static void main(String[] args) {
		new GenCFRExample().generate();
	}

}
