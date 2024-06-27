package com.ArjayAquino.service;

import com.ArjayAquino.model.Product;

import java.math.BigDecimal;

/**
 * Service interface for managing the cart.
 */
public interface CartService {
    void addProductToCart(Product product, int quantity);
    void removeProductFromCart(String productId, int quantity);
    String viewCart();
    BigDecimal calculateTotal();
}
