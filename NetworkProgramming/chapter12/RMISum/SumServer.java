// package chapter12.RMI2;

import java.io.*;
import java.rmi.*;

public class SumServer
{
  public static void main (String[] argv) 
  {
    try 
    {
      
      Sum robj = new Sum();
      Naming.rebind ("Sum", robj);
      System.out.println ("Hello, Server is ready.");
    } 
    catch (Exception e) 
    {
      System.out.println ("Hello Server failed: " + e);
    }
  }
}
