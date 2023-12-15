package rmiClient;

import java.rmi.NotBoundException;
import java.rmi.Remote;
import java.rmi.RemoteException;
import java.rmi.registry.LocateRegistry;
import java.rmi.registry.Registry;

import rmiServer.IProduct;

public class Client {
public static void main(String[] args) throws RemoteException, NotBoundException {
	 Registry RMIReg = LocateRegistry.getRegistry("127.0.0.1",234);
	 Remote remoteService1 = RMIReg.lookup("canon");
	 IProduct p1 = (IProduct) remoteService1;
	 System.out.print(p1.getDescription());
}
}
