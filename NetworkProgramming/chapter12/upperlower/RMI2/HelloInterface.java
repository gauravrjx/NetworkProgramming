package chapter12.upperlower.RMI2;

import java.rmi.*;

public interface HelloInterface extends Remote 
{
  public String say() throws RemoteException;
}
