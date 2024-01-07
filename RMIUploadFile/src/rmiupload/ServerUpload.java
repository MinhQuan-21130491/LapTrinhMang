package rmiupload;

import java.rmi.AlreadyBoundException;
import java.rmi.RemoteException;
import java.rmi.registry.LocateRegistry;
import java.rmi.registry.Registry;

public class ServerUpload {
	public static void main(String[] args) throws RemoteException, AlreadyBoundException {
		Registry reg = LocateRegistry.createRegistry(1022);
		Upload upload = new Upload();
		reg.bind("upload", upload);
	}
	
}
