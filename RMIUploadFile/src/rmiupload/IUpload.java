package rmiupload;

import java.rmi.Remote;
import java.rmi.RemoteException;

public interface IUpload extends Remote{
	public void create(String df) throws RemoteException;
	public void write(String sessionId, byte[] data, int size) throws RemoteException;
	public void write(byte[] data, int size) throws RemoteException;
	public void close(String sessionId) throws RemoteException;

}
