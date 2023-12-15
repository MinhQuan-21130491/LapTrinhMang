package rmiServer;
import java.rmi.*;

import java.rmi.registry.LocateRegistry;
import java.rmi.registry.Registry;
import java.util.Locale;

public class Server {
	public static void main(String[] args) throws RemoteException, AlreadyBoundException {
	Registry reg = LocateRegistry.getRegistry(23456);
	 ProductImp canon = new ProductImp("Canon...");
	 ProductImp ip = new ProductImp("ip15 pro max");
	 reg.bind("ip", ip);
	 reg.bind("canon", canon);
	}

}
