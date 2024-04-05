package bo;

import java.sql.PreparedStatement;
import java.sql.ResultSet;

import dao.dao_ConnectiveSQLServer;



public class bo_Login {
	
	//gọi hàm kết nối SQLserver - SQL database
	dao_ConnectiveSQLServer dao_connective = new dao_ConnectiveSQLServer();
	
	/*
	 * hàm login
	 * đúng khi hàm Login_account và Login_postion đều đúng
	 */
	public Boolean Login(String id, String password, String position) {
		try {
			dao_connective.connective();
			if(Login_account(id, password) && Login_postion(id, position))
				return true;
		} catch (Exception e) {
			// TODO: handle exception
			e.printStackTrace();
		}
		return false;
	}
	
	//hàm kiểm tra account(id && password) có tồn tại hay không?
	public Boolean Login_account(String id, String password) {
		try {
			String sql = "SELECT * FROM LOGIN WHERE LOGIN_Id = ? AND LOGIN_Password = ?";
			PreparedStatement cmd = dao_connective.cn.prepareStatement(sql);
			cmd.setString(1, id);
			cmd.setString(2, password);
			ResultSet rs = cmd.executeQuery();
			if(rs.next())
				return true;
		} catch (Exception e) {
			// TODO: handle exception
			e.printStackTrace();
		}
		return false;
	}
	
	//Hàm kiểm tra chức vụ đó có tồn tại id đăng nhập vào hay không ?
	public Boolean Login_postion(String id, String position) {
		try {
			String sql = "SELECT * FROM STAFFS WHERE STAFFS_Id = ? AND STAFFS_Position = ? ";
			PreparedStatement cmd = dao_connective.cn.prepareStatement(sql);
			cmd.setString(1, id);
			cmd.setString(2, position);
			ResultSet rs = cmd.executeQuery();
			if(rs.next())
				return true;
		} catch (Exception e) {
			// TODO: handle exception
			e.printStackTrace();
		}
		return false;
	}
	
}
