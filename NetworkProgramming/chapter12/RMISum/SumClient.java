// package chapter12.RMI2;

import java.rmi.*;
import java.io.*;
import java.util.*;

public class SumClient
{
  public static void main (String[] argv) 
  {
    int a,b;
    Scanner s = new Scanner(System.in);
    a = s.nextInt();
    b = s.nextInt();
    try 
    {
      SumInterface summation = (SumInterface) Naming.lookup("//localhost/Sum");
      
      System.out.println(summation.sum(a,b));
    }
    catch (Exception e) 
    {
      System.out.println ("HelloClient exception: " + e);
    }
  }
}
