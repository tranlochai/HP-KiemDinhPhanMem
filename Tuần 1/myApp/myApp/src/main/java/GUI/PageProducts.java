package GUI;

import Entity.Product;
import Service.ProductService;
import Utils.Utils;

import javax.swing.*;
import javax.swing.border.EmptyBorder;
import javax.swing.table.DefaultTableCellRenderer;
import javax.swing.table.DefaultTableModel;
import javax.swing.table.JTableHeader;
import java.awt.*;
import java.awt.event.*;
import java.util.Arrays;
import java.util.List;
import java.util.stream.Collectors;

public class PageProducts extends JPanel {

    private JTable table_sp;
    private JTextField textField_timkiem;
    private JComboBox comboBox;
    static JTabbedPane tabbedPane;
    private CardLayout card;
    ////// vi tri tab trong tabbedpane
    static int index;
    private JButton btnthem_sp;
    private JButton btnxoa_sp;
    private JLabel lbrefresh;
    private ProductService productService = new ProductService();

    public PageProducts() {
        GUI();
        refresh();
    }

    private void GUI() {
        card = new CardLayout();
        setLayout(card);

        setPreferredSize(new Dimension(1120, 749));
        setBorder(new EmptyBorder(5, 5, 5, 5));
        setLayout(new BorderLayout(0, 0));

        table_sp = new JTable();
        table_sp.setSelectionMode(ListSelectionModel.MULTIPLE_INTERVAL_SELECTION);
        table_sp.setFont(new Font("Arial", Font.PLAIN, 22));
        table_sp.setPreferredScrollableViewportSize(new Dimension(350, 400));
        table_sp.setModel(new DefaultTableModel(new Object[][]{},
                new String[]{" ", "Mã hàng", "Tên hàng", "DVT", "Giá bán"}) {
            @Override
            public boolean isCellEditable(int arg0, int arg1) {
                return false;
            }
        });
        table_sp.getColumnModel().getColumn(0).setMaxWidth(70);
        table_sp.getColumnModel().getColumn(3).setMaxWidth(120);
        table_sp.setRowHeight(45);
        table_sp.setFillsViewportHeight(true);

        JTableHeader header = table_sp.getTableHeader();
        header.setFont(new Font("Arial", Font.BOLD, 20));
        header.setBackground(new Color(240, 240, 240));
        header.setOpaque(false);
        header.setPreferredSize(new Dimension(100, 50));

        table_sp.getColumn("Mã hàng").setCellRenderer(new DefaultTableCellRenderer() {
            @Override
            public void setForeground(Color c) {
                super.setForeground(Color.blue);
            }
        });
        table_sp.addMouseMotionListener(new MouseMotionListener() {
            @Override
            public void mouseMoved(MouseEvent e) {
                int column = table_sp.columnAtPoint(e.getPoint());
                if (column == 1) {
                    table_sp.setCursor(Cursor.getPredefinedCursor(Cursor.HAND_CURSOR));
                } else table_sp.setCursor(null);
            }

            @Override
            public void mouseDragged(MouseEvent e) {
            }
        });
        table_sp.addMouseListener(new MouseAdapter() {
            @Override
            public void mouseClicked(MouseEvent e) {
                int mouse = e.getButton();
                int row = table_sp.rowAtPoint(e.getPoint());
                int column = table_sp.getSelectedColumn();
                if (mouse == MouseEvent.BUTTON1 && column == 1 && row > -1) {
                    String id = (String) table_sp.getValueAt(row, column);
                    JPanel pn = new JPanel(new FlowLayout(FlowLayout.LEFT, 7, 5));
                    JButton remove = new JButton(new ImageIcon("Imager\\delete_15px.png"));
                    remove.setCursor(Cursor.getPredefinedCursor(Cursor.HAND_CURSOR));
                    remove.setBorderPainted(false);
                    remove.setContentAreaFilled(false);
                    remove.setPreferredSize(new Dimension(20, 20));
                    JLabel title = new JLabel(id);
                    pn.add(title);
                    remove.addMouseListener(new MouseAdapter() {
                        @Override
                        public void mouseClicked(MouseEvent e) {
                            tabbedPane.remove(Utils.getIndexWithTitle(tabbedPane, id));
                        }
                    });
                    pn.add(remove);
                    int i = Utils.getIndexWithTitle(tabbedPane, id);
                    if (i == -1) {
                        tabbedPane.addTab(id, new ProductDetails(id));
                        tabbedPane.setTabComponentAt(tabbedPane.getTabCount() - 1, pn);
                        tabbedPane.setSelectedIndex(tabbedPane.getTabCount() - 1);
                    } else tabbedPane.setSelectedIndex(i);
                }
            }
        });
        JScrollPane scrollPane = new JScrollPane(table_sp);
        scrollPane.setHorizontalScrollBarPolicy(ScrollPaneConstants.HORIZONTAL_SCROLLBAR_NEVER);

        JPanel panel_SP = new JPanel(new BorderLayout());
        panel_SP.add(scrollPane, BorderLayout.CENTER);

        tabbedPane = new JTabbedPane(SwingConstants.TOP, JTabbedPane.SCROLL_TAB_LAYOUT);
        tabbedPane.setFont(new Font("Arial", Font.PLAIN, 18));
        this.add(tabbedPane, BorderLayout.CENTER);
        tabbedPane.add(panel_SP, "Sản Phẩm", index++);

        JPanel panel_timkiem = new JPanel();
        panel_timkiem.setBackground(Color.WHITE);
        FlowLayout fl_panel_timkiem = (FlowLayout) panel_timkiem.getLayout();
        fl_panel_timkiem.setVgap(10);
        fl_panel_timkiem.setAlignment(FlowLayout.LEFT);
        panel_SP.add(panel_timkiem, BorderLayout.NORTH);

        JLabel lblNewLabel = new JLabel("");
        lblNewLabel.setIcon(new ImageIcon("Imager\\timkiem-25.png"));
        panel_timkiem.add(lblNewLabel);

        JSplitPane splitPane = new JSplitPane();
        panel_timkiem.add(splitPane);

        textField_timkiem = new JTextField();
        textField_timkiem.addKeyListener(new KeyAdapter() {
            @Override
            public void keyReleased(KeyEvent e) {
                String data = textField_timkiem.getText().trim();
                String catagory = (String) comboBox.getSelectedItem();
                switch (catagory) {
                    case "Mã hàng":
                        catagory = "product_Id";
                        break;
                    case "Tên hàng":
                        catagory = "product_Name";
                        break;
                    case "Giá bán":
                        catagory = "product_Price";
                        break;
                    case "Đơn vị tính":
                        catagory = "product_Unit";
                        break;
                    case "Ngày khởi tạo":
                        catagory = "createDate";
                        break;
                    case "Ngày sửa đổi":
                        catagory = "modifiedDate";
                        break;
                }
                if (e.getKeyCode() != 10)
                    return;
                if (Utils.isSpecialCharacters(data))
                    JOptionPane.showMessageDialog(null, "Dữ liệu nhập không được có ký tự đặc biệt");
                else productService.find(table_sp, data, catagory);
            }
        });
        textField_timkiem.setHorizontalAlignment(SwingConstants.LEFT);
        textField_timkiem.setFont(new Font("Arial", Font.PLAIN, 20));
        textField_timkiem.setColumns(30);
        splitPane.setLeftComponent(textField_timkiem);

        comboBox = new JComboBox();
        comboBox.setModel(new DefaultComboBoxModel(new String[]{"Mã hàng", "Tên hàng", "Giá bán", "Đơn vị tính", "Ngày khởi tạo", "Ngày sửa đổi"}));
        comboBox.setFont(new Font("Arial", Font.PLAIN, 17));
        splitPane.setRightComponent(comboBox);

        JPanel panel_bar = new JPanel();
        FlowLayout flowLayout = (FlowLayout) panel_bar.getLayout();
        flowLayout.setAlignment(FlowLayout.LEADING);
        flowLayout.setVgap(20);
        flowLayout.setHgap(30);
        add(panel_bar, BorderLayout.NORTH);

        lbrefresh = new JLabel("Refresh");
        lbrefresh.addMouseListener(new MouseAdapter() {
            @Override
            public void mouseClicked(MouseEvent e) {
                refresh();
            }
        });
        lbrefresh.setCursor(Cursor.getPredefinedCursor(Cursor.HAND_CURSOR));
        lbrefresh.setIconTextGap(7);
        lbrefresh.setIcon(new ImageIcon("Imager\\refresh-25.png"));
        lbrefresh.setFont(new Font("Arial", Font.PLAIN, 22));
        panel_bar.add(lbrefresh);

        btnthem_sp = new JButton("Thêm");
        btnthem_sp.setEnabled(true);
        btnthem_sp.addActionListener(e -> new DialogAddProduct().run());
        btnthem_sp.setBorderPainted(false);
        btnthem_sp.setBorder(null);
        btnthem_sp.setContentAreaFilled(false);
        btnthem_sp.setCursor(Cursor.getPredefinedCursor(Cursor.HAND_CURSOR));
        btnthem_sp.setIconTextGap(7);
        btnthem_sp.setIcon(new ImageIcon("Imager\\themsp-25.png"));
        btnthem_sp.setFont(new Font("Arial", Font.PLAIN, 22));
        panel_bar.add(btnthem_sp);

        btnxoa_sp = new JButton("Xoá");
        btnxoa_sp.setEnabled(true);
        btnxoa_sp.addActionListener(e -> {
            int[] soluong = table_sp.getSelectedRows();
            if (soluong.length > 0) {
                int xacnhan = JOptionPane.showConfirmDialog(null, "Xác nhận xóa " + soluong.length + " Sản phẩm");
                if (xacnhan == JOptionPane.YES_OPTION) {
                    // get id from index
                    productService.remove(Arrays.stream(soluong)
                            .mapToObj((index) -> (String) table_sp.getValueAt(index, 1))
                            .collect(Collectors.toList()));
                    refresh();
                }
            }
        });
        btnxoa_sp.setBorder(null);
        btnxoa_sp.setContentAreaFilled(false);
        btnxoa_sp.setBorderPainted(false);
        btnxoa_sp.setCursor(Cursor.getPredefinedCursor(Cursor.HAND_CURSOR));
        btnxoa_sp.setIconTextGap(7);
        btnxoa_sp.setIcon(new ImageIcon("Imager\\xoa-25.png"));
        btnxoa_sp.setFont(new Font("Arial", Font.PLAIN, 22));
        panel_bar.add(btnxoa_sp);

//        JButton btnnhapfile = new JButton("Nhập File");
//        btnnhapfile.addActionListener(new ActionListener() {
//
//            @Override
//            public void actionPerformed(ActionEvent e) {
//                nhapFile();
//            }
//        });
//        btnnhapfile.setEnabled(DangNhap.action_nhapfile);
//        btnnhapfile.setBorder(null);
//        btnnhapfile.setContentAreaFilled(false);
//        btnnhapfile.setBorderPainted(false);
//        btnnhapfile.setCursor(Cursor.getPredefinedCursor(Cursor.HAND_CURSOR));
//        btnnhapfile.setIconTextGap(5);
//        btnnhapfile.setIcon(new ImageIcon("Imager\\nhapfile-30.png"));
//        btnnhapfile.setFont(new Font("Arial", Font.PLAIN, 22));
//        panel_bar.add(btnnhapfile);

//        JButton btnxuatfile = new JButton("Xuất File");
//        btnxuatfile.addActionListener(new ActionListener() {
//
//            @Override
//            public void actionPerformed(ActionEvent arg0) {
//                xuatfile();
//            }
//        });
//        btnxuatfile.setEnabled(DangNhap.action_xuatfile);
//        btnxuatfile.setBorder(null);
//        btnxuatfile.setBorderPainted(false);
//        btnxuatfile.setContentAreaFilled(false);
//        btnxuatfile.setCursor(Cursor.getPredefinedCursor(Cursor.HAND_CURSOR));
//        btnxuatfile.setIconTextGap(5);
//        btnxuatfile.setIcon(new ImageIcon("Imager\\xuatfile2-30.png"));
//        btnxuatfile.setVerticalTextPosition(SwingConstants.BOTTOM);
//        btnxuatfile.setFont(new Font("Arial", Font.PLAIN, 22));
//        panel_bar.add(btnxuatfile);

    }

