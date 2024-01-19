package controllers;

import entities.Product;
import models.ProductDAO;

import javax.swing.*;
import javax.swing.table.DefaultTableModel;
import java.time.DateTimeException;
import java.time.LocalDate;
import java.time.format.DateTimeFormatter;
import java.util.List;

public class ProductController {
    private final ProductDAO productDAO;

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

    public boolean isValidForm(JDialog root, String name, String price, String category, String expiration){
        if (name.trim().isEmpty()){
            JOptionPane.showMessageDialog(root, "O nome do produto é um campo obrigatório!");
            return false;
        }
        if (price.trim().isEmpty()) {
            JOptionPane.showMessageDialog(root, "O preço do produto é um campo obrigatório.");
        try {
            float priceValue = Float.parseFloat(price);
            if (priceValue < 0) {
                JOptionPane.showMessageDialog(root, "O preço do produto não pode ser negativo.");
                return false;
            }
        } catch (NumberFormatException e) {
            JOptionPane.showMessageDialog(root, "O preço do produto deve conter apenas números.");
            return false;
        }
        } else if(expiration.trim().length() == 0){
            JOptionPane.showMessageDialog(root, "O prazo de validade é um campo obrigatório!");
            return false;
        }

        try{
            LocalDate.parse(expiration, DateTimeFormatter.ISO_LOCAL_DATE);
        } catch (DateTimeException e){
            JOptionPane.showMessageDialog(root, "O formato inserido está incorreto por favor siga o exemplo: YYYY-MM-DD!");
            return false;
        }

        return true;
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

    public void deleteProduct(JDialog root, int product_id) throws Exception{
        if(product_id > 0){
            int response = JOptionPane.showConfirmDialog(root, "Tem certeza que deseja excluir este produto?");
            if(response == JOptionPane.YES_OPTION){
                productDAO.deleteProduct(product_id);
                JOptionPane.showMessageDialog(root, "Produto eliminado com sucesso!");
            }

        } else{
            JOptionPane.showConfirmDialog(root, "Impossivel apagar este produto.");
        }
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

    public int getTotalProducts() throws Exception {
        List<Product> products = getAll();
        return products.size();
    }

    public float getTotalAmountProducts() throws Exception {
        List<Product> products = getAll();
        float acc = 0;
        for (Product product : products) {
            acc += product.getPrice();
        }
       return acc;
    }
    public List<Product> getAll() throws Exception{
        return productDAO.getAllProducts();
    }
}
