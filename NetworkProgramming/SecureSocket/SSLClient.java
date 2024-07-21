package SecureSocket;

import javax.net.ssl.SSLSocket;
import javax.net.ssl.SSLSocketFactory;
import java.io.BufferedReader;
import java.io.InputStreamReader;
import java.io.PrintWriter;

public class SSLClient {
    public static void main(String[] args) {
        String host = "khalti.com";
        int port = 443;
        SSLSocket socket = null;
        try {
            SSLSocketFactory factory = (SSLSocketFactory) SSLSocketFactory.getDefault();

            socket = (SSLSocket) factory.createSocket(host, port);
            socket.setSoTimeout(5000);
            socket.startHandshake();

            PrintWriter writer = new PrintWriter(socket.getOutputStream(), true);
            BufferedReader reader = new BufferedReader(
                new InputStreamReader(socket.getInputStream())
            );

            writer.println("GET / HTTP/1.1");
            writer.println("Host: " + host);
            writer.println("Connection: " + "close");
            writer.println(); // Empty line to end the HTTP request headers

            String response;
            while ((response = reader.readLine()) != null) {
                System.out.println(response);
            }

            writer.close();
            reader.close();
        } catch (Exception e) {
            e.printStackTrace();
        }finally{
            if (socket != null){
                try{
                    socket.close();
                }catch (Exception e){

                }
            }
        }
    }
}

