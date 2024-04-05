package Mapping;

import Entity.OrderDetails;

import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.List;

public class OrderDetaisMapping {
    public OrderDetails mapping(ResultSet resultSet) throws SQLException {
        if (resultSet.next()) {
            OrderDetails orderDetails = new OrderDetails();
            orderDetails.setOrderDetailsId(resultSet.getLong("orderDetails_id"));
            orderDetails.setOrderId(resultSet.getLong("order_Id"));
            orderDetails.setProductId(resultSet.getNString("product_id"));
            orderDetails.setProductCost(resultSet.getLong("product_cost"));
            orderDetails.setQuantity(resultSet.getInt("quantity"));
            orderDetails.setSalePrice(resultSet.getLong("sale_price"));
            return orderDetails;
        }
        return null;
    }

    public List<OrderDetails> mappingAll(ResultSet resultSet) throws SQLException {
        List<OrderDetails> data = new ArrayList<>();
        while (resultSet.next()) {
            data.add(this.mapping(resultSet));
        }
        return data;
    }
}
