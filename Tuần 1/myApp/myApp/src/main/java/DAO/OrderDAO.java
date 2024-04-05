package DAO;

import Entity.Order;
import Entity.OrderDto;
import Mapping.OrderMapping;

import javax.swing.*;
import java.sql.*;
import java.util.Arrays;
import java.util.List;

public class OrderDAO {
    private final Connection connection;
    private final OrderMapping orderMapping;

    public OrderDAO() {
        connection = ConnectSql.getConnect();
        orderMapping = new OrderMapping();
    }

    public long save(Order order) {
        ConnectSql.setAutocommit(connection, false);
        String sql = "insert into `order`(user_id , description , discount ,cost_incurred ) values(?,?,?,?)";
        try (PreparedStatement statement = connection.prepareStatement(sql)) {
            statement.setShort(1, order.getUserId());
            statement.setString(2, order.getDescription());
            statement.setDouble(3, order.getDiscount());
            statement.setLong(4, order.getCost_incurred());

            if (statement.executeUpdate() != 0) {
                connection.commit();
                return this.getLastValue();
            }
        } catch (Exception exception) {
            JOptionPane.showMessageDialog(null, exception.getMessage(), "Lỗi lưu hóa đơn", JOptionPane.ERROR_MESSAGE);
            ConnectSql.rollBack(connection);
        } finally {
            ConnectSql.setAutocommit(connection, true);
        }
        return 0;
    }

    /**
     * Get last value
     *
     * @return
     * @throws SQLException
     */
    public long getLastValue() throws SQLException {
        try (PreparedStatement statement = connection.prepareStatement("SELECT order_Id  FROM `order` ORDER BY order_Id desc LIMIT 1")) {
            ResultSet resultSet = statement.executeQuery();
            if (resultSet.next())
                return resultSet.getLong(1);
        }
        return 0;
    }

    //region findOrder
    public List<OrderDto> findAll() {
        String sql = "select *, "
                + "ROUND( ( sum(od.quantity * od.sale_Price ) + o.cost_incurred ) * (100-o.discount) / 100 , -3) total "
                + "from `order` o "
                + "left join orderdetails od on o.order_Id = od.order_Id "
                + "group by o.order_Id "
                + "order by createDate desc ";
        try (Statement statement = connection.createStatement()) {
            ResultSet rs = statement.executeQuery(sql);
            return orderMapping.mappingAllOrderDto(rs);
        } catch (Exception exception) {
            JOptionPane.showMessageDialog(null, exception.getMessage(), "Lỗi lấy danh sách hóa đơn", JOptionPane.ERROR_MESSAGE);
        }
        return null;
    }

    public List<OrderDto> findByCondition(String value, String condition) {
        String sql = " select * "
                + "from ( "
                + "        select o.*, "
                + "        ROUND((sum(od.quantity * od.sale_Price) + o.cost_incurred) * (100 - o.discount) / 100, -3) total "
                + "        from `order` o "
                + "        left join orderdetails od on o.order_Id = od.order_Id "
                + "        group by o.order_Id "
                + "        order by createDate desc "
                + " ) as temp "
                + " where " + condition + " like N'%" + value + "%' ";
        try (PreparedStatement statement = connection.prepareStatement(sql)) {
            ResultSet rs = statement.executeQuery();
            return orderMapping.mappingAllOrderDto(rs);
        } catch (Exception exception) {
            JOptionPane.showMessageDialog(null, exception.getMessage(), "Tìm kiếm hóa đơn thất bại", JOptionPane.ERROR_MESSAGE);
        }
        return null;
    }

    public Order findByOrderId(long id) {
        String sql = "SELECT o.* " +
                "FROM `order` o " +
                "where o.order_id = ? ";
        try (PreparedStatement statement = connection.prepareStatement(sql)) {
            statement.setLong(1, id);
            return new OrderMapping().mapping(statement.getResultSet());
        } catch (Exception exception) {
            JOptionPane.showMessageDialog(null, exception.getMessage(), "Hiển thị hóa đơn thất bại", JOptionPane.ERROR_MESSAGE);
        }
        return  null;
    }
    //endregion

    public void deleteOrders(int[] ids) {
        StringBuilder idString = new StringBuilder();
        Arrays.stream(ids).forEach(value -> idString.append(value).append(", "));
        String sql = "delete from `order` where order_id in (" + idString.deleteCharAt(idString.lastIndexOf(",")) + ") ";
        try (Statement statement = connection.createStatement()) {
            statement.executeUpdate(sql);
        } catch (Exception exception) {
            JOptionPane.showMessageDialog(null, exception.getMessage(), "Xóa hóa đơn thất bại", JOptionPane.ERROR_MESSAGE);
        }
    }
}
