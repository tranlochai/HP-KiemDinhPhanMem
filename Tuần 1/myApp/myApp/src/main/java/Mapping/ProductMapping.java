package Mapping;

import Entity.Product;

import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.List;

public class ProductMapping {
    public Product mapping(ResultSet resultSet) throws SQLException {
        if (resultSet.next()) {
            Product product = new Product();
            product.setProductId(resultSet.getNString("product_id"));
            product.setProductCost(resultSet.getLong("product_cost"));
            product.setProductPrice(resultSet.getLong("product_price"));
            product.setProductImage(resultSet.getNString("product_image"));
            product.setProductName(resultSet.getNString("product_name"));
            product.setProductUnit(resultSet.getNString("product_unit"));
            product.setCreateDate(resultSet.getDate("createDate"));
            product.setModifiedDate(resultSet.getDate("modifiedDate"));
            return product;
        }
        return null;
    }

    public List<Product> mappingAll(ResultSet resultSet) throws SQLException {
        List<Product> productList = new ArrayList<>();
        while (resultSet.next()) {
            Product product = new Product();
            product.setProductId(resultSet.getNString("product_id"));
            product.setProductCost(resultSet.getLong("product_cost"));
            product.setProductPrice(resultSet.getLong("product_price"));
            product.setProductImage(resultSet.getNString("product_image"));
            product.setProductName(resultSet.getNString("product_name"));
            product.setProductUnit(resultSet.getNString("product_unit"));
            product.setCreateDate(resultSet.getDate("createDate"));
            product.setModifiedDate(resultSet.getDate("modifiedDate"));
            productList.add(product);
        }
        return productList;
    }
}
