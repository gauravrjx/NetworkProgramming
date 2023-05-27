// This is IMPLEMENTER class

// package chapter12.RMI2;

import java.rmi.*;
import java.rmi.server.*;

public class Sum extends UnicastRemoteObject implements SumInterface 
{
  // private int a,b;

  // public Sum (int a, int b) throws RemoteException 
  // {
  //   this.a = a;
  //   this.b = b;
  // }

  protected Sum() throws RemoteException {
    super();
    //TODO Auto-generated constructor stub
  }

  @Override
  public int sum(int a, int b) throws RemoteException 
  {
    return a + b;
  }
}
