package Delete.Repo;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.SQLException;


import Db.Connector;

public class DeleteRepo {
	public static boolean delete(String day, String id) {
		boolean isSuccess = false;
		Connection conn = Connector.makeConn();
		//정말로 "DELETE FROM book WHERE `book`.`no` = 13"을/를 실행하시겠습니까?
		//StringBuilder sql = new StringBuilder(); //하나의 값만 받으 때는 build 사용 안해도 됨
		String name = id;
		String sql1 = "DELETE FROM $" + name + " WHERE `$"+ name + "`.`study_day` = ?";
		try {
			PreparedStatement ps = conn.prepareStatement(sql1.toString());
			ps.setString(1, day);
			
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
