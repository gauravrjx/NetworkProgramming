package secureClientServer;

import java.io.*;
import java.net.*;
import java.security.SecureRandom;

import javax.net.ssl.*;

public class SecureClient {
    public static void main(String[] args) {
        String hostname = "localhost";
        int port = 8888;

        try {
            SSLContext sslContext = SSLContext.getInstance("TLS");
            KeyManager[] keyManagers = null;
            TrustManager[] trustManagers = null;
            SecureRandom secureRandom = new SecureRandom();
            sslContext.init(keyManagers, trustManagers, secureRandom);
            SSLSocketFactory sslSocketFactory = sslContext.getSocketFactory();
            SSLSocket sslSocket = (SSLSocket) sslSocketFactory.createSocket(hostname, port);
            
            System.out.println("Connected to server");
            BufferedReader in = new BufferedReader(new InputStreamReader(sslSocket.getInputStream()));
            PrintWriter out = new PrintWriter(sslSocket.getOutputStream(), true);
            String inputLine;
            
            while ((inputLine = in.readLine()) != null) {
                System.out.println("Received from server: " + inputLine);
                out.println("Client message: " + inputLine);
            }
            sslSocket.close();
        } catch (Exception e) {
            e.printStackTrace();
        }
    }
}
