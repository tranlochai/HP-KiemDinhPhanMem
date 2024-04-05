package view;

import java.awt.BorderLayout;
import java.awt.Color;
import java.awt.EventQueue;

import javax.swing.JButton;
import javax.swing.JFrame;
import javax.swing.JPanel;
import javax.swing.border.EmptyBorder;
import javax.swing.table.DefaultTableModel;

import bo.bo_menu;

import javax.swing.JLabel;
import java.awt.Font;
import javax.swing.SwingConstants;
import javax.swing.JScrollPane;
import javax.swing.JTabbedPane;
import javax.swing.ScrollPaneConstants;
import javax.swing.JList;
import javax.swing.JOptionPane;
import javax.swing.AbstractListModel;
import javax.swing.JComboBox;
import java.awt.event.WindowAdapter;
import java.awt.event.WindowEvent;
import java.awt.event.ActionListener;
import java.awt.event.ActionEvent;
import javax.swing.JTable;

public class view_bill extends JFrame {

	private JPanel contentPane;
	public static JButton button_table;
	public static String zones_name;
	public bo_menu action_menu = new bo_menu();
	public JComboBox cb_type_drinks;
	public JComboBox cb_drinks;
	public JButton bt_payment;
	public JLabel lb_name, lb_money;
	public String bills_id;
	private JTable table_1;
	/**
	 * Launch the application.
	 */
	public static void main(String[] args) {
		EventQueue.invokeLater(new Runnable() {
			public void run() {
				try {
					view_bill frame = new view_bill(button_table, zones_name);
					frame.setVisible(true);
					
				} catch (Exception e) {
					e.printStackTrace();
				}
			}
		});
	}
	/*
	 * tạo mới dữ liệu trong danh sách thức uống
	 */
	public void refresh_table() {
		try {
			float money = action_menu.get_bill_price(bills_id);
			lb_money.setText(String.valueOf(money));
			DefaultTableModel tableModel = new DefaultTableModel();
			tableModel.addColumn("STT");
			tableModel.addColumn("THỨC UỐNG");
			tableModel.addColumn("SỐ LƯỢNG");
			tableModel.addColumn("GIÁ BÁN");
			action_menu.get_info_bills(bills_id);
			int i=1;
			
			while(action_menu.rs_info_bills.next()) {
				Object[] data = new Object[4];
				data[0] = String.valueOf(i);
				String id = action_menu.rs_info_bills.getString("DRINKS_Id");
				data[1] = action_menu.get_drinks_name(id);
				data[2] = action_menu.rs_info_bills.getInt("INFO_Amount");
				data[3] = action_menu.rs_info_bills.getFloat("INFO_Price");
				tableModel.addRow(data);
				i++;
			}
			table_1.setModel(tableModel);
			table_1.setAutoResizeMode(JTable.AUTO_RESIZE_NEXT_COLUMN);
			table_1.getColumnModel().getColumn(0).setPreferredWidth(30);
			table_1.getColumnModel().getColumn(0).setMaxWidth(30);
			table_1.getColumnModel().getColumn(1).setPreferredWidth(400);
		
			table_1.getColumnModel().getColumn(3).setPreferredWidth(300);

		} catch (Exception e) {
			// TODO: handle exception
			e.printStackTrace();
		}
	}
	/*
	 * add type drinks vào cb_type_drinks
	 */
	public void add_cb_type_drinks() {
		action_menu.get_type_drinks();
		for(String data : action_menu.list_type_drinks) {
			cb_type_drinks.addItem(data);		}
	}
	/*
	 * add type drinks vào cb_type_drinks
	 */
	public void add_get_drinks() {
		String type_drinks = (cb_type_drinks.getSelectedItem().toString()).trim();
		action_menu.get_drinks(type_drinks);
		try {
			while(action_menu.rs_drinks.next()) {
				String data = action_menu.rs_drinks.getString("DRINKS_Name");
				cb_drinks.addItem(data);
			}
		} catch (Exception e) {
			// TODO: handle exception
		}
	}
	/*
	 * add info_bill
	 */
	public void add() {
		try {
			int amount = Integer.valueOf(JOptionPane.showInputDialog("Nhập số lượng"));
			String type_drink_name = String.valueOf(cb_type_drinks.getSelectedItem());
			String drinks_name = String.valueOf(cb_drinks.getSelectedItem());
			action_menu.get_type_drinks_id(type_drink_name);;
			float price = action_menu.get_drinks_price(drinks_name);
			float money = price * amount;
			String drinks_id = action_menu.get_drinks_id(drinks_name);
			action_menu.add_bills_money(money, bills_id);
			if(action_menu.check_info_bill(bills_id, drinks_id)) {
				action_menu.add_drinks_amount(bills_id, drinks_id, amount);
			}
			else action_menu.add_info_bills(bills_id, drinks_id, amount, price);
			refresh_table();
		} catch (Exception e2) {
			// TODO: handle exception
			e2.printStackTrace();
		}
	}
	/**
	 * Create the frame.
	 */
	public view_bill(JButton table, String name) {
		addWindowListener(new WindowAdapter() {
			@Override
			public void windowOpened(WindowEvent e) {
				add_cb_type_drinks();
				add_get_drinks();
				int tb_id = Integer.valueOf(lb_name.getName().trim());
				
				bills_id = action_menu.get_bills_id(tb_id);
				refresh_table();
			}
		});
		button_table = table;
		zones_name = name;
		setTitle("CAFFE_MANAGER");
		setBounds(100, 100, 1000, 700);
		contentPane = new JPanel();
		contentPane.setBorder(new EmptyBorder(5, 5, 5, 5));
		setContentPane(contentPane);
		contentPane.setLayout(null);
		lb_name = new JLabel(zones_name+" - "+button_table.getText());
		lb_name.setName(String.valueOf(button_table.getName()));
		lb_name.setHorizontalAlignment(SwingConstants.CENTER);
		lb_name.setFont(new Font("Tahoma", Font.BOLD, 16));
		lb_name.setBounds(418, 10, 206, 38);
		contentPane.add(lb_name);
		
		JLabel lblNewLabel = new JLabel("NHÓM THỨC UỐNG");
		lblNewLabel.setFont(new Font("Tahoma", Font.BOLD, 16));
		lblNewLabel.setBounds(53, 83, 178, 27);
		contentPane.add(lblNewLabel);
		
		JLabel lb_drinks = new JLabel("THỨC UỐNG");
		lb_drinks.setFont(new Font("Tahoma", Font.BOLD, 16));
		lb_drinks.setBounds(429, 83, 178, 27);
		contentPane.add(lb_drinks);
		
		cb_type_drinks = new JComboBox();
		cb_type_drinks.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				cb_drinks.removeAllItems();
				add_get_drinks();
			}
		});
		
		cb_type_drinks.setFont(new Font("Tahoma", Font.BOLD, 14));
		cb_type_drinks.setBounds(220, 83, 183, 26);
		contentPane.add(cb_type_drinks);
		
		cb_drinks = new JComboBox();
		cb_drinks.setFont(new Font("Tahoma", Font.BOLD, 14));
		cb_drinks.setBounds(543, 83, 193, 26);
		contentPane.add(cb_drinks);
		
		JTabbedPane tabbedPane = new JTabbedPane(JTabbedPane.TOP);
		tabbedPane.setFont(new Font("Tahoma", Font.BOLD, 16));
		tabbedPane.setBounds(53, 140, 859, 339);
		contentPane.add(tabbedPane);
		
		JScrollPane scrollPane = new JScrollPane();
		tabbedPane.addTab("THỨC UỐNG GỌI", null, scrollPane, null);
		
		table_1 = new JTable();
		table_1.setFont(new Font("Tahoma", Font.PLAIN, 14));
		scrollPane.setViewportView(table_1);
		
		JButton bt_add = new JButton("THÊM");
		bt_add.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				add();
			}
		});
		bt_add.setFont(new Font("Tahoma", Font.BOLD, 16));
		bt_add.setBounds(794, 83, 118, 26);
		contentPane.add(bt_add);
		
		JButton btnXa = new JButton("XÓA");
		btnXa.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				int i = table_1.getSelectedRow();
				String drinks_name = (String)table_1.getValueAt(i, 1);
				String drinks_id = action_menu.get_drinks_id(drinks_name);
				int amount = Integer.valueOf((String)table_1.getValueAt(i, 2).toString().trim());
				float price = Float.valueOf((String)table_1.getValueAt(i, 3).toString().trim());
				
				i = JOptionPane.showConfirmDialog(null, "Bạn muốn xóa thức uống này khỏi hóa đơn?","Xóa thức uống", JOptionPane.YES_NO_OPTION);
				if(i==0) {
					action_menu.delete_info_bill(bills_id, drinks_id);
					action_menu.add_bills_money((amount*price*-1), bills_id);
				}
				refresh_table();
			}
		});
		btnXa.setFont(new Font("Tahoma", Font.BOLD, 16));
		btnXa.setBounds(794, 119, 118, 26);
		contentPane.add(btnXa);
		
		bt_payment = new JButton("THANH TOÁN");
		bt_payment.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				int i = JOptionPane.showConfirmDialog(null, "Bạn muốn thanh toán hóa đơn này?","Thanh toán hóa đơn",JOptionPane.YES_NO_OPTION);
				if(i==0) {
					action_menu.payment(bills_id, Integer.valueOf(lb_name.getName()));
					dispose();
					button_table.setBackground(Color.LIGHT_GRAY);
				}
			}
		});
		bt_payment.setFont(new Font("Tahoma", Font.BOLD, 16));
		bt_payment.setBounds(53, 543, 154, 26);
		contentPane.add(bt_payment);
		
		JLabel lblTngTinThanh = new JLabel("TỔNG TIỀN:");
		lblTngTinThanh.setFont(new Font("Tahoma", Font.BOLD, 16));
		lblTngTinThanh.setBounds(53, 506, 118, 27);
		contentPane.add(lblTngTinThanh);
		
		lb_money = new JLabel("0.0");
		lb_money.setFont(new Font("Tahoma", Font.BOLD, 16));
		lb_money.setBounds(201, 506, 183, 27);
		contentPane.add(lb_money);
		
		
	}
}
