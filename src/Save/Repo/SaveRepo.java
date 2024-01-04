package Save.Repo;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.SQLException;

import Db.Connector;

public class SaveRepo {
	public static boolean save(String study_day, String status, String title, String detail, String class_time, String self_time, String id) {
		
		boolean isSuccess = false;
		//INSERT INTO `name` (`study_day`, `status`, `title`, `detail`, `class_time`, `self_time`) VALUES ('1', '0', '1', '1', '1', '1')
		Connection conn = Connector.makeConn();
		StringBuilder sql = new StringBuilder();
		String name = id;
		sql.append("INSERT INTO $"+name);
		sql.append("(`study_day`, `status`, `title`, `detail`, `class_time`, `self_time`)");
		sql.append(" values ");
		sql.append("(?, ? ,? ,? ,? ,?)");
		
		try {
			PreparedStatement ps = conn.prepareStatement(sql.toString());
			ps.setString(1, study_day);
			ps.setString(2, status);
			ps.setString(3, title);
			ps.setString(4, detail);
			ps.setString(5, class_time);
			ps.setString(6, self_time);
			
			//executeQuery : select 검색
			//executeUpdate : 나머지 검색
			int result = ps.executeUpdate();
			
			if(result > 0) {
				isSuccess = true;
				System.out.println("성공");
			} else {
				System.out.println("실패");
			}
			
		} catch (SQLException e) {
			e.printStackTrace();
		}
		return isSuccess;
	}
	
}
