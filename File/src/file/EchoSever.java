package file;

import java.io.BufferedReader;
import java.io.FileInputStream;
import java.io.IOException;
import java.io.InputStream;
import java.io.InputStreamReader;
import java.io.PrintWriter;
import java.io.Writer;
import java.net.ServerSocket;
import java.net.Socket;

public class EchoSever {
	
	public static void main(String[] args) {
		
		//b1 connecting
		try {
			ServerSocket severSocket = new ServerSocket(7);
			System.out.println("waiting for client connect....");

			Socket socket = severSocket.accept();
			//b2 data
			BufferedReader bf = new BufferedReader(new InputStreamReader(socket.getInputStream()));
			PrintWriter pr = new PrintWriter(socket.getOutputStream(), true);
			System.out.println("connected client " + socket.getInetAddress());
			while(true) {					
				String line = bf.readLine();
				if(line.equals("q")) break;
				pr.println("minhquan" +line);
			}
			//b3 close
			socket.close();
		} catch (IOException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
	}

}
