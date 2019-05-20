import java.util.concurrent.TimeUnit;

public class DirectoryTest {

	public static void main(String[] args) {
		
		String host = "localhost";
		if(args.length >= 1){
			host = args[0];
		}

		try{
			
			DirectoryClientConnector temp = new DirectoryClientConnector(host, 54321, " temp");
			DirectoryClientConnector client1 = new DirectoryClientConnector(host, 54321, " client 1");
			DirectoryClientConnector client2 = new DirectoryClientConnector(host, 54321, "client 2");
			DirectoryClientConnector client3 = new DirectoryClientConnector(host, 54321, "client 3");
			
			temp.logon();
			temp.getList();
		
			TimeUnit.SECONDS.sleep(10);
			temp.logoff();
			
			client1.logon();
			client1.getList();
	
			TimeUnit.SECONDS.sleep(10);
			client1.logoff();
			
			client2.logon();
			client2.getList();
			TimeUnit.SECONDS.sleep(10);
			client2.logoff();
			
			client3.logon();
			client3.getList();
			TimeUnit.SECONDS.sleep(10);
			client3.logoff();
			
			

		}catch(Exception e){
			System.out.println(e.getMessage());
			e.printStackTrace();
		}
	}

}



