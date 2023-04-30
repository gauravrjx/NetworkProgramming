package chapter7;

import java.io.*;
import java.net.*;
import java.nio.charset.Charset;
import java.nio.file.*;

public class SingleFileHTTPServer {

    public static byte[] content;
    public static byte[] header;
    public int port;
    public String encoding;

    public SingleFileHTTPServer(String data, String encoding,
            String mimeType, int port) throws UnsupportedEncodingException {
        this(data.getBytes(encoding), encoding, mimeType, port);
    }

    public SingleFileHTTPServer(
            byte[] data, String encoding, String mimeType, int port) {
        this.content = data;
        this.port = port;
        this.encoding = encoding;
        String header = "HTTP/1.0 200 OK\r\n"
                + "Server: OneFile 2.0\r\n"
                + "Content-length: " + this.content.length + "\r\n"
                + "Content-type: " + mimeType + "; charset=" + encoding + "r\n\r\n";
        this.header = header.getBytes(Charset.forName("US-ASCII"));
    }

    public static void main(String[] args) {
        // set the port to listen on
        int port = 80;
        String encoding = "UTF-8";
        if (args.length > 2)
            encoding = args[2];
        try {
            Path path = Paths.get(args[0]);
            byte[] data = Files.readAllBytes(path);
            String contentType = URLConnection.getFileNameMap().getContentTypeFor(args[0]);
            SingleFileHTTPServer server = new SingleFileHTTPServer(data, encoding,
                    contentType, port);
        } catch (ArrayIndexOutOfBoundsException ex) {
            System.out.println(
                    "Usage: java SingleFileHTTPServer filename port encoding");
        } catch (IOException ex) {}


        try (ServerSocket server = new ServerSocket(port)) {
            while (true) {
                try {
                    Socket connection = server.accept();
                    try {
                        OutputStream out = new BufferedOutputStream(
                                connection.getOutputStream());
                        InputStream in = new BufferedInputStream(
                                connection.getInputStream());
                        // read the first line only; that's all we need
                        StringBuilder request = new StringBuilder(80);
                        while (true) {
                            int c = in.read();
                            if (c == '\r' || c == '\n' || c == -1)
                                break;
                            request.append((char) c);
                        }
                        // If this is HTTP/1.0 or later send a MIME header
                        if (request.toString().indexOf("HTTP/") != -1) {
                            out.write(header);
                        }
                        out.write(content);
                        out.flush();
                    } catch (IOException ex) {} finally {
                        connection.close();
                    }
                } catch (IOException ex) {} catch (RuntimeException ex) {}
            }
        } catch (IOException ex) {}
    }
}