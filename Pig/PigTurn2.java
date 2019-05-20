
public class PigTurn2 {
	public static void main (String[] args) {
		int goal = 100;
		double[] prob = new double[goal];
		for(int turnTotal = goal -1; turnTotal >= 0; turnTotal--) {
			for (int roll = 2; roll<= 6; roll++) {
				if (turnTotal + roll >= goal) {
					prob[turnTotal] += 1.0/6.0;
				}
				else {
					prob[turnTotal] += prob[turnTotal + roll]/6.0;
				}
			}
		}
		System.out.println(prob[0]);
	} // main
