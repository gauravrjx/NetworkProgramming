/* list out all the network interfaces present in you device 
 * returns only those interfaces, which is connected to the same network
*/
package Chapter2.networkInterface;

import java.net.*;
import java.util.Enumeration;

public class NetworkInterfaceEg {
    public static void main(String[] args) {
        try {
            Enumeration<NetworkInterface> ni = NetworkInterface.getNetworkInterfaces();

            while (ni.hasMoreElements()) {
                NetworkInterface iface = ni.nextElement();
                System.out.println("Interface name: " + iface.getName());
            }

        } catch (SocketException e) {
            // TODO Auto-generated catch block
            e.printStackTrace();
        }
    }
}
