package Chapter2;
/* getByName -> prints host name and IP address */

import java.net.*;

public class GetByName {

	public static void main(String[] args) {
		// TODO Auto-generated method stub
		try {
			InetAddress address = InetAddress.getByName("google.com");
			System.out.println(address);
		}catch (Exception e) {
			System.out.println(e);
		}
	}

}
