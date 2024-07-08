package BasicRedirector;

import java.io.*;
import java.net.*;
import java.util.*;
import java.util.logging.*;

public class RedirectorServer {
    private final static int port = 8009;
    private final static String newSite = "new-resource-path";
    private static final Logger logger = Logger.getLogger(RedirectorServer.class.getName());

    public static void main(String[] args) {
        try {
            ServerSocket server = new ServerSocket(port);
            logger.info("Redirecting connections on port " + server.getLocalPort() + " to " + newSite);
            
            while (true) {
                Socket cs = server.accept();
                handleRequest(cs);
            }
        } catch (IOException e) {
            logger.log(Level.SEVERE, "Server error", e);
        }
    }

    private static void handleRequest(Socket cs) {
        try (BufferedReader reader = new BufferedReader(new InputStreamReader(cs.getInputStream()));
             PrintWriter out = new PrintWriter(cs.getOutputStream(), true)) {

            String input = reader.readLine();
            logger.info("Request received: " + input);
            
            // Split request line to array
            // e.g. GET /old-location HTTP/1.1 => [GET, /old-location, HTTP/1.1] 
            String[] pieces = input.split("\\s+");
            String theFile = pieces[1];

            System.out.println(Arrays.toString(pieces));
            System.out.println(theFile);

            if (input.contains("HTTP")) {
                sendHttpResponse(out, theFile);
            } else {
                logger.warning("Unsupported request: " + input);
                // Handle non-HTTP requests here
            }
        } catch (IOException e) {
            logger.log(Level.SEVERE, "Error handling request", e);
        } finally {
            try {
                cs.close();
            } catch (IOException e) {
                logger.log(Level.SEVERE, "Error closing socket", e);
            }
        }
    }

    private static void sendHttpResponse(PrintWriter out, String theFile) {

        // Setup headers
        out.write("HTTP/1.0 302 FOUND\r\n");
        Date now = new Date();
        out.write("Date: " + now + "\r\n");
        out.write("Server: Redirector 1.1\r\n");
        out.write("Location: " + newSite + theFile + "\r\n");
        out.write("Content-type: text/html\r\n\r\n");

        // setup body
        out.write("<HTML><HEAD><TITLE>Document moved</TITLE></HEAD>\r\n");
        out.write("<BODY><H1>Document moved</H1>\r\n");
        out.write("The document " + theFile
                + " has moved to\r\n<A HREF=\"" + newSite + theFile + "\">"
                + newSite + theFile
                + "</A>.\r\n Please update your bookmarks<P>");
        out.write("</BODY></HTML>\r\n");

        // send everything
        out.flush();
    }
}
