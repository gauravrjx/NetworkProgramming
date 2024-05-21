package Chapter2.addressMethods;
/* getLocalHost -> prints host name and IP address */

import java.net.*;

public class GetLocalHost {

	public static void main(String[] args) {
		// TODO Auto-generated method stub
		try {			
			InetAddress local_host = InetAddress.getLocalHost(); // no remote call
			System.out.println(local_host);
			// System.out.println(local_host.getHostAddress());
		} catch(Exception e) {
			
		}
	}

}
