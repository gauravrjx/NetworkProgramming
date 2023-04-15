package MultithrededServer;

import java.io.*;
// import java.net.*;

public class Clients {
    public static void main(String[] args) throws IOException {
        for(int i =1; i<=5; i++){
            ClientThreads c= new ClientThreads(i);
            c.start();
        }

    }

}
