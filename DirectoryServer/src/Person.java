/*
 * created this to make it easier on myself when adding info to the person
 * in the hashMap.
 * 
 * created a name, ip, and id, port number, and time to live
 * created the next id to increment every time a new person logged on
 */
public class Person {
	public String name;
	public String address;
	public final int id;
	public int port;
	public long ttl;
	
	
	
	public static int NextId = 200;
	
	public Person(String name, String address, int port, long ttl){
		this.name = name;
		this.address = address;
		this.port = port;
		this.ttl = ttl;
		this.id = NextId++;
	}

	public static void main(String[] args) {

	}

}
