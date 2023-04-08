// Check if there is any port within 1000 associated with the localhost
package chapter6;

import java.net.*;
import java.io.*;

public class CheckHostPort {
    protected static String localhost = "127.0.0.1";
    public static void main(String [] args){
        for (int i =7000; i<=8000; i++){
            try {
                Socket s = new Socket(localhost, i);
                System.out.println("port "+ i + " is associated with "+ localhost);

            } catch (IOException e) {
                // TODO: handle exception
    
            }
        }
    }
}
