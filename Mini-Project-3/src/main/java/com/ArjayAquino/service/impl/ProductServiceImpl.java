package com.ArjayAquino.service.impl;

import com.ArjayAquino.model.Product;
import com.ArjayAquino.service.ProductService;

import java.io.*;
import java.util.ArrayList;
import java.util.List;
import java.util.stream.Collectors;

/**
 * Implementation of the product service interface.
 */
public class ProductServiceImpl implements ProductService {
    private List<Product> products;

    public ProductServiceImpl() {
        this.products = new ArrayList<>();
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
        } catch (IOException e) {
            e.printStackTrace();
        }
        return products;
    }

    @Override
    public Product getProductById(String productId) {
        return products.stream()
                .filter(product -> product.getProductId().equals(productId))
                .findFirst()
                .orElse(null);
    }

    @Override
    public void updateProduct(Product product) {
        products = products.stream()
                .map(p -> p.getProductId().equals(product.getProductId()) ? product : p)
                .collect(Collectors.toList());
        saveProducts("products.csv");
    }

    private void saveProducts(String filePath) {
        try (BufferedWriter bw = new BufferedWriter(new FileWriter(new File(getClass().getClassLoader().getResource(filePath).getFile())))) {
            for (Product product : products) { // Write each product to the CSV file
                bw.write(String.format("%s,%s,%d,%.2f%n", product.getProductId(), product.getProductName(), product.getQuantity(), product.getPrice()));
            }
        } catch (IOException e) {
            e.printStackTrace();
        }
    }
}
