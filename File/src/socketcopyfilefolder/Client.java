package socketcopyfilefolder;

import java.io.DataInputStream;
import java.io.DataOutputStream;
import java.io.IOException;
import java.net.ServerSocket;
import java.net.Socket;

public class Client {
	private Socket socket;
	private DataInputStream dis;
	private DataOutputStream dos;
	public Client() {
		 try {
			socket = new Socket("localhost", 7);
			System.out.println("Connected sever");
			dis = new DataInputStream(socket.getInputStream());
			 dos = new DataOutputStream(socket.getOutputStream());
		} catch (IOException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
	}
	public void sendFileOrFolder(String source, String des) {
		try {
			dos.writeUTF(source);
			dos.flush();
			dos.writeUTF(des);
			dos.flush();
			
			dos.close();
			dis.close();
			socket.close();
		} catch (Exception e) {
			// TODO: handle exception
		}
	}
	public static void main(String[] args) {
		new Client().sendFileOrFolder("D:\\ltm\\grade.txt", "D:\\ltm\\grade-copy.txt");
	}
	

}
