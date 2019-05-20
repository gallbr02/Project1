import java.util.Scanner;

public class TCPHeader {
	
	public static String ControlFlags(String[] bytesString){
		String[] control = new String[2];
		control[0] = bytesString[12];
		control[1] = bytesString[13];
		String combined = combine(control);
		return returnBinary(combined);
	}
	public static String returnBinary(String hex){
		int bin = Integer.parseInt(hex, 16);
		return Integer.toBinaryString(bin);
	}
	
	public static int returnDecimal(String hex){
		return Integer.parseInt(hex);
	}
	
	public static long returnDecimalLong(String hex){
		return Long.parseLong(hex);
	}
	
	public static String combine(String[] hex){
		if(hex.length < 3){
			String combine = hex[0] + hex[1];
			return combine;
		}
		else{
			
		}
		return null;
	}
	
	public static void sourcePort(String[] hex){
		
		String[] bite = new String[2];
		
		bite[0] = hex[0];
		bite[1] = hex[1];
		
//		String[] hexs;
//		if(hexs.length < 3){
//			String[] combine = hex[0] + hex[1];
//		}
		
		String combine = combine(hex);
//		String hex2 = null;
//		int returnDec = Integer.parseInt(hex2);
		int sourcePort = returnDecimal(combine);
		
		
		System.out.printf("Source port: %d\n", sourcePort);
		
	}
	public static void destPort(String[] hex){
		String[] bite = new String[2];
		
		bite[0] = hex[0];
		bite[1] = hex[1];
		
		String combine = combine(hex);
		int destPort = returnDecimal(combine);
		
		
		System.out.printf("Source port: %d\n", destPort);
		
	}
	public static void seqNum(String[] hex){
		String[] seq = new String[4];
		int placeHolder = 0;
		for(int i = 0; i < seq.length; i++){
			seq[i] = hex[placeHolder];
			placeHolder++;
		}
		String combined = combine(hex);
		if(combined.length() >= 4){
			long seqnum = returnDecimalLong(combined);
			System.out.println("Seq Num : " + seqnum);
		}
		
		
	}
	public static void ackNum(String[] hex){
		String[] ack = new String[4];
		int placeHolder = 0;
		for(int i = 0; i < hex.length; i++){
			hex[i] = hex[placeHolder];
			placeHolder++;
		}
	}
	public static void length(String[] hex){
		String length = hex[12].charAt(0) + " ";
		System.out.println("Header len : " + length);
		
	}
	public static void NS(char character ){
		System.out.printf("NS : %c \n", character );
	}
	public static void CWR(char character){
		System.out.printf("CWR : %c \n",character );
	}
	public static void ECE(char character){
		System.out.printf("ECE : %c \n", character);
	}
	public static void URG(char character){
		System.out.printf("URG : %c \n",character );
	}
	public static void ACK(char character ){
		System.out.printf("ACK : %c \n", character );
	}
	public static void PSH(char character){
		System.out.printf("PSH : %c \n", character);
	}
	public static void RST(char character){
		System.out.printf("RST : %c \n", character );
	}
	public static void SYN(char character){
		System.out.printf("SYN : %c \n", character);
	}
	public static void FIN(char character){
		System.out.printf("FIN : %c \n", character );
	}
	
	public static void windowSize(String[] hex){
		
	}
	public static void checksum(String[] hex){
		
	}
	public static void urgentPointer(String[] hex){
		
	}
	public static void main(String[] args) {
		System.out.println("Enter TCP data hex bytes ");
		Scanner input = new Scanner(System.in);
		String line = input.nextLine();
		

	}

}
