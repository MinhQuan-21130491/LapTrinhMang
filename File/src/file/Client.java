package file;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.io.PrintWriter;
import java.net.Socket;
import java.net.UnknownHostException;

public class Client {
    BufferedReader bf;
    PrintWriter pr;
    Socket socket;

    public Client() throws UnknownHostException, IOException {
        this.socket = new Socket("127.0.0.1", 7);
        bf = new BufferedReader(new InputStreamReader(socket.getInputStream()));
        pr = new PrintWriter(socket.getOutputStream());
    }

    public void process() {
        try {
            String msgFromServer;
            String msgFromClient;

            while (true) {
                // Gửi tin nhắn từ máy khách đến máy chủ
                System.out.print("Client: ");
                msgFromClient = new BufferedReader(new InputStreamReader(System.in)).readLine();
                pr.println(msgFromClient);
                System.out.println("Client: " + msgFromClient);

                // Đọc phản hồi từ máy chủ
                msgFromServer = bf.readLine();
                if (msgFromServer == null) {
                    break;
                }
                System.out.println("Server: " + msgFromServer);

                if (msgFromServer.equalsIgnoreCase("quit")) {
                    break;
                }

                // Gửi phản hồi từ máy khách đến máy chủ
                System.out.print("Client: ");
                msgFromClient = new BufferedReader(new InputStreamReader(System.in)).readLine();
                pr.println(msgFromClient);
                System.out.println("Client: " + msgFromClient);
            }

            pr.close();
            bf.close();
            socket.close();
        } catch (IOException e) {
            e.printStackTrace();
        }
    }

    public static void main(String[] args) throws UnknownHostException, IOException {
        new Client().process();
    }
}
