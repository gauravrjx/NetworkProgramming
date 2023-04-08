package Chapter2;


import java.net.*;

public class GetLocalHost2 {

	public static void main(String[] args) {
		// TODO Auto-generated method stub
		try {			
			InetAddress address = InetAddress.getByName("127.0.0.1");
			// wrong approach
			// since getLocalHost() is static factory method, access them directly from the class
//			InetAddress local_host = address.getLocalHost(); 
			String fqn_local_host = address.getCanonicalHostName(); // gives FQDN
//			System.out.println(local_host);
			System.out.println(fqn_local_host);
		} catch(Exception e) {
			
		}
	}

}
