
public class ByteTested {

	public static void main(String[] args) {
		//example of the problem
		
		//short can handle values up to 2 to the negative 15th
		short s1 = 0x00FF; //255;
		byte b1 = (byte)s1; // cast to a byte
		System.out.println(b1);
		
		//cant convert a int to byte
		
		//UNLESS its small enough
		
		//ex: =0x0F, too big
		// 	=0x7F fits
		
		byte b2 = (byte) 0xFF;			//make a byte that is all ones
		
		//I want this as an unsigned value e.g 255
		
		
		//promote it to a larger type
		short s2 = (short) (b2 & 0xFF);   //** short s2 = b2 this is wrong with b2;
		
		//bitwise and with mask 0XFF (an int)
		
		System.out.println(s2);
		
		
		//break a 32 bite int into 4 bytes
		int i1 = 0xABCDEF01;
		
		System.out.printf("%X\n", i1); // display the hex
		
		byte d0 =(byte) ((i1 >> 24) & 0xFF) ;// most significant;
		
		byte d1 = (byte) ((i1 >> 16) & 0xFF); 
	
		byte d2 = (byte) ((i1 >> 8) & 0xFF); // shift i1 by 8 bits to the right;
		byte d3 = (byte) (i1 & 0xFF); // least significant type
		
		
		System.out.printf("%x %x %x %x \n", d0 , d1, d2, d3 );
		
		
		//supose i have an array of bytes and i want to turn it into an int
		//convert 4 bytes to an int
		byte[] byteArr = {(byte)0xAA, (byte)0xBB,(byte) 0xCC, (byte) 0x55}; 
		
		long result = 0; // to get unsigned int value
		
		//go through each byte
		for(int i = 0; i < 4; i++)	{
			System.out.printf("%x\n", result);
			//System.out.printf("%x%s\n", byteArr[i], " hey");
			//shift current value over one byte column, then add next value
				// so start at 55 and then go to AA
				// add in byte data
			//use shift operators
			result = (result << 8) + (byteArr[i] & 0xFF);
		}
			System.out.printf("%x\n", result);
	}

}
