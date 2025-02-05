package com.catalogo.CatalogoDigital;

import com.catalogo.CatalogoDigital.config.DatabaseInitializer;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;

@SpringBootApplication
public class CatalogoDigitalApplication {

	public static void main(String[] args) {
		SpringApplication.run(CatalogoDigitalApplication.class, args);

		// Inicializar la base de datos al iniciar la aplicación
		DatabaseInitializer.initialize();
		System.out.println("Base de datos SQLite inicializada correctamente.");
	}
}
