package MultithrededServer;

import java.net.*;
import java.io.*;

public class ClientThreads extends Thread {
    private int no;

    public ClientThreads(int i) {
        this.no = i;
    }

    @Override
    public void run() {

        Socket server;
        try {
            server = new Socket("localhost", 8888);
            System.out.println("connection started from port: " + server.getLocalPort());

            InputStreamReader in = new InputStreamReader(server.getInputStream());
            BufferedReader input = new BufferedReader(in);
            String response = input.readLine();
            System.out.println("message received for port: " + server.getLocalPort() + ", message: " + response);
            server.close();
        } catch (Exception e) {
            // TODO Auto-generated catch block
            e.printStackTrace();
        }
    }

}
