package com.catalogo.CatalogoDigital.controller;

import com.catalogo.CatalogoDigital.model.Product;
import com.catalogo.CatalogoDigital.service.ProductService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.multipart.MultipartFile;

import java.io.IOException;
import java.nio.file.Files;
import java.nio.file.Path;
import java.nio.file.Paths;
import java.util.List;

@RestController
@RequestMapping("/products")
public class ProductController {

    @Autowired
    private ProductService productService;

    private static String UPLOAD_DIR = System.getProperty("user.dir") + "/src/main/resources/static/images";

    @GetMapping
    public ResponseEntity<List<Product>> getAllProducts() {
        List<Product> products = productService.getAllProducts();
        return ResponseEntity.ok(products);
    }

    @PostMapping
    public ResponseEntity<Product> saveProduct(@RequestParam("name") String name,
                                               @RequestParam("description") String description,
                                               @RequestParam("price") double price,
                                               @RequestParam("category") String category,
                                               @RequestParam("image") MultipartFile image) throws IOException {
        String imageName = image.getOriginalFilename();
        Path imagePath = Paths.get(UPLOAD_DIR, imageName);
        Files.write(imagePath, image.getBytes());

        Product product = new Product();
        product.setName(name);
        product.setDescription(description);
        product.setPrice(price);
        product.setCategory(category);
        product.setImage("/images/" + imageName);

        productService.addProduct(product);
        return ResponseEntity.ok(product);
    }

    @PutMapping("/{id}")
    public ResponseEntity<Product> editProduct(@PathVariable Long id, @RequestBody Product product) {
        productService.editProduct(id, product);
        return ResponseEntity.ok(product);
    }

    @DeleteMapping("/{id}")
    public ResponseEntity<Void> deleteProduct(@PathVariable Long id) {
        productService.deleteProduct(id);
        return ResponseEntity.noContent().build();
    }

    @GetMapping("/{id}")
    public ResponseEntity<Product> getProductById(@PathVariable Long id) {
        Product product = productService.getProductById(id);
        return ResponseEntity.ok(product);
    }

    @GetMapping("/category/{category}")
    public ResponseEntity<List<Product>> getProductsByCategory(@PathVariable String category) {
        List<Product> products = productService.getProductsByCategory(category);
        return ResponseEntity.ok(products);
    }
}