package chapter3;

import java.io.*;    
import java.net.*;    
public class SplitingDemo {    
public static void main(String[] args){    
try{    
URL url=new URL("https://user:password@unsplash.com:8000/photos/wRdSItfeMLs#apple");    

System.out.println(url.getHost());
System.out.println(url.getAuthority());
System.out.println(url.getRef());
System.out.println(url.getPort());

}catch(Exception e){System.out.println(e);}    
}    
}    