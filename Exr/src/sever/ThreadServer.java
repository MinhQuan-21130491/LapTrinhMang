package sever;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.io.PrintWriter;
import java.net.Socket;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.List;
import java.util.StringTokenizer;

import util.Dao;
import util.Student;

public class ThreadServer extends Thread {
	Socket socket;
	BufferedReader netIn;
	PrintWriter netOut;
	Dao dao;

	public ThreadServer(Socket socket) throws IOException, ClassNotFoundException, SQLException {
		this.socket = socket;
		netIn = new BufferedReader(new InputStreamReader(socket.getInputStream()));
		netOut = new PrintWriter(socket.getOutputStream(), true);
		dao = new Dao();
	}

	@Override
	public void run() {
		netOut.println("WELCOME TO MANAGE PRODUCT SYSTEM");
		boolean isLogin = false;
		String res = "";
		String userName = null;
		try {
			while (!isLogin) {
				String line = netIn.readLine();
				if (line.equalsIgnoreCase("EXIT"))
					break;
				StringTokenizer stk = new StringTokenizer(line, "\t");
				String command = stk.nextToken().toUpperCase();
				String param = stk.nextToken();
				switch (command) {
				case "USER": {
					if (dao.checkExitsUserName(param)) {
						userName = param;
						res = "OK";
					} else {
						res = "False";
					}
					break;
				}
				case "PW": {
					if(userName == null) {
						res = "UserName first!";
					}else if(dao.checkLogin(userName, param)) {
						res = "Login success!";
						isLogin = true;
					}
					break;
				}
				default:
				}
				netOut.println(res);
				netOut.flush();

			}
			
			while(isLogin) {
				String line = netIn.readLine();
				if (line.equalsIgnoreCase("EXIT"))
					break;
				StringTokenizer stk = new StringTokenizer(line, "\t");
				String command = stk.nextToken().toUpperCase();
				String param = stk.nextToken();
				switch (command) {
				case "FNAME": {
					 printList(netOut, dao.fname(param));
					break;
				}
				case "FID": {
					printList(netOut, dao.fid(Integer.parseInt(param)));
					break;
				}
				default:
				}
				netOut.println(res);
				netOut.flush();
			}
		} catch (IOException | SQLException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
		try {
			socket.close();
		} catch (IOException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
	}
	private static void printList(PrintWriter printWriter, List<Student> list) {
        printWriter.println("===" + list.size() + " results===");
        printWriter.flush();
        for (Student s : list) {
            printWriter.println("-" + s.toString());
            printWriter.flush();
        }        
    }
}
