package GUI;

import Service.ProductService;
import Entity.Product;
import Utils.Utils;
import javax.imageio.ImageIO;
import javax.swing.*;
import javax.swing.filechooser.FileNameExtensionFilter;
import java.awt.*;
import java.awt.event.KeyEvent;
import java.awt.event.MouseAdapter;
import java.awt.event.MouseEvent;
import java.io.File;

public class ProductDetails extends JPanel {
    private JTextField textField_tensp;
    private JTextField textField_masp;
    private JTextField textField_DVT;
    private JTextField textField_le;
    private JLabel lbhinhanh;
    private JTextField textField_url;
    private JTextField textField_createDate;
    private JTextField textField_modifiedDate;
    private JLabel lbtitle;
    private Image icon;
    private JButton btnluu_sp;
    //private JButton btnxoa_ctsp;
    private JLabel lbgianhap;
    private JTextField textField_nhap;

    private JFileChooser fileChooser;
    private ProductService ProductService = new ProductService();
    private final String id;

    public ProductDetails(String id) {
        try {
            UIManager.setLookAndFeel(UIManager.getSystemLookAndFeelClassName());
        } catch (Exception e) {
            JOptionPane.showMessageDialog(null, "Look and Feel not set");
        }
        setLayout(new BorderLayout());

        FileNameExtensionFilter format = new FileNameExtensionFilter("file(.*)", "png", "jpg", "gif", "svg");
        fileChooser = new JFileChooser("Imager_products");
        fileChooser.addChoosableFileFilter(format);
        fileChooser.setAcceptAllFileFilterUsed(false);

        JPanel pn_tong = new JPanel(new BorderLayout());
        pn_tong.add(panel_north(), BorderLayout.NORTH);
        pn_tong.add(panel_center(), BorderLayout.CENTER);
        pn_tong.add(panel_south(), BorderLayout.SOUTH);

        JScrollPane js = new JScrollPane(pn_tong);
        js.getVerticalScrollBar().setUnitIncrement(10);
        add(js, BorderLayout.CENTER);
        /**
         * show data
         */
        this.id = id;
        refresh(this.id);
    }

    private JPanel panel_north() {
        JPanel panel_north = new JPanel();
        FlowLayout fl_panel_north = (FlowLayout) panel_north.getLayout();
        fl_panel_north.setVgap(20);
        fl_panel_north.setHgap(70);
        fl_panel_north.setAlignment(FlowLayout.LEFT);

        lbtitle = new JLabel();
        lbtitle.setFont(new Font("Tahoma", Font.PLAIN, 27));
        panel_north.add(lbtitle);

        return panel_north;
    }

