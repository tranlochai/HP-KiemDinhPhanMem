package GUI;

import javax.swing.*;
import javax.swing.border.EmptyBorder;
import javax.swing.table.DefaultTableCellRenderer;
import javax.swing.table.DefaultTableModel;
import javax.swing.table.JTableHeader;
import java.awt.*;
import java.awt.event.*;

public class CreateOrder extends JPanel {
    static int index;
    private JTable table_DH;
    static JTabbedPane tabbedPane;
    private JTextField textField_thanhtimkiem;
    private JComboBox comboBox;
    private JButton btnxoa_donhang;

    private void refresh() {
        DefaultTableModel datarow = (DefaultTableModel) table_DH.getModel();
        datarow.setRowCount(0);
    }

    public CreateOrder(){
        setBorder(new EmptyBorder(5, 5, 5, 5));
        setLayout(new BorderLayout(0, 0));
        JPanel panel = new JPanel();
        add(panel, BorderLayout.NORTH);
        panel.setLayout(new FlowLayout(FlowLayout.LEFT, 30, 20));

        JLabel lbrefresh = new JLabel("Refresh");
        lbrefresh.addMouseListener(new MouseAdapter() {
            @Override
            public void mouseClicked(MouseEvent e) {
                if (e.getButton() == MouseEvent.BUTTON1) {
                    refresh();
                    /**
                     *
                     */
                   // showDH();
                }
            }
        });
        lbrefresh.setCursor(Cursor.getPredefinedCursor(Cursor.HAND_CURSOR));
        lbrefresh.setIconTextGap(10);
        lbrefresh.setIcon(new ImageIcon("Imager\\refresh-25.png"));
        lbrefresh.setFont(new Font("Arial", Font.PLAIN, 22));
        panel.add(lbrefresh);

        btnxoa_donhang = new JButton("Xóa");
        /**
         *
         * setEnabled
         */
        btnxoa_donhang.setEnabled(false);
        btnxoa_donhang.setCursor(Cursor.getPredefinedCursor(Cursor.HAND_CURSOR));
        btnxoa_donhang.setIcon(new ImageIcon("Imager\\xoa-25.png"));
        btnxoa_donhang.addActionListener(arg0 -> {

            int[] soluong = table_DH.getSelectedRows();
            int xacnhan = JOptionPane.showConfirmDialog(null, "xác nhận xóa " + soluong.length + " đơn hàng");
            if (xacnhan == JOptionPane.YES_OPTION) {
                /**
                 *
                 */
                // xoaDH(soluong);
            }
        });
        btnxoa_donhang.setBorder(null);
        btnxoa_donhang.setBorderPainted(false);
        btnxoa_donhang.setContentAreaFilled(false);
        btnxoa_donhang.setIconTextGap(10);
        btnxoa_donhang.setFont(new Font("Arial", Font.PLAIN, 22));
        panel.add(btnxoa_donhang);

        JButton btnnhapfile = new JButton("Nhập File");
        btnnhapfile.setBorder(null);
        btnnhapfile.setBorderPainted(false);
        btnnhapfile.setContentAreaFilled(false);
        btnnhapfile.addActionListener(new ActionListener() {

            @Override
            public void actionPerformed(ActionEvent e) {
                JOptionPane.showMessageDialog(null, "tính năng đang phát triển");
            }
        });
        btnnhapfile.setCursor(Cursor.getPredefinedCursor(Cursor.HAND_CURSOR));
        btnnhapfile.setVerticalTextPosition(SwingConstants.BOTTOM);
        btnnhapfile.setIconTextGap(10);
        btnnhapfile.setIcon(new ImageIcon("Imager\\nhapfile-30.png"));
        btnnhapfile.setFont(new Font("Arial", Font.PLAIN, 22));
        panel.add(btnnhapfile);

        JButton btnxuatfile = new JButton("Xuất File");
        btnxuatfile.addActionListener(new ActionListener() {

            @Override
            public void actionPerformed(ActionEvent arg0) {
                JOptionPane.showMessageDialog(null, "tính năng đang phát triển");
            }
        });
        btnxuatfile.setBorder(null);
        btnxuatfile.setBorderPainted(false);
        btnxuatfile.setContentAreaFilled(false);
        btnxuatfile.setCursor(Cursor.getPredefinedCursor(Cursor.HAND_CURSOR));
        btnxuatfile.setIcon(new ImageIcon("Imager\\xuatfile2-30.png"));
        btnxuatfile.setVerticalTextPosition(SwingConstants.BOTTOM);
        btnxuatfile.setFont(new Font("Arial", Font.PLAIN, 22));
        panel.add(btnxuatfile);

        tabbedPane = new JTabbedPane(JTabbedPane.TOP);
        tabbedPane.setFont(new Font("Tahoma", Font.PLAIN, 17));
        add(tabbedPane, BorderLayout.CENTER);

        JPanel pn_center = new JPanel(new BorderLayout());

        JPanel pn_thanhtimkiem = new JPanel(new FlowLayout(FlowLayout.LEFT, 5, 10));
        pn_thanhtimkiem.setBackground(Color.WHITE);
        pn_center.add(pn_thanhtimkiem, BorderLayout.NORTH);

        JLabel lblNewLabel = new JLabel("");
        lblNewLabel.setIcon(new ImageIcon("Imager\\timkiem-25.png"));
        pn_thanhtimkiem.add(lblNewLabel);

        JSplitPane splitPane = new JSplitPane();
        splitPane.setBorder(null);
        pn_thanhtimkiem.add(splitPane);

        textField_thanhtimkiem = new JTextField();
        textField_thanhtimkiem.addKeyListener(new KeyAdapter() {
            @Override
            public void keyReleased(KeyEvent e) {
                String txt = textField_thanhtimkiem.getText().trim();
                String dieukien = (String) comboBox.getSelectedItem();
                if (e.getKeyCode() == 10) {
                    /**
                     *
                     */
                   // ds_timkiemDH(txt, dieukien);
                }
            }
        });
        textField_thanhtimkiem.setFont(new Font("Arial", Font.PLAIN, 20));
        splitPane.setLeftComponent(textField_thanhtimkiem);
        textField_thanhtimkiem.setColumns(30);

        comboBox = new JComboBox();
        comboBox.setBorder(UIManager.getBorder("TextField.border"));
        comboBox.setModel(new DefaultComboBoxModel(new String[] { "MaHD", "Ngaytao", "MaNV", "MaKH" ,"MaHang"}));
        comboBox.setFont(new Font("Arial", Font.PLAIN, 17));
        comboBox.setMaximumRowCount(15);
        splitPane.setRightComponent(comboBox);

        JScrollPane scrollPane = new JScrollPane();
        scrollPane.getVerticalScrollBar().setUnitIncrement(10);
        pn_center.add(scrollPane, BorderLayout.CENTER);
        scrollPane.setFont(new Font("Tahoma", Font.PLAIN, 19));
        tabbedPane.addTab("Đơn hàng", new ImageIcon("Imager\\donhang-20.png"), pn_center, null);
        tabbedPane.setTabComponentAt(index++, null);

        JPopupMenu popupMenu = new Tablepopup(table_DH);
        popupMenu.setFocusable(false);
        table_DH = new JTable();
        table_DH.setSelectionMode(ListSelectionModel.MULTIPLE_INTERVAL_SELECTION);

        DefaultTableModel setEdit = new DefaultTableModel(
                new String[] { "", "Mã đơn hàng", "Ngày tạo", "Giảm giá", "Thành tiền" }, 0) {

            // ghi de phuong thuc iscelleditable
            @Override
            public boolean isCellEditable(int row, int column) {
                // khong cho chinh sua column 3 return column !=3

                // khong cho chinh sua
                return false;
            }
        };
        table_DH.setModel(setEdit);
        table_DH.addMouseListener(new MouseAdapter() {
            @Override
            public void mouseClicked(MouseEvent e) {
                if (e.getButton() == MouseEvent.BUTTON3) {
                    // lay ROW MÀ MOUSE ĐANG TRỎ
                    int row = table_DH.rowAtPoint(e.getPoint());
                    if (row != -1) {
                        table_DH.setRowSelectionInterval(row, row);
                        popupMenu.show(table_DH, e.getX(), e.getY());
                    }
                }
            }
        });
        table_DH.setFont(new Font("Arial", Font.PLAIN, 22));
        table_DH.setFillsViewportHeight(true);
        table_DH.setRowHeight(45);
        table_DH.getColumn("").setMaxWidth(70);
        scrollPane.setViewportView(table_DH);

        // custom table

        table_DH.getColumn("Giảm giá").setCellRenderer(new DefaultTableCellRenderer() {
            @Override
            public void setHorizontalAlignment(int alignment) {
                super.setHorizontalAlignment(DefaultTableCellRenderer.RIGHT);
            }
        });
        table_DH.getColumn("Giảm giá").setMinWidth(100);
        table_DH.getColumn("Giảm giá").setMaxWidth(200);
        table_DH.getColumn("Thành tiền").setCellRenderer(new DefaultTableCellRenderer() {
            @Override
            public void setHorizontalAlignment(int alignment) {
                super.setHorizontalAlignment(DefaultTableCellRenderer.CENTER);
            }
        });

        JTableHeader header = table_DH.getTableHeader();
        header.setFont(new Font("Arial", Font.BOLD, 20));
        header.setBackground(new Color(240, 240, 240));
        header.setOpaque(false);
        header.setPreferredSize(new Dimension(100, 50));
    }

