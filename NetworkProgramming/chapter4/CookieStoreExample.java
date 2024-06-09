package chapter4;

import java.net.CookieHandler;
import java.net.CookieManager;
import java.net.CookiePolicy;
import java.net.CookieStore;
import java.net.HttpCookie;
import java.net.URI;
import java.net.URISyntaxException;

public class CookieStoreExample {
    public static void main(String[] args) {
        // defining cookie manager constructor
        CookieManager cookieManager = new CookieManager();
        // setting up default cookie manager
        CookieHandler.setDefault(cookieManager);
        // setting up cookie policy
        cookieManager.setCookiePolicy(CookiePolicy.ACCEPT_ALL);

        // get cookie store from cookieManager
        CookieStore cookieStore = cookieManager.getCookieStore();
        
        // add some cookies to the cookieStore
        try {
            URI uri = new URI("http://example.com");

            // Create cookies
            HttpCookie cookie1 = new HttpCookie("user", "john_doe");
            HttpCookie cookie2 = new HttpCookie("session", "xyz123");

            // Set cookie properties (optional)
            cookie1.setDomain("example.com");
            cookie2.setDomain("example.com");

            // Add cookies to the store
            cookieStore.add(uri, cookie1);
            cookieStore.add(uri, cookie2);

            // Print cookies to verify they were added
            for (HttpCookie cookie : cookieStore.getCookies()) {
                System.out.println("Cookie: " + cookie);
            }

        } catch (URISyntaxException e) {
            e.printStackTrace();
        }
    }
}
