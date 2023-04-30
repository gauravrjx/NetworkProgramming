package secureClientServer;

import java.io.*;
import javax.net.ssl.*;

public class SecureClient {
    public static void main(String[] args) {
        int port = 443; // default https port
        String host = "rojgari.com";
        SSLSocketFactory factory = (SSLSocketFactory) SSLSocketFactory.getDefault();
        SSLSocket socket = null;
        try {
            socket = (SSLSocket) factory.createSocket(host, port);
            // enable all the suites
            String[] supported = socket.getSupportedCipherSuites();
            socket.setEnabledCipherSuites(supported);
            Writer out = new OutputStreamWriter(socket.getOutputStream(), "UTF-8");
            // https requires the full URL in the GET line
            out.write("GET http://" + host + "/ HTTP/1.1\r\n");
            out.write("Host: " + host + "\r\n");
            out.write("\r\n");
            out.flush();
            // read response
            BufferedReader in = new BufferedReader(
                    new InputStreamReader(socket.getInputStream()));
            // read the header
            String s;
            while (!(s = in.readLine()).equals("")) {
                System.out.println(s);
            }
            // read hearder + body
            // while((s = in.readLine()) != null){
            //     System.out.println(s);
            // }
            System.out.println();
            
            // read the body only
            String contentLength = in.readLine();
            int length = Integer.MAX_VALUE;
            try {
                length = Integer.parseInt(contentLength.trim(), 16);
            } catch (NumberFormatException ex) {
            }
            System.out.println(contentLength);
            int c;
            int i = 0;
            while ((c = in.read()) != -1 && i++ < length) {
                System.out.write(c);
            }
            System.out.println();
        } catch (IOException ex) {
            System.err.println(ex);
        } finally {
            try {
                if (socket != null)
                    socket.close();
            } catch (IOException e) {
            }
        }
    }
}