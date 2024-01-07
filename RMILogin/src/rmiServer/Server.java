package rmiServer;

import java.rmi.AlreadyBoundException;
import java.rmi.RemoteException;
import java.rmi.registry.LocateRegistry;
import java.rmi.registry.Registry;

public class Server {
	public static void main(String[] args) throws RemoteException, AlreadyBoundException {
		Registry reg = LocateRegistry.createRegistry(12345);
		DaoImp dao= new DaoImp();
		 reg.bind("jmxrmi", dao);
		}
}
