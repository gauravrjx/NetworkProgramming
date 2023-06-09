package UDPChannel;

import java.io.*;
import java.net.*;
import java.nio.*;
import java.nio.channels.*;
import java.util.*;

public class DatagramChannelClient {
    public final static int PORT = 7000;
    private final static int LIMIT = 900;

    public static void main(String[] args) {
        SocketAddress remote;
        try {
            remote = new InetSocketAddress("localhost", PORT);
        } catch (RuntimeException ex) {
            System.err.println("Usage: java UDPEchoClientWithChannels host");
            return;
        }
        try (DatagramChannel channel = DatagramChannel.open()) {
            // make the channel Non Blocking and connect to remote
            channel.configureBlocking(false);
            channel.connect(remote);

            // register the channel with selector for read or write operation
            // because we are interested in read and write only
            Selector selector = Selector.open();
            channel.register(selector, SelectionKey.OP_READ | SelectionKey.OP_WRITE);

            // buffer allocation
            ByteBuffer buffer = ByteBuffer.allocate(4);

            int n = 0;
            int numbersRead = 0;
            while (true) {
                if (numbersRead == LIMIT)
                    break;
                    
                // wait one minute for a connection
                selector.select(60000);
                // ask selector to return all the 
                Set<SelectionKey> readyKeys = selector.selectedKeys();
                if (readyKeys.isEmpty() && n == LIMIT) {
                    // All packets have been written and it doesn't look like any
                    // more are will arrive from the network
                    break;
                } else {
                    Iterator<SelectionKey> iterator = readyKeys.iterator();
                    while (iterator.hasNext()) {
                        SelectionKey key = (SelectionKey) iterator.next();
                        iterator.remove();
                        if (key.isReadable()) {
                            buffer.clear();
                            channel.read(buffer);
                            buffer.flip();
                            int echo = buffer.getInt();
                            System.out.println("Read: " + echo);
                            numbersRead++;
                        }
                        if (key.isWritable()) {
                            buffer.clear();
                            buffer.putInt(n);
                            buffer.flip();
                            channel.write(buffer);
                            System.out.println("Wrote: " + n);
                            n++;
                            if (n == LIMIT) {
                                // All packets have been written; switch to read-only mode
                                key.interestOps(SelectionKey.OP_READ);
                            }
                        }
                    }
                }
            }
            System.out.println("Echoed " + numbersRead + " out of " + LIMIT +
                    " sent");
            System.out.println("Success rate: " + 100.0 * numbersRead / LIMIT +
                    "%");
        } catch (IOException ex) {
            System.err.println(ex);
        }
    }
}
