package rmiupload;

import java.io.BufferedOutputStream;
import java.io.File;
import java.io.FileNotFoundException;
import java.io.FileOutputStream;
import java.io.IOException;
import java.io.OutputStream;
import java.rmi.RemoteException;
import java.rmi.server.UnicastRemoteObject;
import java.util.HashMap;
import java.util.Map;
import java.util.UUID;

public class Upload extends UnicastRemoteObject implements IUpload  {
	Map<String, OutputStream> session;
	OutputStream os;

	protected Upload() throws RemoteException {
		super();
		session = new HashMap<>();
	}

	@Override
	public void create(String df) throws RemoteException {
		String sessionId = UUID.randomUUID().toString();
		try {
		OutputStream out = new BufferedOutputStream(new FileOutputStream(df));
		os = new FileOutputStream(new File(df));
//			session.put(sessionId, out);
		} catch (FileNotFoundException e) {
			 throw new RemoteException(e.getMessage());
		}
		//return sessionId;
	}

	@Override
	public void write(byte[] data, int size) throws RemoteException {
		try {
			//session.get(sessionId).write(data,0,size);
			os.write(data, 0, size);
		} catch (IOException e) {
			 throw new RemoteException(e.getMessage());
		}
	}

	@Override
	public void close(String sessionId) throws RemoteException {
		try {
			session.get(sessionId).close();
			session.remove(sessionId);
		} catch (IOException e) {
			 throw new RemoteException(e.getMessage());
		}
	}

	@Override
	public void write(String sessionId, byte[] data, int size) throws RemoteException {
		// TODO Auto-generated method stub
		
	}

}
