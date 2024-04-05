package view;

import java.awt.BorderLayout;
import java.awt.Color;
import java.awt.Component;
import java.awt.EventQueue;

import javax.swing.BorderFactory;
import javax.swing.JFrame;
import javax.swing.JPanel;
import javax.swing.border.Border;
import javax.swing.border.EmptyBorder;

import bo.bo_revenue;
import bo.bo_setzones;
import dao.dao_ConnectiveSQLServer;

import java.awt.Window.Type;
import javax.swing.JLabel;
import javax.swing.JOptionPane;

import java.awt.Font;
import java.awt.Frame;

import javax.swing.SwingConstants;
import javax.swing.JComboBox;
import javax.swing.DefaultComboBoxModel;
import javax.swing.JButton;
import java.awt.event.ActionListener;
import java.awt.event.MouseEvent;
import java.awt.event.MouseListener;
import java.awt.event.ActionEvent;
import java.awt.event.WindowAdapter;
import java.awt.event.WindowEvent;

public class view_main extends JFrame {
	public static String STAFFS_Id;
	public dao_ConnectiveSQLServer dao_connective = new dao_ConnectiveSQLServer();
	public JComboBox comboBox_zones;
	public JButton lb_revenue;
	public JButton lb_addtable;
	private JPanel contentPane;
	private JLabel lb_action;
	private JLabel lb_main;
	private Border panelBorder = BorderFactory.createEmptyBorder(20, 20, 20, 20);
	public bo_setzones action_setzones = new bo_setzones();
	public bo_revenue action_revenue = new bo_revenue();
	/**
	 * Launch the application.
	 */
	public static void main(String[] args) {
		EventQueue.invokeLater(new Runnable() {
			public void run() {
				try {
					view_main frame = new view_main(STAFFS_Id);
					frame.setLocationRelativeTo(null);
					frame.setVisible(true);
				} catch (Exception e) {
					e.printStackTrace();
				}
			}
		});
	}
	
