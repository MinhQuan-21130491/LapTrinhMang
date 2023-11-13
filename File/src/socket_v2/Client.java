package socket_v2;

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


public static void main(String[] args) throws UnknownHostException, IOException {
	Socket socket = new Socket("localhost", 2000);
	DataInputStream netIn = new DataInputStream(socket.getInputStream());
	DataOutputStream netOut = new DataOutputStream(socket.getOutputStream());
	 BufferedInputStream bis = null;
	System.out.println("Lenh upload la upload file");
	System.out.println("Lenh exit la thoat");
	while(true) {
		System.out.println("Nhap: lenh source des");
	BufferedReader userIn = new BufferedReader(new InputStreamReader(System.in));
	
	String line = userIn.readLine();
	if(line.equals("exit")) {
		netOut.writeUTF(line);
		break;
	}
	StringTokenizer stk = new StringTokenizer(line);
	String command = stk.nextToken();
	String source = stk.nextToken();
	String des = stk.nextToken();
	System.out.println("command " + command);
	System.out.println("source " + source);
	System.out.println("des " + des);
	
	// mở file nguồn
	File fileSouce = new File(source);
	if(!fileSouce.exists()) {
		System.out.println("Source file not exist!");
		break;
	}
	netOut.writeUTF(command);
	netOut.writeUTF(des);
	String response  = netIn.readUTF();
	if(response.equals("Fail")) {
		break;
	}
	netOut.writeLong(fileSouce.length());
	System.out.println("File length " + fileSouce.length());
    bis = new BufferedInputStream(new FileInputStream(fileSouce));
	int data;
	byte[] buffer = new byte[100*1024];
	while((data = bis.read(buffer)) != -1) {
		netOut.write(buffer, 0, data);
		netOut.flush();
	}
	String response2 = netIn.readUTF();
	System.out.println(response2);
	if(!response2.equals("Completed")) {
		break;
	}
	}
	System.out.println("Exited");
	socket.close();
}
}
