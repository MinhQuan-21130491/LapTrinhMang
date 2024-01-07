package client;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.io.PrintWriter;
import java.net.Socket;

public class Client extends Thread {

    private final BufferedReader netIn;
    private final PrintWriter netOut;

    public Client(Socket socket) throws IOException {
        this.netIn = new BufferedReader(new InputStreamReader(socket.getInputStream()));
        this.netOut = new PrintWriter(socket.getOutputStream(), true);
    }

    @Override
    public void run() {
        try {
            // Đọc phản hồi từ server trong luồng mới
            Thread responseThread = new Thread(() -> {
                try {
                    String response;
                    while ((response = netIn.readLine()) != null) {
                        System.out.println(response);
                    }
                } catch (IOException e) {
                    e.printStackTrace();
                }
            });
            responseThread.start();

            // Gửi dữ liệu từ người dùng đến server
            while (true) {
                BufferedReader bf = new BufferedReader(new InputStreamReader(System.in));
                try {
                    String line = bf.readLine();
                    netOut.println(line);
                    if (line.equalsIgnoreCase("EXIT")) {
                        break;
                    }
                } catch (IOException e) {
                    e.printStackTrace();
                }
            }
        } catch (Exception e) {
            e.printStackTrace();
        }
    }

    public static void main(String[] args) {
        try {
            Socket socket = new Socket("127.0.0.1", 6969);
            // Khởi tạo client và bắt đầu luồng mới
            Client client = new Client(socket);
            client.start();
        } catch (IOException e) {
            e.printStackTrace();
        }
    }
}
