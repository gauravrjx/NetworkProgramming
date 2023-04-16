package ServerWithLog;

import java.io.*;
import java.net.*;
import java.util.logging.*;

public class Server {
    public static Logger logger = Logger.getLogger("requests");

    public static void main(String[] args) {
        ServerSocket ss=null;
        try {
            ss = new ServerSocket(9000);
        }catch(IOException e){
            logger.log(Level.SEVERE, "could not start the server");
        }

            logger.log(Level.INFO, "Waiting for connections....");
        
            try{
            Socket cs;
            cs = ss.accept();
            logger.log(Level.INFO, "CONNECTED");

            // read the message
            InputStreamReader in = new InputStreamReader(cs.getInputStream());
            BufferedReader input = new BufferedReader(in);
            String message = input.readLine();
            logger.log(Level.INFO, "client says: " + message);
            // send response
            PrintWriter out = new PrintWriter(cs.getOutputStream(), true);
            out.println("you said, " + "'" + message + "'");

            ss.close();
        } catch (IOException e) {
            // TODO Auto-generated catch block
            logger.log(Level.SEVERE, "Cannot establish the connection", e);

        } catch (Exception e) {
            logger.log(Level.SEVERE, "Something went wrong " + e.getMessage(), e);
        }
    }
}
