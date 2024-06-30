package chapter7;

import java.net.*;
import java.io.*;
import java.util.Date;

public class DaytimeServer {
    // The port number for the Daytime Protocol
    public final static int PORT = 13001;

    public static void main(String[] args) {
        try (ServerSocket server = new ServerSocket(PORT)) {
            while (true) {
                try (Socket connection = server.accept()) {
                    System.out.println("New request received: "+ connection.getInetAddress());
                    Writer out = new OutputStreamWriter(connection.getOutputStream());
                    Date now = new Date();
                    out.write(now.toString() + "\r\n");
                    out.flush();
                    connection.close();

                } catch (IOException ex) {
                    ex.printStackTrace();
                }
            }
        } catch (IOException ex) {
            // Handle exceptions related to the server socket
            System.err.println("Server exception: " + ex.getMessage());
            ex.printStackTrace();
        }
    }
}

