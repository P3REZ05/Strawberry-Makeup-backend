package com.catalogo.CatalogoDigital.repository;

import com.catalogo.CatalogoDigital.config.DatabaseConnection;
import com.catalogo.CatalogoDigital.model.Admin;
import org.springframework.stereotype.Repository;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.List;
import java.util.Optional;

@Repository
public class AdminRepository {

    public void addAdmin(Admin admin) {
        String sql = "INSERT INTO admin (nombre, contraseña) VALUES (?, ?)";

        try (Connection conn = DatabaseConnection.getConnection();
             PreparedStatement pstmt = conn.prepareStatement(sql)) {
            pstmt.setString(1, admin.getNombre());
            pstmt.setString(2, admin.getContraseña());
            pstmt.executeUpdate();
        } catch (SQLException e) {
            e.printStackTrace();
            System.out.println("❌ Error al guardar admin: " + e.getMessage());
        }
    }

    public Optional<Admin> findByNombre(String nombre) {
        String sql = "SELECT * FROM admin WHERE nombre = ?";
        Admin admin = null;

        try (Connection conn = DatabaseConnection.getConnection();
             PreparedStatement pstmt = conn.prepareStatement(sql)) {
            pstmt.setString(1, nombre);
            ResultSet rs = pstmt.executeQuery();

            if (rs.next()) {
                admin = new Admin();
                admin.setId(rs.getLong("id"));
                admin.setNombre(rs.getString("nombre"));
                admin.setContraseña(rs.getString("contraseña"));
            }
        } catch (SQLException e) {
            e.printStackTrace();
            System.out.println("❌ Error al obtener admin: " + e.getMessage());
        }

        return Optional.ofNullable(admin);
    }

    public List<Admin> getAdmins() {
        String sql = "SELECT * FROM admin";
        List<Admin> admins = new ArrayList<>();

        try (Connection conn = DatabaseConnection.getConnection();
             PreparedStatement pstmt = conn.prepareStatement(sql);
             ResultSet rs = pstmt.executeQuery()) {
            while (rs.next()) {
                Admin admin = new Admin();
                admin.setId(rs.getLong("id"));
                admin.setNombre(rs.getString("nombre"));
                admin.setContraseña(rs.getString("contraseña"));
                admins.add(admin);
            }
        } catch (SQLException e) {
            e.printStackTrace();
            System.out.println("❌ Error al obtener admins: " + e.getMessage());
        }

        return admins;
    }
}