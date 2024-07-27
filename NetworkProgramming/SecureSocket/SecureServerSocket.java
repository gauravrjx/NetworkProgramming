package SecureSocket;

import java.io.*;
import java.net.Socket;
import java.security.KeyManagementException;
import java.security.KeyStore;
import java.security.KeyStoreException;
import java.security.NoSuchAlgorithmException;
import java.security.UnrecoverableKeyException;
import java.security.cert.CertificateException;
import javax.net.ssl.*;

public class SecureServerSocket {
    public final static int PORT = 7000;
    public final static String algorithm = "TLS";

    public static void main(String[] args) {
        try {
            SSLContext context = SSLContext.getInstance(algorithm);

            // Load the server key store
            KeyStore ks = KeyStore.getInstance("JKS");
            char[] password = "hellonepal".toCharArray();
            ks.load(new FileInputStream("SecureSocket/serverkey.keys"), password);

            // Initialize key manager factory with the server key store
            KeyManagerFactory kmf = KeyManagerFactory.getInstance("SunX509");
            kmf.init(ks, password);

            context.init(kmf.getKeyManagers(), null, null);

            // Create secure server socket
            SSLServerSocketFactory factory = context.getServerSocketFactory();
            SSLServerSocket server = (SSLServerSocket) factory.createServerSocket(PORT);

            // Server only authentication
            server.setNeedClientAuth(false);

            while (true) {
                try (Socket theConnection = server.accept()) {
                    InputStream in = theConnection.getInputStream();
                    int c;
                    while ((c = in.read()) != -1) {
                        System.out.write(c);
                    }
                } catch (IOException ex) {
                    ex.printStackTrace();
                }
            }
        } catch (IOException | KeyManagementException | KeyStoreException | NoSuchAlgorithmException | CertificateException | UnrecoverableKeyException ex) {
            ex.printStackTrace();
        }
    }
}

