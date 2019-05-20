import java.io.FileInputStream;
import java.io.FileNotFoundException;
import java.io.FileOutputStream;
import java.io.IOException;
import java.util.TreeMap;

public class HuffmanZip {
	private static TreeMap<Character, Integer> frequencies;
	
		
		frequencies = new TreeMap<Character, Integer>();
		
		stream.close();
		return frequencies;
	
	}
	/**
	 * 
	 * @param filename
	 * @throws IOException
	 */
	public void encode(String filename) throws IOException{
		FileInputStream stream = new FileInputStream(filename);
		TreeMap<Charater,Integer> compare(String string)throws IOException
		TreeMap<Character, Integer> map = compare(filename);
		
		
			//Extremely lost not really understanding this code will need 
			//to meet with you to get a better understanding
		
		stream.close();
	}
	/**
	 * 
	 * @param filename
	 */
	public void decode(String filename) {
		BitOutputStream stream = new BitOutputStream(filename);
		TreeMap<Character, Integer> map = compare(filename);
		
			//Extremely lost not really understanding this code will need 
			//to meet with you to get a better understanding
		
	}
	/**
	 * 
	 * @param args
	 */
	public static void main(String args) {
		encode("war-and-peace.txt");
		decode("war-and-peace.txt");
	}

}
