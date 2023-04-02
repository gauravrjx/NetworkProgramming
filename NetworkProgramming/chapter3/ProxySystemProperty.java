package chapter3;

import java.net.*;
import java.io.*;

public class ProxySystemProperty {
	public static void main(String[] args) {
		// Set the HTTP proxy host system property
		System.setProperty("http.proxyHost", "192.168.1.106");
		// System.out.println(System.getProperties());

		try {
			// Create a URL object to make an HTTP request
			URL url = new URL("https://www.example.com");

			// Open an HTTP connection using the URL object
			HttpURLConnection conn = (HttpURLConnection) url.openConnection();

			// Send an HTTP GET request
			conn.setRequestMethod("GET");

			// Get the input stream from the HTTP connection
			InputStream inputStream = conn.getInputStream();

			// Read the response from the server
			BufferedReader reader = new BufferedReader(new InputStreamReader(inputStream));
			String line;
			StringBuilder response = new StringBuilder();
			while ((line = reader.readLine()) != null) {
				response.append(line);
			}
			reader.close();

			// Print the response to the console
			System.out.println(response.toString());

			// Close the HTTP connection
			conn.disconnect();

		} catch (Exception e) {
			e.printStackTrace();
		}
	}
}
