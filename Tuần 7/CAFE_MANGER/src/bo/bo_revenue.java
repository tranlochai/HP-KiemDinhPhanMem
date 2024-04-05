package bo;

import java.sql.PreparedStatement;
import java.sql.ResultSet;

import dao.dao_ConnectiveSQLServer;

public class bo_revenue {
	dao_ConnectiveSQLServer connectiveSQL = new dao_ConnectiveSQLServer();
	public ResultSet rs_bills;
	//hàm kiểm tra doanh thu, chỉ giành cho chức vụ Quản Lí
	public void revenue() {
		try {
			connectiveSQL.connective();
			String sql = "SELECT *\r\n" + 
					"FROM BILLS\r\n" + 
					"WHERE MONTH(BILLS_TimeOut) = MONTH(GETDATE()) AND  YEAR(BILLS_TimeOut) = YEAR(GETDATE())";
			PreparedStatement cmd = connectiveSQL.cn.prepareStatement(sql);
			rs_bills = cmd.executeQuery(); 
		} catch (Exception e) {
			// TODO: handle exception
			e.printStackTrace();
		}
	}
	//kiểm tra chức vụ
	public boolean check_pos(String id) {
		try {
			connectiveSQL.connective();
			String sql = "SELECT STAFFS_Id\r\n" + 
					"FROM STAFFS\r\n" + 
					"WHERE STAFFS_Position = N'Quản Lí' and STAFFS_Id = ?";
			PreparedStatement cmd = connectiveSQL.cn.prepareStatement(sql);
			cmd.setString(1, id);
			ResultSet rs = cmd.executeQuery();
			if(rs.next())
				return true;
		} catch (Exception e) {
			// TODO: handle exception
			e.printStackTrace();
		}
		return false;
	}
	//hàm trả về tổng doanh thu tháng
		public float month_revenue() {
			float month = 0;
			try {
				connectiveSQL.connective();
				String sql = "SELECT SUM(BILLS_Price)as sum\r\n" + 
						"FROM BILLS\r\n" + 
						"WHERE MONTH(BILLS_TimeOut) = MONTH(GETDATE()) AND  YEAR(BILLS_TimeOut) = YEAR(GETDATE())";
				PreparedStatement cmd = connectiveSQL.cn.prepareStatement(sql);
				ResultSet rs = cmd.executeQuery();
				if(rs.next())
					month = rs.getFloat("sum");
			} catch (Exception e) {
				// TODO: handle exception
				e.printStackTrace();
			}
			return month;
		}
}
