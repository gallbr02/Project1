import java.util.Comparator;

public class HNodeComparator implements Comparator <HNode>{
	
	@Override
	public int compare(HNode n1, HNode n2) {
		int sum1 = n1.frequency;
		int sum2 = n2.frequency;
		
		if(sum1 < sum2) {
			return -1;
		}
		else if(sum1 > sum2) {
			return 1;
		}
		else {
			return 0;
		}
	}
}
