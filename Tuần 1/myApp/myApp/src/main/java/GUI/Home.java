package GUI;

import javax.swing.*;
import javax.swing.border.BevelBorder;
import javax.swing.border.EmptyBorder;
import javax.swing.border.LineBorder;
import javax.swing.border.TitledBorder;
import javax.swing.plaf.metal.MetalToggleButtonUI;
import java.awt.*;
import java.awt.event.MouseAdapter;
import java.awt.event.MouseEvent;
import java.awt.event.WindowEvent;
import java.awt.event.WindowFocusListener;

public class Home extends JFrame {
    private Color color_enter = new Color(39, 89, 160);
    private Color color_exit = new Color(66, 103, 178);
    private JPanel contentPane;
    private JTextField txtWelcome;
    private CardLayout cardlayout;
    private JToggleButton btndonhang;
    private JToggleButton btnsanpham;
    private JToggleButton btnkhachhang;
    private JToggleButton btnbanhang;
    private JToggleButton btnbaocao;
    private JPanel panel_cardlayout;
    private JPanel panel_cardlayout_baocao;
    private JPanel pageProduct = new PageProducts();
    private JPanel pageOrder = new PageOrders();
    private JPanel pageCreateOrder;
    private JPanel panel_cardlayout_khachhang;
    private JPopupMenu popupMenu;
    private JLabel lblNewLabel_1;
    private JMenuItem mntmdangxuat;
    static JMenuItem mntmhoso;
    static JMenuItem mntmQLNhanVien;
    private JPanel panel_cardlayout_tongquan;
    private JSeparator separator;
    static boolean check;
    static boolean isUserDetailsEnable;

    public void run() {
        EventQueue.invokeLater(() -> {
            try {
                this.setVisible(true);
            } catch (Exception e) {
                e.printStackTrace();
            }
        });
    }

    private MetalToggleButtonUI metal = new MetalToggleButtonUI() {
        @Override
        protected Color getSelectColor() {
            return color_enter;
        }
    };

    private void showlayout(String s) {
        cardlayout.show(panel_cardlayout, s);
    }

    private void addPopup(Component component, final JPopupMenu popup) {
        component.addMouseListener(new MouseAdapter() {

            @Override
            public void mouseClicked(MouseEvent e) {
                if (e.getButton() == MouseEvent.BUTTON1) {
                    showMenu(e);
                }
            }

            private void showMenu(MouseEvent e) {
                popup.show(e.getComponent(), 0, e.getComponent().getHeight());
            }
        });
    }

