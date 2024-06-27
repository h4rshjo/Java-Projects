package com.ArjayAquino;

import com.ArjayAquino.model.Product;
import com.ArjayAquino.service.impl.ProductServiceImpl;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;

import java.util.List;

import static org.junit.jupiter.api.Assertions.*;

public class ProductServiceImplTest {

    private ProductServiceImpl productService;

    @BeforeEach
    public void setUp() {
        productService = new ProductServiceImpl();
    }

    @Test
    public void testLoadProducts() {
        List<Product> products = productService.loadProducts("products.csv");
        assertFalse(products.isEmpty());
        assertEquals(20, products.size());
    }

    @Test
    public void testGetProductById() {
        productService.loadProducts("products.csv");
        Product product = productService.getProductById("P1");
        assertNotNull(product);
        assertEquals("Apple", product.getProductName());
    }

    @Test
    public void testUpdateProduct() {
        productService.loadProducts("products.csv");
        Product product = productService.getProductById("P1");
        product.setQuantity(45);
        productService.updateProduct(product);
        Product updatedProduct = productService.getProductById("P1");
        assertEquals(45, updatedProduct.getQuantity());
    }
}
