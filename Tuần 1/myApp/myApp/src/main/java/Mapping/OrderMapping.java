package Mapping;

import Entity.Order;
import Entity.OrderDto;

import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.List;

public class OrderMapping {
    public Order mapping(ResultSet resultSet) throws SQLException {
        if (resultSet.next()) {
            Order order = new Order();
            order.setOrderId(resultSet.getLong("order_Id"));
            order.setCreateDate(resultSet.getTimestamp("createDate"));
            order.setUserId(resultSet.getShort("user_id"));
            order.setDescription(resultSet.getNString("description"));
            order.setDiscount(resultSet.getDouble("discount"));
            order.setCost_incurred(resultSet.getLong("cost_incurred"));
            return order;
        }
        return null;
    }

    public List<Order> mappingAll(ResultSet resultSet) throws SQLException {
        List<Order> orderList = new ArrayList<>();
        while (resultSet.next()) {
            orderList.add(this.mapping(resultSet));
        }
        return orderList;
    }

    public List<OrderDto> mappingAllOrderDto(ResultSet resultSet) throws SQLException {
        List<OrderDto> orderList = new ArrayList<>();
        while (resultSet.next()) {
            OrderDto order = new OrderDto();
            order.setOrderId(resultSet.getLong("order_Id"));
            order.setCreateDate(resultSet.getTimestamp("createDate"));
            order.setUserId(resultSet.getShort("user_id"));
            order.setDescription(resultSet.getNString("description"));
            order.setDiscount(resultSet.getDouble("discount"));
            order.setCost_incurred(resultSet.getLong("cost_incurred"));
            order.setTotalPrice( resultSet.getLong("total"));
            orderList.add(order);
        }
        return orderList;
    }
}
