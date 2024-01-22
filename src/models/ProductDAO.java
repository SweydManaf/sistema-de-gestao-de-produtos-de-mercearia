package models;

import database.DataBaseConnection;
import entities.Product;

import java.sql.*;
import java.time.LocalDate;
import java.util.ArrayList;
import java.util.List;
import java.util.Optional;

public class ProductDAO {
    public List<Product> getAllProducts() throws Exception{
        String query = "SELECT * FROM Products";
        Connection conn = new DataBaseConnection().getConnection();
        PreparedStatement ps = conn.prepareStatement(query);

        ResultSet rs = ps.executeQuery();
        List<Product> products = new ArrayList<>();

        while (rs.next()){
            int id = rs.getInt("id");
            String name = rs.getString("name");
            String category = rs.getString("category");
            float price = rs.getFloat("price");
            Timestamp expiration_date = rs.getTimestamp("expiration_date");
            Timestamp created_at = rs.getTimestamp("created_at");
            products.add(new Product(id, name, category, price, expiration_date.toLocalDateTime().toLocalDate(), created_at.toLocalDateTime().toLocalDate()));
        }

        rs.close();
        conn.close();
        return products;
    }

    public Product getProductByID(int product_id) throws Exception{
        List<Product> products = getAllProducts();

        Optional<Product> product = products.stream().filter(product_s -> (product_s.getId() == product_id)).findFirst();
        return product.orElse(null);
    }

    public void createProduct(Product product) throws Exception {
        String query = "INSERT INTO Products (name, category, price, expiration_date, created_at) VALUES (?, ?, ?, ?, ?)";
        Connection conn = new DataBaseConnection().getConnection();
        PreparedStatement ps = conn.prepareStatement(query);
        ps.setString(1, product.getName());
        ps.setString(2, product.getCategory());
        ps.setFloat(3, product.getPrice());
        ps.setDate(4, Date.valueOf(product.getExpiration_date()) );
        ps.setDate(5, Date.valueOf(product.getCreated_at()));
        ps.executeUpdate();
        conn.close();
    }

    public void updateProduct(Product product) throws Exception{
        String query = "UPDATE Products set name = ?, category = ?, price = ?, expiration_date = ?, created_at = ? WHERE " + "id = " + product.getId();

        Connection conn = new DataBaseConnection().getConnection();

        PreparedStatement ps = conn.prepareStatement(query);
        ps.setString(1, product.getName());
        ps.setString(2, product.getCategory());
        ps.setFloat(3, product.getPrice());
        ps.setDate(4, Date.valueOf(product.getExpiration_date()) );
        ps.setDate(5, Date.valueOf(product.getCreated_at()) );

        ps.executeUpdate();
        conn.close();
    }

    public void deleteProduct(int product_id) throws Exception{
        String query = "DELETE FROM Products WHERE id = ?";

        Connection conn = new DataBaseConnection().getConnection();

        PreparedStatement ps = conn.prepareStatement(query);
        ps.setInt(1, product_id);
        ps.executeUpdate();

        conn.close();
    }

    public boolean isValidProduct(Product product) throws Exception{
        List<Product> products = getAllProducts();
        Optional<Product> productFinded = products.stream().filter(p -> (p.getId() == product.getId())).findFirst();

        return productFinded.isPresent();
    }
}
