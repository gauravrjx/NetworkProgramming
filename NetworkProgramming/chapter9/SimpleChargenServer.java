package chapter9;

import java.io.OutputStream;
import java.net.ServerSocket;
import java.net.Socket;
import java.nio.ByteBuffer;

public class SimpleChargenServer {
    public static void main(String[] args) {
        byte[] rotation = new byte[95 * 2];
        for (byte i = ' '; i <= '~'; i++) {
            rotation[i - ' '] = i;
            rotation[i - ' ' + 95] = i;
        }

        try (ServerSocket serverSocket = new ServerSocket(8888)) {
            System.out.println("Chargen server started on port 8888");

            while (true) {
                try (Socket clientSocket = serverSocket.accept()) {
                    System.out.println("Accepted connection from " + clientSocket.getInetAddress());

                    OutputStream out = clientSocket.getOutputStream();
                    ByteBuffer buffer = ByteBuffer.allocate(74);  // Buffer for 72 characters + \n \r -> total 74
                    int position = 0;

                    // Send continuous stream of 72-character lines to the client
                    while (true) {

                        buffer.clear();  // Clear the buffer for new data
                        buffer.put(rotation, position, 72);
                        buffer.put((byte) '\r'); // index 73
                        buffer.put((byte) '\n'); // index 74

                        buffer.flip();
                        out.write(buffer.array(), 0, buffer.limit());  // Write the buffer to the client

                        // Move to the next starting position
                        position++;
                        if (position == 95) {
                            position = 0;
                        }
                    }
                } catch (Exception e) {
                    System.err.println(e.getMessage());
                }
            }
        } catch (Exception e) {
            System.err.println(e.getMessage());
        }
    }
}
