package test;

import java.rmi.Remote;
import java.rmi.RemoteException;

public interface ISearch extends Remote{
	public String findById(String id) throws RemoteException;
	public String findByName(String partName) throws RemoteException;
}
