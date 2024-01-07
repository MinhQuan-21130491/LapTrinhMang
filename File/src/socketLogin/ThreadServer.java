package socketLogin;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.io.PrintWriter;
import java.net.Socket;
import java.sql.SQLException;
import java.util.List;
import java.util.StringTokenizer;

public class ThreadServer extends Thread {
	Socket socket;
	PrintWriter pr;
	BufferedReader bf;
	Dao dao;

	public ThreadServer(Socket socket) {
		this.socket = socket;
		dao = new Dao();
		try {
			pr = new PrintWriter(socket.getOutputStream(), true);
			bf =  new BufferedReader(new InputStreamReader(socket.getInputStream()));
		} catch (IOException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
	}

	@Override
	public void run() {

		String com, param, response;
		String line;
		try {
			pr.println("Login now!");
			String userName = "";
			boolean isLogin = false;
			String res = "";
			while(!isLogin) {	
				 line = bf.readLine();
				if(line.equalsIgnoreCase("quit")) {
					break;
				}
				StringTokenizer stk = new StringTokenizer(line);
				String command = stk.nextToken().toUpperCase();
				String param = stk.nextToken();
				System.out.println(param);
				switch(command) {
				case "USER": 
					try {
						if(!dao.checkExitsUserName(param)) {
							res ="UserName not exist!";
						}else {
							userName = param;
							res ="Ok";
						}
					} catch (SQLException e) {
						// TODO Auto-generated catch block
						e.printStackTrace();
					}
					break;
				case "PW" :
					if(userName.equals("")) {
						res = "UserName first!";
					}else {
						try {
							if(dao.checkLogin(userName, param)) {
								res ="Login success!";
								isLogin = true;
							}else {
								res ="Login fail!";
							}
						} catch (SQLException e) {
							// TODO Auto-generated catch block
							e.printStackTrace();
						}
					}
					break;
				default: 
					res ="Error";
					break;
				}
				pr.println(res);
			}
			String rs ;
			while (isLogin) {
				line = bf.readLine();
				if (line==null ||"QUIT".equalsIgnoreCase(line)) break;
				StringTokenizer st = new StringTokenizer(line);
				com = st.nextToken().toUpperCase();
				param = st.nextToken();
				switch(com) {
				case "FINDBYID":
					rs =  dao.fid(Integer.parseInt(param));
					if (rs==null) response = "Khong tim thay";
					else response = rs;

					break;
				case "FINDBYNAME":
					param = line.substring(com.length()).trim();
					rs = dao.fname(param);
					if (rs==null) response = "Khong tim thay";
					else response = rs;
					break;
				default:
					response = "Lenh khong hop le";
					break;
			}
			netOut.println(response);
	            }		
			socket.close();
			JDBCUtil.closeConnection(dao.connection);
		} catch (IOException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
	}
	private static void printList(PrintWriter printWriter, List<Student> list) {
        printWriter.println("===" + list.size() + " results===");
        for (Student s : list) {
            printWriter.println("-" + s.toString());
        }
    }

	

}
