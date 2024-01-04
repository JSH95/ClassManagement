package Modify.Repo;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.SQLException;

import Db.Connector;


public class ModifyRepo {
	public static boolean modify(String study_day, String status, String title, String detail, String class_time, String self_time, String id) {
			
		boolean isSuccess = false;
		Connection conn = Connector.makeConn();
		//UPDATE `name` SET `study_day` = '2023-02-06', `status` = '1', `title` = '자바 첫 수업up', `detail` = '자바에대한 첫수업을 진행up', `class_time` = '2', `self_time` = '1' WHERE `name`.`study_day` = '2023-02-05'
		StringBuilder sql = new StringBuilder();
		String name = id;
		sql.append("UPDATE $"+ name);
		sql.append(" SET ");
		sql.append("`study_day` = ?, ");
		sql.append("`status` = ?, ");
		sql.append("`title` = ?, ");
		sql.append("`detail` = ?, ");
		sql.append("`class_time` = ?, ");
		sql.append("`self_time` = ?");
		
				
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
