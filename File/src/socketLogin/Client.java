package socketLogin;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.io.PrintWriter;
import java.net.Socket;

public class Client {
    
    public static void main(String[] args) {
        try {
            BufferedReader netIn;
            PrintWriter netOut;
            Socket socket;
            socket = new Socket("127.0.0.1", 54321);
            netIn = new BufferedReader(new InputStreamReader(socket.getInputStream()));
            netOut = new PrintWriter(socket.getOutputStream(), true);

            System.out.println(netIn.readLine());
            while (true) {
                BufferedReader bf = new BufferedReader(new InputStreamReader(System.in));

                String line = bf.readLine();
                netOut.println(line);
                if (line.equalsIgnoreCase("EXIT")) {
                    break;
                }
                String response = netIn.readLine();
                System.out.println(response);
            }
        } catch (IOException e) {
            e.printStackTrace();
        }
    }
}
