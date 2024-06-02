package Chapter2.GetterMethods;
/* Returns the IP address in String format */

import java.net.InetAddress;

public class GetHostAddress {

	public static void main(String[] args) {
		// TODO Auto-generated method stub
		try {
			InetAddress address =  InetAddress.getByName("api.rojgari.com");
			String ip_address = address.getHostAddress();// returns String
			System.out.println(ip_address);
		} catch(Exception e) {
			
		}
	}
	

}
