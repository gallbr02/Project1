//Barndon gallagher
import java.util.Scanner;

public class MoreStars {


	public static void main(String[] args) {
		Scanner input = new Scanner (System.in);
		//rectangle 
		System.out.println("Eneter the height: ");
		int height = input.nextInt();
		System.out.println("Enter the weight: ");

		int weight = input.nextInt();
		
		for (int i =1; i<=height; i++){
			for(int j= 1; j <= weight; j++){
				if(i ==1 || i == height || j == 1 || j == weight){
					System.out.print("*");
				}else{
				System.out.print(" ");
				}
			}
			System.out.println();
		}
		System.out.println();
		System.out.println();
		
		
	

	}

}
