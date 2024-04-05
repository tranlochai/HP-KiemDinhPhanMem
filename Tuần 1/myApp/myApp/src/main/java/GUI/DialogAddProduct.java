package GUI;

import Service.ProductService;
import Entity.Product;
import Utils.Utils;

import javax.swing.*;
import javax.swing.border.EmptyBorder;
import javax.swing.border.LineBorder;
import javax.swing.filechooser.FileNameExtensionFilter;
import java.awt.*;
import java.awt.event.FocusAdapter;
import java.awt.event.FocusEvent;
import java.awt.event.KeyEvent;
import java.awt.event.KeyListener;

public class DialogAddProduct extends JDialog implements KeyListener {

    private JPanel contentPane;
    private JTextField textField_tensp;
    private JTextField textField_masp;
    private JTextField textField_le;
    private JTextField textField_gianhap;
    private JTextArea ta_diachifile;
    private JTextField textField_dvt;
    private JFileChooser fileChooser;

    /**
     * Launch the application.
     */
    public void run() {
        try {
            UIManager.setLookAndFeel(UIManager.getSystemLookAndFeelClassName());
        } catch (Exception e) {
            JOptionPane.showMessageDialog(null, "Look and Feel not set");
        }
        EventQueue.invokeLater(() -> {
            try {
                setVisible(true);
            } catch (Exception e) {
                e.printStackTrace();
            }
        });
    }

