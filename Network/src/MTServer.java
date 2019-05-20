import java.io.BufferedReader;
import java.io.InputStreamReader;
import java.io.PrintWriter;
import java.net.ServerSocket;
import java.net.Socket;

public class MTServer implements Runnable{

	public static final int PORT = 12345;
	
	protected Socket socket;
	
	public MTServer(Socket socket){
		this.socket = socket;	
		
		System.out.println("Connection received!");
		
		//printing out information about the connection
		System.out.printf("From: %s:%d\n", 
			socket.getInetAddress().getHostName(),
			socket.getPort());
		
		System.out.printf("To: %s:%d\n", 
			socket.getLocalAddress().getHostName(),
			socket.getLocalPort());
	}
	
	public void run(){
		try {
			//set up Network input and output.
			BufferedReader inFromClient = new BufferedReader(
					new InputStreamReader(socket.getInputStream()));
			
			//setup PrintWriter with automatic line flushing
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
		catch(Exception e){
			System.err.println(e.getMessage());
			e.printStackTrace();	
		}
		
	}
	
	public static void main(String[] args) {
		try {
			//create a server socket (TCP)
			ServerSocket server = new ServerSocket(PORT);
			while(true){
				//listen for a connection
				//accept blocks until a connection is received
				Socket socket = server.accept();
				
				MTServer threadServer = new MTServer(socket);
				
				//create the thread
				Thread t = new Thread(threadServer);
				
				//start the thread
				//this will return AND call run
				t.start();
			}
			//server.close();
		}
		catch(Exception e){
			System.err.println(e.getMessage());
			e.printStackTrace();	
		}

	}

}