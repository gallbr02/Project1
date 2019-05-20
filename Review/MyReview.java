import java.util.Scanner;

public class MyReview {

	public static void main(String[] args) {
		Scanner input = new Scanner (System.in);
		
		//convert int to a string
		/*int n = 5;
		String m = " " + n;
		
		// get the first character from a string
		
		System.out.println("Enter a word ");
		
		String line = input.nextLine();
		
		char first = line.charAt(0);
		System.out.print("first char is " + first);
		*/
		//make a rectangle from stars
		/*System.out.println("What is the height ");
		int height = input.nextInt();
		System.out.println("What is the width ");
		int width = input.nextInt();
		
		for(int i = 1; i <= height; i++){
			for(int j = 1; j<= width; j++){
				if(i ==1 || i == height || j == 1 || j == width){
					System.out.print("*");
				}else{
					System.out.print(" ");
				}
				System.out.print(" ");
			}//second for
			System.out.println();
		}//first for
	
	*/
		// make a method that adds all the elements in the array
		double[] numbers = new double [3];
		numbers[0] = 1;
		numbers[1] = 1;
		numbers[2] = 1;
		
		System.out.println(addNumbers(numbers));
		
		//swap
		int a = 2;
		int b = 6;
		int temp = a;
		a= b;
		b = temp;
		
		System.out.println(a);
		
		//bounds of a numeric value
		System.out.println("enter a number: ");
		int x = input.nextInt();
		if(0< x && x < 100){
			System.out.println(" your number is between 0 and 100. ");
		}else{
			System.out.println("your number is outside of 0 and 100. ");
		}
		
		//format a double value
		double value = 20.6573;
		System.out.printf("%.2f\n",value);
		
		//use character data numerically
		char c = 'A'+ 10;
		int number = c;
		System.out.println(number + "hi");
		
		//counter control loop
		int sum = 0;
		int n = 1;
		while (n< 10){
			sum = sum + n*n;
			n = n+1;	
		}System.out.println(sum);
		
		}
	public static double addNumbers (double[] numbers){
		int total = 0;
		for( int i = 0; i < numbers.length ; i++){
			total += numbers[i];
		}return total;
	}
	public static void randomNumbers (double[][] random){
		
	}
	
	}
	


