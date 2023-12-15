package socketLogin;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;
import java.util.ArrayList;
import java.util.List;

public class Dao {
	Connection connection;

	public Dao() {
		connection = JDBCUtil.getConnection();
	}
	public boolean checkExistsUserName(String userName) throws SQLException {
		String sql = "select * from User where userName =?";
		PreparedStatement pr = connection.prepareStatement(sql);
		pr.setString(1, userName);
		ResultSet r = pr.executeQuery();
		while(r.next()) {
			return true;
		}
		return false;

	}
	public boolean isLogin(String userName, String password) throws SQLException {
		String sql = "select * from User where userName =? and passWord =?";
		PreparedStatement pr = connection.prepareStatement(sql);
		pr.setString(1, userName);
		pr.setString(2, password);
		ResultSet r = pr.executeQuery();
		while(r.next()) {
			return true;
		}
		return false;
	}

	public List<Student> findByName(String param) throws SQLException {
		List<Student> re = new ArrayList<>();
		String sql = "select * from Student where name like ?";
		PreparedStatement pr = connection.prepareStatement(sql);
		pr.setString(1, "%" +param);
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
		return re;
	}
	public List<Student> findById(int param) throws SQLException {
		List<Student> re = new ArrayList<>();
		String sql = "select * from Student where ID =?";
		PreparedStatement pr = connection.prepareStatement(sql);
		pr.setInt(1, param);
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
		return re;
	}
	public static void main(String[] args) throws SQLException {
		Dao dao = new Dao();
		System.out.println(dao.findByName("quan"));
	}
}
