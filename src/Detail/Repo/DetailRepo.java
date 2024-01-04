package Detail.Repo;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;

import Db.Connector;

public class DetailRepo {

	public static String[] detail(String day, String id) { 
		Connection conn = Connector.makeConn();
		String[] classInfo = new String[6];
		String name = id;
		String sql = "select * from $" + name + " where study_day = ? ";
		
		try {
			PreparedStatement ps = conn.prepareStatement(sql);
		
			ps.setString(1, day);
			ResultSet rs = ps.executeQuery();
		
			while(rs.next()) { //정보가 맞다면 데이터 갯수 만큼 증가
				String Study_day = rs.getString(1);
				String status = rs.getString(2);
				String title = rs.getString(3);
				String detail = rs.getString(4);
				String class_time = rs.getString(5);
				String self_time = rs.getString(6);
			
				classInfo[0]= Study_day;
				classInfo[1]= status;
				classInfo[2]= title;
				classInfo[3]= detail;
				classInfo[4]= class_time;
				classInfo[5]= self_time;
				
			}
			
		} catch (SQLException e) {
			
			e.printStackTrace();
		}
		return classInfo;
	}
		
}
