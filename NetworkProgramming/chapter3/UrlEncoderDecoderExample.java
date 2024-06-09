package chapter3;

import java.net.URLEncoder;
import java.net.URLDecoder;

public class UrlEncoderDecoderExample {
   public static void main(String[] args) {
      try {
         // URL to be encoded
         String url = "https://www.example.com/about us/search?q=query string";

         // Encode the URL
         String encodedUrl = URLEncoder.encode(url, "UTF-8");

         // Print the encoded URL
         System.out.println(encodedUrl);

         // Decode the URL
         String decodedUrl = URLDecoder.decode(encodedUrl, "UTF-8");

         // Print the decoded URL
         System.out.println(decodedUrl);

      } catch (Exception e) {
         e.printStackTrace();
      }
   }
}

