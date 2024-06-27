package com.ArjayAquino.model;

import lombok.Getter;
import lombok.Setter;

import java.math.BigDecimal;
import java.util.ArrayList;
import java.util.List;

/**
 * Represents a shopping cart in the e-commerce system.
 */
@Getter
@Setter
public class Cart {
    private List<Product> items;

    /**
     * Constructs an empty Cart.
     */
    public Cart() {
        this.items = new ArrayList<>();
    }

    /**
     * Adds a product to the cart.
     *
     * @param product the product to add
     * @param quantity the quantity of the product to add
     */
    public void addProduct(Product product, int quantity) {
        for (Product p : items) { // Loop through items in the cart
            if (p.getProductId().equals(product.getProductId())) { // Check if product is already in the cart
                p.setQuantity(p.getQuantity() + quantity);
                return;
            }
        }
        Product newProduct = new Product(product.getProductId(), product.getProductName(), quantity, product.getPrice());
        items.add(newProduct);
    }

    /**
     * Removes a specified quantity of a product from the cart.
     *
     * @param productId the product ID
     * @param quantity the quantity to remove
     */
    public void removeProduct(String productId, int quantity) {
        for (Product product : items) { // Loop through items in the cart
            if (product.getProductId().equals(productId)) { // Check if the product is in the cart
                int currentQuantity = product.getQuantity();
                if (quantity > currentQuantity) { // Check if quantity to remove is valid
                    throw new IllegalArgumentException("Cannot remove more than the current quantity in the cart.");
                }
                int newQuantity = currentQuantity - quantity;
                if (newQuantity > 0) { // Update the quantity if more than zero
                    product.setQuantity(newQuantity);
                } else { // Remove the product from the cart if quantity becomes zero
                    items.remove(product);
                }
                return;
            }
        }
        throw new IllegalArgumentException("Product not found in the cart.");
    }

    /**
     * Calculates the total price of items in the cart.
     *
     * @return the total price as BigDecimal
     */
    public BigDecimal calculateTotalPrice() {
        return items.stream()
                .map(product -> product.getPriceAsBigDecimal().multiply(BigDecimal.valueOf(product.getQuantity())))
                .reduce(BigDecimal.ZERO, BigDecimal::add);
    }

    @Override
    public String toString() {
        if (items.isEmpty()) { // Check if the cart is empty
            return "Nothing is here yet";
        }
        StringBuilder cartContent = new StringBuilder();
        items.forEach(product -> cartContent.append(product.toString()).append("\n"));
        return cartContent.toString();
    }
}
