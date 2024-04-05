package Service;

import DAO.OrderDAO;
import Entity.Order;
import Entity.OrderDto;
import Entity.Product;
import Entity.User;
import GUI.PageCreateOrder;
import Utils.Utils;

import javax.swing.*;
import javax.swing.table.DefaultTableModel;
import java.util.List;

public class OrderService {

    private ProductService productService = new ProductService();
    private OrderDAO orderDAO = new OrderDAO();
    private UserService userService = new UserService();


    private int indexExistsProductId(JTable table, String id) {
        int size = table.getRowCount();
        for (int i = 0; i < size; i++) {
            if (table.getValueAt(i, 0).equals(id))
                return i;
        }
        return -1;
    }

    // table , textField_tiencongthem.getText() , lbthanhtien , lbkhachphaitra
    public void tongTien(PageCreateOrder pageCreateOrder) {
        JTable table = pageCreateOrder.getTable();
        JTextField tien_cong_them = pageCreateOrder.getTextField_tiencongthem();
        JTextField textField_giamgia = pageCreateOrder.getTextField_giamgia();
        JLabel thanh_tien = pageCreateOrder.getLbthanhtien();
        JLabel khach_phai_tra = pageCreateOrder.getLbkhachphaitra();
        JTextField tien_khach_dua = pageCreateOrder.getTextField_tienkhachdua();
        JLabel tien_thua = pageCreateOrder.getLbtienthua();

        int rowcount = table.getRowCount();
        double tiencongthem = Utils.StringToNumber(
                tien_cong_them.getText()).doubleValue();
        if (rowcount == 0) {
            thanh_tien.setText("0.0");
            khach_phai_tra.setText("0.0");
            double tienthua =
                    Utils.StringToNumber(tien_khach_dua.getText()).doubleValue() - tiencongthem;
            tien_thua.setText(Utils.numberToString(tienthua));
        }

        int quantity;
        double dongia;
        double tongtiensanpham;
        double tong = 0;
        double giamgia;
        double khachphaitra;
        for (int i = 0; i < rowcount; i++) {
            quantity = Integer.parseInt((String) table.getValueAt(i, 3));
            dongia = Double.parseDouble((String) table.getValueAt(i, 4));
            tongtiensanpham = quantity * dongia;
            tong += tongtiensanpham;

            table.setValueAt(Utils.numberToString(tongtiensanpham), i, 5);

            thanh_tien.setText(Utils.numberToString(tong));
            giamgia = Double.parseDouble(textField_giamgia.getText().trim());
            khachphaitra = (Utils.StringToNumber(thanh_tien.getText()).doubleValue() + tiencongthem)
                    * (100 - giamgia) / 100;
            tien_cong_them.setText(Utils.numberToString(tiencongthem));
            khach_phai_tra.setText(Utils.numberToString(khachphaitra));

            this.tienkhachdua(pageCreateOrder);
        }

    }

    public void tienkhachdua(PageCreateOrder pageCreateOrder) {
        JTextField tien_khach_dua = pageCreateOrder.getTextField_tienkhachdua();
        JLabel khach_phai_tra = pageCreateOrder.getLbkhachphaitra();
        JLabel tien_thua = pageCreateOrder.getLbtienthua();
        try {
            double tienkhachdua = Utils.StringToNumber(tien_khach_dua.getText()).doubleValue();
            double khachphaitra = Utils.StringToNumber(khach_phai_tra.getText()).doubleValue();

            tien_khach_dua.setText(Utils.numberToString(tienkhachdua));
            tien_thua.setText(Utils.numberToString(tienkhachdua - khachphaitra));

        } catch (Exception e) {
            tien_khach_dua.setText("0");
            tien_khach_dua.requestFocus();
        }


    }

    public void kiemtra_tiencongthem(JTextField textField_tiencongthem) {
        try {
            double tiencongthem = Utils.StringToNumber(textField_tiencongthem.getText()).doubleValue();
            if (tiencongthem < 0) {
                textField_tiencongthem.setText("0");
            }
        } catch (Exception e) {
            textField_tiencongthem.setText("0");
        }
    }

    public void kiemtra_giamgia(JTextField textField_giamgia) {
        try {
            Double so = Double.parseDouble(textField_giamgia.getText());
            if (so < 0 || so > 100)
                textField_giamgia.setText("0");
            //tongtien
        } catch (Exception e1) {
            textField_giamgia.requestFocus();
            textField_giamgia.setText("0");
        }
    }

    public void updateTable(JTable table, String productId) {
        Product product = productService.findById(productId);
        DefaultTableModel datarow = (DefaultTableModel) table.getModel();
        if (product != null) {
            int index = indexExistsProductId(table, productId);
            if (index > -1) {
                int sl = Integer.parseInt((String) table.getValueAt(index, 3));
                datarow.setValueAt((sl + 1) + "", index, 3);
            } else
                datarow.addRow(new String[]{
                        product.getProductId(),
                        product.getProductName(),
                        product.getProductUnit(),
                        "1",
                        product.getProductPrice() + "",
                        product.getProductPrice() + ""
                });
        }
    }

    public long save(Order order) {
        return orderDAO.save(order);
    }

    public void findAll(JTable table) {
        DefaultTableModel datarow = (DefaultTableModel) table.getModel();
        datarow.setRowCount(0);
        int index = 1;
        for (OrderDto value : orderDAO.findAll()) {
            datarow.addRow(new String[]{
                    index + "",
                    value.getOrderId() + "",
                    value.getCreateDate() + "",
                    value.getDiscount() + "",
                    value.getTotalPrice() + ""});
            index++;
        }
    }

    public void findByCondition(JTable table, String field, String condition) {
        List<OrderDto> data = orderDAO.findByCondition(field, condition);
        DefaultTableModel datarow = (DefaultTableModel) table.getModel();
        datarow.setRowCount(0);
        int index = 1;
        if (data != null && !data.isEmpty()) {
            for (OrderDto value : data) {
                datarow.addRow(new String[]{
                        index + "",
                        value.getOrderId() + "",
                        value.getCreateDate() + "",
                        value.getDiscount() + "",
                        value.getTotalPrice() + ""});
                index++;
            }
        }
    }

//    public void findByOrderId(GUI.OrderDetails orderDetailsGUI) {
//        Order order = orderDAO.findByOrderId(orderDetailsGUI.getOrder_id());
//        if (order != null && order.getUserId() != 0) {
//            User user = userService.findById(order.getUserId());
//            if (user != null) {
//                orderDetailsGUI.getLbmanv().setText(user.getUserId() + "-" + user.getUserName());
//            }
//            orderDetailsGUI.getLbmahoadon().setText(orderDetailsGUI.getOrder_id() + "");
//            orderDetailsGUI.getLbngaytao().setText(order.getCreateDate() + "");
//            orderDetailsGUI.getTextArea().setText(order.getDescription());
//        }
//    }

    public void deleteOrders(int[] ids) {
        orderDAO.deleteOrders(ids);
    }
}
