/* WAP to create a very simple client that establishes SSL connection
 * with server and read the response.
 */
package SecureSocket;

import javax.net.ssl.*;
import java.io.*;
import java.net.*;
import java.security.SecureRandom;

public class SecureSocketExample {

    public static void main(String[] args) {
        String hostname = "localhost";
        int port = 8888;

        try {
            // Create an SSL context with the TLS protocol
            SSLContext sslContext = SSLContext.getInstance("TLS");

            // Initialize the SSL context with a key manager and trust manager
            KeyManager[] keyManagers = null;
            TrustManager[] trustManagers = null;
            SecureRandom secureRandom = new SecureRandom();
            sslContext.init(keyManagers, trustManagers, secureRandom);

            // Create an SSL socket factory from the SSL context
            SSLSocketFactory sslSocketFactory = sslContext.getSocketFactory();

            // Create an SSL socket and connect to the server
            SSLSocket sslSocket = (SSLSocket) sslSocketFactory.createSocket(hostname, port);
            sslSocket.startHandshake();

            System.out.println("############################################################");
            // Get the SSL session and print out the protocol and cipher suite used
            SSLSession sslSession = sslSocket.getSession();
            System.out.println("Protocol: " + sslSession.getProtocol());
            System.out.println("Cipher suite: " + sslSession.getCipherSuite());
            System.out.println("############################################################");

            // Use the SSL socket like a regular socket
            PrintWriter out = new PrintWriter(sslSocket.getOutputStream(), true);
            BufferedReader in = new BufferedReader(new InputStreamReader(sslSocket.getInputStream()));
            out.println("GET / HTTP/1.1");
            out.println("Host: " + hostname);
            out.println();
            String response;
            while ((response = in.readLine()) != null) {
                System.out.println(response);
            }
            // Close the SSL socket
            sslSocket.close();
        } catch (Exception e) {
            e.printStackTrace();
        }
    }
}

