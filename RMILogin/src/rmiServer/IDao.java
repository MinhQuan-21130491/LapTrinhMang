package rmiServer;

import java.rmi.Remote;
import java.rmi.RemoteException;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.List;
public interface IDao extends Remote {
	public String findByName(String param, String sessionId) throws RemoteException;
	public List<Student> findById(String param, String sessionId) throws RemoteException;
	public String getGreeting()throws RemoteException;
	public String checkExistUser(String userName) throws RemoteException;
	public String checkLogin(String userName, String password) throws RemoteException;
}
