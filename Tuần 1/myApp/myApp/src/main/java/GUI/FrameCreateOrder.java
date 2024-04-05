package GUI;

import javax.swing.*;
import javax.swing.event.ChangeListener;
import java.awt.*;
import java.awt.event.*;

public class FrameCreateOrder extends JFrame implements ActionListener {
    @Override
    public void actionPerformed(ActionEvent e) {
        Object action = e.getSource();
        if (action == mnthemDH) {
            tabbedPane.setSelectedIndex(tabbedPane.getTabCount() - 1);
        } else if (action == mnthemSP) {
            new DialogAddProduct().run();
        }
//        else if (action == mnthemKH) {
//            themkhachhang them = new themkhachhang();
//            them.run();
//        } else if (action == mnthemNKH) {
//            themnhomkhachhang them = new themnhomkhachhang();
//            them.run();
//        }
        else if (action == mnthoat) {
            dispose();
        }
    }

    public void addTab() {
        try {
            int i = tabbedPane.getTabCount() - 1;
            if (i == tabbedPane.getSelectedIndex()) {
                tabbedPane.setComponentAt(i, new PageCreateOrder(this));
                tabbedPane.setTabComponentAt(i, new AddTab(this));
                tabbedPane.setSelectedIndex(i);
                tabbedPane.add(null, "+", tabbedPane.getTabCount());
            }
        } catch (Exception e) {
            JOptionPane.showMessageDialog(null, "cannot add Tab " + e);
        }
    }

    public void removeTab(int index) {
        if (index > -1) {
            tabbedPane.setSelectedIndex(index - 1);
            tabbedPane.remove(index);
            if (tabbedPane.getTabCount() == 1) {
               tabbedPane.setSelectedIndex( tabbedPane.getTabCount()-1 );
            }
        }
    }

    ChangeListener changelistener = e -> addTab();

    public JTabbedPane tabbedPane;
    private JMenuItem mnthemSP;
    private JMenuItem mnthemKH;
    private JMenuItem mnthemNKH;
    private JMenuItem mnthemDH;
    private JMenuItem mnthoat;

    public void run() {
        EventQueue.invokeLater(() -> {
            try {
                setTitle("1.5.0");
                setVisible(true);
            } catch (Exception e) {
                JOptionPane.showMessageDialog(null, e);
            }
        });
    }

    public FrameCreateOrder() {
        setDefaultCloseOperation(JFrame.DISPOSE_ON_CLOSE);
        setBounds(100, 100, 1383, 773);
        getContentPane().setLayout(new BorderLayout(0, 0));

        tabbedPane = new JTabbedPane(JTabbedPane.TOP);
        tabbedPane.setFont(new Font("Tahoma", Font.PLAIN, 17));
        tabbedPane.setTabLayoutPolicy(JTabbedPane.SCROLL_TAB_LAYOUT);
        getContentPane().add(tabbedPane, BorderLayout.CENTER);

        tabbedPane.add(new PageCreateOrder(this), "Đơn ");
        tabbedPane.setTabComponentAt(0, new AddTab(this));
        tabbedPane.setSelectedIndex(0);
        // them dau cong
        tabbedPane.add(null, "+", tabbedPane.getTabCount());

        // LUU Y : ĐẶT EVENT Ở CUỐI CÙNG
        tabbedPane.addChangeListener(changelistener);

        JMenuBar menuBar = new JMenuBar();
        setJMenuBar(menuBar);

        JMenu mnNewMenu = new JMenu("Thêm");
        mnNewMenu.setFont(new Font("Arial", Font.PLAIN, 16));
        mnNewMenu.setHorizontalAlignment(SwingConstants.CENTER);
        mnNewMenu.setPreferredSize(new Dimension(65, 24));
        menuBar.add(mnNewMenu);

        mnthemSP = new JMenuItem("Thêm sản phẩm");
        mnthemSP.addActionListener(this);
        mnthemSP.setAccelerator(KeyStroke.getKeyStroke(KeyEvent.VK_S, InputEvent.ALT_MASK));
        mnthemSP.setIconTextGap(7);
        mnthemSP.setPreferredSize(new Dimension(300, 30));
        mnthemSP.setIcon(new ImageIcon("Imager\\themsp-25.png"));
        mnthemSP.setFont(new Font("Arial", Font.PLAIN, 17));
        mnNewMenu.add(mnthemSP);

        mnthemKH = new JMenuItem("Thêm khách hàng");
        mnthemKH.addActionListener(this);
        mnthemKH.setAccelerator(KeyStroke.getKeyStroke(KeyEvent.VK_K, InputEvent.ALT_MASK));
        mnthemKH.setIconTextGap(7);
        mnthemKH.setPreferredSize(new Dimension(250, 30));
        mnthemKH.setIcon(new ImageIcon("Imager\\themkhachhang-25.png"));
        mnthemKH.setFont(new Font("Arial", Font.PLAIN, 17));
        mnNewMenu.add(mnthemKH);

        mnthemNKH = new JMenuItem("Thêm nhóm KH");
        mnthemNKH.addActionListener(this);
        mnthemNKH.setAccelerator(KeyStroke.getKeyStroke(KeyEvent.VK_N, InputEvent.ALT_MASK));
        mnthemNKH.setIconTextGap(7);
        mnthemNKH.setPreferredSize(new Dimension(250, 30));
        mnthemNKH.setIcon(new ImageIcon("Imager\\themnhomkhachhang-25.png"));
        mnthemNKH.setFont(new Font("Arial", Font.PLAIN, 17));
        mnNewMenu.add(mnthemNKH);

        mnthemDH = new JMenuItem("Thêm đơn hàng");
        mnthemDH.addActionListener(this);
        mnthemDH.setAccelerator(KeyStroke.getKeyStroke(KeyEvent.VK_N, InputEvent.SHIFT_MASK | InputEvent.CTRL_MASK));
        mnthemDH.setPreferredSize(new Dimension(250, 30));
        mnthemDH.setIconTextGap(7);
        mnthemDH.setFont(new Font("Arial", Font.PLAIN, 17));
        mnNewMenu.add(mnthemDH);

        JSeparator separator = new JSeparator();
        separator.setPreferredSize(new Dimension(250, 3));
        mnNewMenu.add(separator);

        mnthoat = new JMenuItem("Thoát");
        mnthoat.addActionListener(this);
        mnthoat.setPreferredSize(new Dimension(250, 30));
        mnthoat.setHorizontalAlignment(SwingConstants.LEFT);
        mnthoat.setFont(new Font("Arial", Font.PLAIN, 17));
        mnNewMenu.add(mnthoat);

        addWindowListener(new WindowAdapter() {
            @Override
            public void windowOpened(WindowEvent e) {
//                boolean action_them = DangNhap.action_them;
//                mnthemKH.setEnabled(action_them);
//                mnthemNKH.setEnabled(action_them);
//                mnthemSP.setEnabled(action_them);
            }
        });
    }
}
