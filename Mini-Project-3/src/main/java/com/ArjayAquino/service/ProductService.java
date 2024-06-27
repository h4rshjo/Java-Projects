package com.ArjayAquino.service;

import com.ArjayAquino.model.Product;

import java.util.List;

/**
 * Service interface for managing products.
 */
public interface ProductService {
    List<Product> loadProducts(String filePath);
    Product getProductById(String productId);
    void updateProduct(Product product);
}