    // danh sach san pham cần xóa đã đảm bảo có dữ liệu
//    private void xoaSP(int[] soluong) {
//        int size = soluong.length;
//        Statement st = null;
//        String sql = "(";
//        try {
//            st = DangNhap.con.createStatement();
//            for (int i = 0; i < size; i++) {
//                sql += "N'" + (String) table_sp.getValueAt(soluong[i], 1) + "',";
//            }
//            // xoa dau , cuoi cung
//            sql = sql.substring(0, sql.length() - 1);
//            sql += ")";
//
//            ResultSet rs = st.executeQuery("select count(*) from chitiethoadon where mahang in " + sql);
//            if (rs.next()) {
//                // hóa đơn không chứa các mã hàng thì cho phép xóa các mặt hàng đó
//                if (rs.getInt(1) == 0) {
//                    st.executeUpdate("delete from mathang where mahang in " + sql);
//                } else {
//                    JOptionPane.showMessageDialog(null,
//                            "các mặt hàng đang tồn tại trong hóa đơn,bạn phải xóa các đơn hàng chứa các mặt hàng đó trước.");
//                }
//            }
//            refresh();
//        } catch (Exception e) {
//            System.out.println("cardpanel_sanpham -xoaSP: " + e.getMessage());
//        }
//    }

