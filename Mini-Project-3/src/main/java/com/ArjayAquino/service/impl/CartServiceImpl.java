package com.ArjayAquino.service.impl;

import com.ArjayAquino.model.Cart;
import com.ArjayAquino.model.Product;
import com.ArjayAquino.service.CartService;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

import java.math.BigDecimal;

/**
 * Implementation of the cart service interface.
 */
public class CartServiceImpl implements CartService {
    private static final Logger logger = LoggerFactory.getLogger(CartServiceImpl.class);
    private Cart cart;

    public CartServiceImpl() {
        this.cart = new Cart();
        logger.info("CartServiceImpl initialized.");
    }

    @Override
    public void addProductToCart(Product product, int quantity) {
        cart.addProduct(product, quantity);
        logger.info("Added product to cart: {} - quantity: {}", product.getProductId(), quantity);
    }

    @Override
    public void removeProductFromCart(String productId, int quantity) {
        cart.removeProduct(productId, quantity);
        logger.info("Removed product from cart: {} - quantity: {}", productId, quantity);
    }

    @Override
    public String viewCart() {
        return cart.toString();
    }

    @Override
    public BigDecimal calculateTotal() {
        return cart.calculateTotalPrice();
    }
}
