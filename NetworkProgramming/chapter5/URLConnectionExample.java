
package chapter5;

import java.io.*;
import java.net.*;

public class URLConnectionExample {
	public static void main(String[] args) {
		try {
			URL u = new URL("http://www.oreilly.com/");
			URLConnection uc = u.openConnection();
			System.out.println(uc.getURL());
		} catch (IOException ex) {
			System.err.println(ex);
		}
	}
}
