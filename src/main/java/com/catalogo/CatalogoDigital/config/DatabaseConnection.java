package com.catalogo.CatalogoDigital.config;

import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.SQLException;

public class DatabaseConnection {
    private static final String URL = "jdbc:sqlite:catalogo.db";

    public static Connection getConnection() {
        try {
            Connection conn = DriverManager.getConnection(URL);
            System.out.println("Conexión exitosa a la base de datos SQLite.");
            return conn;
        } catch (SQLException e) {
            throw new RuntimeException("Error al conectar a SQLite", e);
        }
    }
}
