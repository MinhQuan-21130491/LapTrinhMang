package sever;

import java.io.IOException;
import java.net.ServerSocket;
import java.net.Socket;
import java.sql.SQLException;

public class Sever {
	public static void main(String[] args) throws IOException, ClassNotFoundException, SQLException {
		ServerSocket server = new ServerSocket(6969);
		while(true) {
			Socket socket = server.accept();
			
			ThreadServer th = new ThreadServer(socket);
			th.start();
		}
	}
}
