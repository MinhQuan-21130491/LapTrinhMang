package Server;

import java.rmi.RemoteException;
import java.rmi.server.UnicastRemoteObject;
import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.HashMap;
import java.util.Map;

public class DaoImp extends UnicastRemoteObject implements IDAO {
	Map<String, User> session;
	Connection con = null;

	protected DaoImp() throws RemoteException {
		session = new HashMap<>();
		String driver = "net.ucanaccess.jdbc.UcanaccessDriver";
		String url = "jdbc:ucanaccess://F:\\Student.mdb";
		try {
			Class.forName(driver);
			try {
				con = DriverManager.getConnection(url);
			} catch (SQLException e) {
				e.printStackTrace();
			}
		} catch (ClassNotFoundException e) {
			e.printStackTrace();
		}
	}
	public void closeConnection() {
		try {
			con.close();
		} catch (SQLException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
	}
	@Override
	public String greeting() throws RemoteException {
		return "welcome you to with application search!";
	}

	@Override
	public String checkExitsUserName(String userName) throws RemoteException {
		String sql = "select * from User where userName = ?";
		try {
			PreparedStatement pr = con.prepareStatement(sql);
			pr.setString(1, userName);
			ResultSet rs = pr.executeQuery();
			if(rs.next()) return "OK";
		} catch (SQLException e) {
			throw new RemoteException(e.getMessage());
		}
		return "UserName not exits!";
	}

	@Override
	public String checkLogin(String userName, String passWord) throws RemoteException {
		// TODO Auto-generated method stub
		return null;
	}

}
