package file;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.io.OutputStreamWriter;
import java.io.PrintWriter;
import java.net.Socket;

public class ThreadSever extends Thread {
	Socket socket;
	BufferedReader bf;
	BufferedReader severIn;
	PrintWriter pr;
	

	public ThreadSever(Socket socket) throws IOException {
	this.socket = socket;
	bf = new BufferedReader(new InputStreamReader(socket.getInputStream()));
	pr = new PrintWriter(new OutputStreamWriter(socket.getOutputStream()), true);
	}

	 @Override
	    public void run() {
	        try {
	            String msgFromClient;
	            String msgFromServer;

	            while (true) {
	                // Đọc tin nhắn từ máy khách
	                msgFromClient = bf.readLine();
	                if (msgFromClient == null) {
	                    break;
	                }
	                System.out.println("Client: " + msgFromClient);

	                if (msgFromClient.equalsIgnoreCase("quit")) {
	                    break;
	                }

	                // Gửi phản hồi từ máy chủ đến máy khách
	                System.out.print("Server: ");
	                msgFromServer = new BufferedReader(new InputStreamReader(System.in)).readLine();
	                pr.println(msgFromServer);
	                System.out.println("Server: " + msgFromServer);

	                // Đọc tin nhắn từ máy chách
	                msgFromClient = bf.readLine();
	                if (msgFromClient == null) {
	                    break;
	                }
	                System.out.println("Client: " + msgFromClient);

	                if (msgFromClient.equalsIgnoreCase("quit")) {
	                    break;
	                }
	            }

	            bf.close();
	            pr.close();
	            socket.close();
	        } catch (IOException e) {
	            e.printStackTrace();
	        }
	    }
	}