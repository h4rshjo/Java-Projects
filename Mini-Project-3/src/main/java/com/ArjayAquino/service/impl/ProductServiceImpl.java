package com.ArjayAquino.service.impl;

import com.ArjayAquino.model.Product;
import com.ArjayAquino.service.ProductService;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

import java.io.*;
import java.util.ArrayList;
import java.util.List;
import java.util.stream.Collectors;

/**
 * Implementation of the product service interface.
 */
public class ProductServiceImpl implements ProductService {
    private static final Logger logger = LoggerFactory.getLogger(ProductServiceImpl.class);
    private List<Product> products;

    public ProductServiceImpl() {
        this.products = new ArrayList<>();
        logger.info("ProductServiceImpl initialized.");
    }

    @Override
    public List<Product> loadProducts(String filePath) {
        products.clear();
        try (BufferedReader br = new BufferedReader(new InputStreamReader(getClass().getClassLoader().getResourceAsStream(filePath)))) {
            if (br == null) { // Check if the file is found
                throw new IOException("File not found: " + filePath);
            }
            String line;
            while ((line = br.readLine()) != null) { // Read each line from the CSV file
                String[] details = line.split(",");
                Product product = new Product(details[0], details[1], Integer.parseInt(details[2]), Double.parseDouble(details[3]));
                products.add(product);
            }
            logger.info("Loaded products from file: {}", filePath);
        } catch (IOException e) {
            logger.error("Error loading products from file: {}", filePath, e);
        }
        return products;
    }

    @Override
    public Product getProductById(String productId) {
        Product product = products.stream()
                .filter(p -> p.getProductId().equals(productId))
                .findFirst()
                .orElse(null);
        logger.info("Retrieved product by ID: {} - found: {}", productId, product != null);
        return product;
    }

    @Override
    public void updateProduct(Product product) {
        products = products.stream()
                .map(p -> p.getProductId().equals(product.getProductId()) ? product : p)
                .collect(Collectors.toList());
        saveProducts("products.csv");
        logger.info("Updated product: {}", product.getProductId());
    }

    private void saveProducts(String filePath) {
        try (BufferedWriter bw = new BufferedWriter(new FileWriter(new File(getClass().getClassLoader().getResource(filePath).getFile())))) {
            for (Product product : products) { // Write each product to the CSV file
                bw.write(String.format("%s,%s,%d,%.2f%n", product.getProductId(), product.getProductName(), product.getQuantity(), product.getPrice()));
            }
            logger.info("Saved products to file: {}", filePath);
        } catch (IOException e) {
            logger.error("Error saving products to file: {}", filePath, e);
        }
    }
}
