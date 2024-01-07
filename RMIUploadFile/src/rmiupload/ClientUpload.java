package rmiupload;

import java.io.BufferedInputStream;
import java.io.FileInputStream;
import java.io.IOException;
import java.io.InputStream;
import java.rmi.NotBoundException;
import java.rmi.registry.LocateRegistry;
import java.rmi.registry.Registry;

public class ClientUpload {
	public static void main(String[] args) throws NotBoundException, IOException {
		Registry reg = LocateRegistry.getRegistry(1022);
		IUpload upload = (IUpload) reg.lookup("upload");
		
		String sf = "D:\\ltm\\NetProgrammingStudent\\1 - IntroToNetProgramming.pdf";
		String df = "D:\\ltm\\NetProgrammingStudent\\1 - IntroToNetProgramming-copy1.pdf";
		
		InputStream in = new BufferedInputStream(new FileInputStream(sf));
        upload.create(df);
		int bf;
		byte[] data = new byte[1024*100];
		while((bf = in.read(data)) != -1) {
			upload.write(data, bf);
//			upload.write(sessionId, data, bf);
		}
		in.close();
//		upload.close(sessionId);
		System.out.println("Done!");
	}

}
