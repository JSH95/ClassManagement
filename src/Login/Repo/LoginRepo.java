package Login.Repo;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;

import Db.Connector;
import javafx.stage.StageStyle;
import sun.applet.Main;

public class LoginRepo {
	//서버에서 로그인 정보 대입
	public boolean login(String id, String password) {
		
		boolean loginFlag = false;
		
		Connection conn = Connector.makeConn();
		
		String sql = "select* from admininfo where id = ? and password = ?";
		
		try {
			PreparedStatement ps = conn.prepareStatement(sql);
			ps.setString(1, id);
			ps.setString(2, password);
			ResultSet rs = ps.executeQuery();
						
			int count = 0;
			
			while(rs.next()) { //정보가 맞다면 데이터 갯수 만큼 증가
				count++;
			}
			if(count > 0) {
				loginFlag = true;
			}
						
		} catch (SQLException e) {
			e.printStackTrace();
		}
		
		return loginFlag;
		
	}
	
}
