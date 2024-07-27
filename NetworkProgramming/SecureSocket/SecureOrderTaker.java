/*
 * keytool -genkey -alias ourstore -keyalg RSA -keystore jnp2e19.keys
 */

package SecureSocket;

import java.io.*;
import java.net.Socket;
import java.security.KeyManagementException;
import java.security.KeyStore;
import java.security.KeyStoreException;
import java.security.NoSuchAlgorithmException;
import java.security.UnrecoverableKeyException;
import java.security.cert.CertificateException;
import java.util.Arrays;

import javax.net.ssl.*;

public class SecureOrderTaker {
    public final static int PORT = 7000;
    public final static String algorithm = "TLS";

    public static void main(String[] args) {
        try {
            SSLContext context = SSLContext.getInstance(algorithm);

            // The reference implementation only supports X.509 keys
            KeyManagerFactory kmf = KeyManagerFactory.getInstance("SunX509");

            // Oracle's default kind of key store
            KeyStore ks = KeyStore.getInstance("JKS");
            
            // For security, every key store is encrypted with a
            // passphrase that must be provided before we can load
            // it from disk.
            char[] password = System.console().readPassword("Enter the password:");

            // load the key
            ks.load(new FileInputStream("SecureSocket/serverkey.keys"), password);
            kmf.init(ks, password);

            // syntax:keyManager, trustManager, random string.
            context.init(kmf.getKeyManagers(), null, null);
            Arrays.fill(password, '0');
            
            /**
             * Add and Load the trust store
             */
            // KeyStore ts = KeyStore.getInstance("JKS");
            // ts.load(new FileInputStream("SecureSocket/truststore.keys"), password);
            // TrustManagerFactory tmf = TrustManagerFactory.getInstance("SunX509");
            // tmf.init(ts);
            // context.init(kmf.getKeyManagers(), tmf.getTrustManagers(), null);

            // create secure server socket
            SSLServerSocketFactory factory = context.getServerSocketFactory();
            SSLServerSocket server = (SSLServerSocket) factory.createServerSocket(PORT);
            
            // add anonymous (non-authenticated) cipher suites
            // server.setNeedClientAuth(false);
            server.setNeedClientAuth(false);
            server.setWantClientAuth(false);
            String[] supported = server.getSupportedCipherSuites();
            String[] anonCipherSuitesSupported = new String[supported.length];
            int numAnonCipherSuitesSupported = 0;
            // System.out.println(Arrays.toString(supported));
            for (int i = 0; i < supported.length; i++) {
                if (supported[i].indexOf("_anon_") > 0) {
                    anonCipherSuitesSupported[numAnonCipherSuitesSupported++] = supported[i];
                }
            }

            // get old enabled suites and add new one as well
            String[] oldEnabled = server.getEnabledCipherSuites();
            System.out.println(oldEnabled.length);
            String[] newEnabled = new String[
                oldEnabled.length + 
                numAnonCipherSuitesSupported
            ];
            System.out.println(newEnabled.length);
            System.arraycopy(
                oldEnabled, 0, newEnabled, 0, oldEnabled.length
            );
            System.arraycopy(
                anonCipherSuitesSupported, 0, newEnabled,
                oldEnabled.length, numAnonCipherSuitesSupported
            );
            System.out.println(newEnabled.length);

            server.setEnabledCipherSuites(newEnabled);
            // SSL set up completed
            
            // The normal read and write operations
            while (true) {
                // This socket will be secure,
                // but there's no indication of that in the code!
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
        } catch (IOException | KeyManagementException
                | KeyStoreException | NoSuchAlgorithmException
                | CertificateException | UnrecoverableKeyException ex) {
            ex.printStackTrace();
        }
    }
}
