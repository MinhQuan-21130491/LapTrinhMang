package socket;

import java.io.BufferedOutputStream;
import java.io.DataInputStream;
import java.io.DataOutputStream;
import java.io.File;
import java.io.FileOutputStream;
import java.io.IOException;
import java.net.Socket;

public class ThreadServer extends Thread {
	Socket socket;
	DataOutputStream dos;
	DataInputStream dis;
	BufferedOutputStream bos;

	public ThreadServer(Socket socket) {
		this.socket = socket;
		try {
			dos = new DataOutputStream(this.socket.getOutputStream());
			dis = new DataInputStream(this.socket.getInputStream());
		} catch (IOException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
	}

	@Override
	public void run() {
			try {
				String des = dis.readUTF();
				File desFile = new File(des);
				bos = new BufferedOutputStream(new FileOutputStream(desFile));
				int data = 0;
				byte[] arr = new byte[100 * 1024];
				while ((data = dis.read(arr)) != -1) {
					bos.write(arr, 0, data);
				}
				socket.close();
				dis.close();
				bos.close();
			} catch (IOException e) {
				// TODO Auto-generated catch block
				e.printStackTrace();
			}
	
		

	}

}
