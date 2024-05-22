package Chapter2.networkInterface.getters;

import java.net.*;

public class Main {
    public static void main(String[] args) throws Exception {
        InetAddress address = InetAddress.getByName("127.0.0.1");
        NetworkInterface ni = NetworkInterface.getByInetAddress(address);

        /* 
            getName() => returns the name of network interface object (here, ni)
        */
        System.out.println("interface name:"+ ni.getName());
        /* 
            getDisplayName() => returns the human redable name of network interface object (here, ni)
                e.g. Local Area Connection 2 instead of lan2 (may not work on Unix)
        */
        System.out.println("interface name:"+ ni.getDisplayName());
    }
}
