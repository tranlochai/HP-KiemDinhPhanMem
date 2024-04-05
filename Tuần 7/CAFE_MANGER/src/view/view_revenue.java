package view;

import java.awt.BorderLayout;
import java.awt.EventQueue;

import javax.swing.JFrame;
import javax.swing.JPanel;
import javax.swing.border.EmptyBorder;
import javax.swing.table.DefaultTableModel;

import bo.bo_revenue;

import javax.swing.JTabbedPane;
import javax.swing.JScrollPane;
import javax.swing.JTable;
import java.awt.event.WindowAdapter;
import java.awt.event.WindowEvent;
import javax.swing.JLabel;
import java.awt.Font;

public class view_revenue extends JFrame {

	private JPanel contentPane;
	private JTable table;
	public bo_revenue action_revenue = new bo_revenue();
	public JLabel lb_revenue;
	private JLabel lblDoanhThuThangs;
	/**
	 * Launch the application.
	 */
	public static void main(String[] args) {
		EventQueue.invokeLater(new Runnable() {
			public void run() {
				try {
					view_revenue frame = new view_revenue();
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
		action_revenue.revenue();
		try {
			DefaultTableModel tableModel = new DefaultTableModel();
			tableModel.addColumn("STT");
			tableModel.addColumn("ID");
			tableModel.addColumn("THỜI GIAN VÀO");
			tableModel.addColumn("THỜI GIAN RA VỀ");
			tableModel.addColumn("MÃ NHÂN VIÊN");
			tableModel.addColumn("THANH TOÁN HÓA ĐƠN");
			int i=1;
			while(action_revenue.rs_bills.next()) {
				Object[] data = new String[6];
				data[0] = String.valueOf(i);
				data[1] = action_revenue.rs_bills.getString("BILLS_Id");
				data[2] = action_revenue.rs_bills.getString("BILLS_TimeIn");
				data[3] = action_revenue.rs_bills.getString("BILLS_TimeOut");
				if(data[3]!=null) {
					data[4] = action_revenue.rs_bills.getString("STAFFS_Id");
					data[5] = action_revenue.rs_bills.getString("BILLS_Price");
					tableModel.addRow(data);
					i++;
				}
			}
			table.setModel(tableModel);
			table.setAutoResizeMode(JTable.AUTO_RESIZE_NEXT_COLUMN);
			table.getColumnModel().getColumn(0).setMaxWidth(30);
			table.getColumnModel().getColumn(1).setMaxWidth(70);
			table.getColumnModel().getColumn(4).setPreferredWidth(30);
			lb_revenue.setText(String.valueOf(action_revenue.month_revenue()));
		} catch (Exception e) {
			// TODO: handle exception
			e.printStackTrace();
		}
	}
	/**
	 * Create the frame.
	 */
	public view_revenue() {
		setTitle("CAFFE_MANAGER");
		addWindowListener(new WindowAdapter() {
			@Override
			public void windowOpened(WindowEvent e) {
				refresh_table();
			}
		});
		setBounds(100, 100, 703, 619);
		contentPane = new JPanel();
		contentPane.setBorder(new EmptyBorder(5, 5, 5, 5));
		setContentPane(contentPane);
		contentPane.setLayout(null);
		
		JTabbedPane tabbedPane = new JTabbedPane(JTabbedPane.TOP);
		tabbedPane.setBounds(10, 64, 669, 429);
		contentPane.add(tabbedPane);
		
		JScrollPane scrollPane = new JScrollPane();
		tabbedPane.addTab("Danh Sách Hóa Đơn", null, scrollPane, null);
		
		table = new JTable();
		scrollPane.setViewportView(table);
		
		JLabel lblNewLabel = new JLabel("TỔNG DOANH THU:");
		lblNewLabel.setFont(new Font("Tahoma", Font.BOLD, 16));
		lblNewLabel.setBounds(10, 503, 179, 35);
		contentPane.add(lblNewLabel);
		
		lb_revenue = new JLabel("0.0");
		lb_revenue.setFont(new Font("Tahoma", Font.PLAIN, 16));
		lb_revenue.setBounds(181, 503, 249, 26);
		contentPane.add(lb_revenue);
		
		lblDoanhThuThangs = new JLabel("BẢNG DOANH THU THÁNG");
		lblDoanhThuThangs.setFont(new Font("Tahoma", Font.BOLD, 16));
		lblDoanhThuThangs.setBounds(225, 10, 236, 35);
		contentPane.add(lblDoanhThuThangs);
	}
}