    private void themTab() {
//        try {
//            int row = table_DH.getSelectedRow();
//            chitietDonHang chitiet = new chitietDonHang(table_DH.getValueAt(row, 1));
//            tabbedPane.add(chitiet, "Đơn hàng " + (index), index);
//            tabbedPane.setTabComponentAt(index, new customJTabbedpane(this));
//            tabbedPane.setSelectedIndex(index);
//            index++;
//        } catch (Exception e1) {
//            System.out.println("cardpanel_donhang - themTab: " + e1.getMessage());
//        }
    }

    static void removeTab(int i) {
        tabbedPane.remove(i);
        index--;
    }

    class Tablepopup extends JPopupMenu {

        private JMenuItem item_xem;
       // private JMenuItem item_sua;
        JTable table;

        public Tablepopup(JTable table) {
            this.table = table;
            item_xem = new JMenuItem("Xem", new ImageIcon("Imager\\eyes-18.png"));
            item_xem.addActionListener(new ActionListener() {

                @Override
                public void actionPerformed(ActionEvent e) {
                    String event = e.getActionCommand();
                    if (event.equalsIgnoreCase("xem"))
                        themTab();
                }
            });
            item_xem.setPreferredSize(new Dimension(250, 30));
            item_xem.setFont(new Font("Arial", Font.PLAIN, 14));
            add(item_xem);

            add(new Separator());

//            item_sua = new JMenuItem("Sửa");
//            item_sua.addActionListener(new ActionListener() {
//
//                @Override
//                public void actionPerformed(ActionEvent e) {
//                    themTab();
//                }
//            });
//            item_sua.setPreferredSize(new Dimension(250, 30));
//            item_sua.setFont(new Font("Arial", Font.PLAIN, 14));
//            add(item_sua);
        }
    }

