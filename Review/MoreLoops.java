
public class MoreLoops {

	public static void main(String[] args) {
		int count = 1; //loop starts at 1
		
		//keep going until the condition is false
		while(count <= 10){
			//iteration: one execution of the body of the loop
			//print the current value of count
			System.out.println(count);
			//go to the next
			count++;
			
		}
		
		System.out.println("---------------------");
		
		//print out all integers between 20 and 50
		count = 20;
		while(count <= 50){
			System.out.println(count);
			count++;
			
		}
		

		
		System.out.println("---------------------");
		
		//print out all integers between 20 and 50 by 2
		count = 20;
		while(count <= 50){
			System.out.println(count);
			count = count + 2;
			
		}
		
		System.out.println("---------------------");
		
		//WARNING: be careful of your conditional operators
		
		//print out all integers between 20 and 50 by 2
		/*
		 count = 20;
		
		while(count != 51){
			System.out.println(count);
			count = count + 2;
			
		}
		*/
		
		
		//Now print out the values from 50 to 20
		count = 50;
		while(count >= 20){
			System.out.println(count);
			count--;
		}
		
		
		//sum the numbers from start to end
		
		//constant values
		//variables that don't change
		// have a type, a name, an initial value
		// also have the word final
		// all caps
		final int START = 143;
		final int END = 10000;
		final int STEP = 2;
		
		//Can't do this: START++;
		
		//Planning:
		//What variables will I need?
		//sum: describing the current total of values
		//interval: step size 1
		//current value
		
		//What does one iteration look like?
		//add the current value to the sum
		//go to the next one
	
		int sum = 0; //before we deal with any numbers
		int current = START;
		
		while(current <= END){
			//add the current value to the sum
			sum = sum + current;
			
			current = current + STEP; //go to the next one	
		}
		System.out.printf("Sum from %d to %d by %d is %d.\n", START, END, STEP, sum);
		
		
		//flip a coin 10000 times and count how many times HEADS comes up
		//int flip = (int)(2*Math.random());
		final int HEADS = 0;
		final int TAILS = 1;
		final int TRIALS = 10000;
		
		int headCount = 0;
		count = 1;
		
		while(count <= TRIALS){
			//flip the coin
			int flip = (int)(2*Math.random());
			
			if(flip == HEADS){
				headCount++;
			}
			
			count++;
		}
		System.out.printf("%d heads out of %d flips.\n", headCount, TRIALS);
		
		
	}

}
