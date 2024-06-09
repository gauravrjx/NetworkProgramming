package chapter3;

import java.net.*;

public class UriCreationMethod1
{
 public static void main(String[] args) throws Exception 
 {
     String uribase = "https://www.example.org/";
     String urirelative = "languages/java";
     String str = "https://www.google.co/?q=networking-in-java#"+""
             + "intro";
       
     // Constructor to create a new URI
     // by parsing the string

     // APPROACH #1: directly from str
     URI uriBase = new URI(uribase);
     System.out.println("Base URI = " + uriBase);

     // Creating URI using create() method
     URI uri = URI.create(str);
     System.out.println("using create method: " + uri);
       

     URI uriRelative = new URI(urirelative);
     System.out.println("Relative URI = " + uriRelative);

     // APPROACH #2: use existing URI and APPEND another one
     // resolve() method to resolve(concatenate) PATH into URI
     URI uriResolvedNoQuery = uriBase.resolve(uriRelative);
     URI uriResolvedWithQuery = uri.resolve(uriRelative); // query of uri is removed from the URI
     System.out.println("Resolved URI No Query = " + uriResolvedNoQuery.toString());
     System.out.println("Resolved URI With Query = " + uriResolvedWithQuery.toString()); // the query and fragment is dropped
}
}
