package com.ArjayAquino.service.impl;

import com.ArjayAquino.model.Cart;
import com.ArjayAquino.model.Product;
import com.ArjayAquino.service.CartService;

import java.math.BigDecimal;

/**
 * Implementation of the cart service interface.
 */
public class CartServiceImpl implements CartService {
    private Cart cart;

    public CartServiceImpl() {
        this.cart = new Cart();
    }

    @Override
    public void addProductToCart(Product product, int quantity) {
        cart.addProduct(product, quantity);
    }

    @Override
    public void removeProductFromCart(String productId, int quantity) {
        cart.removeProduct(productId, quantity);
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
