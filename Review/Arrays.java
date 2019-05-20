import java.util.Scanner;

public class Arrays {

	public static void main(String[] args) {
		double x = 3.5;
		double y = -23;
		System.out.printf("%f, %f\n", x, y);
		y = x;
		System.out.printf("%f, %f\n", x, y);
		y = y - 1;
		System.out.printf("%f, %f\n", x, y);
		
		double[] numbers = new double[5];
		numbers[0] = 1.5;
		numbers[3] = -7;
		
		outputArray(numbers);
		
		double[] other = numbers;
		other[0] = 6;
		
		outputArray(numbers);
		
		arrayReferenceTest(numbers);
		outputArray(numbers);
		
		// primitive types (int, double, char, boolean) are copied
		//   when assigned or passed as parameters
		
		// arrays are references to data not copies
		// objects are references too
		//   e.g. String, Scanner
		
		
		//read words from user
		Scanner input = new Scanner(System.in);
		String[] words = getWords(input);
		outputArray(words);
		
		//print command line arguments (parameters from the operating system)
		outputArray(args);
	} //end main

	//return an array from a method
	public static String[] getWords(Scanner input){
		System.out.print("How many words will you enter? ");
		int n = input.nextInt();
		String[] result = new String[n];
		for(int i = 0; i < n; i++){
			System.out.print("Enter a word: ");
			result[i] = input.next();
		}
		return result;
	}
	
	
	public static void arrayReferenceTest(double[] arr){
		for(int i = 0; i < arr.length; i++){
			arr[i] += 2;
		}
	}
	
	//output an array of doubles
	public static void outputArray(double[] arr){
		for(int i = 0; i < arr.length; i++){
			System.out.printf("%d: %f\n", i, arr[i]);
		}
		System.out.println("-----------------");
	}
	
	//output an array of Strings
	public static void outputArray(String[] arr){
		for(int i = 0; i < arr.length; i++){
			System.out.printf("%d: %s\n", i, arr[i]);
		}
		System.out.println("-----------------");
	}
}