package Chapter2.GetterMethods;
/* returns the host name in String format */

import java.net.InetAddress;

public class GetHostName {

	public static void main(String[] args) {
		// TODO Auto-generated method stub
		try {
			InetAddress address = InetAddress.getByName("127.0.0.1");
			String host_name = address.getHostName(); // returns String
			System.out.println(host_name);
		} catch(Exception e) {
			
		}
	}
	

}
