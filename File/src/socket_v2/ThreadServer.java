package socket_v2;

import java.io.BufferedOutputStream;
import java.io.DataInputStream;
import java.io.DataOutputStream;
import java.io.File;
import java.io.FileOutputStream;
import java.io.IOException;
import java.io.InputStreamReader;
import java.io.OutputStreamWriter;
import java.net.Socket;
import java.util.StringTokenizer;

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
			     while (true) {
				String command =  dis.readUTF();
				if(command.equals("exit")) {
					System.out.println("Disconnect socket " + socket.getInetAddress());
					socket.close();
					break;
				}
				String des = dis.readUTF();
				File desFile = new File(des);
				bos = new BufferedOutputStream(new FileOutputStream(desFile));
				String state = "Success";
				if(!desFile.exists()) {
					state ="Fail";
					dos.flush();
					socket.close();
					break;
				}
				dos.writeUTF(state);
				System.out.println("created des File");
				Long size = dis.readLong();
				int data = 0;
				byte[] buffer = new byte[100*1024];
				while(data <= size) {
					dis.read(buffer, 0, data);
					bos.write(buffer, 0, data);
					bos.flush();
					data += size;
				}
				System.out.println("Completed");
				dos.writeUTF("Completed");
				dos.flush();
			     }
			} catch (IOException e) {
				// TODO Auto-generated catch block
				e.printStackTrace();
			}
			
		

	}

}
