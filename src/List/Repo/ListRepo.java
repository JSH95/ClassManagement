package List.Repo;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;

import Db.Connector;
import sun.font.StrikeCache;

public class ListRepo {

	public String[][] list(String id) {
		Connection conn = Connector.makeConn();
		String[][] result = new String[0][0];
		String name = id; //id별 db불러오기
		String sql = "select* from $" + name;
		try {
			PreparedStatement ps = conn.prepareStatement(sql,
				ResultSet.TYPE_SCROLL_SENSITIVE,
				ResultSet.CONCUR_UPDATABLE);
			
			ResultSet rs = ps.executeQuery();
			rs.last();
			int count = rs.getRow();
			rs.beforeFirst();
			
			result = new String[count][5];
			int i = 0;
			while(rs.next()) { //정보가 맞다면 데이터 갯수 만큼 증가
				String[] ClassInfo = new String[5];
				
				String Study_day = rs.getString(1); //String.valueOf
				String status = rs.getString(2);
				String title = rs.getString(3);
				String class_time = rs.getString(5);
				String self_time = rs.getString(6);
				
				ClassInfo[0]= Study_day;
				ClassInfo[1]= status;
				ClassInfo[2]= title;
				ClassInfo[3]= class_time;
				ClassInfo[4]= self_time;
				
				result[i] = ClassInfo;
				i++;
			}
			
		} catch (SQLException e) {
			e.printStackTrace();
		}
		return result;
	}
}

