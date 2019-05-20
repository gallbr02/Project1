
import java.io.*;
import java.net.*;

public class NetworkServer {

	public static final int PORT = 12345;
	
	public static void main(String[] args) {
		try {
			
			//create a server socket (TCP)
			ServerSocket server = new ServerSocket(PORT);
			
			
			while(true){
				//listen for a connection
				//accept blocks until a connection is received
				Socket socket = server.accept();
				
				System.out.println("Connection received!");
				
				//printing out information about the connection
				System.out.printf("From: %s:%d\n", 
					socket.getInetAddress().getHostName(),
					socket.getPort());
				
				System.out.printf("To: %s:%d\n", 
					socket.getLocalAddress().getHostName(),
					socket.getLocalPort());
				
				
				//set up Network input and output.
				BufferedReader inFromClient = new BufferedReader(
						new InputStreamReader(socket.getInputStream()));
				
				//setup printwriter with automatic line flushing
				PrintWriter outToClient = new PrintWriter(socket.getOutputStream(), true);
				
				outToClient.println("Welcome!");
				
				String line = inFromClient.readLine();
				
				while(line != null && !line.equalsIgnoreCase("QUIT")){
					outToClient.printf("You said: %s\n", line);
					System.out.printf("Client said: %s\n", line);
					
					//next line
					line = inFromClient.readLine();
				}
				outToClient.println("BYE");
				//move the data in the buffer out to the network
				outToClient.flush();
				
				//close the socket
				socket.close();
			}
			
		}
		catch(Exception e){
			System.err.println(e.getMessage());
		}
		finally {
			System.out.println("Done.");
		}
	}

}
