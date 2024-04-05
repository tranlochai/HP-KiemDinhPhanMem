package bo;

import java.awt.Button;
import java.awt.Color;
import java.awt.Font;
import java.awt.event.MouseEvent;
import java.awt.event.MouseListener;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.Random;

import javax.swing.JButton;
import javax.swing.JOptionPane;
import javax.swing.JPanel;

import dao.dao_ConnectiveSQLServer;
import view.view_bill;

public class bo_setzones {

	public ArrayList<JButton> list_button_tables = new ArrayList<JButton>();
	public int sl=0;
	public int x, y, width, height, table;
	public int component_x=214, component_y=66, component_width=1262, component_height=574;
	JButton button_tables;
	public String zones_id, zones_name;
	public String STAFFS_Id;
	public void set_staff_id(String id) {
		// TODO Auto-generated constructor stub
		STAFFS_Id = id;
	}
	public dao_ConnectiveSQLServer dao_connective = new dao_ConnectiveSQLServer();
	public void zones_table(String zones_name) {
		try {
			this.zones_name = zones_name;
			table=0;
			reset_size();
			reomove_list_tables();
			list_button_tables.removeAll(list_button_tables);
			if(get_zones_id(zones_name))
				get_zones_tables();
		} catch (Exception e) {
			// TODO: handle exception
			e.printStackTrace();
		}
	}
	/*
	 * hàm tìm zones_id dựa trên zones_name
	 */
	public boolean get_zones_id(String zones_name) {
		try {
			String sql = "SELECT ZONES_Id FROM ZONES WHERE ZONES_Name = ?";
			PreparedStatement cmd = dao_connective.cn.prepareStatement(sql);
			cmd.setString(1, zones_name);
			ResultSet rs = cmd.executeQuery();
			if(rs.next()) {
				zones_id = rs.getString("ZONES_Id");
				return true;
			}
			
		} catch (Exception e) {
			// TODO: handle exception
			e.printStackTrace();
		}
		return false;
	}
	/*
	 * hàm tìm tất cả các bàn(tables) trong khu vực đó(zones_id)
	 */
	public void get_zones_tables() {
		try {
			String sql = "SELECT * FROM TABLES WHERE ZONES_Id = ?";
			PreparedStatement cmd = dao_connective.cn.prepareStatement(sql);
			cmd.setString(1, zones_id);
			ResultSet rs = cmd.executeQuery();
			while(rs.next()) {
				table++;
				button_tables = new JButton();
				String table_id = String.valueOf(rs.getInt("TABLES_Id"));
				String table_name = rs.getString("TABLES_NAME");
				button_tables.setName(table_id);
				button_tables.setText(table_name);
				button_tables.setBounds(x, y, width, height);
				button_tables.setFont(new Font("Tahoma", Font.BOLD, 16));
				button_tables.setBackground(Color.LIGHT_GRAY);
				if(!table_check(Integer.valueOf(button_tables.getName())))
					button_tables.setBackground(Color.GREEN);;
				set_event(button_tables);
				list_button_tables.add(button_tables);
				
				set_size();
			}

		} catch (Exception e) {
			// TODO: handle exception
			e.printStackTrace();
		}
	}
	/*
	 * add các tables và id tables của zones vào button_tables
	 */
	public void reomove_list_tables() {
		try {
			for(int i=0; i<list_button_tables.size(); i++) {
				list_button_tables.get(i).setVisible(false);
			}
		} catch (Exception e) {
			e.printStackTrace();
			// TODO: handle exception
		}
	}
	/*
	 * thay đổi x, y, width, height của button chứa table về giá trị ban đầu
	 */
	public void reset_size() {
		x=226;
		y=81;
		width=179;
		height=68;
		
	}
	/*
	 * thay đổi x, y, width, height của button chứa table tiếp theo
	 */
	public void set_size() {
		if(table%6==0) {
			y += (height + 25);
			x = 226;
		}
		else {
			x += (width + 25);
		}
	}
	/*
	 * set event cho tất cả các button tables
	 */
	public void set_event(JButton table) {
			MouseListener mouseEvent  = new MouseListener() {
				@Override
				public void mouseReleased(MouseEvent e) {
					// TODO Auto-generated method stub
				}
				
				@Override
				public void mousePressed(MouseEvent e) {
					// TODO Auto-generated method stub
					
				}
				
				@Override
				public void mouseExited(MouseEvent e) {
					// TODO Auto-generated method stub
					
				}
				
				@Override
				public void mouseEntered(MouseEvent e) {
					// TODO Auto-generated method stub
					
				}
				
				@Override
				public void mouseClicked(MouseEvent e) {
					// TODO Auto-generated method stub
					int i=0;
					int table_id = Integer.valueOf(table.getName());
					if(table_check(table_id)) {
						i = JOptionPane.showConfirmDialog(null, "Mở "+table.getText(), "Mở Bàn", JOptionPane.YES_NO_OPTION);
					if(i==0) {
						table_use(table_id);
						table.setBackground(Color.GREEN);
						System.out.println("staff: "+STAFFS_Id);
						add_bills(table_id);
						
					}
					}
					if(i==0) {
						view_bill jframe_bill = new view_bill(table, zones_name);
						jframe_bill.setLocationRelativeTo(null);
						jframe_bill.setVisible(true);
					}
				}
			};
			table.addMouseListener(mouseEvent);
		}
	/*
	 * table
	 */
	//kiểm tra bàn có ai đang sử dụng hay không /
	public boolean table_check(int table_id) {
		try {
			String sql = "SELECT * \r\n" + 
					"FROM TABLES\r\n" + 
					"WHERE TABLES_Id = ?";
			PreparedStatement cmd = dao_connective.cn.prepareStatement(sql);
			cmd.setInt(1, table_id);
			ResultSet rs = cmd.executeQuery();
			if(rs.next()) {
				boolean status = rs.getBoolean("TABLES_Status");
				if(!status)
					return true;
			}
		} catch (Exception e) {
			e.printStackTrace();
			// TODO: handle exception
		}
		return false;
	}
	//chuyển bàn sang trạng thái có người dùng
	public void table_use(int table_id) {
		try {
			String sql = "UPDATE TABLES\r\n" + 
					"SET TABLES_Status=1\r\n" + 
					"WHERE TABLES_Id=?";
			PreparedStatement cmd = dao_connective.cn.prepareStatement(sql);
			cmd.setInt(1, table_id);
			cmd.executeUpdate();
		} catch (Exception e) {
			e.printStackTrace();
			// TODO: handle exception
		}
	}
	//thêm n bàn
	public void add_table(int count) {
		try {
			int count_tables = get_count_tables();
			int count_all_tables = get_count_id();
			System.out.println(count_tables);
			System.out.println(count_all_tables);
			for(int i=1; i<=count; i++) {
				String sql = "INSERT INTO TABLES(TABLES_Id, TABLES_NAME, TABLES_Status, ZONES_Id)\r\n" + 
						"VALUES (?, ?, 0, ?);";
				PreparedStatement cmd = dao_connective.cn.prepareStatement(sql);
				cmd.setInt(1, (count_all_tables+i));
				cmd.setString(2, "Bàn "+(count_tables+i));
				cmd.setString(3, zones_id);
				cmd.executeUpdate();
			}
		} catch (Exception e) {
			// TODO: handle exception
			e.printStackTrace();
		}
	}
	//tìm tổng số bàn(trong khu vực đó){
	public int get_count_tables() {
		int count=0;
		try {
			String sql = "SELECT count(TABLES_Id)as COUNT\r\n" + 
					"FROM TABLES\r\n" + 
					"WHERE ZONES_Id = ?";
			PreparedStatement cmd = dao_connective.cn.prepareStatement(sql);
			cmd.setString(1, zones_id);
			ResultSet rs = cmd.executeQuery();
			if(rs.next()) {
				count  = rs.getInt("COUNT");
			}
		} catch (Exception e) {
			// TODO: handle exception
		}
		return count;
	}
	//tìm tổng số bàn của quán(id lớn nhất = tổng số bàn){
	public int get_count_id() {
		int count=0;
		try {
			String sql = "SELECT count(TABLES_Id) as COUNT\r\n" + 
					"FROM TABLES";
			PreparedStatement cmd = dao_connective.cn.prepareStatement(sql);
			ResultSet rs = cmd.executeQuery();
			if(rs.next()) {
				count  = rs.getInt("COUNT");
			}
		} catch (Exception e) {
			// TODO: handle exception
		}
		return count;
	}
	//tạo hóa đơn
	public void add_bills(int table_id) {
		try {
			String sql = "insert into BILLS(BILLS_Id,BILLS_TimeIn,STAFFS_Id,BILLS_Price,TABLES_Id) \r\n" + 
					"VALUES (?,GETDATE(),?,0,?)";
			PreparedStatement cmd = dao_connective.cn.prepareStatement(sql);
			Random generator = new Random();
			String bills_id = "BL" + (String.valueOf(generator.nextInt(10000)));
			cmd.setString(1, bills_id);
			cmd.setString(2, STAFFS_Id);
			cmd.setInt(3, table_id);
			cmd.executeUpdate();
		} catch (Exception e) {
			// TODO: handle exception
			e.printStackTrace();
		}
	}
	
}

