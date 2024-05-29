package chapter3;
import java.net.*;

public class TestProtocol {
    public static void main(String[] args) {
        testProtocol("ftp://192.168.99.2");
        testProtocol("mailto:jr.gaurav2015@gmail.com");
        testProtocol("http://google.com");
        testProtocol("abcd://google.com");
    }


    private static void testProtocol(String url){
        try{
            URL u = new URL(url);
            System.out.println(u.getProtocol() + " is suported.");
        }catch(Exception ex) {
            String protocol = url.substring(0, url.indexOf(":"));
            System.out.println(protocol + " is not supported");
        }
    }
}
