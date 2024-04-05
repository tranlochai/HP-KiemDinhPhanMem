package bo;

import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.util.ArrayList;

import javax.swing.JOptionPane;
import javax.swing.table.DefaultTableModel;
import javax.swing.table.TableModel;

import dao.dao_ConnectiveSQLServer;

public class bo_menu {
	dao_ConnectiveSQLServer connectiveSQL = new dao_ConnectiveSQLServer();
	public ArrayList<String> list_type_drinks = new ArrayList<String>();
	public String type_drinks_id;
	public float drink_price;
	public ResultSet rs_drinks;
	public ResultSet rs_info_bills;
	public void get_type_drinks() {
		connectiveSQL.connective();
		try {
			String sql = "SELECT TYPE_DRINKS_Name FROM TYPE_DRINKS";
			PreparedStatement cmd = connectiveSQL.cn.prepareStatement(sql);
			ResultSet rs = cmd.executeQuery();
			while(rs.next()) {
				list_type_drinks.add(rs.getString("TYPE_DRINKS_Name"));
			}
		} catch (Exception e) {
			// TODO: handle exception
			e.printStackTrace();
		}
		return;
	}
	/*
	 * lấy danh sách thức uống thuộc nhóm nước(theo id)
	 */
	public void get_drinks(String type_drinks) {
		try {
			get_type_drinks_id(type_drinks);
			String sql = "SELECT * FROM DRINKS WHERE TYPE_DRINKS_Id = ?";
			PreparedStatement cmd = connectiveSQL.cn.prepareStatement(sql);
			cmd.setString(1,type_drinks_id);
			rs_drinks = cmd.executeQuery();
		} catch (Exception e) {
			// TODO: handle exception
			e.printStackTrace();
		}
	}
	public void get_type_drinks_id(String type_drinks) {
		try {
			String sql = "SELECT * FROM TYPE_DRINKS WHERE TYPE_DRINKS_Name = ?";
			PreparedStatement cmd = connectiveSQL.cn.prepareStatement(sql);
			cmd.setString(1,type_drinks);
			ResultSet rs = cmd.executeQuery();
			if(rs.next()) {
				type_drinks_id =  rs.getString("TYPE_DRINKS_Id");
			}
		} catch (Exception e) {
			// TODO: handle exception
			e.printStackTrace();
		}
	}
	/*
	 * menu
	 */
	//thêm thức uống
	public void add_drink(String id, String name, String price) {
		
		try {
			float drink_price = Float.valueOf(price);
			String sql = "INSERT INTO DRINKS (DRINKS_Id, DRINKS_Name, DRINKS_Price, DRINKS_Status, TYPE_DRINKS_Id) \r\n" + 
					"VALUES (?, ?, ?, 1, ?)";
			PreparedStatement cmd = connectiveSQL.cn.prepareStatement(sql);
			cmd.setString(1, id);
			cmd.setString(2, name);
			cmd.setFloat(3, drink_price);
			cmd.setString(4, type_drinks_id);
			cmd.executeUpdate();
			JOptionPane.showMessageDialog(null, "Thêm thức uống thành công", "Thêm Thức Uống",JOptionPane.OK_CANCEL_OPTION);
			return;
		} catch (Exception e) {
			// TODO: handle exception
			e.printStackTrace();
		}
		JOptionPane.showMessageDialog(null, "Có lỗi xảy ra Id Tồn Tại, thêm thức uống không thành công", "Thêm Thức Uống",JOptionPane.OK_CANCEL_OPTION);
	}
	//kiểm tra bills đã gọi thức uống này chưa
	public boolean check_info_bill(String bills_id, String drinks_id) {
		try {
			String sql = "SELECT *\r\n" + 
					"FROM INFO_BILLS\r\n" + 
					"WHERE BILLS_Id = ? AND DRINKS_Id = ?";
			PreparedStatement cmd = connectiveSQL.cn.prepareStatement(sql);
			cmd.setString(1, bills_id);
			cmd.setString(2, drinks_id);
			ResultSet rs = cmd.executeQuery();
			if(rs.next()) return true;
		} catch (Exception e) {
			// TODO: handle exception
			e.printStackTrace();
		}
		return false;
	}
	//tăng số lượng của thức uống đã gọi trước đó nếu gọi tiếp
	public void add_drinks_amount(String bills_id, String drinks_id, int amount) {
		try {
			String sql = "UPDATE INFO_BILLS\r\n" + 
					"SET INFO_Amount = INFO_Amount + ?\r\n" + 
					"WHERE BILLS_Id = ? AND DRINKS_Id = ?";
			PreparedStatement cmd = connectiveSQL.cn.prepareStatement(sql);
			cmd.setInt(1, amount);
			cmd.setString(2, bills_id);
			cmd.setString(3, drinks_id);
			cmd.executeUpdate();
			return;
		} catch (Exception e) {
			// TODO: handle exception
			e.printStackTrace();
		}
		JOptionPane.showMessageDialog(null, "Có lỗi xảy ra");
	}
	//xóa thức uống
	public void delete_drink(String id) {
		try {
			String sql = "DELETE FROM DRINKS WHERE DRINKS_Id = ?";
			PreparedStatement cmd = connectiveSQL.cn.prepareStatement(sql);
			cmd.setString(1, id);
			cmd.executeUpdate();
			JOptionPane.showMessageDialog(null, "Xóa thức uống thành công", "Xóa Thức Uống",JOptionPane.OK_CANCEL_OPTION);
			return;
		} catch (Exception e) {
			// TODO: handle exception
			e.printStackTrace();
		}
		
	}
	//thay đổi dữ liệu thức uống
	public void fix_drink(String id, String name, String price) {
		try {
			float pr = Float.valueOf(price.trim());
			String sql = "UPDATE DRINKS\r\n" + 
					"SET DRINKS_Name = ?, DRINKS_Price = ?\r\n" + 
					"WHERE DRINKS_Id=?";
			PreparedStatement cmd = connectiveSQL.cn.prepareStatement(sql);
			cmd.setString(1, name);
			cmd.setFloat(2, pr);
			cmd.setString(3, id);
			cmd.executeUpdate();
			JOptionPane.showMessageDialog(null, "Cập nhật dữ liệu thức uống thành công", "Cập nhập dữ liệu Thức Uống",JOptionPane.OK_CANCEL_OPTION);
			return;
		} catch (Exception e) {
			// TODO: handle exception
			e.printStackTrace();
		}
	}
	//lấy giá bán của thức uống vừa chọn
	public float get_drinks_price(String drink_name) {
		float price=0;
		try {
			String sql = "SELECT *\r\n" + 
					"FROM DRINKS\r\n" + 
					"WHERE TYPE_DRINKS_Id=? AND DRINKS_Name=?";
			PreparedStatement cmd = connectiveSQL.cn.prepareStatement(sql);
			cmd.setString(1, type_drinks_id);
			cmd.setString(2, drink_name);
			ResultSet rs = cmd.executeQuery();
			if(rs.next()) {
				price = rs.getFloat("DRINKS_Price");
				return price;
			}
		} catch (Exception e) {
			// TODO: handle exception
			e.printStackTrace();
		}
		return price;
	}
	//lấy bills_id
	public String get_bills_id(int table_id) {
		String bills_id=null;
		try {
			String sql = "SELECT *\r\n" + 
					"FROM BILLS\r\n" + 
					"WHERE TABLES_Id=? AND BILLS_TimeOut IS NULL";
			PreparedStatement cmd = connectiveSQL.cn.prepareStatement(sql);
			cmd.setInt(1, table_id);
			ResultSet rs = cmd.executeQuery();
			if(rs.next()) {
				String id = rs.getString("BILLS_Id");
				bills_id = id;
			}
		} catch (Exception e) {
			e.printStackTrace();
			// TODO: handle exception
		}
		return bills_id;
	}
	//lấy drinks_id
	public String get_drinks_id(String drinks_name) {
		String drinks_id;
		try {
			String sql = "SELECT *\r\n" + 
					"FROM DRINKS\r\n" + 
					"WHERE DRINKS_Name = ?";
			PreparedStatement cmd = connectiveSQL.cn.prepareStatement(sql);
			cmd.setString(1, drinks_name);
			ResultSet rs = cmd.executeQuery();
			if(rs.next()) {
				drinks_id = rs.getString("DRINKS_Id");
				return drinks_id;
			}
		} catch (Exception e) {
			// TODO: handle exception
			e.printStackTrace();
		}
		return null;
	}
	//thêm món vào bills
	public void add_info_bills(String bills_id, String drinks_id, int amount, float price) {
		try {
			String sql = "INSERT INTO INFO_BILLS(BILLS_Id, DRINKS_Id, INFO_Amount, INFO_Price)\r\n" + 
					"VALUES (?, ?, ?, ?)";
			PreparedStatement cmd = connectiveSQL.cn.prepareStatement(sql);
			cmd.setString(1, bills_id);
			cmd.setString(2, drinks_id);
			cmd.setInt(3, amount);
			cmd.setFloat(4, price);
			cmd.executeUpdate();
		} catch (Exception e) {
			// TODO: handle exception
			e.printStackTrace();
		}
	}
	//lấy all info_bills của 1 hóa đơn theo mã hóa đơn
	public void get_info_bills(String bills_id) {
		try {
			String sql = "SELECT * \r\n" + 
					"FROM INFO_BILLS\r\n" + 
					"WHERE BILLS_Id = ?";
			PreparedStatement cmd = connectiveSQL.cn.prepareStatement(sql);
			cmd.setString(1, bills_id);
			rs_info_bills = cmd.executeQuery();
		} catch (Exception e) {
			// TODO: handle exception
			e.printStackTrace();
		}
	}
	//lấy drinks_name từ drinks_id
	public String get_drinks_name(String id) {
		String drinks_name = null;
		try {
			String sql = "SELECT *\r\n" + 
					"FROM DRINKS\r\n" + 
					"WHERE DRINKS_Id = ?";
			PreparedStatement cmd = connectiveSQL.cn.prepareStatement(sql);
			cmd.setString(1, id);
			ResultSet rs = cmd.executeQuery();
			if(rs.next())
				drinks_name = rs.getString("DRINKS_Name");
		} catch (Exception e) {
			// TODO: handle exception
			e.printStackTrace();
		}
		return drinks_name;
	}
	//tăng số lượng tiền tương ứng vào hóa đơn của khách hàng khi gọi món
	public void add_bills_money(float money, String bills_id) {
		try {
			String sql = "UPDATE BILLS\r\n" + 
					"SET BILLS_Price = BILLS_Price + ?\r\n" + 
					"WHERE BILLS_Id = ?";
			PreparedStatement cmd = connectiveSQL.cn.prepareStatement(sql);
			cmd.setFloat(1, money);
			cmd.setString(2, bills_id);
			cmd.executeUpdate();
		} catch (Exception e) {
			// TODO: handle exception
			e.printStackTrace();
		}
	}
	//tổng tiền của hóa đơn
	public float get_bill_price(String bills_id) {
		float money = 0;
		try {
			String sql = "SELECT *\r\n" + 
					"FROM BILLS\r\n" + 
					"WHERE BILLS_Id = ?";
			PreparedStatement cmd = connectiveSQL.cn.prepareStatement(sql);
			cmd.setString(1, bills_id);
			ResultSet rs = cmd.executeQuery();
			if(rs.next())
				money = rs.getFloat("BILLS_Price");
		} catch (Exception e) {
			// TODO: handle exception
			e.printStackTrace();
		}
		return money;
	}
	//xóa 1 món khỏi hóa đơn
	public void delete_info_bill(String bills_id, String drinks_id) {
		try {
			String sql = "DELETE FROM INFO_BILLS\r\n" + 
					"WHERE BILLS_Id = ? AND DRINKS_Id = ? ";
			PreparedStatement cmd = connectiveSQL.cn.prepareStatement(sql);
			cmd.setString(1, bills_id);
			cmd.setString(2, drinks_id);
			cmd.executeUpdate();
			JOptionPane.showMessageDialog(null, "Xóa thức uống khỏi hóa đơn thành công");
			return;
		} catch (Exception e) {
			// TODO: handle exception
			e.printStackTrace();
		}
		JOptionPane.showMessageDialog(null, "Xóa thức uống khỏi hóa đơn thất bại");
	}
	//thanh toán hóa đơn
	public void payment(String bills_id, int table_id) {
		try {
			refresh_table(table_id);
			get_timeOUT(bills_id);
		} catch (Exception e) {
			// TODO: handle exception
			e.printStackTrace();
		}
	}
	//set trạng thái bàn
	public void refresh_table(int table_id) {
		try {
			String sql = "UPDATE TABLES\r\n" + 
					"SET TABLES_Status = 0\r\n" + 
					"WHERE TABLES_Id = ?";
			PreparedStatement cmd = connectiveSQL.cn.prepareStatement(sql);
			cmd.setInt(1, table_id);
			cmd.executeUpdate();
		} catch (Exception e) {
			// TODO: handle exception
			e.printStackTrace();
		}
	}
	//set update timeinOUT
	public void get_timeOUT(String bills_id) {
		try {
			String sql = "UPDATE BILLS\r\n" + 
					"SET BILLS_TimeOut = GETDATE()\r\n" + 
					"WHERE BILLS_Id = ?";
			PreparedStatement cmd = connectiveSQL.cn.prepareStatement(sql);
			cmd.setString(1, bills_id);
			cmd.executeUpdate();
		} catch (Exception e) {
			// TODO: handle exception
			e.printStackTrace();
		}
	}
}
