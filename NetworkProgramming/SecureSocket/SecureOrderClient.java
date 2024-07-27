package SecureSocket;
import java.io.*;
import javax.net.ssl.*;
import java.security.KeyStore;

public class SecureOrderClient {
    public static void main(String[] args) {
        String host = "localhost";
        int port = 7000;
        String algorithm = "TLS";

        try {
            // Load client trust store
            KeyStore ts = KeyStore.getInstance("JKS");
            char[] password = System.console().readPassword("Enter the password:");
            ts.load(new FileInputStream("SecureSocket/client.truststore"), password);

            // Initialize trust manager factory with the client trust store
            TrustManagerFactory tmf = TrustManagerFactory.getInstance("SunX509");
            tmf.init(ts);

            // Initialize SSL context
            SSLContext context = SSLContext.getInstance(algorithm);
            context.init(null, tmf.getTrustManagers(), null);

            // Create SSL socket
            SSLSocketFactory factory = context.getSocketFactory();
            try (SSLSocket sslSocket = (SSLSocket) factory.createSocket(host, port)) {
                sslSocket.startHandshake();

                // Send data to the server
                PrintWriter out = new PrintWriter(new BufferedWriter(new OutputStreamWriter(sslSocket.getOutputStream())));
                out.println("Hello Secure Server");
                out.flush();

                // Read response from the server
                BufferedReader in = new BufferedReader(new InputStreamReader(sslSocket.getInputStream()));
                String serverResponse;
                while ((serverResponse = in.readLine()) != null) {
                    System.out.println("Server: " + serverResponse);
                }
            }
        } catch (Exception e) {
            e.printStackTrace();
        }
    }
}


