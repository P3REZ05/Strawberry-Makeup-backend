package com.catalogo.CatalogoDigital.service;

import com.catalogo.CatalogoDigital.model.Product;
import com.catalogo.CatalogoDigital.config.DatabaseConnection;
import org.springframework.stereotype.Service;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.List;

@Service
public class ProductService {

    public List<Product> getAllProducts() {
        List<Product> products = new ArrayList<>();
        String sql = "SELECT * FROM products";

        try (Connection conn = DatabaseConnection.getConnection();
             PreparedStatement pstmt = conn.prepareStatement(sql);
             ResultSet rs = pstmt.executeQuery()) {

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
            System.err.println("❌ Error al obtener productos: " + e.getMessage());
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
            System.out.println("✅ Producto guardado correctamente.");
        } catch (SQLException e) {
            System.err.println("❌ Error al guardar producto: " + e.getMessage());
        }
    }
}
