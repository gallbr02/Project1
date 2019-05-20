
import java.io.*;
import java.net.*;
import java.util.*;

public class NetworkClient {

	public static final int PORT = 12345;
	
	public static void main(String[] args) {
		Scanner keyboard = new Scanner(System.in);
		
		System.out.print("Enter a server: ");
		String host = keyboard.nextLine();
		
		//use "try with resources" so everything gets closed
		try (
				Socket socket = new Socket(host, PORT);
				BufferedReader inFromServer = new BufferedReader(
						new InputStreamReader(socket.getInputStream()));
				PrintWriter outToServer = new PrintWriter(socket.getOutputStream(), true);
						
				)	
		{
			//server will say Welcome
			String reply = inFromServer.readLine();
			System.out.printf("Server says: %s\n", reply);
			
			boolean done = false;
			
			while(!done){
				System.out.print("> ");
				String line = keyboard.nextLine();
				
				if(line.equalsIgnoreCase("QUIT")){
					done = true;
				}
				
				outToServer.println(line);
				
				reply = inFromServer.readLine();
				System.out.println(reply);
			}
			
			//quit -- temporary
			//outToServer.println("QUIT");
			//outToServer.flush();
		}
		catch(Exception e){
			System.err.println(e.getMessage());
		}
	}

}