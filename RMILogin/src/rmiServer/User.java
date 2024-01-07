package rmiServer;

import java.rmi.RemoteException;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.List;

public class User {
	String userName;
	String passWord;
	public User(String userName, String passWord) {
		super();
		this.userName = userName;
		this.passWord = passWord;
	}
	public User() {
		// TODO Auto-generated constructor stub
	}
	public String findByName(PreparedStatement pr) {
		StringBuilder stb = new StringBuilder();
		try {
			ResultSet r = pr.executeQuery();
			while(r.next()) {
				int id = r.getInt("ID");
				String name = r.getString("name");
				int age= r.getInt("age");
				int bYear = r.getInt("bYear");
				double grade = r.getDouble("grade");
				Student st = new Student(id, name, age, bYear, grade);
				stb.append(st.toString() + userName + "\n");
			}
		} catch (SQLException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
		return stb.toString();
	}
	@Override
	public String toString() {
		return "User [userName=" + userName + ", passWord=" + passWord + "]";
	}
	


}