	/**
	 * Create the frame.
	 */
	public view_main(String id) {
		addWindowListener(new WindowAdapter() {
			@Override
			public void windowOpened(WindowEvent e) {
				try {
					STAFFS_Id = id;
					action_setzones.set_staff_id(STAFFS_Id);
					action_setzones.dao_connective.connective();
					String zones_name = (comboBox_zones.getSelectedItem().toString()).trim();
					action_setzones.zones_table(zones_name);
					for(JButton tables: action_setzones.list_button_tables) {	
						tables.setVisible(true);
						contentPane.add(tables);
					}
					setContentPane(contentPane);
					if(action_revenue.check_pos(STAFFS_Id)) {
						contentPane.add(lb_revenue);
					}
				} catch (Exception e2) {
					// TODO: handle exception
					e2.printStackTrace();
				}
			}
		});
		setTitle("CAFFE_MANAGER");
		setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
		setBounds(0, 0, 1500, 820);
		contentPane = new JPanel();
		contentPane.setBorder(new EmptyBorder(5, 5, 5, 5));
		setContentPane(contentPane);
		contentPane.setLayout(null);
		
		//set viền cho khung button
		lb_action = new JLabel("");
		lb_action.setOpaque(false);
		lb_action.setBorder(BorderFactory.createLineBorder(Color.BLACK, 3));
		lb_action.setBounds(10, 10, 206, 630);
		contentPane.add(lb_action);
		
		JLabel lb_address = new JLabel("Địa chỉ: 77 Nguyễn Huệ - Thành Phố Huế - Thừa Thiên Huế");
		lb_address.setHorizontalAlignment(SwingConstants.CENTER);
		lb_address.setFont(new Font("Tahoma", Font.BOLD, 18));
		lb_address.setBounds(0, 687, 1486, 34);
		contentPane.add(lb_address);
		
		JLabel lb_name = new JLabel("Quán Cafe 4 Anh Em");
		lb_name.setHorizontalAlignment(SwingConstants.CENTER);
		lb_name.setFont(new Font("Tahoma", Font.BOLD, 18));
		lb_name.setBounds(0, 650, 1486, 34);
		contentPane.add(lb_name);
		
		JLabel lb_phone = new JLabel("Số Điện Thoại: 0123456789");
		lb_phone.setHorizontalAlignment(SwingConstants.CENTER);
		lb_phone.setFont(new Font("Tahoma", Font.BOLD, 18));
		lb_phone.setBounds(0, 722, 1486, 34);
		contentPane.add(lb_phone);
		
		comboBox_zones = new JComboBox();
		comboBox_zones.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				try {
					for(JButton tables: action_setzones.list_button_tables) {	
						tables.setVisible(false);
						contentPane.remove(tables);
					}
					//setContentPane(contentPane);
					String zones_name = (comboBox_zones.getSelectedItem().toString()).trim();
					action_setzones.zones_table(zones_name);
					for(JButton tables: action_setzones.list_button_tables) {	
						tables.setVisible(true);
						contentPane.add(tables);
					}
					
				} catch (Exception e2) {
					// TODO: handle exception
					e2.printStackTrace();
				}
			
			}
		});
		comboBox_zones.setFont(new Font("Tahoma", Font.BOLD, 18));
		comboBox_zones.setModel(new DefaultComboBoxModel(new String[] {"Tầng 1", "Tầng 2", "Tầng 3", "Tầng 4", "Tầng 5"}));
		comboBox_zones.setBounds(226, 22, 186, 34);
		contentPane.add(comboBox_zones);
		
		lb_main = new JLabel("");
		lb_main.setFont(new Font("Tahoma", Font.BOLD, 15));
		lb_main.setOpaque(false);
		lb_main.setBorder(BorderFactory.createLineBorder(Color.BLACK, 3));
		lb_main.setBounds(212, 10, 1264, 630);
		contentPane.add(lb_main);
		
		JButton bt_changepassword = new JButton("Đổi Mật Khẩu");
		bt_changepassword.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				view_changepassword jframe_changepasssword = new view_changepassword(STAFFS_Id);
				jframe_changepasssword.setVisible(true);
				jframe_changepasssword.setLocationRelativeTo(null);
			}
		});
		bt_changepassword.setFont(new Font("Tahoma", Font.BOLD, 16));
		bt_changepassword.setBounds(22, 59, 180, 42);
		contentPane.add(bt_changepassword);
		
		JButton bt_logout = new JButton("Đăng Xuất");
		bt_logout.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				int n = JOptionPane.showConfirmDialog(null, "Bạn có muốn đăng xuất khỏi tài khoản", "Đăng xuất khỏi tài khoản", JOptionPane.YES_NO_OPTION);
				if(n==0)
					dispose();
			}
		});
		bt_logout.setFont(new Font("Tahoma", Font.BOLD, 16));
		bt_logout.setBounds(22, 124, 180, 42);
		contentPane.add(bt_logout);
		
		JButton lb_menu = new JButton("Thực Đơn");
		lb_menu.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				view_menu jframe_menu = new view_menu(action_setzones.zones_id);
				jframe_menu.setVisible(true);
				jframe_menu.setLocationRelativeTo(null);
			}
		});
		lb_menu.setFont(new Font("Tahoma", Font.BOLD, 16));
		lb_menu.setBounds(22, 193, 180, 42);
		contentPane.add(lb_menu);
		
		lb_addtable = new JButton("Thêm Bàn");
		lb_addtable.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				int count_table = Integer.valueOf(JOptionPane.showInputDialog("Nhập số lượng bàn muốn thêm"));
				if(count_table!=0) 
					action_setzones.add_table(count_table);
				try {
					for(JButton tables: action_setzones.list_button_tables) {	
						tables.setVisible(false);
						contentPane.remove(tables);
					}
					//setContentPane(contentPane);
					String zones_name = (comboBox_zones.getSelectedItem().toString()).trim();
					action_setzones.zones_table(zones_name);
					for(JButton tables: action_setzones.list_button_tables) {	
						tables.setVisible(true);
						contentPane.add(tables);
					}
					
				} catch (Exception e2) {
					// TODO: handle exception
					e2.printStackTrace();
				}
			}
		});
		lb_addtable.setFont(new Font("Tahoma", Font.BOLD, 16));
		lb_addtable.setBounds(22, 262, 180, 42);
		contentPane.add(lb_addtable);
		
		lb_revenue = new JButton("Doanh Thu");
		lb_revenue.setFont(new Font("Tahoma", Font.BOLD, 16));
		lb_revenue.setBounds(22, 324, 180, 42);
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
				view_revenue jframe_revenue = new view_revenue();
				jframe_revenue.setLocationRelativeTo(null);
				jframe_revenue.setVisible(true);
			}
		};
		lb_revenue.addMouseListener(mouseEvent);
		
		
	}
}
