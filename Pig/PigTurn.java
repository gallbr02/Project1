import java.util.Random;

public class PigTurn {

	public static void main(String[] args) {
		// TODO Auto-generated method stub
		Random random = new Random ();
		//		System.out.println("Ya Muthas Onions");
		for (int goal = 2; goal <= 100; goal++) {
			System.out.println("Goal: " + goal);
			int numGames = 1000000;
			int totalWins = 0;
			int totalScore = 0;
			for (int i=0; i < numGames; i++) {
				int roll = 0;
				int turnTotal = 0;
				while(turnTotal < goal && roll != 1){
					roll = random.nextInt(6) + 1;
					//			System.out.println(roll);
					if (roll > 1) {
						turnTotal = turnTotal + roll;
					}
					else {
						turnTotal = 0;
					}
				}
//				System.out.println(turnTotal);
				if (turnTotal >= goal) {
					totalWins++; //same as totalWins = totalWins
					totalScore += turnTotal; //same as totalScore = totalScore;
				}
			}
			System.out.println((double) totalWins / numGames);
			System.out.println((double) totalScore / numGames);
		}
	}//main

}//class

