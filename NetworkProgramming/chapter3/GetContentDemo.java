package chapter3;

import java.io.*;
import java.net.*;

public class GetContentDemo {
	public static void main(String[] args) {
		try {
			URL url = new URL("https://unsplash.com/napi/topics/people");
			URLConnection connection = url.openConnection( );   
        	String responseType = connection.getContentType( );  
			Object o = url.getContent();
			
			System.out.println("The response type is : "+responseType);  
			System.out.print(o);
			// System.out.print(o.getClass().getName());
		} catch (IOException e) {
			System.out.println(e);
		}
	}
}