    private void refresh() {
        DefaultTableModel datarow = (DefaultTableModel) table_sp.getModel();
        datarow.setRowCount(0);
        List<Product> productList = productService.findAll();
        if (productList != null && productList.size() > 0) {
            int size = productList.size();
            for (int i = 0; i < size; i++) {
                Product product = productList.get(i);
                datarow.addRow(new String[]{i + 1 + "",
                        product.getProductId(),
                        product.getProductName(),
                        product.getProductUnit(),
                        Utils.numberToString(product.getProductPrice())});
            }
        }
    }

//    private void nhapFile() {
//        FileNameExtensionFilter format = new FileNameExtensionFilter("file(.*)", "txt");
//        ChooserFile chooser = new ChooserFile(null, format, false);
//        chooser.show();
//        String url = chooser.getUrl();
//        ArrayList<ob_sanpham> ds = new ArrayList<ob_sanpham>();
//        if (url != null) {
//            try {
//
//                themsanpham them = new themsanpham();
//                FileInputStream f = new FileInputStream(url);
//                BufferedReader br = new BufferedReader(new InputStreamReader(f, "UTF-8"));
//                while (true) {
//                    String txt = br.readLine();
//                    if (txt == null || txt.equals("")) {
//                        break;
//                    }
//                    String o[] = txt.split("[;|]");
//
//                    ob_sanpham sp = new ob_sanpham();
//                    sp.setMahang(o[0].trim());
//                    sp.setTenhang(o[1].trim());
//                    sp.setGianhap(Double.parseDouble(o[2]));
//                    sp.setGiabanle(Double.parseDouble(o[3]));
//                    sp.setDonvitinh(o[4].trim());
//                    sp.setGhichu(o[5].trim());
//                    sp.setAnh(o[6].trim());
//                    sp.setNgaykhoitao(o[7].trim());
//
//                    ds.add(sp);
//                }
//                them.luuSanPham(ds);
//                br.close();
//            } catch (Exception e) {
//                JOptionPane.showMessageDialog(null, "lỗi dữ liệu =>" + e.getMessage());
//            }
//        }
//    }

//    private void xuatfile() {
//        String tableName = "MATHANG";
//        String mahang = "MAHANG";
//        xuatfile xuat = new xuatfile(table_sp, tableName, mahang);
//        xuat.run();
//    }
}
