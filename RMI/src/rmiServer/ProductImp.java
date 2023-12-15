package rmiServer;


import java.rmi.RemoteException;
import java.rmi.server.UnicastRemoteObject;

public class ProductImp extends UnicastRemoteObject implements IProduct  {
	String name;

	protected ProductImp(String name) throws RemoteException {
		this.name = name;
		// TODO Auto-generated constructor stub
	}

	@Override
	public String getDescription() throws RemoteException {
	return name;
	}

	

}
