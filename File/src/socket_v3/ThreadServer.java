package socket_v3;

import java.io.BufferedReader;
import java.io.DataInputStream;
import java.io.DataOutputStream;
import java.io.File;
import java.io.FileInputStream;
import java.io.FileNotFoundException;
import java.io.FileReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.io.PrintWriter;
import java.io.UnsupportedEncodingException;
import java.net.Socket;
import java.util.StringTokenizer;

public class ThreadServer extends Thread {
	Socket socket;
	PrintWriter pos;
	BufferedReader bis;
	double operand1, operand2;
	char operator;
	public ThreadServer(Socket socket) {
		this.socket = socket;
		try {
			pos = new PrintWriter(this.socket.getOutputStream());
			bis = new BufferedReader(new InputStreamReader(socket.getInputStream()));
		} catch (IOException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
	}
	@Override
	public void run() {
			try {
				pos.println("welcome " + socket.getInetAddress() + " Enter exit to out");
				pos.flush();
				while(true) {
				String line = bis.readLine();
				if(line.equalsIgnoreCase("exit"))break;
				String rsp = "";
				try {
					requestAnal(line);
					switch (operator) {
					case '+': 
						rsp = operand1 + " + "+ operand2 + " ="  + (operand1+operand2);break;
					case '*': 
						rsp = operand1 + " * "+ operand2 + " ="  + (operand1*operand2);break;
					case '/': 
						rsp = operand1 + " / "+ operand2 + " ="  + (operand1/operand2);break;
					case '-': 
						rsp = operand1 + " - "+ operand2 + " ="  + (operand1-operand2);break;
					}
					pos.println(rsp);
					pos.flush();
				} catch (MyException e) {
					pos.println(e.getMessage());
				}
				
				}
				socket.close();
			} catch (IOException e) {
				// TODO Auto-generated catch block
				e.printStackTrace();
			} 
			
				
		
			
	}
	private void requestAnal(String request) throws MyException {
		StringTokenizer st  = new StringTokenizer(request, "+-*/");
		String str1, str2;
		str1 = st.nextToken();
		str2 = st.nextToken();
		System.out.println(str1);
		System.out.println(str2);
		try {
			this.operand1 = Double.parseDouble(str1);
		} catch (NumberFormatException e) {
			throw new MyException("a not number");
		}
		this.operand2 = Double.parseDouble(str2);
		this.operator = request.charAt(str1.length());
		
	}
	public static void main(String[] args) throws UnsupportedEncodingException {
		try {
			PrintWriter pos = new PrintWriter("D:\\ltm\\t.txt");
			pos.println("Minhquan pro quá");
			pos.flush();
			BufferedReader bf = new BufferedReader(new InputStreamReader(new FileInputStream("D:\\ltm\\t.txt"), "UTF-8"));
			try {
				System.out.println(bf.readLine());
				bf.close();
			} catch (IOException e) {
				// TODO Auto-generated catch block
				e.printStackTrace();
			}
		} catch (FileNotFoundException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
	}
	
	
	

}
