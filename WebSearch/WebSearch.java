import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.net.URL;
import java.util.Scanner;

public class WebSearch {

	public static void main(String[] args) {
		// integers to count the number of lines and to count when the lines match what im looking for
		int numbers = 0;
		int sum = 0;
		
		//created a scanner to prompt the user
		Scanner input = new Scanner (System.in);
		System.out.println("Enter a URL ");
		String url = input.nextLine();

		System.out.println("What are you looking for?: ");
		String looking = input.nextLine();

		
		try{ 
			//used the openStrem() to get an input stream of the url
			// i used the example url: http://cs.gettysburg.edu
			URL search = new URL(url);
			BufferedReader in = new BufferedReader(new InputStreamReader(search.openStream()));

			//created a string in order to print out the line that read
			// went through the loop and increased the counts when needed
			//if what i was looking for was in the string then it would print the number line and the string
			String inputLine;
			while(in.ready()){
				++numbers;
				inputLine = in.readLine();
				if(inputLine.contains(looking)){
					System.out.println(numbers + ". \t" + inputLine);
					++sum;
				}
			}
			System.out.println(sum + " matches. ");
			in.close();
			
		}
		catch(IOException ioe){
			System.out.println("Invalid search ");
			ioe.printStackTrace();
		}
		input.close();

	}

}
