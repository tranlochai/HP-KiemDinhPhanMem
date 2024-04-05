package view;

import java.awt.BorderLayout;
import java.awt.EventQueue;

import javax.swing.JFrame;
import javax.swing.JPanel;
import javax.swing.border.EmptyBorder;

import bo.bo_changepassword;

import java.awt.Window.Type;
import javax.swing.JLabel;
import java.awt.Font;
import javax.swing.SwingConstants;
import javax.swing.JTextField;
import javax.swing.JPasswordField;
import javax.swing.JButton;
import java.awt.event.ActionListener;
import java.awt.event.ActionEvent;

public class view_changepassword extends JFrame {

	private JPanel contentPane;
	private JPasswordField tf_password;
	private JPasswordField tf_newpassword1;
	private JPasswordField tf_newpassword2;
	public static String ID;
	public bo_changepassword changepasssword_action = new bo_changepassword();
	/**
	 * Launch the application.
	 */
	public static void main(String[] args) {
		EventQueue.invokeLater(new Runnable() {
			public void run() {
				try {
					view_changepassword frame = new view_changepassword(ID);
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
	public view_changepassword(String id) {
		ID = id;
		
		setTitle("CAFFE_MANAGER");
		setBounds(0, 0, 590, 387);
		contentPane = new JPanel();
		contentPane.setBorder(new EmptyBorder(5, 5, 5, 5));
		setContentPane(contentPane);
		contentPane.setLayout(null);
		
		JLabel lb_password = new JLabel("M\u1EADt Kh\u1EA9u Hi\u1EC7n T\u1EA1i");
		lb_password.setFont(new Font("Tahoma", Font.BOLD, 16));
		lb_password.setBounds(81, 105, 183, 39);
		contentPane.add(lb_password);
		
		JLabel lbliMtKhu = new JLabel("\u0110\u1ED5i M\u1EADt Kh\u1EA9u");
		lbliMtKhu.setHorizontalAlignment(SwingConstants.CENTER);
		lbliMtKhu.setFont(new Font("Tahoma", Font.BOLD, 16));
		lbliMtKhu.setBounds(182, 39, 183, 39);
		contentPane.add(lbliMtKhu);
		
		tf_password = new JPasswordField();
		tf_password.setFont(new Font("Tahoma", Font.PLAIN, 14));
		tf_password.setBounds(262, 105, 241, 28);
		contentPane.add(tf_password);
		tf_password.setColumns(10);
		
		JLabel lblMtKhuMi = new JLabel("M\u1EADt Kh\u1EA9u M\u1EDBi");
		lblMtKhuMi.setFont(new Font("Tahoma", Font.BOLD, 16));
		lblMtKhuMi.setBounds(81, 143, 183, 39);
		contentPane.add(lblMtKhuMi);
		
		tf_newpassword1 = new JPasswordField();
		tf_newpassword1.setFont(new Font("Tahoma", Font.PLAIN, 14));
		tf_newpassword1.setColumns(10);
		tf_newpassword1.setBounds(262, 143, 241, 28);
		contentPane.add(tf_newpassword1);
		
		JLabel lblNhpLiMt = new JLabel("Nh\u1EADp L\u1EA1i M\u1EADt Kh\u1EA9u");
		lblNhpLiMt.setFont(new Font("Tahoma", Font.BOLD, 16));
		lblNhpLiMt.setBounds(81, 185, 183, 39);
		contentPane.add(lblNhpLiMt);
		
		tf_newpassword2 = new JPasswordField();
		tf_newpassword2.setFont(new Font("Tahoma", Font.PLAIN, 14));
		tf_newpassword2.setColumns(10);
		tf_newpassword2.setBounds(262, 185, 241, 28);
		contentPane.add(tf_newpassword2);
		
		JButton btnNewButton = new JButton("L\u01B0u Thay \u0110\u1ED5i");
		btnNewButton.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				try {
					String password = tf_password.getText();
					String newpassword1 = tf_newpassword1.getText();
					String newpassword2 = tf_newpassword2.getText();
					if(changepasssword_action.changepassword(ID, password, newpassword1, newpassword2)) {
						dispose();
					};
				} catch (Exception e1) {
					// TODO: handle exception
					e1.printStackTrace();
				}
			}
		});
		btnNewButton.setFont(new Font("Tahoma", Font.BOLD, 16));
		btnNewButton.setBounds(335, 237, 165, 39);
		contentPane.add(btnNewButton);
	}
}
