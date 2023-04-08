package Chapter2;

import java.net.*;

public class SpamFiltering {
	public static final String blackhole = "sbl.spamhaus.org";

	private static boolean isSpam(String addr) {
		try {
			String query = blackhole;
			InetAddress ia = InetAddress.getByName(addr);
			byte[] bytes = ia.getAddress();

			for (byte b : bytes) {
				int a = b < 0 ? b + 256 : b;
				query = a + "." + query;
				
			}
			
			InetAddress address = InetAddress.getByName(query);
			String hostAddress = address.getHostAddress();
			
            return hostAddress == "127.0.0.2";
            
		} catch (UnknownHostException e) {
			return false;
		}
	}

	public static void main(String[] args) {
		// TODO Auto-generated method stub
		String[] arr = { "127.0.0.2", "8.8.8.8", "76.76.21.21" };
		for (String arg : arr) {
			if (isSpam(arg)) {

				System.out.println(arg + " is Spammer");
			} else {

				System.out.println(arg + " is Legit");
			}
		}

	}

}
