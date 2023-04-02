package chapter3;
import java.net.*;
import java.net.UnknownHostException;
import java.util.Arrays;
  


public class Inet6AddressExample {
	
	  
	    public static void main(String[] args) throws UnknownHostException 
	    {
	  
	        String host = "localhost";
	        byte add[] = { 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 1 };
	  
	        //getByAddress() method
	        Inet6Address ip1 = Inet6Address.getByAddress(host, add, 5);
	        Inet6Address ip2 = Inet6Address.getByAddress(null, add, 5);
	          
	        // getAddress() method
	        System.out.println("Address : " + Arrays.toString(ip1.getAddress()));
	        System.out.println("Address : " +ip1);
	        System.out.println("Address : " +ip2);

	        // getHostAddress() method
	        System.out.println("Host Address : " + ip1.getHostAddress());
	  
	  
	        // equals() method
	        System.out.println("ip1==ip2 : " + ip1.getHostAddress().equals(ip2.getHostAddress()));
	  
	    }

		private String getHostAddress() {
			// TODO Auto-generated method stub
			return null;
		}

		private long[] getAddress() {
			// TODO Auto-generated method stub
			return null;
		}

		private static Inet6Address getByAddress(String host, byte[] add, int i) {
			// TODO Auto-generated method stub
			return null;
		}
	  
	}



