import java.util.Scanner;

public class students {

	public static void main(String[] args) {
		Scanner input = new Scanner (System.in);
		//find out how many people
		//find out gpa, major, name, credits
		
		for(int i = 100; i>= 20; i= i-5){
			System.out.println(i);
		}
		
		char c = 'a';
		int count = c;
		while(c>= 'a' && c<='z'){
			System.out.println(c = (char) (c+2));
		}
		count++;
		int n = 5;
		for(int i = 1; i <=n ; i++){
			for(int j = 1; j <= i; j++){
				System.out.print(j);
			}
			System.out.println();
		}
		
		System.out.println();
		System.out.println();
		
		String[] name;
		String[] majors;
		double[] gpas;
		int [] credits;
		
		System.out.println("how many people ");
		int x = input.nextInt();
		
		name = new String[n];
		majors= new String[n];
		gpas= new double[n];
		credits= new int[n];

		for(int i = 0; i < n; i++){
			System.out.printf("Enter name (%d): ", i);
				name[i]=input.next();
			System.out.printf("Enter major (%d): ", i);
				majors[i]=input.next();
			System.out.printf("Enter gpa (%d): ", i);
				gpas[i]=input.nextDouble();
			System.out.printf("Enter credits (%d): ", i);
				credits[i]=input.nextInt();	
		}
		printStudent(0, name, majors, gpas, credits);
		
	}
	public static void printStudent (int student, String[] n, String[] m, double[] g, int[] c){
		System.out.printf("%s : %s\n", " NAME ", n[student] );
		System.out.printf("%s : %s\n", " MAJOR ", m[student] );
		System.out.printf("%s : %f\n", " GPA ", g[student] );
		System.out.printf("%s : %d\n", " CREDITS ", c[student] );
	
	}

}