    public DialogAddProduct() {
        FileNameExtensionFilter format = new FileNameExtensionFilter("file(.*)", "png", "jpg", "gif", "svg");
        fileChooser = new JFileChooser("Imager_products");
        fileChooser.addChoosableFileFilter(format);
        fileChooser.setAcceptAllFileFilterUsed(false);
        setDefaultCloseOperation(JFrame.DISPOSE_ON_CLOSE);
        setBounds(100, 100, 852, 704);
        setResizable(false);
        // VO HIÊU HÓA FRAME KHÁC KHI JDIALOG NÀY ĐC MỞ
        setModal(true);

        contentPane = new JPanel();
        contentPane.setBackground(Color.WHITE);
        contentPane.setBorder(new EmptyBorder(5, 5, 5, 5));
        setContentPane(contentPane);
        contentPane.setLayout(null);

        JSeparator separator = new JSeparator();
        separator.setBounds(12, 66, 746, 2);
        contentPane.add(separator);

        JLabel lblNewLabel = new JLabel("Thêm Mới Sản Phẩm");
        lblNewLabel.setFont(new Font("Arial", Font.PLAIN, 30));
        lblNewLabel.setBounds(32, 13, 373, 40);
        contentPane.add(lblNewLabel);

        JLabel lblNewLabel_1 = new JLabel("Tên Sản Phẩm");
        lblNewLabel_1.setHorizontalAlignment(SwingConstants.LEFT);
        lblNewLabel_1.setFont(new Font("Arial", Font.PLAIN, 17));
        lblNewLabel_1.setBounds(429, 107, 173, 33);
        contentPane.add(lblNewLabel_1);

        JLabel lblNewLabel_1_1 = new JLabel("Mã Sản Phẩm");
        lblNewLabel_1_1.setHorizontalAlignment(SwingConstants.LEFT);
        lblNewLabel_1_1.setFont(new Font("Arial", Font.PLAIN, 17));
        lblNewLabel_1_1.setBounds(53, 107, 173, 33);
        contentPane.add(lblNewLabel_1_1);

        JLabel lblNewLabel_1_2 = new JLabel("Giá bán");
        lblNewLabel_1_2.setHorizontalAlignment(SwingConstants.LEFT);
        lblNewLabel_1_2.setFont(new Font("Arial", Font.PLAIN, 17));
        lblNewLabel_1_2.setBounds(619, 186, 109, 33);
        contentPane.add(lblNewLabel_1_2);

        textField_tensp = new JTextField();
        textField_tensp.setBorder(new LineBorder(new Color(0, 0, 0), 2, true));
        textField_tensp.setFont(new Font("Arial", Font.PLAIN, 17));
        textField_tensp.setBounds(480, 140, 330, 33);
        contentPane.add(textField_tensp);
        textField_tensp.setColumns(10);
        textField_tensp.addFocusListener(new FocusAdapter() {
            @Override
            public void focusGained(FocusEvent e) {
                textField_tensp.selectAll();
            }
        });

        textField_masp = new JTextField();
        textField_masp.setBorder(new LineBorder(new Color(0, 0, 0), 2, true));
        textField_masp.setFont(new Font("Arial", Font.PLAIN, 17));
        textField_masp.setColumns(10);
        textField_masp.setBounds(100, 140, 291, 33);
        contentPane.add(textField_masp);
        textField_masp.addFocusListener(new FocusAdapter() {
            @Override
            public void focusGained(FocusEvent e) {
                textField_masp.selectAll();
            }
        });

        textField_le = new JTextField("0");
        textField_le.setBorder(new LineBorder(new Color(0, 0, 0), 2, true));
        textField_le.setFont(new Font("Arial", Font.PLAIN, 17));
        textField_le.setColumns(10);
        textField_le.setBounds(652, 222, 158, 33);
        contentPane.add(textField_le);
        textField_le.addFocusListener(new FocusAdapter() {
            @Override
            public void focusGained(FocusEvent e) {
                textField_le.selectAll();
            }
        });

        JLabel lbhinhanh = new JLabel("");
        lbhinhanh.setBounds(426, 287, 360, 360);
        lbhinhanh.setHorizontalAlignment(JLabel.CENTER);
        contentPane.add(lbhinhanh);

        JLabel lblNewLabel_1_5_1 = new JLabel("Giá nhập");
        lblNewLabel_1_5_1.setHorizontalAlignment(SwingConstants.LEFT);
        lblNewLabel_1_5_1.setFont(new Font("Arial", Font.PLAIN, 17));
        lblNewLabel_1_5_1.setBounds(53, 186, 109, 33);
        contentPane.add(lblNewLabel_1_5_1);

        textField_gianhap = new JTextField("0");
        textField_gianhap.setBorder(new LineBorder(new Color(0, 0, 0), 2, true));
        textField_gianhap.setFont(new Font("Arial", Font.PLAIN, 17));
        textField_gianhap.setColumns(10);
        textField_gianhap.setBounds(100, 222, 136, 33);
        contentPane.add(textField_gianhap);
        textField_gianhap.addFocusListener(new FocusAdapter() {
            @Override
            public void focusGained(FocusEvent e) {
                textField_gianhap.selectAll();
            }
        });

        JButton btnLuu = new JButton("Lưu");
        btnLuu.setCursor(Cursor.getPredefinedCursor(Cursor.HAND_CURSOR));
        btnLuu.setSelected(true);
        btnLuu.setFont(new Font("Arial", Font.PLAIN, 17));
        btnLuu.setFocusable(false);
        btnLuu.setBounds(32, 614, 109, 33);
        contentPane.add(btnLuu);
        btnLuu.addActionListener(e -> saveProduct());

        JButton btnthoat = new JButton("Thoát");
        btnthoat.addActionListener(e -> dispose());
        btnthoat.setCursor(Cursor.getPredefinedCursor(Cursor.HAND_CURSOR));
        btnthoat.setFont(new Font("Arial", Font.PLAIN, 17));
        btnthoat.setFocusable(false);
        btnthoat.setBounds(193, 615, 115, 33);
        contentPane.add(btnthoat);

        ta_diachifile = new JTextArea();
        ta_diachifile.setLineWrap(true);
        ta_diachifile.setWrapStyleWord(true);
        ta_diachifile.setFont(new Font("Arial", Font.PLAIN, 14));
        ta_diachifile.setBounds(53, 537, 291, 49);
        ta_diachifile.setEditable(false);
        contentPane.add(ta_diachifile);

        JButton btnChonHinhAnh = new JButton("Chọn Hình ảnh");

        btnChonHinhAnh.setFont(new Font("Arial", Font.PLAIN, 17));
        btnChonHinhAnh.setBounds(53, 491, 151, 33);
        contentPane.add(btnChonHinhAnh);
        btnChonHinhAnh.setCursor(Cursor.getPredefinedCursor(Cursor.HAND_CURSOR));
        btnChonHinhAnh.setFocusable(false);

        JLabel lblNewLabel_1_2_2 = new JLabel("DVT");
        lblNewLabel_1_2_2.setHorizontalAlignment(SwingConstants.LEFT);
        lblNewLabel_1_2_2.setFont(new Font("Arial", Font.PLAIN, 17));
        lblNewLabel_1_2_2.setBounds(53, 272, 109, 33);
        contentPane.add(lblNewLabel_1_2_2);

        textField_dvt = new JTextField();
        textField_dvt.setBorder(new LineBorder(new Color(0, 0, 0), 2, true));
        textField_dvt.setFont(new Font("Arial", Font.PLAIN, 17));
        textField_dvt.setColumns(10);
        textField_dvt.setBounds(100, 308, 129, 33);
        contentPane.add(textField_dvt);
        textField_dvt.addFocusListener(new FocusAdapter() {
            @Override
            public void focusGained(FocusEvent e) {
                textField_dvt.selectAll();
            }
        });

        btnChonHinhAnh.addActionListener(arg0 -> {
            String url = null;
            int i = fileChooser.showDialog(null, "Open");
            if (i == JFileChooser.APPROVE_OPTION)
                url = fileChooser.getSelectedFile().getAbsolutePath();
            Image icon = new ImageIcon(url).getImage().getScaledInstance(lbhinhanh.getWidth(),
                    lbhinhanh.getHeight(), Image.SCALE_SMOOTH);
            lbhinhanh.setIcon(new ImageIcon(icon));
            ta_diachifile.setText(url);
        });
    }

    private ProductService ProductService = new ProductService();

    private void saveProduct() {
        try {
            Product product = new Product();
            product.setProductName(textField_tensp.getText().trim());
            product.setProductPrice(Long.parseLong(textField_le.getText()));
            product.setProductUnit(textField_dvt.getText().trim());
            product.setProductImage(Utils.pathImage(ta_diachifile.getText().trim()));
            product.setProductCost(Long.parseLong(textField_gianhap.getText()));
            product.setProductId(textField_masp.getText());
            if (ProductService.save(product)) {
                JOptionPane.showMessageDialog(null, "Lưu thành công");
                dispose();
            }

        } catch (NumberFormatException numberFormatException) {
            JOptionPane.showMessageDialog(null, numberFormatException, "Lưu thất bại", JOptionPane.ERROR_MESSAGE);
        } catch (Exception exception) {
            JOptionPane.showMessageDialog(null, exception, "Lưu thất bại", JOptionPane.ERROR_MESSAGE);
        }
    }

    @Override
    public void keyPressed(KeyEvent e) {
    }

    @Override
    public void keyReleased(KeyEvent e) {
        if (e.getKeyCode() == 10) {
            saveProduct();
        }
    }

    @Override
    public void keyTyped(KeyEvent e) {
    }
}
