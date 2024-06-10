package chapter5;

import java.io.IOException;
import java.net.MalformedURLException;
import java.net.URL;
import java.net.URLConnection;

public class AllHeaders {
    public static void main(String[] args) {
            try {
                URL u = new URL("http://merojob.com/");
                URLConnection uc = u.openConnection();

                // infinite loop
                for (int j = 1;; j++) {
                    String header = uc.getHeaderField(j);
                    if (header == null)
                        break;
                    System.out.println(uc.getHeaderFieldKey(j) + ": " + header);
                }
            } catch (MalformedURLException ex) {
                System.err.println("url is not a URL I understand.");
            } catch (IOException ex) {
                System.err.println(ex);
            }
            System.out.println();
    }
}
