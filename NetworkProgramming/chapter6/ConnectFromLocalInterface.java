// WAP to connect to remote socket from local interface and any wildcard port

package chapter6;

import java.io.*;
import java.net.*;

public class ConnectFromLocalInterface {
    public static void main(String [] args) {
        try {

            InetAddress url = InetAddress.getByName("time.nist.gov");
            // Getting the local interface
            InetAddress localInteface = InetAddress.getByName("192.168.1.106");
            
            // connecting to remote, from local interface and any random port
            Socket socket = new Socket(url, 13, localInteface, 0);
            socket.setSoTimeout(15000);
            InputStream in = socket.getInputStream();

            InputStreamReader reader = new InputStreamReader(in);
            BufferedReader br = new BufferedReader(reader);
            
            String line;
            while((line=br.readLine()) != null){
                System.out.println(line);
            }
        } catch (IOException e) {
            System.out.println("something went wrong");
        }
    }
}
