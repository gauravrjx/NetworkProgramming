package Chapter2;

import java.net.*;
import java.util.Arrays;
import java.net.InetAddress;

public class InetAddressMethod {
	public static void main(String[] arg) throws Exception {
//		InetAddress ip = InetAddress.getByName("0.0.0.0"); //isAnyLocalAddress -> wildcardAddress
//		InetAddress ip = InetAddress.getByName("127.0.0.1"); // isAnyLoopbackAddress -> localhost
//		InetAddress ip = InetAddress.getByName("192.168.108.21"); // siteLocalAddress -> within subnet
		InetAddress ip = InetAddress.getByName("224.0.2.1"); // muticastAddress(224-239 or FF)
//		InetAddress ip = InetAddress.getByName("FF02:0:0:0:0:0:0:1"); // MCLinkLocal (FF02 or FF12)
//		InetAddress ip = InetAddress.getByName("FF09:0:0:0:0:0:0:1"); //MCNodeLocal (FF01 or FF11)
//		InetAddress ip = InetAddress.getByName("FF09:0:0:0:0:0:0:1"); //MCSiteLocal (FF05 or FF15)
//		InetAddress ip = InetAddress.getByName("FF08:0:0:0:0:0:0:1"); // MCOrgLocal (FF08 or FF18)
				
		System.out.print("\nisAnyLocalAddress : " + ip.isAnyLocalAddress());
		System.out.print("\nisLinkLocalAddress : " + ip.isLinkLocalAddress());
		System.out.print("\nisLoopbackAddress : " + ip.isLoopbackAddress());
		System.out.print("\nisMCGlobal : " + ip.isMCGlobal());
		System.out.print("\nisMCLinkLocal : " + ip.isMCLinkLocal());
		System.out.print("\nisMCNodeLocal : " + ip.isMCNodeLocal());
		System.out.print("\nisMCOrgLocal : " + ip.isMCOrgLocal());
		System.out.print("\nisMCSiteLocal : " + ip.isMCSiteLocal());
		System.out.print("\nisMulticastAddress : " + ip.isMulticastAddress());
		System.out.print("\nisSiteLocalAddress : " + ip.isSiteLocalAddress());
	}
}
