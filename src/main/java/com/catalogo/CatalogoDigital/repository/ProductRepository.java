package com.catalogo.CatalogoDigital.repository;

import com.catalogo.CatalogoDigital.model.Product;
import com.catalogo.CatalogoDigital.config.DatabaseConnection;
import org.springframework.stereotype.Repository;

import java.sql.*;
import java.util.ArrayList;
import java.util.List;

@Repository
public class ProductRepository {

    public List<Product> getAllProducts() {
        List<Product> products = new ArrayList<>();
        String sql = "SELECT * FROM products";

        try (Connection conn = DatabaseConnection.getConnection();
             Statement stmt = conn.createStatement();
             ResultSet rs = stmt.executeQuery(sql)) {

            while (rs.next()) {
                Product product = new Product();
                product.setId(rs.getInt("id"));
                product.setName(rs.getString("name"));
                product.setDescription(rs.getString("description"));
                product.setPrice(rs.getDouble("price"));
                product.setImage(rs.getString("image"));
                product.setCategory(rs.getString("category"));
                products.add(product);
            }
        } catch (SQLException e) {
            e.printStackTrace();
        }
        return products;
    }

    public void saveProduct(Product product) {
        String sql = "INSERT INTO products (name, description, price, image, category) VALUES (?, ?, ?, ?, ?)";

        try (Connection conn = DatabaseConnection.getConnection();
             PreparedStatement pstmt = conn.prepareStatement(sql)) {

            pstmt.setString(1, product.getName());
            pstmt.setString(2, product.getDescription());
            pstmt.setDouble(3, product.getPrice());
            pstmt.setString(4, product.getImage());
            pstmt.setString(5, product.getCategory());
            pstmt.executeUpdate();

        } catch (SQLException e) {
            e.printStackTrace();
        }
    }

    public void updateProduct(Long id, Product product) {
        String sql = "UPDATE products SET name = ?, description = ?, price = ?, category = ?, image = ? WHERE id = ?";

        try (Connection conn = DatabaseConnection.getConnection();
             PreparedStatement pstmt = conn.prepareStatement(sql)) {

            pstmt.setString(1, product.getName());
            pstmt.setString(2, product.getDescription());
            pstmt.setDouble(3, product.getPrice());
            pstmt.setString(4, product.getCategory());
            pstmt.setString(5, product.getImage());
            pstmt.setLong(6, id);
            pstmt.executeUpdate();

        } catch (SQLException e) {
            e.printStackTrace();
        }
    }

    public void deleteProduct(Long id) {
        String sql = "DELETE FROM products WHERE id = ?";

        try (Connection conn = DatabaseConnection.getConnection();
             PreparedStatement pstmt = conn.prepareStatement(sql)) {

            pstmt.setLong(1, id);
            pstmt.executeUpdate();

        } catch (SQLException e) {
            e.printStackTrace();
        }
    }

    public Product getProductById(Long id) {
        Product product = new Product();
        String sql = "SELECT * FROM products WHERE id = ?";

        try (Connection conn = DatabaseConnection.getConnection();
             PreparedStatement pstmt = conn.prepareStatement(sql)) {

            pstmt.setLong(1, id);
            try (ResultSet rs = pstmt.executeQuery()) {
                if (rs.next()) {
                    product.setId(rs.getInt("id"));
                    product.setName(rs.getString("name"));
                    product.setDescription(rs.getString("description"));
                    product.setPrice(rs.getDouble("price"));
                    product.setCategory(rs.getString("category"));
                    product.setImage(rs.getString("image"));
                }
            }
        } catch (SQLException e) {
            e.printStackTrace();
        }
        return product;
    }

    public List<Product> getProductsByCategory(String category) {
        List<Product> products = new ArrayList<>();
        String sql = "SELECT * FROM products WHERE category = ?";

        try (Connection conn = DatabaseConnection.getConnection();
             PreparedStatement pstmt = conn.prepareStatement(sql)) {

            pstmt.setString(1, category);
            try (ResultSet rs = pstmt.executeQuery()) {
                while (rs.next()) {
                    Product product = new Product();
                    product.setId(rs.getInt("id"));
                    product.setName(rs.getString("name"));
                    product.setDescription(rs.getString("description"));
                    product.setPrice(rs.getDouble("price"));
                    product.setCategory(rs.getString("category"));
                    product.setImage(rs.getString("image"));
                    products.add(product);

                }
            }
        } catch (SQLException e) {
            System.out.println("❌ Error al obtener productos por categoría: " + e.getMessage());
            e.printStackTrace();
}
        return products;
    }
}