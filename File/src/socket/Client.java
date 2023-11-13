package socket;

import java.io.BufferedInputStream;
import java.io.BufferedOutputStream;
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
	
	public static void main(String[] args) throws UnknownHostException, IOException {
			Socket socket = new Socket("localhost", 2000);
			DataInputStream dis = new DataInputStream(socket.getInputStream());
			DataOutputStream dos = new DataOutputStream(socket.getOutputStream());
			BufferedReader bf = new BufferedReader(new InputStreamReader(System.in));
			String path = bf.readLine();
			StringTokenizer str = new StringTokenizer(path);
			String upload = str.nextToken();
			String source = str.nextToken();
			String des  = str.nextToken();
			dos.writeUTF(des);
			dos.flush();
			File fileSource = new File(source);
			BufferedInputStream bis = new BufferedInputStream(new FileInputStream(fileSource));
			int data;
			byte[] buffer = new byte[100*1024];
			while((data = bis.read(buffer)) != -1) {
				dos.write(buffer, 0, data);
				dos.flush();
			}
			bis.close();
			dos.close();
			socket.close();
	}

}
