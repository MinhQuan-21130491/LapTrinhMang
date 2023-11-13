package socket_v3;

import java.io.DataInputStream;
import java.io.DataOutputStream;
import java.io.IOException;
import java.net.Socket;

public class ThreadServer extends Thread {
	Socket socket;
	DataOutputStream dos;
	DataInputStream dis;
	
	

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
			String state = dis.readUTF();
			if(!state.equals("Fail")) {
			int num1 = dis.readInt();
			String alg = dis.readUTF();
			int num2 = dis.readInt();
			
			if(alg.equals("+")) {
				System.out.println(num1 + " + " + num2 + " = " + (num1+num2)) ;
			}else if(alg.equals("-")) {
				System.out.println(num1 + " - " + num2 + " = " + (num1-num2)) ;
			}else if(alg.equals("*")) {
				System.out.println(num1 + " * " + num2 + " = " + (num1*num2)) ;
			}else if(alg.equals("/")) {
				System.out.println(num1 + " / " + num2 + " = " + (num1/num2)) ;
			}
			}
			socket.close();
			
		} catch (IOException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
	}
	
	

}
