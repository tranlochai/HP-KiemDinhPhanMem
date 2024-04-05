package bo;

import java.sql.PreparedStatement;
import java.sql.ResultSet;

import javax.swing.JOptionPane;

import dao.dao_ConnectiveSQLServer;

public class bo_changepassword {
	//gọi hàm kết nối SQLserver - SQL database
	dao_ConnectiveSQLServer dao_connective = new dao_ConnectiveSQLServer();
	/*
	 * hàm đổi mật khẩu
	 * kiểm tra 2 điều kiện trước khi đổi mật khẩu:
	 * 1. mật khẩu mới phải trùng với mật khẩu xác nhận lại
	 * 2. mật khẩu cũ phải đúng
	 */
	public boolean changepassword(String id, String password, String newpassword1, String newpassword2) {
		try {
			dao_connective.connective();
			if(Kiemtra_newpassword(newpassword1, newpassword2))
				if(Kiemtra_password(id, password)) {
					String sql = "UPDATE LOGIN SET LOGIN_Password = ? WHERE LOGIN_Id = ?";
					PreparedStatement cmd = dao_connective.cn.prepareStatement(sql);
					cmd.setString(1, newpassword1);
					cmd.setString(2, id);
					cmd.executeUpdate();
					JOptionPane.showMessageDialog(null, "Đổi mật khẩu thành công!");
					return true;
				}
		} catch (Exception e) {
			// TODO: handle exception
			e.printStackTrace();
		}
		return false;
	}
	/*
	 * hàm kiểm tra xem 2 mật khẩu mới nhập vào có trùng nhau chưa ?
	 */
	public boolean Kiemtra_newpassword(String newpassword1, String newpassword2) {
		try {
			if(newpassword1.equals(newpassword2)) return true;
		} catch (Exception e) {
			// TODO: handle exception
			e.printStackTrace();
		}
		JOptionPane.showMessageDialog(null, "Nhập mật khẩu mới và nhập lại mật khẩu không trùng nhau!");
		return false;
	}
	
	/*
	 * hàm kiểm tra lại mật khẩu cũ đúng hay chưa ?
	 */
	public boolean Kiemtra_password(String id, String password) {
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
		JOptionPane.showMessageDialog(null, "Nhập sai mật khẩu!");
		return false;
	}
	
}
