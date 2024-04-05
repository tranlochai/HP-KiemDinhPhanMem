package Service;

import DAO.OrderDetailsDAO;
import Entity.Order;
import Entity.OrderDetails;
import Entity.User;

import javax.swing.*;
import javax.swing.table.DefaultTableModel;
import java.util.List;

public class OrderDetailsService {
    private OrderDetailsDAO orderDetailsDAO = new OrderDetailsDAO();
    private UserService userService = new UserService();

    public boolean saveAll(List<OrderDetails> orderDetailsList) {
        return orderDetailsDAO.saveAll(orderDetailsList);
    }

    public void findByOrderId(JTable table ,long id) {
        DefaultTableModel datarow = (DefaultTableModel) table.getModel();
        datarow.setRowCount(0);
        List<OrderDetails> orderDetailsList = orderDetailsDAO.findByOrderId(id);
        orderDetailsList.forEach(value -> {
            datarow.addRow(new Object[]{ });
        });
    }

    public void deleteOrderDetails(int[] ids) {
        orderDetailsDAO.deleteOrderDetails(ids);
    }
}
