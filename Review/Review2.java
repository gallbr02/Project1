import java.util.Scanner;

public class Review2 {

	public static void main(String[] args) {
		Scanner input = new Scanner (System.in);
		double n = (int) 5* Math.random() +1;
		System.out.println(n);
		
		//generate random numbers in range
				//- range from 20 - 50
				int r1 = (int)(30* Math.random()+ 20);
				System.out.println(r1);
				//- range from 60 - 90
				int r2 = (int)(30* Math.random()+ 60);
				System.out.println(r2);
				
				
		
		//counter control loop
		int x = 1;
		while (x<= 10){
			System.out.println(x);
			x++;
		}
		//System.out.println("Enter a int:  ");
		//int b = input.nextInt();
		//System.out.println(b);
		
		//print out arrays
		double[] array = new double[3];
		array[0]= 1;
		array[1]= 2;
		array[2]= 3;
		System.out.printf("%.2f, %.2f, %.2f \n",array[0], array[1], array[2]);
		
		double[] array1 = {1, 2, 3};
		System.out.println(array1[2]);
		
		// 2d
		int [][] table = new int[4][6];
		table [1][2]= 8;
		//set each value to the chart
		for(int i = 0; i< table.length; i++){
			for(int j = 0; j < table[i].length; j++){
				table[i][j] = (i+1)*(j+1);
			}
		}
		print(table);
		
		//Mathmatical table
		int [][] math = new int [10][10];
		for(int i = 0; i < math.length; i++){
			for(int j=0; j< math[i].length; j ++){
				math[i][j] = (i+1) * (j+1);
			}
		}
		print(math);
		
		//find min and max value
		System.out.println("enter two values: ");
		int a = input.nextInt();
		int c = input.nextInt();
		
		System.out.println(" the max between"+ a + " and " +c +" is: "+max(a,c));
	
		int sum = a + c;
		System.out.println(sum);
		
		int avg = sum/2;
		System.out.println(avg);
		
		// i roll a dice 100 times, count how many times it lands on 5.
		System.out.printf("you rolled a %d", rollDice());
		System.out.printf(" you rolled a dice 100 times and landed on 5 %d times",countDice());
		
		
		
		
	}//end main
		
	public static int rollDice (){
	int roll = (int)(Math.random() *6 +1);
	return roll;
	}
	public static int countDice(){
		int count = 0;
		for(int i = 1; i <= 100; i ++){
			int x = rollDice();
			if(x == 5)
				count++;
			}
			return count;
	}

	public static void print(int[][] arr2d){
		for(int i = 0; i < arr2d.length; i ++){
			for(int j = 0; j < arr2d[i].length; j++){
				System.out.printf("%6d", arr2d[i][j]);
			}
			System.out.println();// new line for each row
		}System.out.println();		
	}
	public static int max (int a, int b){
		if(a > b){
			return a;
		}else{
			return b;
		}
	}
	
	
	
	
	
	
	
	

}
