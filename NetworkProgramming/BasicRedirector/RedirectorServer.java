package BasicRedirector;

import java.io.*;
import java.net.*;
import java.util.*;
import java.util.logging.*;;

public class RedirectorServer {
    private final static int port = 8000;
    private final static String newSite = "new-page";
    private static final Logger logger = Logger.getLogger("Redirector");

    // public Redirector(String newSite, int port) {
    // this.port = port;
    // this.newSite = newSite;
    // }
    public static void main(String[] args) {
        // Redirector redirector = new Redirector(, 8000);
        try {
            ServerSocket server = new ServerSocket(port);
            logger.info("Redirecting connections on port "
                    + server.getLocalPort() + " to " + newSite);
            while (true) {
                Socket cs = server.accept();

                PrintWriter out = new PrintWriter(cs.getOutputStream(), true);
                String input = new BufferedReader(
                        new InputStreamReader(cs.getInputStream())).readLine();

                String[] pieces = input.split("\\w*");
                System.out.println(pieces);
                String theFile = pieces[1];

                if (input.indexOf("HTTP") != -1) {
                    out.write("HTTP/1.0 302 FOUND\r\n");
                    Date now = new Date();
                    out.write("Date: " + now + "\r\n");
                    out.write("Server: Redirector 1.1\r\n");
                    out.write("Location: " + newSite + theFile + "\r\n");
                    out.write("Content-type: text/html\r\n\r\n");
                    out.flush();
                }
                // produce HTML that says where the document has moved to.
                out.write("<HTML><HEAD><TITLE>Document moved</TITLE></HEAD>\r\n");
                out.write("<BODY><H1>Document moved</H1>\r\n");
                out.write("The document " + theFile
                        + " has moved to\r\n<A HREF=\"" + newSite + theFile + "\">"
                        + newSite + theFile
                        + "</A>.\r\n Please update your bookmarks<P>");
                out.write("</BODY></HTML>\r\n");
                out.flush();

                try {
                    cs.close();
                } catch (Exception e) {
                }
            }
        } catch (IOException e) {
            // TODO Auto-generated catch block
            e.printStackTrace();
        }
    }
    // public void redirect() throws IOException {

    // }
}
