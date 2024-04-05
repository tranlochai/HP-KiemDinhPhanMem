package Service;

import DAO.ProductDAO;
import Entity.Product;
import Utils.Utils;

import javax.swing.*;
import javax.swing.table.DefaultTableModel;
import java.util.List;

public class ProductService {
    private ProductDAO productDAO;

    public ProductService() {
        productDAO = new ProductDAO();
    }

    public void find(JTable table, String data, String category) {
        List<Product> productList = productDAO.find(data, category);
        if (productList != null && !productList.isEmpty()) {
            DefaultTableModel datarow = (DefaultTableModel) table.getModel();
            datarow.setRowCount(0);
            int index = 1;
            for (Product item : productList) {
                datarow.addRow(new String[]{index + "",
                        item.getProductId(),
                        item.getProductName(),
                        item.getProductUnit(),
                        Utils.numberToString(item.getProductPrice())});
                index++;
            }
        }
    }

    public DefaultListModel<String> find(String data, String category) {
        List<Product> productList = productDAO.find(data, category);
        if (productList != null && !productList.isEmpty()) {
            DefaultListModel<String> dataConvert = new DefaultListModel<>();
            productList.forEach(value ->
                    dataConvert.addElement(value.getProductId() + "-" + value.getProductName()));
            return dataConvert;
        }
        return null;
    }


    public List<Product> findAll() {
        return productDAO.findAll();
    }

    public Product findById(String id) {
        return productDAO.findById(id);
    }

    public boolean save(Product product) {
        return productDAO.save(product);
    }

    public boolean update(Product product) {
        return productDAO.update(product);
    }

    public void remove(List<String> idList) {
        productDAO.deleteIn(idList);
    }
}
