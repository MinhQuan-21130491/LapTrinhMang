package rmiClient;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.rmi.NotBoundException;

import java.rmi.Remote;
import java.rmi.RemoteException;
import java.rmi.registry.LocateRegistry;
import java.rmi.registry.Registry;
import java.sql.SQLException;
import java.util.StringTokenizer;

import rmiServer.IDao;

public class Client {
	public static void main(String[] args) throws RemoteException, NotBoundException {
		Registry RMIReg = LocateRegistry.getRegistry("127.0.0.1", 12345);
		Remote remoteService1 = RMIReg.lookup("jmxrmi");
		IDao dao = (IDao) remoteService1;
		System.out.println(dao.getGreeting());
		String ssid = null;
		String userName = null;
		while(ssid == null) {
			try {
				BufferedReader bf = new BufferedReader(new InputStreamReader(System.in));
				StringTokenizer tokenizer = new StringTokenizer(bf.readLine());
				String command = tokenizer.nextToken().toUpperCase();
				if (command.equalsIgnoreCase("quit"))break;
				String param = tokenizer.nextToken();
				
				switch (command) {
				case "USER":
					String re = dao.checkExistUser(param);
					System.out.println(re);
					if(re.equalsIgnoreCase("Ok!")) userName = param;
					break;
				case "PW":
					String re2 = dao.checkLogin(userName, param);
					if(!re2.equalsIgnoreCase("LoginFail!")) {
						System.out.println("Login success!");
						ssid = re2;
					}else {
						System.out.println(re2);
					}
					break;
				default:
					System.out.println("Đã xảy ra lỗi");
					break;
				}
			} catch (RemoteException e) {
				// TODO Auto-generated catch block
				e.printStackTrace();
			} catch (IOException e) {
				// TODO Auto-generated catch block
				e.printStackTrace();
			}
		}
		while (ssid != null) {
			BufferedReader bf = new BufferedReader(new InputStreamReader(System.in));
			try {
				StringTokenizer tokenizer = new StringTokenizer(bf.readLine());
				String command = tokenizer.nextToken().toUpperCase();
				if (command.equalsIgnoreCase("quit"))break;
				String param = tokenizer.nextToken();
				switch (command) {
				case "FNAME":
					System.out.println(dao.findByName(param, ssid));
					break;
				default:
					System.out.println("Đã xảy ra lỗi");
					break;
				}
			} catch (RemoteException e) {
				// TODO Auto-generated catch block
				e.printStackTrace();
			} catch (IOException e) {
				// TODO Auto-generated catch block
				e.printStackTrace();
			}

		}
	}
}
