package chapter6;

import java.net.*;
import java.io.*;

public class PrintAllPorts {
    public static void main(String [] args){
        for (int i =1000; i<=2000; i++){
            try {
                ServerSocket s = new ServerSocket(i);
                s.close();
            } catch (IOException e) {
                // TODO: handle exception
                System.out.println("port "+i+ " is already in use");
            }
        }
    }
}
