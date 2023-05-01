package chapter9;

import java.io.IOException;
import java.net.InetSocketAddress;
import java.nio.ByteBuffer;
import java.nio.channels.ServerSocketChannel;
import java.nio.channels.SocketChannel;
import java.nio.charset.StandardCharsets;

public class ChargenServer {
    public static void main(String[] args) throws IOException {
        // Create a new server socket channel
        ServerSocketChannel serverSocket = ServerSocketChannel.open();
        serverSocket.bind(new InetSocketAddress(8080));

        // to make the connection non blocking
        serverSocket.configureBlocking(false);

        while (true) {
            // Accept incoming connections
            SocketChannel client = serverSocket.accept();

            if (client != null) {
                // Generate and send the chargen sequence
                ByteBuffer buffer = ByteBuffer.allocate(1024);
                for (int i = 32; i < 127; i++) {
                    buffer.put((byte) i);
                }

                /*
                The difference between the two characters is that the carriage 
                return character (\r) moves the cursor to the beginning of the 
                line, while the line feed character (\n) moves the cursor down to the next line.
                
                combination moves the cursor to the begniing of the next line
                */

                buffer.put((byte) '\r');
                buffer.put((byte) '\n');                
                buffer.flip();
                client.write(buffer);

                // Close the connection
                client.close();
            }
        }
    }
}

