package socket_v2;

import java.io.BufferedOutputStream;
import java.io.DataInputStream;
import java.io.DataOutputStream;
import java.io.File;
import java.io.FileNotFoundException;
import java.io.FileOutputStream;
import java.io.IOException;
import java.net.ServerSocket;
import java.net.Socket;

public class Sever {
public static void main(String[] args) {
	try {
			ServerSocket serverSocket = new ServerSocket(2000);
			System.out.println("Waiting for client connect.... ");
			while(true) {
			 Socket	socket = serverSocket.accept();
				System.out.println("Connected client");
				Thread th = new ThreadServer(socket);
				th.start();
			}
		} catch (IOException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
	
}
}
