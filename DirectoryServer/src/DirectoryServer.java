import java.io.*;
import java.net.*;
import java.util.*;

public class DirectoryServer {

	public static final int PORT = 54321;
	public static final int TIME = 10;

	public static void main(String[] args) {

		HashMap<Integer, Client> users = new HashMap<Integer, Client>();
		ServerSocket server = null;
		try {
			server = new ServerSocket(PORT);
		}catch (Exception e) {
			System.err.println("Cant Connect.");
			System.err.println(e); 
			System.exit(-1);  
		}

		while(true) {
			try {
				// listen for a connection
				// block until something connects
				Socket socket = server.accept();
				System.out.println("Connection Recieved");

				//remove the people that have timed out from their server
				removeUser(users);

				//set up network input and output
				BufferedReader inFromClient = new BufferedReader(new InputStreamReader(socket.getInputStream()));
				// get the output stream of the connected socket

				PrintWriter outFromClient = new PrintWriter(socket.getOutputStream());

				String readLine = inFromClient.readLine();
				System.out.println(readLine);

				//split client response into array, i.e. YourName.java

				String[] command = readLine.split(" ");

				if (command[0].equalsIgnoreCase("LOGON")) {
					Logon(command, outFromClient, users, socket);
				}
				else if (command[0].equalsIgnoreCase("LIST")) {
					List(command, outFromClient, users);
				}
				else if (command[0].equalsIgnoreCase("PING")) {
					Ping(command, outFromClient, users);
				}
				else if (command[0].equalsIgnoreCase("LOGOFF")) {
					Logoff(command, outFromClient, users);
				}
				else { // unknown command
					outFromClient.println("Unknown Command.");
				}
				outFromClient.flush();
				socket.close();

			}catch (Exception e) {
				System.err.println(e);
				
				if (server.isClosed() || !server.isBound()) {

					System.exit(-2);

				}
			}
		}
	}

	public static void Logon(String[] command, PrintWriter outFromClient, HashMap<Integer, Client> users, Socket socket) {
		// check command 

		if (command.length !=3) { 
			outFromClient.println("Error : illegal message format"); 
		}
		else {

			//check to make sure id is valid
			int id = -1;
			try {
				
				
				id = Integer.parseInt(command[1]);
				
				System.out.println("Hello");
				System.out.println(command.length + " checking length");
				
				String Ip = socket.getInetAddress().getHostAddress();

				long ttl = System.currentTimeMillis()/1000 + TIME;

			
				// add client to hashmap
				Client client = new Client(command[2], Ip, id, ttl);
				
				users.put(client.id, client);
				
				outFromClient.println("Server Resp: ADDED:"+client.id+":"+TIME);
				
				

			}catch (NumberFormatException nfe) {
				outFromClient.println("Error :Invalid port=" + command[1]);
				nfe.printStackTrace();
			}
		}
	}

	public static void removeUser(HashMap<Integer,Client> users) {

		long currtime = System.currentTimeMillis()/1000;

		Iterator<Map.Entry<Integer, Client>> iter = users.entrySet().iterator();
		// go through all clients in the map

		while (iter.hasNext()) {
			Map.Entry<Integer, Client> match = iter.next();

			if (match.getValue().ttl < currtime) {
				System.out.println("Client" +  match.getValue().name + ",id= " + match.getValue().id + ", " + match.getValue().name +" timed out.");
				iter.remove(); 
			}
		}	
	}



	public static void List(String[] command, PrintWriter outFromClient, HashMap<Integer, Client> users) {

		// check to see if command is proper length

		if (command.length != 2) { 
			outFromClient.println("Error: Illegal Message Format."); 
		}else {
			int id = -1;
			try { 
				id = Integer.parseInt(command[1]);	

			}catch (Exception e) {
				outFromClient.println("Invalid id="+ command[1]);
				return;

			}
			if(users.get(id) == null) {
				outFromClient.println("Error, no user with that id.");
			}else { 
				outFromClient.println("Server Resp: " + users.size() );

				Iterator<Map.Entry<Integer, Client>> iter = users.entrySet().iterator();

				while(iter.hasNext()) {
					Map.Entry<Integer, Client> match = iter.next();
					outFromClient.println(match.getValue());
				}
			}
		}
	}

	public static void Ping(String[] command, PrintWriter outFromClient, HashMap<Integer, Client> users) {

		// check to see if command is proper length

		if (command.length != 2) { 
			outFromClient.println("Error, Illegal message format");
		}else {
			int id = -1;
			try {
				id = Integer.parseInt(command[1]);

			}catch (Exception e) {

				outFromClient.println("Error invalid id " + command[1]);
				return;
			}
			// check to make sure the user exists

			Client isAlive = users.get(id);

			if(isAlive == null) { 
				outFromClient.println("User is no longer alive."); 
			}else {

				outFromClient.println("PONG:"+TIME);
				isAlive.ttl = System.currentTimeMillis()*1000 + TIME;
			}
		}
	}

	public static void Logoff(String[] command, PrintWriter outFromClient, HashMap<Integer, Client> users) {
		if (command.length != 2) { 
			outFromClient.println("Error: illegal message format."); 
		}else {
			try {
				int id = Integer.parseInt(command[1]);

				if (users.remove(id) == null) { 
					outFromClient.println("Error: no user with taht id"); 
				}else { 
					outFromClient.println("Server Resp: DONE:" + id);
				}
			}catch (NumberFormatException nfe) {
				outFromClient.println("Error: no user with that id");

			}catch (NullPointerException npe) {
				outFromClient.println("Error: no user with that id");

			}
		}
	}
}


class Client {
	public String name;
	public String address;
	public final int id;
	public int port;
	public long ttl;


	public static int NEXT_ID = 200;


	public Client(String name, String address, int port, long ttl) {
		this.name   = name;
		this.port = port;
		this.address = address;
		this.ttl = ttl;
		this.id = ++NEXT_ID;

	}
}