    private JPanel panel_center() {
        JPanel panel_center = new JPanel();

        GridBagLayout gbl_panel_center = new GridBagLayout();
        gbl_panel_center.columnWidths = new int[]{468, 0, 0, 0, 0, 56, 0};
        gbl_panel_center.rowHeights = new int[]{16, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0};
        gbl_panel_center.columnWeights = new double[]{0.0, 1.0, 1.0, 1.0, 0.0, 0.0, Double.MIN_VALUE};
        gbl_panel_center.rowWeights = new double[]{0.0, 0.0, 0.0, 0.0, 0.0, 0.0, 0.0, 0.0, 1.0, 1.0, 1.0, 0.0, 0.0};
        panel_center.setLayout(gbl_panel_center);

        JLabel lbten = new JLabel("Tên sản phẩm");
        lbten.setFont(new Font("Arial", Font.PLAIN, 17));
        GridBagConstraints gbc_lbten = new GridBagConstraints();
        gbc_lbten.insets = new Insets(20, 70, 5, 5);
        gbc_lbten.anchor = GridBagConstraints.WEST;
        gbc_lbten.gridx = 0;
        gbc_lbten.gridy = 0;
        panel_center.add(lbten, gbc_lbten);

        lbgianhap = new JLabel("Giá nhập");
        lbgianhap.setVisible(true);
        lbgianhap.setFont(new Font("Arial", Font.PLAIN, 17));
        GridBagConstraints gbc_lbgianhap = new GridBagConstraints();
        gbc_lbgianhap.anchor = GridBagConstraints.WEST;
        gbc_lbgianhap.fill = GridBagConstraints.VERTICAL;
        gbc_lbgianhap.insets = new Insets(20, 50, 5, 5);
        gbc_lbgianhap.gridx = 1;
        gbc_lbgianhap.gridy = 4;
        panel_center.add(lbgianhap, gbc_lbgianhap);

        textField_nhap = new JTextField("0");
        textField_nhap.setVisible(true);
        textField_nhap.setPreferredSize(new Dimension(6, 33));
        textField_nhap.setMinimumSize(new Dimension(6, 33));
        textField_nhap.setFont(new Font("Arial", Font.PLAIN, 17));
        textField_nhap.setColumns(15);
        GridBagConstraints gbc_textField_nhap = new GridBagConstraints();
        gbc_textField_nhap.insets = new Insets(0, 60, 5, 10);
        gbc_textField_nhap.fill = GridBagConstraints.BOTH;
        gbc_textField_nhap.gridx = 1;
        gbc_textField_nhap.gridy = 5;
        panel_center.add(textField_nhap, gbc_textField_nhap);

        textField_url = new JTextField();
        textField_url.setFont(new Font("Tahoma", Font.PLAIN, 15));
        textField_url.setEditable(false);
        GridBagConstraints gbc_textField_url = new GridBagConstraints();
        gbc_textField_url.anchor = GridBagConstraints.NORTH;
        gbc_textField_url.insets = new Insets(20, 70, 5, 5);
        gbc_textField_url.fill = GridBagConstraints.HORIZONTAL;
        gbc_textField_url.gridx = 2;
        gbc_textField_url.gridy = 8;
        panel_center.add(textField_url, gbc_textField_url);
        textField_url.setColumns(25);
        textField_url.setBorder(null);

        textField_le = new JTextField("0");
        textField_le.setPreferredSize(new Dimension(6, 33));
        textField_le.setMinimumSize(new Dimension(6, 33));
        textField_le.setFont(new Font("Arial", Font.PLAIN, 17));
        textField_le.setColumns(15);
        GridBagConstraints gbc_textField_le = new GridBagConstraints();
        gbc_textField_le.insets = new Insets(0, 60, 5, 10);
        gbc_textField_le.fill = GridBagConstraints.BOTH;
        gbc_textField_le.gridx = 1;
        gbc_textField_le.gridy = 3;
        panel_center.add(textField_le, gbc_textField_le);
        //------
        JLabel lbDVT = new JLabel("DVT");
        lbDVT.setFont(new Font("Arial", Font.PLAIN, 17));
        GridBagConstraints gbc_lbDVT = new GridBagConstraints();
        gbc_lbDVT.fill = GridBagConstraints.HORIZONTAL;
        gbc_lbDVT.gridwidth = 2;
        gbc_lbDVT.anchor = GridBagConstraints.SOUTH;
        gbc_lbDVT.insets = new Insets(20, 70, 5, 5);
        gbc_lbDVT.gridx = 0;
        gbc_lbDVT.gridy = 4;
        panel_center.add(lbDVT, gbc_lbDVT);

        textField_DVT = new JTextField();
        textField_DVT.setFont(new Font("Arial", Font.PLAIN, 17));
        GridBagConstraints gbc_textField_DVT = new GridBagConstraints();
        gbc_textField_DVT.fill = GridBagConstraints.BOTH;
        gbc_textField_DVT.insets = new Insets(0, 90, 5, 250);
        gbc_textField_DVT.gridx = 0;
        gbc_textField_DVT.gridy = 5;
        panel_center.add(textField_DVT, gbc_textField_DVT);
        textField_DVT.setColumns(15);
        //-------
        JLabel lbCreateDate = new JLabel("Ngày tạo");
        lbDVT.setFont(new Font("Arial", Font.PLAIN, 17));
        GridBagConstraints gbc_lbCreateDate = new GridBagConstraints();
        gbc_lbCreateDate.fill = GridBagConstraints.HORIZONTAL;
        gbc_lbCreateDate.anchor = GridBagConstraints.SOUTH;
        gbc_lbCreateDate.insets = new Insets(20, 70, 5, 5);
        gbc_lbCreateDate.gridx = 0;
        gbc_lbCreateDate.gridy = 6;
        panel_center.add(lbCreateDate, gbc_lbCreateDate);

        textField_createDate = new JTextField();
        textField_createDate.setFont(new Font("Arial", Font.PLAIN, 17));
        textField_createDate.setEnabled(false);
        GridBagConstraints gbc_textField_createDate = new GridBagConstraints();
        gbc_textField_createDate.insets = new Insets(10, 90, 5, 250);
        gbc_textField_createDate.fill = GridBagConstraints.BOTH;
        gbc_textField_createDate.gridx = 0;
        gbc_textField_createDate.gridy = 7;
        panel_center.add(textField_createDate, gbc_textField_createDate);
        textField_createDate.setColumns(15);

        JLabel lbModifiedDate = new JLabel("Ngày sửa");
        lbDVT.setFont(new Font("Arial", Font.PLAIN, 17));
        GridBagConstraints gbc_lbModifiedDate = new GridBagConstraints();
        gbc_lbModifiedDate.fill = GridBagConstraints.HORIZONTAL;
        gbc_lbModifiedDate.gridwidth = 2;
        gbc_lbModifiedDate.anchor = GridBagConstraints.SOUTH;
        gbc_lbModifiedDate.insets = new Insets(20, 50, 5, 5);
        gbc_lbModifiedDate.gridx = 1;
        gbc_lbModifiedDate.gridy = 6;
        panel_center.add(lbModifiedDate, gbc_lbModifiedDate);

        textField_modifiedDate = new JTextField();
        textField_modifiedDate.setEnabled(false);
        textField_modifiedDate.setFont(new Font("Arial", Font.PLAIN, 17));
        GridBagConstraints gbc_modifiedDate = new GridBagConstraints();
        gbc_modifiedDate.fill = GridBagConstraints.BOTH;
        gbc_modifiedDate.insets = new Insets(10, 60, 5, 10);
        gbc_modifiedDate.gridx = 1;
        gbc_modifiedDate.gridy = 7;
        panel_center.add(textField_modifiedDate, gbc_modifiedDate);
        textField_DVT.setColumns(15);

        lbhinhanh = new JLabel("");
        lbhinhanh.setSize(new Dimension(250, 250));
        GridBagConstraints gbc_lbhinhanh = new GridBagConstraints();
        gbc_lbhinhanh.gridwidth = 1;
        gbc_lbhinhanh.gridheight = 6;
        gbc_lbhinhanh.insets = new Insets(20, 70, 5, 5);
        gbc_lbhinhanh.anchor = GridBagConstraints.WEST;
        gbc_lbhinhanh.gridx = 2;
        gbc_lbhinhanh.gridy = 1;
        panel_center.add(lbhinhanh, gbc_lbhinhanh);

        JLabel lbmasp = new JLabel("Mã sản phẩm");
        lbmasp.setFont(new Font("Arial", Font.PLAIN, 17));
        GridBagConstraints gbc_lbmasp = new GridBagConstraints();
        gbc_lbmasp.gridwidth = 2;
        gbc_lbmasp.anchor = GridBagConstraints.SOUTHWEST;
        gbc_lbmasp.insets = new Insets(20, 70, 5, 5);
        gbc_lbmasp.gridx = 0;
        gbc_lbmasp.gridy = 2;
        panel_center.add(lbmasp, gbc_lbmasp);

        textField_tensp = new JTextField();
        textField_tensp.setPreferredSize(new Dimension(6, 33));
        textField_tensp.setMinimumSize(new Dimension(6, 33));
        textField_tensp.setFont(new Font("Arial", Font.PLAIN, 17));
        GridBagConstraints gbc_textField_tensp = new GridBagConstraints();
        gbc_textField_tensp.fill = GridBagConstraints.BOTH;
        gbc_textField_tensp.insets = new Insets(0, 90, 5, 5);
        gbc_textField_tensp.gridx = 0;
        gbc_textField_tensp.gridy = 1;
        panel_center.add(textField_tensp, gbc_textField_tensp);
        textField_tensp.setColumns(35);

        JLabel lbgiabanle = new JLabel("Giá bán");
        lbgiabanle.setFont(new Font("Arial", Font.PLAIN, 17));
        GridBagConstraints gbc_lbgiabanle = new GridBagConstraints();
        gbc_lbgiabanle.insets = new Insets(20, 50, 5, 5);
        gbc_lbgiabanle.anchor = GridBagConstraints.SOUTHWEST;
        gbc_lbgiabanle.gridx = 1;
        gbc_lbgiabanle.gridy = 2;
        panel_center.add(lbgiabanle, gbc_lbgiabanle);

        textField_masp = new JTextField();
        textField_masp.setEditable(false);
        textField_masp.setPreferredSize(new Dimension(6, 33));
        textField_masp.setMinimumSize(new Dimension(6, 33));
        textField_masp.setFont(new Font("Arial", Font.PLAIN, 17));
        GridBagConstraints gbc_textField_masp = new GridBagConstraints();
        gbc_textField_masp.fill = GridBagConstraints.BOTH;
        gbc_textField_masp.insets = new Insets(5, 90, 5, 100);
        gbc_textField_masp.gridx = 0;
        gbc_textField_masp.gridy = 3;
        panel_center.add(textField_masp, gbc_textField_masp);
        textField_masp.setColumns(20);

        JLabel lbmota = new JLabel("Thay đổi hình ảnh");
        lbmota.addMouseListener(new MouseAdapter() {
            @Override
            public void mouseClicked(MouseEvent e) {
                if (e.getButton() == MouseEvent.BUTTON1) {
                    String url = null;
                    int i = fileChooser.showDialog(null, "Open");
                    if (i == JFileChooser.APPROVE_OPTION)
                        url = fileChooser.getSelectedFile().getAbsolutePath();
                    Image icon = new ImageIcon(url).getImage().getScaledInstance(250, 250, Image.SCALE_SMOOTH);
                    lbhinhanh.setIcon(new ImageIcon(icon));
                    lbhinhanh.setText("");
                    textField_url.setText(url);
                }
            }
        });
        lbmota.setCursor(Cursor.getPredefinedCursor(Cursor.HAND_CURSOR));
        lbmota.setForeground(Color.BLUE);
        lbmota.setFont(new Font("Arial", Font.PLAIN, 17));
        GridBagConstraints gbc_lbmota = new GridBagConstraints();
        gbc_lbmota.anchor = GridBagConstraints.WEST;
        gbc_lbmota.insets = new Insets(0, 70, 5, 5);
        gbc_lbmota.gridx = 2;
        gbc_lbmota.gridy = 0;
        panel_center.add(lbmota, gbc_lbmota);

        return panel_center;
    }

