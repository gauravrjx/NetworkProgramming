package chapter3;

import java.net.*;

public class SplitingDemo {
    public static void main(String[] args) {
        try {
            URL url = new URL("http://username:password@0.0.0.0:27017/nextgenblog?socketTimeoutMS=3000");

            System.out.println("Host name: " + url.getHost());
            System.out.println("Authority: " + url.getAuthority());
            System.out.println("Reference: " + url.getRef());
            System.out.println("Port: " + url.getPort());
            System.out.println("Query param: " + url.getQuery());

        } catch (Exception e) {
            System.out.println(e);
        }
    }
}