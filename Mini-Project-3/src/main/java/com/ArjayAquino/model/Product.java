package com.ArjayAquino.model;

import lombok.Getter;
import lombok.Setter;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

import java.math.BigDecimal;

/**
 * Represents a product in the e-commerce system.
 */
@Getter
@Setter
public class Product {
    private static final Logger logger = LoggerFactory.getLogger(Product.class);
    private String productId;
    private String productName;
    private int quantity;
    private BigDecimal price;

    /**
     * Constructs a Product with specified details.
     *
     * @param productId   the product ID
     * @param productName the product name
     * @param quantity    the quantity of the product
     * @param price       the price of the product
     */
    public Product(String productId, String productName, int quantity, double price) {
        this.productId = productId;
        this.productName = productName;
        this.quantity = quantity;
        this.price = BigDecimal.valueOf(price);
        logger.info("Product created: {} - {}", productId, productName);
    }

    /**
     * Gets the price as a double.
     *
     * @return the price as double
     */
    public double getPrice() {
        return price.doubleValue();
    }

    /**
     * Sets the price as a double.
     *
     * @param price the price to set
     */
    public void setPrice(double price) {
        this.price = BigDecimal.valueOf(price);
        logger.info("Price set for product {}: {}", productId, price);
    }

    /**
     * Gets the price as BigDecimal.
     *
     * @return the price as BigDecimal
     */
    public BigDecimal getPriceAsBigDecimal() {
        return price;
    }

    @Override
    public String toString() {
        return String.format("%-10s %-15s %-10d %-10.2f", productId, productName, quantity, getPrice());
    }
}
