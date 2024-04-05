package view;

import java.awt.BorderLayout;
import java.awt.EventQueue;

import javax.swing.JFrame;
import javax.swing.JPanel;
import javax.swing.border.EmptyBorder;

import bo.bo_menu;


import javax.swing.JLabel;
import javax.swing.JOptionPane;
import javax.swing.SwingConstants;
import java.awt.Font;
import javax.swing.JComboBox;
import java.awt.event.WindowAdapter;
import java.awt.event.WindowEvent;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.Map;

import javax.swing.JTabbedPane;
import javax.swing.JScrollPane;
import javax.swing.JTable;
import javax.swing.table.DefaultTableModel;
import javax.swing.table.TableColumnModel;

import Bean.Drinks;

import java.awt.event.MouseAdapter;
import java.awt.event.MouseEvent;
import java.awt.event.ActionListener;
import java.awt.event.ActionEvent;
import javax.swing.JButton;
import javax.swing.JTextField;

public class view_menu extends JFrame {

	private JPanel contentPane;
	public static String zones_id=null;
	private bo_menu action_menu = new bo_menu();
	private ArrayList<String> list_type_drinks;
	
	public Map<String, ArrayList<Drinks>> ds_drinks = new HashMap<String, ArrayList<Drinks>>();
	
	
	
