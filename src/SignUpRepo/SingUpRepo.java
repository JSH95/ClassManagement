package SignUpRepo;


import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;

import Db.Connector;

public class SingUpRepo {
	
	public boolean singup(String id, String password, String name) {
		boolean singFlag = false;
		Connection conn = Connector.makeConn();
		StringBuilder sql = new StringBuilder();	
//INSERT INTO `admininfo` (`id`, `password`, `is_active`, `name`) VALUES ('fhqht9477', 'k9114339', '0', '정성하')
		sql.append("INSERT INTO `admininfo`");
		sql.append("(`id`, `password`, `is_active`, `name`)");
		sql.append(" values ");
		sql.append("(?, ?, 0, ?)");
		try {
			PreparedStatement ps = conn.prepareStatement(sql.toString());
			ps.setString(1, id);
			ps.setString(2, password);
			ps.setString(3, name);
			
			int result = ps.executeUpdate();
			
			if(result > 0) {
				singFlag = true;
				System.out.println("성공");
			} else {
				//if (e1.getMessage().contains("PRIMARY")) {
				//	JOptionPane.showMessageDialog(null, "아이디 중복!", "아이디 중복 오류", 1);
				System.out.println("실패");
			}
			
		} catch (SQLException e) {
			e.printStackTrace();
		}
		return singFlag;
	}
	
	public boolean idCheck(String id2) {
		
		boolean idflag = false;
		
		Connection conn = Connector.makeConn();
		
		String sql = "select* from admininfo where id = ?";
		
		try {
			PreparedStatement ps = conn.prepareStatement(sql);
			ps.setString(1, id2);
			ResultSet rs = ps.executeQuery();
						
			int count = 0;
			
			while(rs.next()) { //정보가 맞다면 데이터 갯수 만큼 증가
				count++;
			}
			if(count > 0) {
				idflag = true;
			}
						
		} catch (SQLException e) {
			e.printStackTrace();
		}
		
		return idflag;
		
	}
	public boolean dbCreate(String id) {  //회원 Db 생성
		boolean dbflag = false;
		Connection conn = Connector.makeConn();
		StringBuilder sql = new StringBuilder();
		
		//CREATE TABLE id (`study_day` VARCHAR(10) NOT NULL , `status` CHAR(2) NOT NULL DEFAULT '0' , `title` VARCHAR(255) NOT NULL , `detail` VARCHAR(2000) NOT NULL , `class_time` VARCHAR(10) NOT NULL , `self_time` VARCHAR(10) NOT NULL)
		String name = id;
		sql.append("CREATE TABLE $"+ name);
		sql.append(" (`study_day` VARCHAR(10) NOT NULL , `status` CHAR(2) NOT NULL DEFAULT '0' , `title` VARCHAR(255) NOT NULL , `detail` VARCHAR(2000) NOT NULL , `class_time` VARCHAR(10) NOT NULL , `self_time` VARCHAR(10) NOT NULL)");		
		
		try {
			PreparedStatement ps = conn.prepareStatement(sql.toString());
			int result = ps.executeUpdate();
			
			if(result > 0) {
				dbflag = true;
				System.out.println("실패");
			} else {
				System.out.println("성공");
			}
			
		} catch (SQLException e) {
			e.printStackTrace();
		}
		return dbflag;	
	}

}