    private JPanel panel_south() {
        JPanel panel_south = new JPanel();
        FlowLayout flowLayout_1 = (FlowLayout) panel_south.getLayout();
        flowLayout_1.setVgap(50);
        flowLayout_1.setHgap(70);
        flowLayout_1.setAlignment(FlowLayout.LEFT);

        btnluu_sp = new JButton("Lưu");
        btnluu_sp.setEnabled(true);
        btnluu_sp.setToolTipText("Alt + s");
        btnluu_sp.setMnemonic(KeyEvent.VK_S);
        btnluu_sp.addActionListener(e -> {
            updateProduct();
            refresh(id);
        });
        btnluu_sp.setPreferredSize(new Dimension(100, 40));
        btnluu_sp.setFont(new Font("Tahoma", Font.PLAIN, 17));
        btnluu_sp.setCursor(Cursor.getPredefinedCursor(Cursor.HAND_CURSOR));
        panel_south.add(btnluu_sp);

//        btnxoa_ctsp = new JButton("Xóa");
//        btnxoa_ctsp.setEnabled(true);
//        btnxoa_ctsp.addActionListener(new ActionListener() {
//            @Override
//            public void actionPerformed(ActionEvent e) {
//                String sql_chitietHD = " DELETE FROM CHITIETHOADON WHERE MAHANG = ?"; // XOA BẢNG CON CHỨA MÃ HÀNG CẦN
//                // xoa
//
//                String sql_mathang = " DELETE FROM  MATHANG WHERE MAHANG = ? "; // XÓA MÃ HÀNG
//                try {
//
//                    PreparedStatement pr = DangNhap.con.prepareStatement(sql_chitietHD);
//                    pr.setString(1, textField_masp.getText());
//                    pr.addBatch();
//                    pr = DangNhap.con.prepareStatement(sql_mathang);
//                    pr.setString(1, textField_masp.getText());
//                    pr.addBatch();
//                    int xacnhan = JOptionPane.showConfirmDialog(null, "Xác nhận xóa sản phẩm ? ", null,
//                            JOptionPane.YES_NO_OPTION);
//                    if (xacnhan == JOptionPane.YES_OPTION) {
//                        pr.executeBatch();
//                        refresh();
//                    }
//                } catch (Exception e2) {
//                    JOptionPane.showMessageDialog(null, "Lỗi xóa sản phẩm ");
//                    System.out.println("chi tiet san pham - btnxoa: " + e2.getMessage());
//                }
//
//            }
//        });
//        btnxoa_ctsp.setFocusable(false);
//        btnxoa_ctsp.setForeground(Color.black);
//        btnxoa_ctsp.setBackground(new Color(240, 240, 240));
//        btnxoa_ctsp.setPreferredSize(new Dimension(150, 40));
//        btnxoa_ctsp.setFont(new Font("Tahoma", Font.PLAIN, 17));
//        btnxoa_ctsp.setCursor(Cursor.getPredefinedCursor(Cursor.HAND_CURSOR));
//        panel_south.add(btnxoa_ctsp);
        return panel_south;
    }

