package chapter3;

// import com.macfaq.net.*;
import java.net.*;
import java.io.*;

public class CommunicationClientServer {
    public static void main(String[] args) {
        String [] q = {"Java"};
        String target = "";
        for (int i = 0; i < q.length; i++) {
            target += q[i] + " ";
        }
        target = target.trim();
        // QueryString query = new QueryString("search", target);
        try {
            URL u = new URL("https://unsplash.com/nautocomplete/" + target);
            InputStream in = new BufferedInputStream(u.openStream());
            InputStreamReader theHTML = new InputStreamReader(in);
            int c;
            while ((c = theHTML.read()) != -1) {
                System.out.print((char) c);
            }
        } catch (MalformedURLException ex) {
            System.err.println(ex);
        } catch (IOException ex) {
            System.err.println(ex);
        }
    }
}
