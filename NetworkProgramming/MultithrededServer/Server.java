// Multithreaded Daytime server-client
package MultithrededServer;

import java.io.*;
import java.net.*;

public class Server {
    public final static int PORT = 8000;

    public static void main(String[] args) {
        try (ServerSocket server = new ServerSocket(PORT)) {
            while (true) {
                try {
                    Socket connection = server.accept();
                    Thread task = new ServerThreads(connection);
                    task.start();
                } catch (IOException ex) {
                }
            }
        } catch (IOException ex) {
            System.err.println("Couldn't start server");
        }
    }
}