	public JComboBox cb_type_drinks;
	public JTable table;
	public JTextField tf_id;
	public JTextField tf_drinks;
	public JTextField tf_price;
	/**
	 * Launch the application.
	 */
	public static void main(String[] args) {
		EventQueue.invokeLater(new Runnable() {
			public void run() {
				try {
					view_menu frame = new view_menu(zones_id);
					frame.setVisible(true);
				} catch (Exception e) {
					e.printStackTrace();
				}
			}
		});
	}
	/*
	 * add type drinks vào cb_type_drinks
	 */
	public void add_cb_type_drinks() {
		action_menu.get_type_drinks();
		
		
		for(String data : action_menu.list_type_drinks) {
			cb_type_drinks.addItem(data);
			action_menu.get_drinks(data);
			ArrayList<Drinks> ds = new ArrayList<Drinks>();
			
			try {
				while(action_menu.rs_drinks.next()) {
					Drinks temp = new Drinks(action_menu.rs_drinks.getString("DRINKS_Id"),action_menu.rs_drinks.getString("DRINKS_Name"),action_menu.rs_drinks.getString("DRINKS_Price"));
					ds.add(temp);
				}
			} catch (SQLException e) {
				e.printStackTrace();
			}
			
			ds_drinks.put(data, ds);
		}
	}
	/*
	 * tạo mới dữ liệu trong danh sách thức uống
	 */
	public void refresh_table() {
		String type_drinks = (cb_type_drinks.getSelectedItem().toString()).trim();
		System.out.println(cb_type_drinks.getSelectedItem().toString());
		action_menu.get_drinks(type_drinks);
		try {
			DefaultTableModel tableModel = new DefaultTableModel();
			tableModel.addColumn("STT");
			tableModel.addColumn("ID");
			tableModel.addColumn("THỨC UỐNG");
			tableModel.addColumn("GIÁ BÁN");
			
			int i=1;
			while(action_menu.rs_drinks.next()) {
				String[] data = new String[4];
				data[0] = String.valueOf(i);
				data[1] = action_menu.rs_drinks.getString("DRINKS_Id");
				data[2] = action_menu.rs_drinks.getString("DRINKS_Name");
				data[3] = action_menu.rs_drinks.getString("DRINKS_Price");
				tableModel.addRow(data);
				i++;
			}
			table.setModel(tableModel);
			table.setAutoResizeMode(JTable.AUTO_RESIZE_NEXT_COLUMN);
			table.getColumnModel().getColumn(0).setPreferredWidth(30);
			table.getColumnModel().getColumn(0).setMaxWidth(30);
			table.getColumnModel().getColumn(1).setMaxWidth(200);
			table.getColumnModel().getColumn(3).setPreferredWidth(120);
			table.getColumnModel().getColumn(3).setMaxWidth(1500);
		} catch (Exception e) {
			// TODO: handle exception
			e.printStackTrace();
		}
	}
	public Drinks getindex_id(String id, ArrayList<Drinks> temp) {
		for (Drinks dr : temp) {
			if(dr.getId().equals(id)) {
				return dr.toStringDrink();
			}
		}
		return null;
	}
	/**
	 * Create the frame.
	 */
	public view_menu(String id) {
		setTitle("CAFFE_MANAGER");
		addWindowListener(new WindowAdapter() {
			@Override
			public void windowOpened(WindowEvent e) {
				add_cb_type_drinks();
				refresh_table();
			}
		});
		setBounds(100, 100, 926, 700);
		contentPane = new JPanel();
		contentPane.setBorder(new EmptyBorder(5, 5, 5, 5));
		setContentPane(contentPane);
		contentPane.setLayout(null);
		
		JLabel lblNhmThcUng = new JLabel("Nh\u00F3m Th\u1EE9c U\u1ED1ng:");
		lblNhmThcUng.setHorizontalAlignment(SwingConstants.CENTER);
		lblNhmThcUng.setFont(new Font("Tahoma", Font.BOLD, 16));
		lblNhmThcUng.setBounds(7, 60, 254, 42);
		contentPane.add(lblNhmThcUng);
		
		cb_type_drinks = new JComboBox();
		cb_type_drinks.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				refresh_table();
			}
		});
		
		cb_type_drinks.setFont(new Font("Tahoma", Font.BOLD, 16));
		cb_type_drinks.setBounds(221, 61, 269, 31);
		contentPane.add(cb_type_drinks);
		
		JLabel lblMenu = new JLabel("TH\u1EF0C \u0110\u01A0N");
		lblMenu.setHorizontalAlignment(SwingConstants.CENTER);
		lblMenu.setFont(new Font("Tahoma", Font.BOLD, 18));
		lblMenu.setBounds(272, 9, 254, 42);
		contentPane.add(lblMenu);
		
		JTabbedPane tabbedPane = new JTabbedPane(JTabbedPane.TOP);
		tabbedPane.setFont(new Font("Tahoma", Font.BOLD, 16));
		tabbedPane.setBounds(60, 112, 466, 431);
		contentPane.add(tabbedPane);
		
		JScrollPane scrollPane = new JScrollPane();
		tabbedPane.addTab("Danh Sách Thực Đơn", null, scrollPane, null);
		
		table = new JTable();
		table.addMouseListener(new MouseAdapter() {
			@Override
			public void mouseClicked(MouseEvent e) {
				
				int i = table.getSelectedRow();
				tf_id.setText((String)table.getValueAt(i, 1));
				tf_drinks.setText((String)table.getValueAt(i, 2));
				tf_price.setText((String)table.getValueAt(i, 3));
			}
		});
		table.setFont(new Font("Tahoma", Font.PLAIN, 14));
		table.setModel(new DefaultTableModel(
			new Object[][] {
			},
			new String[] {
				"STT", "ID", "TH\u1EE8C U\u1ED0NG", "GI\u00C1 B\u00C1N"
			}
		));
		table.getColumnModel().getColumn(0).setPreferredWidth(30);
		table.getColumnModel().getColumn(0).setMaxWidth(30);
		table.getColumnModel().getColumn(1).setMaxWidth(200);
		table.getColumnModel().getColumn(3).setMaxWidth(1500);
		scrollPane.setViewportView(table);
		
		JButton bt_add = new JButton("THÊM");
		bt_add.addActionListener(new ActionListener() { 
			public void actionPerformed(ActionEvent e) {
				String id = tf_id.getText().toString().trim();
				String name = tf_drinks.getText().toString().trim();
				String price = tf_price.getText().toString().trim();
				System.out.println(id);
				
				
					if(id.equals("")) {
						JOptionPane.showMessageDialog(null, "ID chưa được nhập", "Thêm Thức Uống",JOptionPane.OK_CANCEL_OPTION);
						return;
					}
					if(!id.matches("DR\\d{3}")) {
						JOptionPane.showMessageDialog(null, "ID chưa đúng định đạng DR__", "Thêm Thức Uống",JOptionPane.OK_CANCEL_OPTION);
						return;
					}
					if(name.equals("")) {
						JOptionPane.showMessageDialog(null, "Tên thức uống chưa được nhập", "Thêm Thức Uống",JOptionPane.OK_CANCEL_OPTION);
						return;
					}
					if(price.equals("")) {
						JOptionPane.showMessageDialog(null, "Giá bán thức uống chưa được nhập", "Thêm Thức Uống",JOptionPane.OK_CANCEL_OPTION);
						return;
					}
					if(price.matches("\\d")){
						JOptionPane.showMessageDialog(null, "Giá bán thức uống phải là số", "Thêm Thức Uống",JOptionPane.OK_CANCEL_OPTION);
						return;
					}
					
					
				int i = JOptionPane.showConfirmDialog(null, "Bạn muốn thêm thức uống "+name+" với giá bán "+price, "Thêm Thức Uống", JOptionPane.YES_NO_OPTION);
				if(i == 0) {
					ArrayList<Drinks> temp = ds_drinks.get(cb_type_drinks.getSelectedItem());
					for (int j = 0; j < temp.size(); j++) {
						System.out.println("id:" + id + ", " + temp.get(j).getId());
						if(temp.get(j).getId().equals(id)) {
							JOptionPane.showMessageDialog(null, "Loại Thức Uống Này Đã Có!", "Thêm Thức Uống",JOptionPane.OK_CANCEL_OPTION);
							return;
						}
					}
					
					ds_drinks.get(cb_type_drinks.getSelectedItem()).add(new Drinks(id, name, price));
					action_menu.add_drink(id, name, price);
					refresh_table();
				}
			
			}
		});
		bt_add.setFont(new Font("Tahoma", Font.BOLD, 16));
		bt_add.setBounds(60, 566, 139, 52);
		contentPane.add(bt_add);
		
		JButton bt_delete = new JButton("XÓA");
		bt_delete.addActionListener(new ActionListener() { 
			public void actionPerformed(ActionEvent e) {
				String id = (tf_id.getText().toString()).trim();
				String name = (tf_drinks.getText().toString()).trim();
				if(id.equals("")) {
					JOptionPane.showMessageDialog(null, "ID chưa nhập!", "Xóa Thức Uống",JOptionPane.OK_CANCEL_OPTION);
					return;
				}
				if(!id.matches("DR\\d{3}")) {
					JOptionPane.showMessageDialog(null, "ID định dạng sai!", "Xóa Thức Uống",JOptionPane.OK_CANCEL_OPTION);
					return;
				}
				int i = JOptionPane.showConfirmDialog(null, "Bạn muốn xóa thức uống "+name, "Xóa Thức Uống", JOptionPane.YES_NO_OPTION);
				if(i==0) {
					ArrayList<Drinks> temp = ds_drinks.get(cb_type_drinks.getSelectedItem());
					int k = 0;
					for (k = 0; k < temp.size(); k++) {
						
						System.out.println(id + ", " + temp.get(k).getId() + ", "+k+", s:"+temp.size());
						if(temp.get(k).getId().equals(id)) {
							break;
						}
					}
					if(k >= temp.size()) {
						JOptionPane.showMessageDialog(null, "ID này không tồn tại!", "Xóa Thức Uống",JOptionPane.OK_CANCEL_OPTION);
						return;
					}else {
						ds_drinks.get(cb_type_drinks.getSelectedItem()).remove(getindex_id(id, temp));
						action_menu.delete_drink(id);
						refresh_table();
					}
					
				}
				
			} 
		});
		bt_delete.setFont(new Font("Tahoma", Font.BOLD, 16));
		bt_delete.setBounds(221, 566, 139, 52);
		contentPane.add(bt_delete);
		
		JButton bt_fix = new JButton("SỬA");
		bt_fix.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				String id = (tf_id.getText().toString()).trim();
				String name = (tf_drinks.getText().toString()).trim();
				String price = (tf_price.getText().toString()).trim();
				int i = JOptionPane.showConfirmDialog(null, "Bạn muốn cập nhật dữ liệu vừa thay đổi", "Thêm Thức Uống", JOptionPane.YES_NO_OPTION);
				if(i==0) {
					action_menu.fix_drink(id, name, price);
					refresh_table();
				}
			}
		});
		bt_fix.setFont(new Font("Tahoma", Font.BOLD, 16));
		bt_fix.setBounds(387, 566, 139, 52);
		contentPane.add(bt_fix);
		
		JLabel lblId = new JLabel("ID:");
		lblId.setHorizontalAlignment(SwingConstants.LEFT);
		lblId.setFont(new Font("Tahoma", Font.BOLD, 16));
		lblId.setBounds(573, 150, 127, 42);
		contentPane.add(lblId);
		
		JLabel lblThcUng = new JLabel("THỨC UỐNG:");
		lblThcUng.setHorizontalAlignment(SwingConstants.LEFT);
		lblThcUng.setFont(new Font("Tahoma", Font.BOLD, 16));
		lblThcUng.setBounds(573, 228, 127, 42);
		contentPane.add(lblThcUng);
		
		JLabel lblNhmNc = new JLabel("ĐƠN GIÁ:");
		lblNhmNc.setHorizontalAlignment(SwingConstants.LEFT);
		lblNhmNc.setFont(new Font("Tahoma", Font.BOLD, 16));
		lblNhmNc.setBounds(573, 308, 127, 42);
		contentPane.add(lblNhmNc);
		
		tf_id = new JTextField();
		tf_id.setFont(new Font("Tahoma", Font.PLAIN, 14));
		tf_id.setBounds(686, 150, 190, 33);
		contentPane.add(tf_id);
		tf_id.setColumns(10);
		
		tf_drinks = new JTextField();
		tf_drinks.setFont(new Font("Tahoma", Font.PLAIN, 14));
		tf_drinks.setColumns(10);
		tf_drinks.setBounds(686, 224, 190, 33);
		contentPane.add(tf_drinks);
		
		tf_price = new JTextField();
		tf_price.setFont(new Font("Tahoma", Font.PLAIN, 14));
		tf_price.setColumns(10);
		tf_price.setBounds(686, 308, 190, 33);
		contentPane.add(tf_price);
	}
}
