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

    public SingleFileHTTPServer(
        String data,
        String encoding,
        String mimeType,
        int port
    ) throws UnsupportedEncodingException {
        this(data.getBytes(encoding), encoding, mimeType, port);
    }

    public SingleFileHTTPServer(
        byte[] data, 
        String encoding, 
        String mimeType, 
        int port
    ) {
        this.content = data;
        this.port = port;
        this.encoding = encoding;
        String header = 
            "HTTP/1.0 200 OK\r\n"
            + "Server: OneFile 2.0\r\n"
            + "Content-length: " + this.content.length + "\r\n"
            + "Content-type: " + mimeType + "; charset=" + encoding + "r\n\r\n";
        
        SingleFileHTTPServer.header = header.getBytes(Charset.forName("US-ASCII"));
    }

    public static void main(String[] args) {
        // set the port to listen on
        int port = 9001;
        String file = "chapter7/index.html";
        String encoding = "UTF-8";
        // if (args.length > 2)
        // encoding = args[2];
        try {
            Path path = Paths.get(file);
            byte[] data = Files.readAllBytes(path);
            String contentType = URLConnection.getFileNameMap().getContentTypeFor(path.toString());
            
            new SingleFileHTTPServer(data, encoding, contentType, port);

        } catch (IOException ex) {
            ex.printStackTrace();
        }

        try (ServerSocket server = new ServerSocket(port)) {
            while (true) {
                try {
                    Socket connection = server.accept();
                    try {
                        OutputStream out = new BufferedOutputStream(
                            connection.getOutputStream()
                        );
                        InputStream in = new BufferedInputStream(
                            connection.getInputStream()
                        );
                        // read the incoming HTTP request line (http method, path, version) 
                        // (e.g. GET /index.html HTTP/1.1))
                        // only first line is enough, so break the loop if line is changed
                        StringBuilder request = new StringBuilder(80);
                        while (true) {
                            int c = in.read();
                            if (c == '\r' || c == '\n' || c == -1)
                                break;
                            request.append((char) c);
                        }

                        // Prepare RESPONSE
                        // step 1: prepare header
                        // If this is HTTP/1.0 or later send a MIME header
                        if (request.toString().indexOf("HTTP/") != -1) {
                            out.write(header);
                        }
                        // step 2: prepare body
                        out.write(content);

                        // step 3: send them
                        out.flush();
                    } catch (IOException ex) {
                        ex.printStackTrace();
                    } finally {
                        connection.close();
                    }
                } catch (IOException ex) {
                    ex.printStackTrace();
                } catch (RuntimeException ex) {
                    ex.printStackTrace();
                }
            }
        } catch (IOException ex) {
            ex.printStackTrace();
        }
    }
}