    private void updateProduct() {
        try {
            Product product = new Product();
            product.setProductName(textField_tensp.getText().trim());
            product.setProductPrice(Long.parseLong(textField_le.getText()));
            product.setProductUnit(textField_DVT.getText().trim());
            product.setProductImage(Utils.pathImage(textField_url.getText().trim()));
            product.setProductCost(Long.parseLong(textField_nhap.getText()));
            product.setProductId(textField_masp.getText());
            if (ProductService.update(product)) {
                JOptionPane.showMessageDialog(null, "update thành công");
            }
        } catch (NumberFormatException numberFormatException) {
            JOptionPane.showMessageDialog(null, numberFormatException, "update thất bại", JOptionPane.ERROR_MESSAGE);
        } catch (Exception ex) {
            JOptionPane.showMessageDialog(null, "update thất bại", null, JOptionPane.ERROR_MESSAGE);
        }
    }

    private void refresh(String id) {
        Product product = ProductService.findById(id);
        lbtitle.setText(product.getProductName());
        textField_url.setText(System.getProperty("user.dir") + "\\" + product.getProductImage());
        textField_le.setText(product.getProductPrice() + "");
        textField_DVT.setText(product.getProductUnit());
        textField_nhap.setText(product.getProductCost() + "");
        textField_tensp.setText(product.getProductName());
        textField_masp.setText(product.getProductId());
        textField_createDate.setText(product.getCreateDate() + "");
        textField_modifiedDate.setText(product.getModifiedDate() + "");
        try {
            icon = new ImageIcon(ImageIO.read(
                    new File(product.getProductImage()))
            ).getImage();
            lbhinhanh.setIcon(new ImageIcon(icon.getScaledInstance(250, 250, Image.SCALE_SMOOTH)));
        } catch (Exception ex) {
            textField_url.setText("");
        }
    }
}
