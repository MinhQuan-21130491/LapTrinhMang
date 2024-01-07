package client;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.io.PrintWriter;
import java.net.Socket;
import java.net.UnknownHostException;

public class Client {
	public static void main(String[] args) throws UnknownHostException, IOException {
		Socket socket = new Socket("127.0.0.1", 2000);
		BufferedReader netIn = new BufferedReader(new InputStreamReader(socket.getInputStream()));
		PrintWriter netOut = new PrintWriter(socket.getOutputStream(), true);
		BufferedReader bf = new BufferedReader(new InputStreamReader(System.in));
		System.out.println(netIn.readLine());
		while(true) {
			String line = bf.readLine();
			netOut.println(line);
			if(line.equalsIgnoreCase("quit")) break;
			System.out.println(netIn.readLine());
		}
		bf.close();
		socket.close();
	}

}
