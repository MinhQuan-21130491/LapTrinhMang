package socket_v3;

import java.io.BufferedReader;
import java.io.DataInputStream;
import java.io.DataOutputStream;
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
		System.out.println("Nhap phep tinh: ");
		BufferedReader userIn = new BufferedReader(new InputStreamReader(System.in));
		String line = userIn.readLine();
		StringTokenizer stk = new StringTokenizer(line);
		String num1 = stk.nextToken();
		String alg = stk.nextToken();
		String num2 = stk.nextToken();
		int num1Val = 0;
		int num2Val = 0;
		try {
			 num1Val = Integer.parseInt(num1);
			 num2Val = Integer.parseInt(num2);
		} catch (Exception e) {
			System.out.println("Invalid calculation!");
			netOut.writeUTF("Fail");
			socket.close();
			return;
		}
		if(alg.equals("/") && num2Val == 0) {
			System.out.println("not division with 0");
			netOut.writeUTF("Fail");
			socket.close();
		}else {
		netOut.writeUTF("Success");
		netOut.writeInt(num1Val);
		netOut.writeUTF(alg);
		netOut.writeInt(num2Val);
		netOut.flush();
		
		userIn.close();
		socket.close();
		}

	}

}
