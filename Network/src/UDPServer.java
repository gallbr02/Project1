import java.net.*;
import java.io.*;

public class UDPServer {
public static final int SERVER_PORT = 12345;
public static final int BUFFER_PORT = 1024;

	/*
	 * use client: netcat -u localhost 12345
	 * 
	 */



	public static void main(String[] args) {
		try{
			// create a datagram socket
			DatagramSocket serverSocket = new DatagramSocket(SERVER_PORT);
			
			while(true){
				//first: allocate space for the data
				
				//an array of bytes
				byte[] revData = new  byte[BUFFER_PORT];
				
				//create a packet to receive the data
				//pass it the buffer and length of the buffer
				DatagramPacket revPacket = new DatagramPacket(revData, revData.length);
				
				
				//wait for communication. will fill in revPacket when data arrives
				
				serverSocket.receive(revPacket);
				
				
				//get packet info
				InetAddress addr = revPacket.getAddress();
				int port = revPacket.getPort();
				
				//translate data into a string
				String msg = new String(revPacket.getData());
				
				System.out.printf("Message recieved from %s:%d, %s\n", addr, port, msg);
				
				//get a reply
				
				String reply = msg.toUpperCase();
				
				//get the string as array of bytes
				byte[] sendData = reply.getBytes();
				
				
				//create packet to send
				//send it back where it came from
				DatagramPacket sendPacket = new DatagramPacket(sendData, sendData.length, addr, port);
				
				//have the server send the packet back
				serverSocket.send(sendPacket);
				
			}
			
		}catch(Exception e){
			e.printStackTrace();
		}

	}

}
