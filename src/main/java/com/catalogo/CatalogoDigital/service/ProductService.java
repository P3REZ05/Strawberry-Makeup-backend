package com.catalogo.CatalogoDigital.service;

import com.catalogo.CatalogoDigital.model.Product;
import com.catalogo.CatalogoDigital.repository.ProductRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
public class ProductService {

    @Autowired
    private ProductRepository productRepository;

    public List<Product> getAllProducts() {
        return productRepository.getAllProducts();
    }

    public void addProduct(Product product) {
        productRepository.saveProduct(product);
    }

    public void editProduct(Long id, Product product) {
        productRepository.updateProduct(id, product);
    }

    public void deleteProduct(Long id) {
        productRepository.deleteProduct(id);
    }

    public Product getProductById(Long id) {
        return productRepository.getProductById(id);
    }

    public List<Product> getProductsByCategory(String category) {
        return productRepository.getProductsByCategory(category);
    }
}