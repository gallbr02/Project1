import java.util.Scanner;

public class CharacterData {

	public static void main(String[] args) {
		Scanner input = new Scanner(System.in);
		char c = 'A';
		//char c = 'B';
		//char c = 'a';
		//char c = '3';
		//char c = '\n';
		
		System.out.println(c);
		System.out.println("Yo type ");
		String line1 = input.nextLine();
		int count = 1;
		for(int i = 1; i< line1.length(); i ++){
			count++; 
			
		}System.out.println(" there are " + count+ " letters in your word.");

		char b = 'B';
		System.out.println(b);
		
		
		int n = c;
		System.out.println(n+ "hi");
		
		char d = 'A' + 10;
		System.out.println(d+"hi 2");
		
		char u = 'B';
		//determine the lower case version
		//find the distance to 'A'
		//add that distance to 'a'
		if(u >= 'A' && u <= 'Z'){
			char l = (char)((u - 'A') + 'a');
			System.out.println(l);
		}
		else {
			System.out.println(u + " is not upper case!");
		}
		
		//Character class
		// gives us back a boolean: true or false
		if(Character.isUpperCase(u)){
			System.out.println( Character.toLowerCase(u));
		}
		else {
			System.out.println(u + " is not upper case!");
		}
		
		// Strings groups of characters
		String name = "Clif";
		System.out.println(name);
		
		
		System.out.print("Type something: ");
		
		//String word = input.next();
		//System.out.println(word);
		
		String line = input.nextLine();
		System.out.println(line);
		
		//get the first character from the string
		//get the char from a location (index): starts at 0
		char first = line.charAt(0);
		System.out.println("First char: " + first);
		
		//This tells me if they are the same object,
		//we ant to know if they contain the same data
		//if(line == "password"){   DON'T DO THIS
		
		//are the contents the same?
		if(line.equals("password")){
			System.out.println("Correct");
		}
		else {
			System.out.println("Wrong");
		}
		
		//length: provides the number of characters in a Strings
		System.out.println( line.length() );
		
		
		//substring
		String str = "Entire String";
		//print out part of it
		//provide begin and end index
		//starts at the character at begin location
		//ends one prior to the end location
		System.out.println( str.substring(1, 4) );
	}

}