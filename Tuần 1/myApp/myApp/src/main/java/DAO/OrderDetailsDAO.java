package DAO;

import Entity.OrderDetails;
import Mapping.OrderDetaisMapping;

import javax.swing.*;
import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.Statement;
import java.util.Arrays;
import java.util.List;

public class OrderDetailsDAO {
    private Connection connection;

    public OrderDetailsDAO() {
        connection = ConnectSql.getConnect();
    }

    public boolean saveAll(List<OrderDetails> orderDetailsList) {
        ConnectSql.setAutocommit(connection, false);
        String sql = "insert into orderDetails(product_id, quantity, order_id, sale_price, product_cost) values(?,?,?,?,?)";
        try (PreparedStatement statement = connection.prepareStatement(sql);) {
            for (OrderDetails item : orderDetailsList) {
                statement.setString(1, item.getProductId());
                statement.setInt(2, item.getQuantity());
                statement.setLong(3, item.getOrderId());
                statement.setLong(4, item.getSalePrice());
                statement.setLong(5, item.getProductCost());
                statement.addBatch();
            }
            if (statement.executeBatch() != null) {
                connection.commit();
                return true;
            }
        } catch (Exception exception) {
            JOptionPane.showMessageDialog(null, exception.getMessage(), "Lỗi khi lưu chi tiết hóa đơn", JOptionPane.ERROR_MESSAGE);
            ConnectSql.rollBack(connection);
        } finally {
            ConnectSql.setAutocommit(connection, true);
        }
        return false;
    }

    public void deleteOrderDetails(int[] ids) {
        StringBuilder idString = new StringBuilder();
        Arrays.stream(ids).forEach(value -> idString.append(value).append(", "));
        String sql = "delete from orderdetails where order_id in (" + idString.deleteCharAt(idString.lastIndexOf(",")) + ") ";
        try (Statement statement = connection.createStatement()) {
            statement.executeUpdate(sql);
        } catch (Exception exception) {
            JOptionPane.showMessageDialog(null, exception.getMessage(), "Xóa hóa đơn thất bại", JOptionPane.ERROR_MESSAGE);
        }
    }

    public List<OrderDetails> findByOrderId(long id) {
        String sql = "select * from orderDetails where order_id = ? ";
        try (PreparedStatement statement = connection.prepareStatement(sql)) {
            statement.setLong(1, id);
            return new OrderDetaisMapping().mappingAll(statement.getResultSet());
        } catch (Exception exception) {
            JOptionPane.showMessageDialog(null, exception.getMessage(), "Hiển thị hóa đơn thất bại", JOptionPane.ERROR_MESSAGE);
        }
        return null;
    }
}
