package util;

import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;

public class Dao {
	Connection connection = null;
	public Dao() throws ClassNotFoundException, SQLException {
		Class.forName("net.ucanaccess.jdbc.UcanaccessDriver");
		String url = "jdbc:ucanaccess://C:\\Users\\DATA\\OneDrive\\Documents\\Database2.accdb";
		connection = DriverManager.getConnection(url);
	}
	public void close() throws SQLException {
		connection.close();
	}
	public boolean checkExitsUserName(String userName) throws SQLException {
		boolean flag = false;
		PreparedStatement pr = connection.prepareStatement("select * from User where userName =?");
		pr.setString(1, userName);
		ResultSet rs = pr.executeQuery();
		if(rs.next()) flag = true;
		return flag;
	}
	public boolean checkLogin(String userName, String password) throws SQLException {
		boolean flag = false;
		PreparedStatement pr = connection.prepareStatement("select * from User where userName =? and passWord =?");
		pr.setString(1, userName);
		pr.setString(2, password);
		ResultSet rs = pr.executeQuery();
		if(rs.next()) flag = true;
		return flag;
	}
	public ArrayList<Student> fname(String name) throws SQLException {
		ArrayList<Student> re= new ArrayList<>();
		PreparedStatement pr = connection.prepareStatement("select * from Student where name like ?");
		pr.setString(1, "%" + name);
		ResultSet rs = pr.executeQuery();
		while(rs.next()) {
			int id = rs.getInt("ID");
			String ten = rs.getString("name");
			int bYear = rs.getInt("bYear");
			double grade = rs.getDouble("grade");
			Student st = new Student(id, ten, bYear, grade);
			re.add(st);
			
		}
		return re;
	}
	public ArrayList<Student> fid(int idS) throws SQLException {
		ArrayList<Student> re= new ArrayList<>();
		PreparedStatement pr = connection.prepareStatement("select * from Student where ID =?");
		pr.setInt(1,idS);
		ResultSet rs = pr.executeQuery();
		while(rs.next()) {
			int id = rs.getInt("ID");
			String name = rs.getString("name");
			int bYear = rs.getInt("bYear");
			double grade = rs.getDouble("grade");
			Student st = new Student(id, name, bYear, grade);
			re.add(st);
			
		}
		return re;
	}
	public static void main(String[] args) throws ClassNotFoundException, SQLException {
		System.out.println(new Dao().fname("quan"));
	}

}
