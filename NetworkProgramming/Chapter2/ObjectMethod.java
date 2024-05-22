package Chapter2;

import java.net.InetAddress;
import java.net.UnknownHostException;

public class ObjectMethod {
	public static void main(String[] args) {
		try {
			InetAddress addr1 = InetAddress.getByName("rojgari.com");
			InetAddress addr2 = InetAddress.getByName("api.rojgari.com");

			// #1. equals() => Check if the two InetAddress objects are equal (compares the ip address)
			if (addr1.equals(addr2)) {
				System.out.println("both are same");
			} else {
				System.out.println("both are not same");
			}
			// #2. toString() => returns the short representation of inetaddress object
			System.out.println(addr1.toString() + " " + addr2.toString());

			// #3. hashCode() => returns a hash code based on the IP address bytes
			System.out.println(addr1.hashCode());

		} catch (UnknownHostException e) {
			e.printStackTrace();
		}
	}
}
