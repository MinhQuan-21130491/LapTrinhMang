package socketupdownloadfile;

import java.io.BufferedInputStream;
import java.io.BufferedReader;
import java.io.DataInputStream;
import java.io.DataOutputStream;
import java.io.File;
import java.io.FileInputStream;
import java.io.IOException;
import java.io.InputStreamReader;
import java.net.Socket;
import java.net.UnknownHostException;
import java.util.StringTokenizer;
public class Client {
	Socket socket;
	DataOutputStream dos;
	DataInputStream dis;
	BufferedInputStream bis;
	BufferedReader bf;
	public Client() {
		try {
			socket = new Socket("localhost", 123);
			System.out.println("Connected to sever");
			dos = new DataOutputStream(socket.getOutputStream());
			dis = new DataInputStream(socket.getInputStream());
		} catch (UnknownHostException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		} catch (IOException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
		}
	public void uploadFile() { 
		bf = new BufferedReader(new InputStreamReader(System.in));
		try {
			String path = bf.readLine();
			StringTokenizer str = new StringTokenizer(path);
			String request = str.nextToken();
			String sourceFile = str.nextToken();
			String desFile =  str.nextToken();
			File fileSource = new File(sourceFile);
			if(!fileSource.exists()) {
				dos.writeUTF("File no exist!");
				dos.flush();
				return;
			}else {
				long size = fileSource.length();
				dos.writeLong(size);
				dos.flush();
				dos.writeUTF(desFile);
				dos.flush();
				bis = new BufferedInputStream(new FileInputStream(fileSource));
				int data;
				byte[] arrByte = new byte[100*1024];
				while((data = bis.read(arrByte)) != -1) {
					dos.write(arrByte, 0, data);					
				}
			//	System.out.println(dis.readUTF());
				bf.close();
				dos.close();
				dis.close();
				bis.close();
				//socket.close();
			}
		} catch (IOException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
	
	}
	public static void main(String[] args) {
		new Client().uploadFile();
	}

}
