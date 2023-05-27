// STEP #1: Define Interface
import java.rmi.*;

public interface HelloInterface extends Remote 
{
  public String say() throws RemoteException;
}
