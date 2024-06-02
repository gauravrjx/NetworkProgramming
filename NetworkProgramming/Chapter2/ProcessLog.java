package Chapter2;

import java.io.*;
import java.net.*;

public class ProcessLog {

	public static void main(String[] args) {
		String file = "logfile.txt";
		try {
				FileReader fin = new FileReader(file);
				BufferedReader bin = new BufferedReader(fin);

			for (String entry = bin.readLine(); entry != null; entry = bin.readLine()) {
				// separate out the IP address
				int index = entry.indexOf(' '); // position of the first space
				String ip = entry.substring(0, index); // read IP 
				String theRest = entry.substring(index); // reads remain (extra information)

				// Ask DNS for the hostname and print it out
				try {
					InetAddress address = InetAddress.getByName(ip);
					System.out.println(address.getHostName() + theRest);
				} catch (UnknownHostException ex) {
					System.err.println(entry);
				}
			}
			bin.close();
		} catch (IOException ex) {
			System.out.println("Exception: " + ex);
		}
	}
}