    public Home() {
        addWindowFocusListener(new WindowFocusListener() {
            @Override
            public void windowLostFocus(WindowEvent arg0) {
            }

            @Override
            public void windowGainedFocus(WindowEvent arg0) {
                if (check) {
                    mntmQLNhanVien.setEnabled(check);
                }
                if (isUserDetailsEnable) {
                    mntmhoso.setEnabled(isUserDetailsEnable);
                }
            }
        });

        setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
        setBounds(100, 100, 1383, 773);
        contentPane = new JPanel();
        contentPane.setBorder(new EmptyBorder(5, 5, 5, 5));
        contentPane.setLayout(new BorderLayout(0, 0));
        setContentPane(contentPane);

        JPanel panelMenu = new JPanel();
        panelMenu.setBorder(new BevelBorder(BevelBorder.RAISED, new Color(64, 64, 64), new Color(64, 64, 64),
                Color.DARK_GRAY, Color.DARK_GRAY));
        panelMenu.setFocusable(false);
        panelMenu.setBackground(color_exit);

        JScrollPane scrollPane = new JScrollPane(panelMenu);
        scrollPane.setBorder(new EmptyBorder(0, 0, 0, 0));
        scrollPane.setHorizontalScrollBarPolicy(ScrollPaneConstants.HORIZONTAL_SCROLLBAR_NEVER);
        scrollPane.setViewportBorder(new TitledBorder(null, "", TitledBorder.LEADING, TitledBorder.TOP, null, null));

        GridBagLayout gbl_panelMenu = new GridBagLayout();
        gbl_panelMenu.columnWidths = new int[]{0, 0};
        gbl_panelMenu.rowHeights = new int[]{0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0};
        gbl_panelMenu.columnWeights = new double[]{1.0, Double.MIN_VALUE};
        gbl_panelMenu.rowWeights = new double[]{0.0, 0.0, 0.0, 0.0, 0.0, 0.0, 0.0, 0.0, 0.0, 0.0, 0.0, 0.0, 1.0,
                Double.MIN_VALUE};
        panelMenu.setLayout(gbl_panelMenu);

        txtWelcome = new JTextField();
        txtWelcome.setOpaque(false);
        txtWelcome.setHorizontalAlignment(SwingConstants.CENTER);
        txtWelcome.setEditable(false);
        txtWelcome.setFont(new Font("Arial", Font.BOLD, 26));
        txtWelcome.setText("Welcome\r\n");
        txtWelcome.setBorder(new EmptyBorder(0, 0, 0, 0));
        txtWelcome.setForeground(Color.WHITE);
        txtWelcome.setPreferredSize(new Dimension(300, 70));
        GridBagConstraints gbc_txtWelcome = new GridBagConstraints();
        gbc_txtWelcome.insets = new Insets(0, 0, 5, 0);
        gbc_txtWelcome.fill = GridBagConstraints.BOTH;
        gbc_txtWelcome.gridx = 0;
        gbc_txtWelcome.gridy = 0;
        panelMenu.add(txtWelcome, gbc_txtWelcome);
        txtWelcome.setColumns(10);

        btndonhang = new JToggleButton("Đơn Hàng");
        btndonhang.setUI(metal);
        btndonhang.setIconTextGap(35);
        btndonhang.setIcon(new ImageIcon("Imager\\donhang2.png"));
        btndonhang.setHorizontalTextPosition(SwingConstants.RIGHT);
        btndonhang.setFocusable(false);
        btndonhang.addMouseListener(new MouseAdapter() {
            @Override
            public void mouseEntered(MouseEvent arg0) {
                btndonhang.setBackground(color_enter);
            }

            @Override
            public void mouseExited(MouseEvent e) {
                btndonhang.setBackground(color_exit);
            }

            @Override
            public void mouseReleased(MouseEvent e) {
                if (e.getButton() == MouseEvent.BUTTON1)
                    showlayout("donhang");
            }
        });


        btndonhang.setBorder(new EmptyBorder(0, 0, 0, 0));
        btndonhang.setPreferredSize(new Dimension(300, 60));
        btndonhang.setForeground(Color.WHITE);
        btndonhang.setBackground(color_exit);
        btndonhang.setFont(new Font("Arial", Font.PLAIN, 17));
        GridBagConstraints gbc_btndonhang = new GridBagConstraints();
        gbc_btndonhang.fill = GridBagConstraints.VERTICAL;
        gbc_btndonhang.gridx = 0;
        gbc_btndonhang.gridy = 2;
        panelMenu.add(btndonhang, gbc_btndonhang);

        btnsanpham = new JToggleButton("Sản Phẩm");
        btnsanpham.setUI(metal);
        btnsanpham.setIconTextGap(38);
        btnsanpham.setIcon(new ImageIcon("Imager\\sanpham.png"));
        btnsanpham.setHorizontalTextPosition(SwingConstants.RIGHT);
        btnsanpham.setFocusable(false);
        btnsanpham.addMouseListener(new MouseAdapter() {
            @Override
            public void mouseEntered(MouseEvent arg0) {
                btnsanpham.setBackground(color_enter);

            }

            @Override
            public void mouseExited(MouseEvent e) {
                btnsanpham.setBackground(color_exit);
            }

            @Override
            public void mouseReleased(MouseEvent e) {
                if (e.getButton() == MouseEvent.BUTTON1)
                    showlayout("sp");
            }
        });

        btnsanpham.setBorder(new EmptyBorder(0, 0, 0, 0));
        btnsanpham.setPreferredSize(new Dimension(300, 60));
        btnsanpham.setForeground(Color.WHITE);
        btnsanpham.setBackground(color_exit);
        btnsanpham.setFont(new Font("Arial", Font.PLAIN, 17));
        GridBagConstraints gbc_btnsanpham = new GridBagConstraints();
        gbc_btnsanpham.gridx = 0;
        gbc_btnsanpham.gridy = 3;
        panelMenu.add(btnsanpham, gbc_btnsanpham);

//        btnkhachhang = new JToggleButton("Khách Hàng");
//        btnkhachhang.setUI(metal);
//        btnkhachhang.setIconTextGap(30);
//        btnkhachhang.setIcon(new ImageIcon("Imager\\khachhang2-35.png"));
//        btnkhachhang.setHorizontalTextPosition(SwingConstants.RIGHT);
//        btnkhachhang.setFocusable(false);
//        btnkhachhang.addMouseListener(new MouseAdapter() {
//            @Override
//            public void mouseEntered(MouseEvent arg0) {
//                btnkhachhang.setBackground(color_enter);
//            }
//
//            @Override
//            public void mouseExited(MouseEvent e) {
//                btnkhachhang.setBackground(color_exit);
//            }
//
//            @Override
//            public void mouseReleased(MouseEvent e) {
//                if (e.getButton() == MouseEvent.BUTTON1)
//                    showlayout("kh");
//            }
//        });
//        btnkhachhang.setBorder(new EmptyBorder(0, 0, 0, 0));
//        btnkhachhang.setPreferredSize(new Dimension(300, 60));
//        btnkhachhang.setForeground(Color.WHITE);
//        btnkhachhang.setBackground(color_exit);
//        btnkhachhang.setFont(new Font("Arial", Font.PLAIN, 17));
//        GridBagConstraints gbc_btnkhachhang = new GridBagConstraints();
//        gbc_btnkhachhang.gridx = 0;
//        gbc_btnkhachhang.gridy = 4;
//        panelMenu.add(btnkhachhang, gbc_btnkhachhang);

        btnbanhang = new JToggleButton("Bán Hàng");
        btnbanhang.setVisible(true);
        btnbanhang.setUI(metal);
        btnbanhang.setIconTextGap(37);
        btnbanhang.setIcon(new ImageIcon("Imager\\banhang-30.png"));
        btnbanhang.setHorizontalTextPosition(SwingConstants.RIGHT);
        btnbanhang.setFocusable(false);
        btnbanhang.addMouseListener(new MouseAdapter() {
            @Override
            public void mouseEntered(MouseEvent arg0) {
                btnbanhang.setBackground(color_enter);
            }

            @Override
            public void mouseExited(MouseEvent e) {
                btnbanhang.setBackground(color_exit);
            }

            @Override
            public void mouseReleased(MouseEvent e) {
                if (e.getButton() == MouseEvent.BUTTON1)
                    new FrameCreateOrder().run();
            }
        });
        btnbanhang.setBorder(new EmptyBorder(0, 0, 0, 0));
        btnbanhang.setPreferredSize(new Dimension(300, 60));
        btnbanhang.setForeground(Color.WHITE);
        btnbanhang.setBackground(color_exit);
        btnbanhang.setFont(new Font("Arial", Font.PLAIN, 17));
        GridBagConstraints gbc_btnbanhang = new GridBagConstraints();
        gbc_btnbanhang.fill = GridBagConstraints.VERTICAL;
        gbc_btnbanhang.gridx = 0;
        gbc_btnbanhang.gridy = 5;
        panelMenu.add(btnbanhang, gbc_btnbanhang);

        btnbaocao = new JToggleButton("Báo Cáo");
        btnbaocao.setUI(metal);
        btnbaocao.setIconTextGap(35);
        btnbaocao.setIcon(new ImageIcon("Imager\\baocao2.png"));
        btnbaocao.setHorizontalTextPosition(SwingConstants.RIGHT);
        btnbaocao.setFocusable(false);
        btnbaocao.addMouseListener(new MouseAdapter() {
            @Override
            public void mouseEntered(MouseEvent arg0) {
                btnbaocao.setBackground(color_enter);
            }

            @Override
            public void mouseExited(MouseEvent e) {
                btnbaocao.setBackground(color_exit);
            }

            @Override
            public void mouseReleased(MouseEvent e) {
                if (e.getButton() == MouseEvent.BUTTON1)
                    showlayout("baocao");
            }
        });
        btnbaocao.setBorder(new EmptyBorder(0, 0, 0, 0));
        btnbaocao.setPreferredSize(new Dimension(300, 60));
        btnbaocao.setForeground(Color.WHITE);
        btnbaocao.setBackground(color_exit);
        btnbaocao.setFont(new Font("Arial", Font.PLAIN, 17));
        GridBagConstraints gbc_btnbaocao = new GridBagConstraints();
        gbc_btnbaocao.fill = GridBagConstraints.VERTICAL;
        gbc_btnbaocao.gridx = 0;
        gbc_btnbaocao.gridy = 6;
        panelMenu.add(btnbaocao, gbc_btnbaocao);

        lblNewLabel_1 = new JLabel("Anh Nhật");
        lblNewLabel_1.setForeground(Color.CYAN);
        lblNewLabel_1.setPreferredSize(new Dimension(27, 35));
        GridBagConstraints gbc_lblNewLabel_1 = new GridBagConstraints();
        gbc_lblNewLabel_1.anchor = GridBagConstraints.SOUTH;
        gbc_lblNewLabel_1.gridx = 0;
        gbc_lblNewLabel_1.gridy = 12;
        panelMenu.add(lblNewLabel_1, gbc_lblNewLabel_1);
        contentPane.add(scrollPane, BorderLayout.WEST);
        /*
         *
         *
         */
        ButtonGroup group = new ButtonGroup();
        group.add(btnbanhang);
        group.add(btnbaocao);
        group.add(btndonhang);
        //     group.add(btnkhachhang);
        group.add(btnsanpham);
        /*
         *
         *
         */
        JPanel panel_2 = new JPanel();
        panel_2.setBackground(Color.WHITE);
        contentPane.add(panel_2, BorderLayout.CENTER);
        panel_2.setLayout(new BorderLayout(0, 0));

        JPanel panel_1 = new JPanel();
        panel_1.setBorder(new BevelBorder(BevelBorder.RAISED, null, null, new Color(0, 0, 255), null));
        panel_1.setBackground(new Color(240, 240, 240));
        panel_2.add(panel_1, BorderLayout.NORTH);

        panel_1.setLayout(new FlowLayout(FlowLayout.RIGHT, 15, 0));
        JLabel btnNewButton_8 = new JLabel(new ImageIcon("Imager\\account-45.png"));
        btnNewButton_8.setFocusable(false);
        btnNewButton_8.setHorizontalTextPosition(SwingConstants.RIGHT);
        btnNewButton_8.setToolTipText("Tài Khoản");
        btnNewButton_8.setBorder(null);
        panel_1.add(btnNewButton_8);

        popupMenu = new JPopupMenu();
        popupMenu.setBorder(new LineBorder(new Color(0, 0, 0), 1, true));
        popupMenu.setFont(new Font("Arial", Font.BOLD, 17));
        popupMenu.setBackground(Color.WHITE);
        addPopup(btnNewButton_8, popupMenu);

        mntmhoso = new JMenuItem("Hồ Sơ");
        mntmhoso.setPreferredSize(new Dimension(200, 40));
        mntmhoso.setOpaque(false);
        mntmhoso.addActionListener(e -> {
            if (e.getSource() == mntmhoso) {
                new UserDetails().run();
            }
        });
        mntmhoso.setFont(new Font("Arial", Font.BOLD, 15));
        popupMenu.add(mntmhoso);

        mntmQLNhanVien = new JMenuItem("Quản lý nhân viên");
        mntmQLNhanVien.setEnabled(false);
        //   mntmQLNhanVien.addActionListener(e -> new jframe_quanlyNhanVien().run() );
        mntmQLNhanVien.setPreferredSize(new Dimension(200, 40));
        mntmQLNhanVien.setOpaque(false);
        mntmQLNhanVien.setFont(new Font("Arial", Font.BOLD, 15));
        mntmQLNhanVien.setBackground(Color.WHITE);
        popupMenu.add(mntmQLNhanVien);

        mntmdangxuat = new JMenuItem("Đăng Xuất");
        mntmdangxuat.setPreferredSize(new Dimension(200, 40));
        mntmdangxuat.setOpaque(false);
        mntmdangxuat.addActionListener(e -> {
            if (e.getSource() == mntmdangxuat) {
//					dispose();
//					DangNhap dangnhap = new DangNhap();
//					dangnhap.run();
                System.exit(0);
            }
        });

        separator = new JSeparator();
        separator.setRequestFocusEnabled(false);
        separator.setForeground(Color.BLACK);
        popupMenu.add(separator);

        mntmdangxuat.setFont(new Font("Arial", Font.BOLD, 15));
        mntmdangxuat.setBackground(Color.WHITE);
        popupMenu.add(mntmdangxuat);

        cardlayout = new CardLayout();
        panel_cardlayout = new JPanel(cardlayout);
        panel_cardlayout.setBackground(Color.WHITE);

//        panel_cardlayout_baocao = new cardpanel_baocao();
//        panel_cardlayout.add(panel_cardlayout_baocao, "baocao");
//
        panel_cardlayout.add(pageProduct, "sp");
        panel_cardlayout.add(pageOrder, "donhang");
//
//        panel_cardlayout_khachhang = new cardpanel_khachhang();
//        panel_cardlayout.add(panel_cardlayout_khachhang, "kh");

        panel_2.add(panel_cardlayout, BorderLayout.CENTER);
    }


}
