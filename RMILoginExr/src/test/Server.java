package test;

import java.rmi.RemoteException;
import java.rmi.registry.LocateRegistry;
import java.rmi.registry.Registry;

public class Server {

	public static void main(String[] args) throws RemoteException {
		Registry reg = LocateRegistry.createRegistry(1099);
		Dao dao = new Dao();
		reg.rebind("SEARCH",dao);
	}

}
