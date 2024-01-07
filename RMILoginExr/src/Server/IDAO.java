package Server;

import java.rmi.Remote;
import java.rmi.RemoteException;

public interface IDAO extends Remote {
	
	public String greeting() throws RemoteException;
	public String checkExitsUserName(String userName) throws RemoteException;
	public String checkLogin(String userName, String passWord) throws RemoteException;

}
