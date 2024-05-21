package Chapter2.GetterMethods;

import java.net.*;

public class GetAddress {
    public static void main(String[] args) {
       try {
        InetAddress address = InetAddress.getByName("127.0.0.1");
        byte [] adr = address.getAddress();
        System.out.println(adr.length);

    } catch (UnknownHostException e) {
        // TODO Auto-generated catch block
        e.printStackTrace();
    } 
    }
}
