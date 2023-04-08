package Chapter2;
/* Returns the IP address in String format */

import java.net.InetAddress;

public class GetHostAddress {

	public static void main(String[] args) {
		// TODO Auto-generated method stub
		try {
			InetAddress address =  InetAddress.getByName("gauravjaiswal.com.np");
			String ip_address = address.getHostAddress();// returns String
			String hostname = address.getHostName();
			System.out.println(ip_address);
			System.out.println(hostname);
		} catch(Exception e) {
			
		}
	}
	

}
