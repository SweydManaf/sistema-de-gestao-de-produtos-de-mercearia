package controllers;

import entities.Product;
import models.ProductDAO;

import javax.swing.*;
import javax.swing.table.DefaultTableModel;
import java.util.List;

public class ProductController {
    private ProductDAO productDAO;

    public ProductController(){
        productDAO = new ProductDAO();
    }

    public boolean createProduct(JDialog root, Product product) throws Exception{
        if(!productDAO.isValidProduct(product)){
            productDAO.createProduct(product);
            return true;
        } else {
            JOptionPane.showConfirmDialog(root, "O producto que pretende adicionar já existe!");
            return false;
        }
    }

    public boolean create(Product product) throws Exception{
        if(!productDAO.isValidProduct(product)){
            productDAO.createProduct(product);
            return true;
        }
        return false;
    }

    public void updateProduct(Product product) throws Exception{
        productDAO.updateProduct(product);
    }

    public void deleteProduct(int product_id) throws Exception{
        productDAO.deleteProduct(product_id);
    }

    public Product getProductByID(int product_id) throws Exception{
        return productDAO.getProductByID(product_id);
    }

    public void getAllProducts(JTable productsTable) throws Exception{
        // get current model of table
        DefaultTableModel model = (DefaultTableModel) productsTable.getModel();

        // clear old data on table
        model.setRowCount(0);

        // get all available products and output to tblProduct
        List<Product> products = productDAO.getAllProducts();
//        products.forEach(product -> model.addRow(product.toda));
    }

    public List<Product> getAll() throws Exception{
        return productDAO.getAllProducts();
    }
}
