package Db;

import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.PreparedStatement;
import java.sql.SQLException;

public class Connector {
	
	public static Connection makeConn() {//db연결됨
		
		Connection con = null;
		String id = "root";
		String pw = "";
		String url = "jdbc:mysql://localhost/classmanagement";
		
		try {
			con = DriverManager.getConnection(url, id, pw);
			System.out.println(con);
		} catch (SQLException e) {
			e.printStackTrace();
		}
		return con;
		
	}

}
