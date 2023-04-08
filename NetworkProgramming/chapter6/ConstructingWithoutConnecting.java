package chapter6;

import java.net.*;
import java.io.*;

public class ConstructingWithoutConnecting {
    public static void main(String [] args) {
        try {
            Socket socket = new Socket();
            // fill in socket options
            SocketAddress address = new InetSocketAddress("example.com", 80);
            socket.connect(address);
            InputStream in = socket.getInputStream();
            InputStreamReader inr = new InputStreamReader(in);
            BufferedReader br = new BufferedReader(inr);
            System.out.println(br.readLine());
            socket.close();
           } catch (IOException ex) {
            System.err.println(ex);
           }
    }
}
