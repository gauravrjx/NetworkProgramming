package MultithrededServer;

import java.io.*;
import java.net.*;
import java.util.*;

public class ServerThreads extends Thread {
    private Socket connection;

    ServerThreads(Socket connection) {
        this.connection = connection;
        System.out.println("connection started for port: "+ this.connection.getPort());

    }

    @Override
    public void run() {
        try {
            System.out.println("processing for client with port: "+ this.connection.getPort());
            PrintWriter out = new PrintWriter(connection.getOutputStream(), true);
            Date now = new Date();
            out.println(now.toString());
            // out.flush();
        } catch (Exception ex) {
            System.err.println(ex);
        } finally {
            try {
                connection.close();
            } catch (IOException e) {
                // ignore;
            }
        }
    }
}
