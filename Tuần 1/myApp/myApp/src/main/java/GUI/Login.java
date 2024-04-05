package GUI;

import Service.UserService;
import Entity.User;
import Utils.UserLogin;

import javax.swing.*;
import javax.swing.border.EtchedBorder;
import javax.swing.border.TitledBorder;
import javax.swing.plaf.metal.MetalButtonUI;
import java.awt.*;
import java.awt.event.KeyEvent;
import java.awt.event.KeyListener;
import java.awt.event.MouseAdapter;
import java.awt.event.MouseEvent;

public class Login extends JFrame implements KeyListener {

    private JButton btnlogin;
    private JPasswordField password;
    private JTextField username;
    private JPanel panel_1;
    private JPanel contentPane;
    private JLabel alert;

    /**
     * Launch the application.
     */
    public void run() {
        EventQueue.invokeLater(() -> {
            try {
                this.setVisible(true);
            } catch (Exception e) {
                e.printStackTrace();
            }
        });
    }

    /**
     * Create the frame.
     */
    private CardLayout card;
    private JPanel pn_ketnoi;
    private JPanel pn_dangnhap;
    private Service.UserService UserService = new UserService();

    public Login() {
        setResizable(false);
        setTitle("Login\r\n");
        setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
        setBounds(100, 100, 514, 479);

        card = new CardLayout();
        contentPane = new JPanel(card);
        setContentPane(contentPane);

        pn_dangnhap = new JPanel();
        pn_dangnhap.setLayout(null);
        pn_dangnhap.setBackground(Color.WHITE);
        pn_dangnhap.setBorder(new TitledBorder(UIManager.getBorder("TitledBorder.border"), "", TitledBorder.LEADING,
                TitledBorder.ABOVE_TOP, null, new Color(0, 0, 0)));

        panel_1 = new JPanel();
        panel_1.setBackground(Color.WHITE);
        contentPane.add(panel_1, "dangnhap");
        panel_1.setLayout(null);

        username = new JTextField();
        username.addMouseListener(new MouseAdapter() {
            @Override
            public void mouseClicked(MouseEvent e) {
                username.selectAll();
                alert.setText("");
            }
        });
        username.addKeyListener(this);
        EtchedBorder etchedBorder = new EtchedBorder(Color.WHITE, Color.WHITE);
        TitledBorder titledBorder = new TitledBorder(etchedBorder);
        username.setBorder(titledBorder);
        username.setHorizontalAlignment(SwingConstants.CENTER);
        username.setText("");
        username.setBounds(135, 75, 244, 45);
        username.setBackground(Color.WHITE);
        username.setFont(new Font("Arial", Font.PLAIN, 22));
        username.setColumns(18);
        panel_1.add(username);

        password = new JPasswordField(""); // ****
        password.addMouseListener(new MouseAdapter() {
            @Override
            public void mouseClicked(MouseEvent e) {
                password.selectAll();
            }
        });
        password.addKeyListener(this);
        etchedBorder = new EtchedBorder(Color.WHITE, Color.WHITE);
        titledBorder = new TitledBorder(etchedBorder);
        password.setBorder(titledBorder);
        password.setHorizontalAlignment(SwingConstants.CENTER);
        password.setBounds(135, 140, 244, 45);
        password.setBackground(Color.WHITE);
        password.setFont(new Font("Arial", Font.PLAIN, 22));
        password.setColumns(18);
        panel_1.add(password);

        alert = new JLabel("");
        alert.setForeground(new Color(178, 34, 34));
        alert.setDisplayedMnemonic('*');
        alert.setBounds(100, 225, 318, 32);
        alert.setFont(new Font("Arial", Font.PLAIN, 20));
        panel_1.add(alert);

        btnlogin = new JButton("Log in");
        btnlogin.setUI(new MetalButtonUI() {
            @Override
            protected Color getSelectColor() {
                return new Color(0, 60, 108);
            }
        });
        btnlogin.addActionListener(e -> checkUser());
        btnlogin.setFocusable(false);
        btnlogin.addKeyListener(this);
        etchedBorder = new EtchedBorder(Color.WHITE, Color.WHITE);
        titledBorder = new TitledBorder(etchedBorder);
        btnlogin.setBorder(titledBorder);
        btnlogin.setForeground(new Color(252, 255, 255));
        btnlogin.setBackground(new Color(34, 67, 156));
        btnlogin.setFont(new Font("Tahoma", Font.PLAIN, 35));
        btnlogin.setBounds(130, 300, 250, 60);
        panel_1.add(btnlogin);

        JSeparator separator = new JSeparator();
        separator.setForeground(Color.BLACK);
        separator.setBounds(85, 120, 325, 5);
        panel_1.add(separator);

        JSeparator separator_1 = new JSeparator();
        separator_1.setForeground(Color.BLACK);
        separator_1.setBounds(85, 189, 325, 5);
        panel_1.add(separator_1);

        JLabel lbuser = new JLabel("");
        lbuser.setIcon(new ImageIcon("Imager\\male_user_32px.png"));
        lbuser.setBounds(85, 75, 35, 35);

        panel_1.add(lbuser);

        JLabel lbpass = new JLabel();
        lbpass.setIcon(new ImageIcon("Imager\\unlock_32px.png"));
        lbpass.setBounds(85, 150, 35, 32);

        panel_1.add(lbpass);

        JLabel lblNewLabel = new JLabel("Sign in");
        lblNewLabel.setForeground(new Color(22, 27, 33));
        lblNewLabel.setFont(new Font("Arial", Font.BOLD, 48));
        lblNewLabel.setBackground(Color.WHITE);
        lblNewLabel.setBounds(175, 6, 158, 56);
        panel_1.add(lblNewLabel);
    }

    public void checkUser() {
        User user = UserService.checkUser(username.getText(), password.getText());
        if (user == null) {
            alert.setText("Sai tên đăng nhập hoặc mật khẩu");
        } else {
            dispose();
            UserLogin.userLogin = user;
            new Home().run();
        }
    }

    @Override
    public void keyTyped(KeyEvent e) {

    }

    @Override
    public void keyPressed(KeyEvent e) {
        if (e.getKeyCode() == e.VK_ENTER) {
            checkUser();
        }
    }

    @Override
    public void keyReleased(KeyEvent e) {

    }
}
