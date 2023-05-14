package UDPChannel;

import java.io.*;
import java.net.*;
import java.nio.*;
import java.nio.channels.*;

public class DatagramChannelServer {
    public final static int PORT = 7000;
    public final static int MAX_PACKET_SIZE = 65507;

    public static void main(String[] args) {
        try {
            // creating a channel by calling the open() method
            DatagramChannel channel = DatagramChannel.open();
            DatagramSocket socket = channel.socket();

            // binding udp server socket to a address
            SocketAddress address = new InetSocketAddress(PORT);
            socket.bind(address);

            // allocating buffer size
            ByteBuffer buffer = ByteBuffer.allocateDirect(MAX_PACKET_SIZE);
            
            // accepting the request, and sending back as response
            while (true) {
                SocketAddress client = channel.receive(buffer);
                buffer.flip();
                channel.send(buffer, client);
                buffer.clear();
            }
        } catch (IOException ex) {
            System.err.println(ex);
        }
    }
}
