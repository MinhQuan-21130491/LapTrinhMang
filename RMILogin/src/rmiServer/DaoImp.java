package rmiServer;

import java.rmi.RemoteException;
import java.rmi.server.UnicastRemoteObject;
import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;
import java.util.UUID;
public class DaoImp extends UnicastRemoteObject implements IDao {
	Connection connection;
	Map<String, User> session;
	protected DaoImp() throws RemoteException {
		super();
	connection = JDBCUtil.getConnection();
	session = new HashMap<>();
	}
	@Override
	public String getGreeting() throws RemoteException {
		return "welcom";
	}
	@Override
	public String checkExistUser(String userName) throws RemoteException {
		String sql = "Select * from User where userName =?";
		try {
			PreparedStatement pr = connection.prepareStatement(sql);
			pr.setString(1, userName);
			ResultSet rs = pr.executeQuery();
			if(rs.next()) {
				return "Ok!";
			}
		} catch (SQLException e) {
			 throw new RemoteException(e.getMessage());
		}
		
		return "UserName not exist!";
	}
	@Override
	public String checkLogin(String userName, String password) throws RemoteException {
			String sql = "select * from User where userName =? and passWord =?";
			System.out.println(userName);
			System.out.print(password);
			String ssid = null;
			try {
				PreparedStatement pr = connection.prepareStatement(sql);
				pr.setString(1, userName);
				pr.setString(2, password);
				ResultSet rs = pr.executeQuery();
				if(rs.next()) {
					ssid = UUID.randomUUID().toString();
					User user = new User(rs.getString("userName"), rs.getString("passWord"));
					session.put(ssid, user);
					return ssid;
				}
			} catch (SQLException e) {
				 throw new RemoteException(e.getMessage());
			}
			return "LoginFail!";
	}
	public String findByName(String param, String sessionId)throws RemoteException {
		try {
			String sql = "select * from Student where name like ?";
			PreparedStatement pr = connection.prepareStatement(sql);
			pr.setString(1, "%" +param);
			if(session.get(sessionId).findByName(pr).length() == 0) {
				return "0 result!";
			}
			return session.get(sessionId).findByName(pr);
			} catch (SQLException e) {
			throw new RemoteException(e.getMessage());
		}
	}
	public List<Student> findById(String param, String sessionId) throws RemoteException {
		List<Student> re = new ArrayList<>();
		if(sessionId != null) {
		try {
			String sql = "select * from Student where ID =?";
			PreparedStatement pr = connection.prepareStatement(sql);
			pr.setString(1, param);
			ResultSet r = pr.executeQuery();
			while(r.next()) {
				int id = r.getInt("ID");
				String name = r.getString("name");
				int age= r.getInt("age");
				int bYear = r.getInt("bYear");
				double grade = r.getDouble("grade");
				Student st = new Student(id, name, age, bYear, grade);
				re.add(st);
			}
		} catch (SQLException e) {
			throw new RemoteException(e.getMessage());
		}
		}
		return re;
	}
	

}
