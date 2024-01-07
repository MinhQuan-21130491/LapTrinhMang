package test;
import java.rmi.RemoteException;
import java.rmi.server.UnicastRemoteObject;
import java.security.*;
import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.List;

public class Dao extends UnicastRemoteObject implements ISearch {

	private Connection conn=null;
	public void closeConnection() throws SQLException {
		if (conn!=null) conn.close();
	}
	public Dao() throws RemoteException{
		try {
			String driver = "net.ucanaccess.jdbc.UcanaccessDriver";
			String url = "jdbc:ucanaccess://F:\\student.mdb";
			Class.forName(driver);
			conn = DriverManager.getConnection(url);
		} catch (Exception e) {
			e.printStackTrace();
		}
	}

	public String findById(String id) throws RemoteException{
		try {
			int stId = Integer.parseInt(id);
			String sql = "select * from student where ID = ?";
			PreparedStatement stat = conn.prepareStatement(sql);
			stat.setInt(1, stId);
			ResultSet rs = stat.executeQuery();
			String res = null;
			if (rs.next()) {
				res = rs.getString("ID") + "\t" + rs.getString("name");
			}
			return res;	
		} catch (SQLException e) {
			throw new RemoteException(e.getMessage());
		}
	}
	public String findByName(String partName) throws RemoteException{
		try {
			String sql = "select * from student where name like ?";
			PreparedStatement stat = conn.prepareStatement(sql);
			stat.setString(1, "%" + partName);
			ResultSet rs = stat.executeQuery();
			String res = "";
			while (rs.next()) {
				res += rs.getString("ID") + "\t" + rs.getString("name") + "\n";
			}
			if (res.isEmpty()) res = null;
			return res;	
		} catch (SQLException e) {
			throw new RemoteException(e.getMessage());
		}
	}
}
