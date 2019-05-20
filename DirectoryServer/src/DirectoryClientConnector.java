import java.io.*;
import java.net.*;
import java.util.*;
import java.io.PrintStream;
import java.net.Socket;
import java.util.ArrayList;
import java.util.Scanner;

public class DirectoryClientConnector implements Runnable {


	public static final int SERVER_PORT = 54321;

	// clientConnector variables
	public String serverName;
	public String name;
	public int port;
	public int id;
	public int time;

	

	//socket variables
	protected Socket socket;
	protected Scanner inFromServer;
	protected PrintStream outToServer;

		

	//created volatile variable in a thread to be able to be seen in another
	protected volatile boolean loggedOn = false; 
	protected volatile boolean displayList = false;


	public DirectoryClientConnector(String serverName, int port, String name){

		this.serverName = serverName;
		this.port = port;
		this.name = name;
		new Thread(this).start();
	}


	public void run(){
		
		logon();
		try {
			while(loggedOn) {
					if (displayList) {
						getList();
						displayList = false;
					}
					this.ping();
					Thread.sleep(this.time/2*1000);
			}

			// done running 
			socket.close();
			outToServer.close();
			inFromServer.close();
			
		}catch (Exception e) {
			System.err.println(e.getMessage());
			e.printStackTrace();
		}
	}

	public void logon() {

		try {
			socket = new Socket(this.serverName, SERVER_PORT);
			outToServer = new PrintStream(socket.getOutputStream());
			inFromServer = new Scanner(socket.getInputStream());
			outToServer.printf("LOGON %d%s\n", this.port, this.name);
			System.out.printf("Sending message:\nLOGON %d%s\n", this.port, this.name);

			String line = inFromServer.nextLine();
			String[] nextLine = line.split(":");		
			
			if(nextLine[0].equals("ERROR")) {
				System.out.printf("Message received from server:\n" + line + "\n FAILED\n");
				Exception error = new Exception(nextLine[1]);
				throw error;
			}
			else {
				System.out.printf("Created and logged client:\n" + line + "\nPASSED\n");
				
				outToServer.print("Not passing through this point ");
				
				//this is where the problem is
				this.id  = Integer.parseInt(nextLine[1]);
				
				outToServer.print("Not passing through this point ");
				this.time = Integer.parseInt(nextLine[2]);
				
				loggedOn = true;
				socket.close();
				outToServer.close();
				inFromServer.close();

			}
		}catch (Exception e) {
			System.err.println(e.getMessage());
			e.printStackTrace();

		}
	}

	public ArrayList<String> getList(){
		try {

			socket = new Socket(this.serverName, SERVER_PORT);
			outToServer = new PrintStream(socket.getOutputStream());
			inFromServer = new Scanner(socket.getInputStream());
			outToServer.println("LIST " + this.id);

			System.out.printf("Sending Message:\nLIST " + this.id + "\n");

			String line = inFromServer.nextLine();
			String[] nextLine = line.split(":");
			if(nextLine[0].equals("Error")) {
				Exception error = new Exception(nextLine[1]);
				throw error;

			}else {

				try {
					System.out.printf("Message received from server:\n" + line + "\n");
					int connection = Integer.parseInt(nextLine[1]);
					ArrayList<String> response = new ArrayList<String>();
					for (int i = 0; i < connection; ++i) {
						response.add(inFromServer.nextLine());
					}

					System.out.printf(Arrays.toString(response.toArray()) + "\nPASSED\n");
					
					outToServer.print("Not passing through this point ");
					
					return response;
					
				}catch (NumberFormatException ex) {
					System.out.println(ex.getMessage());
					ex.printStackTrace();
				}	
				System.out.printf("getList produces an ArrayList %s" + "\nPASSED\n");
				System.out.printf("getList returns the correct number of clients %s" + "\nPASSED\n");
			}
		}catch (Exception e) {
			System.err.println(e.getMessage());
		}
		return null;

	}


	public void logoff() {
		try {
		
			socket = new Socket(this.serverName, SERVER_PORT);
			outToServer = new PrintStream(socket.getOutputStream());
			inFromServer = new Scanner(socket.getInputStream());

			outToServer.println("LOGOFF "+ this.id);

			System.out.printf("Sending message:\nLOGOFF " + this.id + "\n");

			String line = inFromServer.nextLine();
			String[] nextLine = line.split(":");

			if(nextLine[0].equals("ERROR")) {
				System.out.printf("Message received from server:\n" + line + "\nFAILED\n");
				Exception error = new Exception(nextLine[1]);
				throw error;
			}else {

				loggedOn = false;
				System.out.printf("Message received from server:\n" + line + "\nPASSED\n");
				
				outToServer.print("Not passing through this point ");
				
				socket.close();
				outToServer.close();
				inFromServer.close();

			}

		}catch (Exception e) {
			System.err.println(e.getMessage());
		}
	}
	
	private void ping() { 
		try {

			socket = new Socket(this.serverName, SERVER_PORT);
			outToServer = new PrintStream(socket.getOutputStream());
			inFromServer = new Scanner(socket.getInputStream());

			outToServer.println("PING " + this.id);

			System.out.printf("Message sent to server:\nPING " + this.id + "\n");

			String line = inFromServer.nextLine();
			String[] nextLine = line.split(":");

			if(nextLine[0].equals("Error")) {
				System.out.printf("Message recieved from server:\n" + line + "\nFAILED\n");
				Exception error = new Exception(nextLine[1]);
				throw error;

			}else {
				System.out.printf("Message received from server:\n" + line + "\nPASSED\n");
				
				outToServer.print("Not passing through this point ");
				this.time = Integer.parseInt(nextLine[1]);

				socket.close();
				outToServer.close();
				inFromServer.close();
			}
		}catch (Exception e) {
			System.err.println(e.getMessage());
		}
	}

}

