package DAO;

import Entity.Product;
import Mapping.ProductMapping;

import javax.swing.*;
import java.sql.Connection;
import java.sql.Date;
import java.sql.PreparedStatement;
import java.time.LocalDate;
import java.util.Collections;
import java.util.List;

public class ProductDAO {
    private Connection connection;

    public ProductDAO() {
        connection = ConnectSql.getConnect();
    }

    public boolean save(Product product) {
        try {
            ConnectSql.setAutocommit(connection, false);
            String sql = "insert into product(product_id , product_name , 
            product_price , product_cost , product_image , product_unit) 
            values(?,?,?,?,?,?)";
            PreparedStatement statement = connection.prepareStatement(sql);
            statement.setString(1, product.getProductId());
            statement.setString(2, product.getProductName());
            statement.setLong(3, product.getProductPrice());
            statement.setLong(4, product.getProductCost());
            statement.setString(5, product.getProductImage());
            statement.setString(6, product.getProductUnit());
            if (statement.executeUpdate() != 0) {
                connection.commit();
                return true;
            }
        } catch (Exception exception) {
            JOptionPane.showMessageDialog(null, exception.getMessage(), "Lỗi lưu sản phẩm", JOptionPane.ERROR_MESSAGE);
            ConnectSql.rollBack(connection);
        } finally {
            ConnectSql.setAutocommit(connection, true);
        }
        return false;
    }

    public boolean update(Product product) {
        try {
            ConnectSql.setAutocommit(connection, false);
            String sql = "update product set  product_name = ? , 
            product_price = ? , product_cost = ?, product_image = ? , 
            product_unit = ? ,modifiedDate = ?  where product_id = ? ";
            PreparedStatement statement = connection.prepareStatement(sql);
            statement.setString(1, product.getProductName());
            statement.setLong(2, product.getProductPrice());
            statement.setLong(3, product.getProductCost());
            statement.setString(4, product.getProductImage());
            statement.setString(5, product.getProductUnit());
            statement.setDate(6, Date.valueOf(LocalDate.now()));
            statement.setString(7, product.getProductId());
            if (statement.executeUpdate() != 0) {
                connection.commit();
                return true;
            }
        } catch (Exception exception) {
            JOptionPane.showMessageDialog(null, exception.getMessage()
            , JOptionPane.ERROR_MESSAGE);
            ConnectSql.rollBack(connection);
        } finally {
            ConnectSql.setAutocommit(connection, true);
        } return false;
    }

    public boolean delete(long productId) {
        try {
            String sql = "delete from product where product_id = ?";
            PreparedStatement statement = connection.prepareStatement(sql);
            statement.setLong(1, productId);
            if (statement.executeUpdate() != 0)
                return true;
        } catch (Exception exception) {
            JOptionPane.showMessageDialog(null, exception.getMessage(), "Lỗi xóa sản phẩm", JOptionPane.ERROR_MESSAGE);
        }
        return false;
    }

    public boolean deleteIn(List<String> idList) {
        try {
            StringBuilder builder = new StringBuilder();
            idList.forEach((value) -> builder.append("'" + value + "'" + ","));
            builder.deleteCharAt(builder.lastIndexOf(","));

            String sql = "delete from product where product_id in (" + builder + " )";
            System.out.println(sql);
            PreparedStatement statement = connection.prepareStatement(sql);
            if (statement.executeUpdate() != 0)
                return true;
        } catch (Exception exception) {
            JOptionPane.showMessageDialog(null, exception.getMessage(), "Lỗi xóa các sản phẩm", JOptionPane.ERROR_MESSAGE);
        }
        return false;
    }

    /**
     * @return
     */
    public List<Product> findAll() {
        String sql = "select * from product order by createDate desc";
        try (PreparedStatement statement = connection.prepareStatement(sql);) {
            return new ProductMapping().mappingAll(statement.executeQuery());
        } catch (Exception exception) {
            JOptionPane.showMessageDialog(null, exception.getMessage(), "Lỗi lấy danh sách sản phẩm", JOptionPane.ERROR_MESSAGE);
        }
        return null;
    }

    /**
     * Tìm kiếm theo : id , tên , ngày tạo , ngày sửa  ,dvt , giá bán
     *
     * @param data
     * @param category
     * @return *
     */
    public List<Product> find(String data, String category) {
        String sql = "select * from product where " + category + " like '%" + data + "%' order by CreateDate desc ";
        try (PreparedStatement statement = connection.prepareStatement(sql);) {
            return new ProductMapping().mappingAll(statement.executeQuery());
        } catch (Exception exception) {
            JOptionPane.showMessageDialog(null, exception.getMessage(), "Lỗi tìm sản phẩm theo danh mục", JOptionPane.ERROR_MESSAGE);
        }
        return null;
    }

    /**
     * @param id
     * @return
     */
    public Product findById(String id) {
        String sql = "select * from product where product_Id = ? ";
        try (PreparedStatement statement = connection.prepareStatement(sql);) {
            statement.setString(1, id);
            return new ProductMapping().mapping(statement.executeQuery());
        } catch (Exception exception) {
            JOptionPane.showMessageDialog(null, exception.getMessage(), "Lỗi tìm sản phẩm", JOptionPane.ERROR_MESSAGE);
            return null;
        }
    }

    public List<Product> findAllOrderByPrice() {
        String sql = "select * from product";
        try (PreparedStatement statement = connection.prepareStatement(sql);) {
            // get list products
            List<Product> products = new ProductMapping().mappingAll(statement.executeQuery());
            // order by price
            int size = products.size();
            for (int i = 0; i < size; i++) {
                for (int j = i + 1; j < size; j++) {
                    if (products.get(i).getProductPrice() > products.get(j).getProductPrice()) {
                        Collections.swap(products, i, j);
                    }
                }
            }
            return products;
        } catch (Exception exception) {
            JOptionPane.showMessageDialog(null, exception.getMessage(), "Lỗi lấy danh sách sản phẩm", JOptionPane.ERROR_MESSAGE);
            return null;
        }
    }
}