    class customJTabbedpane extends JPanel {

        private CreateOrder customTabbed;

        public customJTabbedpane(CreateOrder tabbed) {
            customTabbed = tabbed;
            setLayout(new FlowLayout(FlowLayout.LEFT, 7, 5));
            setOpaque(false);
            setBorder(null);
            addlabel();
            addbutton();
        }

        private void addlabel() {
            JLabel lb = new JLabel() {
                public String getText() { // GHI DE PHUONG THUC getText();
                    int i = tabbedPane.indexOfTabComponent(customJTabbedpane.this);
                    if (i != -1)
                        return tabbedPane.getTitleAt(i);
                    return null;
                }
            };
            add(lb);
        }

        private void addbutton() {
            JButton bt = new JButton();
            // duong vien
            bt.setBorderPainted(false);
            // noi dung
            bt.setContentAreaFilled(false);
            bt.setPreferredSize(new Dimension(20, 20));
            bt.addMouseListener(new MouseAdapter() {
                @Override
                public void mouseClicked(MouseEvent e) {
                    int i = tabbedPane.indexOfTabComponent(customJTabbedpane.this);
                    removeTab(i);
                }
            });

            bt.setIcon(new ImageIcon("Imager\\delete_15px.png"));
            bt.setCursor(Cursor.getPredefinedCursor(Cursor.HAND_CURSOR));
            add(bt);
        }

    }
}
