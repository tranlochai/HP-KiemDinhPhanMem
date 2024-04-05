package GUI;

import Entity.User;
import Service.UserService;
import Utils.UserLogin;

import javax.swing.*;
import javax.swing.border.EmptyBorder;
import javax.swing.border.EtchedBorder;
import java.awt.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.awt.event.WindowAdapter;
import java.awt.event.WindowEvent;

public class UserDetails extends JFrame implements ActionListener {

    private final JTextField tftaikhoan;
    private JPanel contentPane;
    private JTextField tfsdt;
    private JTextField tfdiachi;
    private JTextField tfemail;
    private JTextField tfngayvaolam;
    private JPasswordField passwordField;
    private JButton btnthaydoi, btnluu;

    public void run() {
        EventQueue.invokeLater(() -> {
            try {
                this.setVisible(true);
            } catch (Exception e) {
                e.printStackTrace();
            }
        });
    }

    public UserDetails() {
        addWindowListener(new WindowAdapter() {
            @Override
            public void windowOpened(WindowEvent e) {
                // chỉ cho mở 1 trang frame  1 lần
                Home.isUserDetailsEnable = false;
                Home.mntmhoso.setEnabled(Home.isUserDetailsEnable);
            }

            @Override
            public void windowClosing(WindowEvent e) {
                Home.isUserDetailsEnable = true;
            }
        });

        setResizable(false);
        setDefaultCloseOperation(JFrame.DISPOSE_ON_CLOSE);
        setBounds(100, 100, 1215, 699);
        contentPane = new JPanel();
        contentPane.setBackground(new Color(233, 235, 238));
        contentPane.setBorder(new EmptyBorder(5, 5, 5, 5));
        setContentPane(contentPane);
        contentPane.setLayout(null);

        JSeparator separator = new JSeparator();
        separator.setBackground(Color.LIGHT_GRAY);
        separator.setForeground(Color.BLACK);
        separator.setBounds(52, 68, 1076, 2);
        contentPane.add(separator);

        JLabel lblNewLabel = new JLabel("Thông Tin Nhân Viên");
        lblNewLabel.setHorizontalAlignment(SwingConstants.LEFT);
        lblNewLabel.setFont(new Font("Arial", Font.BOLD, 22));
        lblNewLabel.setBounds(72, 23, 318, 41);
        contentPane.add(lblNewLabel);

        JPanel panel = new JPanel();
        panel.setBackground(Color.WHITE);
        panel.setBounds(264, 93, 864, 521);
        contentPane.add(panel);
        panel.setLayout(null);

        JLabel lbtennhanvien = new JLabel("Tên Nhân Viên");
        lbtennhanvien.setFont(new Font("Arial", Font.BOLD, 16));
        lbtennhanvien.setBounds(33, 13, 116, 28);
        panel.add(lbtennhanvien);

        JLabel lbsdt = new JLabel("Số Điện Thoại");
        lbsdt.setFont(new Font("Arial", Font.BOLD, 16));
        lbsdt.setBounds(33, 94, 116, 28);
        panel.add(lbsdt);

        tfsdt = new JTextField(UserLogin.userLogin.getPhone());
        tfsdt.setEditable(false);
        tfsdt.setBorder(new EtchedBorder(EtchedBorder.RAISED, null, Color.BLACK));
        tfsdt.setFont(new Font("Arial", Font.PLAIN, 17));
        tfsdt.setBounds(75, 122, 322, 31);
        panel.add(tfsdt);
        tfsdt.setColumns(10);

        JLabel lbdiachi = new JLabel("Địa Chỉ");
        lbdiachi.setFont(new Font("Arial", Font.BOLD, 16));
        lbdiachi.setBounds(429, 94, 116, 28);
        panel.add(lbdiachi);

        tfdiachi = new JTextField(UserLogin.userLogin.getAddress());
        tfdiachi.setEditable(false);
        tfdiachi.setBorder(new EtchedBorder(EtchedBorder.RAISED, null, Color.BLACK));
        tfdiachi.setFont(new Font("Arial", Font.PLAIN, 17));
        tfdiachi.setColumns(10);
        tfdiachi.setBounds(465, 122, 354, 31);
        panel.add(tfdiachi);

        JLabel lbtaikhoan = new JLabel("Tài Khoản");
        lbtaikhoan.setFont(new Font("Arial", Font.BOLD, 16));
        lbtaikhoan.setBounds(33, 179, 116, 28);
        panel.add(lbtaikhoan);

        tftaikhoan = new JTextField(UserLogin.userLogin.getUserName());
        tftaikhoan.setBorder(new EtchedBorder(EtchedBorder.RAISED, null, Color.BLACK));
        tftaikhoan.setEditable(false);
        tftaikhoan.setFont(new Font("Arial", Font.PLAIN, 17));
        tftaikhoan.setColumns(10);
        tftaikhoan.setBounds(75, 208, 259, 31);
        panel.add(tftaikhoan);

        JLabel lbemail = new JLabel("Email");
        lbemail.setFont(new Font("Arial", Font.BOLD, 16));
        lbemail.setBounds(33, 258, 116, 28);
        panel.add(lbemail);

        tfemail = new JTextField(UserLogin.userLogin.getEmail());
        tfemail.setEditable(false);
        tfemail.setBorder(new EtchedBorder(EtchedBorder.RAISED, null, Color.BLACK));
        tfemail.setFont(new Font("Arial", Font.PLAIN, 17));
        tfemail.setColumns(10);
        tfemail.setBounds(75, 286, 322, 31);
        panel.add(tfemail);

        JLabel lbngayvaolam = new JLabel("Ngày Vào Làm");
        lbngayvaolam.setFont(new Font("Arial", Font.BOLD, 16));
        lbngayvaolam.setBounds(465, 258, 116, 28);
        panel.add(lbngayvaolam);

        tfngayvaolam = new JTextField(UserLogin.userLogin.getCreateDate().toString());
        tfngayvaolam.setBorder(new EtchedBorder(EtchedBorder.RAISED, null, Color.BLACK));
        tfngayvaolam.setEditable(false);
        tfngayvaolam.setFont(new Font("Arial", Font.PLAIN, 17));
        tfngayvaolam.setColumns(10);
        tfngayvaolam.setBounds(465, 286, 354, 31);
        panel.add(tfngayvaolam);

        JLabel lbmatkhau = new JLabel("Mật Khẩu");
        lbmatkhau.setFont(new Font("Arial", Font.BOLD, 16));
        lbmatkhau.setBounds(429, 179, 116, 28);
        panel.add(lbmatkhau);

        passwordField = new JPasswordField(UserLogin.userLogin.getPassword());
        passwordField.setFont(new Font("Arial", Font.PLAIN, 17));
        passwordField.setEditable(false);
        passwordField.setEchoChar('*');
        passwordField.setBorder(new EtchedBorder(EtchedBorder.RAISED, null, Color.BLACK));
        passwordField.setBounds(465, 208, 229, 31);
        panel.add(passwordField);

        btnthaydoi = new JButton("Thay Đổi");
        btnthaydoi.addActionListener(this);
        btnthaydoi.setFont(new Font("Tahoma", Font.PLAIN, 17));
        btnthaydoi.setFocusable(false);
        btnthaydoi.setBounds(581, 434, 113, 30);
        panel.add(btnthaydoi);

        btnluu = new JButton("Lưu");
        btnluu.setEnabled(false);

        btnluu.addActionListener(this);
        btnluu.setFont(new Font("Tahoma", Font.PLAIN, 17));
        btnluu.setFocusable(false);
        btnluu.setBounds(722, 435, 97, 30);
        panel.add(btnluu);
    }

    private UserService userService = new UserService();

    @Override
    public void actionPerformed(ActionEvent e) {
        String actionName = e.getActionCommand();
        if (actionName.equals("Thay Đổi")) {
            tfdiachi.setEditable(true);
            tfemail.setEditable(true);
            tfsdt.setEditable(true);
            passwordField.setEditable(true);
            if (btnluu.isEnabled() == false)
                btnluu.setEnabled(true);

        } else if (actionName.equals("Lưu")) {
            User user = UserLogin.userLogin;
            user.setPassword(new String(passwordField.getPassword()));
            user.setPhone(tfsdt.getText());
            user.setEmail(tfemail.getText());
            user.setAddress(tfdiachi.getText());
            if (userService.save(user)) {
                UserLogin.userLogin = user;
                JOptionPane.showMessageDialog(this, "Cập nhật thành công");
                tfdiachi.setText(user.getAddress());
                tfemail.setText(user.getEmail());
                tfsdt.setText(user.getPhone());
                passwordField.setText(user.getPassword());
            }
            tfdiachi.setEditable(false);
            tfemail.setEditable(false);
            tfsdt.setEditable(false);
            passwordField.setEditable(false);
            btnluu.setEnabled(false);
        }
    }
}
