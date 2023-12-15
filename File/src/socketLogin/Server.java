package socketLogin;

import java.io.IOException;
import java.net.ServerSocket;
import java.net.Socket;

public class Server {
	public static void main(String[] args) throws IOException {
		try {
			ServerSocket sv = new ServerSocket(54321);
			while(true) {
				Socket socket = sv.accept();
				Thread th = new ThreadServer(socket);
				th.start();
			}
		} catch (IOException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
	
	}

}
