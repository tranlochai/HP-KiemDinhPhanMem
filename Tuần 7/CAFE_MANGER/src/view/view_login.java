package view;

import java.awt.BorderLayout;
import java.awt.EventQueue;

import javax.swing.JFrame;
import javax.swing.JPanel;
import javax.swing.border.EmptyBorder;

import bo.bo_Login;

import java.awt.Window.Type;
import javax.swing.JButton;
import javax.swing.JLabel;
import java.awt.Color;
import java.awt.Font;
import javax.swing.SwingConstants;
import javax.swing.JTextField;
import javax.swing.JPasswordField;
import java.awt.event.ActionListener;
import java.awt.event.ActionEvent;
import javax.swing.JComboBox;
import javax.swing.DefaultComboBoxModel;

public class view_login extends JFrame {

	private JPanel contentPane;
	private JTextField tf_id;
	private JPasswordField tf_password;
	private JLabel lb_loginwrong;
	private JComboBox comboBox_cv;
	public bo_Login login_action = new bo_Login();
	/**
	 * Launch the application.
	 */
	public static void main(String[] args) {
		EventQueue.invokeLater(new Runnable() {
			public void run() {
				try {
					view_login frame = new view_login();
					//hiển thị frame ra giữa màn hình
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
	public view_login() {
		setTitle("CAFFE_MANAGER");
		setBounds(100, 100, 750, 600);
		contentPane = new JPanel();
		contentPane.setBorder(new EmptyBorder(5, 5, 5, 5));
		setContentPane(contentPane);
		contentPane.setLayout(null);
		
		JLabel lblNewLabel_1 = new JLabel("Welcome, please sign in");
		lblNewLabel_1.setHorizontalAlignment(SwingConstants.CENTER);
		lblNewLabel_1.setFont(new Font("Tahoma", Font.BOLD, 28));
		lblNewLabel_1.setBounds(136, 105, 452, 43);
		contentPane.add(lblNewLabel_1);
		
		JLabel lb_id = new JLabel("ID");
		lb_id.setHorizontalAlignment(SwingConstants.LEFT);
		lb_id.setFont(new Font("Tahoma", Font.BOLD, 24));
		lb_id.setBounds(155, 179, 433, 36);
		contentPane.add(lb_id);
		
		JLabel lb_password = new JLabel("Password");
		lb_password.setHorizontalAlignment(SwingConstants.LEFT);
		lb_password.setFont(new Font("Tahoma", Font.BOLD, 24));
		lb_password.setBounds(155, 257, 433, 36);
		contentPane.add(lb_password);
		
		tf_id = new JTextField();
		tf_id.setFont(new Font("Tahoma", Font.PLAIN, 16));
		tf_id.setBounds(155, 217, 395, 36);
		contentPane.add(tf_id);
		tf_id.setColumns(10);
		
		tf_password = new JPasswordField();
		tf_password.setFont(new Font("Tahoma", Font.PLAIN, 16));
		tf_password.setColumns(10);
		tf_password.setBounds(155, 302, 395, 36);
		contentPane.add(tf_password);
		
		/*
		 * event login
		 * nếu login thành công -> tắt jframe login -> mở jframe main với id nhân viên đăng nhập
		 */
		JButton bt_signin = new JButton("Login");
		bt_signin.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				String id = tf_id.getText();
				String password = tf_password.getText();
				String postion = (String) comboBox_cv.getSelectedItem();
//				System.out.println(postion);
				if(login_action.Login(id, password, postion)) {
					//tắt jframe đang mở(jframe login)
					dispose();	
					//khởi tạo jframe main với id nhân viên đăng nhập
					view_main jframe_main = new view_main(id);
					jframe_main.setVisible(true);
					
				}
				else lb_loginwrong.setVisible(true);
			}
		});
		bt_signin.setBackground(Color.LIGHT_GRAY);
		bt_signin.setFont(new Font("Tahoma", Font.PLAIN, 24));
		bt_signin.setBounds(153, 434, 156, 43);
		contentPane.add(bt_signin);
		
		lb_loginwrong = new JLabel("*Incorrect ID or Password please try again");
		lb_loginwrong.setForeground(Color.RED);
		lb_loginwrong.setHorizontalAlignment(SwingConstants.CENTER);
		lb_loginwrong.setFont(new Font("Tahoma", Font.BOLD, 18));
		lb_loginwrong.setBounds(136, 493, 426, 43);
		contentPane.add(lb_loginwrong);
		
		JLabel lblChcV = new JLabel("Chức vụ");
		lblChcV.setHorizontalAlignment(SwingConstants.LEFT);
		lblChcV.setFont(new Font("Tahoma", Font.BOLD, 24));
		lblChcV.setBounds(155, 341, 433, 36);
		contentPane.add(lblChcV);
		
		comboBox_cv = new JComboBox();
		comboBox_cv.setFont(new Font("Tahoma", Font.BOLD, 16));
		comboBox_cv.setModel(new DefaultComboBoxModel(new String[] {"Nhân Viên", "Quản Lí"}));
		comboBox_cv.setBounds(155, 387, 395, 36);
		contentPane.add(comboBox_cv);
		
		JLabel label = new JLabel("");
		label.setOpaque(true);
		label.setForeground(Color.WHITE);
		label.setEnabled(false);
		label.setBackground(new Color(204, 204, 204));
		label.setBounds(121, 159, 496, 336);
		contentPane.add(label);
		lb_loginwrong.setVisible(false);
	}
}
