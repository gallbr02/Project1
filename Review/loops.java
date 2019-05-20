
public class loops {

	public static void main(String[] args) {
		
		int n = 42;
		
		//convert int to a String
		String nStr = "" + n;
		
		//convert a String to an int
		String numberString = "3000";
		n = Integer.parseInt(numberString);
		
		System.out.println(2*n);
		
		//convert String to double
		String doubleString = "1234.5";
		double dbl = Double.parseDouble(doubleString);
		
		System.out.println(2*dbl);
		
		//Formatted output: printf
		
		System.out.printf("Hello\n");
		
		//fill in the blanks with values of variables
		//blanks, given by % and some other info
		
		//%d: indicates a decimal integer
		System.out.printf("Print a number, %d, mid-sentence.\n", n);
		
		//specify the field width: %20d
		System.out.printf("Print a number, %20d, mid-sentence.\n", n);
		
		//%f: (floating point) double
		System.out.printf("Print a double, %f, mid-sentence.\n", dbl);
		System.out.printf("Print a double, %20f, mid-sentence.\n", dbl);
		
		
		//specify precision  %20.2f
		System.out.printf("Print a double, %20.2f, mid-sentence.\n", dbl);
		System.out.printf("Print a double, %.2f, mid-sentence.\n", dbl);
		
		String course = "CS111";
		//%s for strings
		System.out.printf("This is %s.\n", course);
		
		
		System.out.printf("Print Strings %s, ints %d and doubles %f.\n", course, n, dbl);
		
		//repeating ourselves
		// loops: while loops
		
		//4 parts to a loop
		// 1. Where do we start?
		// 2. Where do we stop?
		// 3. How do we get there?
		// 4. What do we repeat?
		
		//print something 10 times.
		int count = 1;  //start (1)
		
		//while with a condition
		//keep going as long as the condition is true
		while(count <= 10) { //(2)
			//this is repeated (4)
			System.out.println("something");
			
			//increment count
			count++;
		}

	}

}