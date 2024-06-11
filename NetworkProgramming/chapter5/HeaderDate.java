package chapter5;

import java.io.IOException;
import java.net.MalformedURLException;
import java.net.URLConnection;
import java.net.*;
import java.util.Date;

public class HeaderDate {
    public static void main(String[] args) {

        try {
            URL url = new URL("http://example.com");
            URLConnection uc = url.openConnection();

            System.out.println("Last Modified: " + uc.getLastModified());
            System.out.println("Last Modified1: " + new Date(uc.getLastModified()));
            
            Date lastModified = new Date(uc.getHeaderFieldDate("Last-Modified", 0));
            System.out.println("Last Modified2: " + lastModified);
            
        } catch (MalformedURLException ex) {
            System.err.println(" is not a URL I understand");
        } catch (IOException ex) {
            System.err.println(ex);
        }
        System.out.println();
    }
}
