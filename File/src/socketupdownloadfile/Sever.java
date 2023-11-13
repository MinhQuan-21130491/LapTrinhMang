package socketupdownloadfile;
import java.io.BufferedInputStream;

import java.io.BufferedOutputStream;
import java.io.DataInputStream;
import java.io.DataOutput;
import java.io.DataOutputStream;
import java.io.FileOutputStream;
import java.io.IOException;
import java.net.ServerSocket;
import java.net.Socket;
public class Sever {
	ServerSocket severSocket;
	DataOutputStream dos;
	DataInputStream dis;
	BufferedOutputStream bos;
	private Socket socket;
	public Sever() {
		System.out.println("Waiting for client connect.....");
		try {
			severSocket = new ServerSocket(123);
			socket = severSocket.accept();
			System.out.println("Connected client " + socket.getInetAddress());
			dos = new DataOutputStream(socket.getOutputStream());
			dis = new DataInputStream(socket.getInputStream());
		} catch (IOException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
	}
	public void downLoadFile() {
		try {
			long size = dis.readLong();
			String des = dis.readUTF();
			bos = new BufferedOutputStream(new FileOutputStream(des));
			int data;
			byte [] arr = new byte[100*1024];
			while((data= dis.read(arr))!= -1) {
				bos.write(arr,0, data);
				bos.flush();
			}
			dos.writeUTF("Download success!!!!");
			
			dos.close();
			dis.close();
			bos.close();
			//socket.close();
		} catch (IOException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
	}
	public static void main(String[] args) {
		new Sever().downLoadFile();
	}
	

}
