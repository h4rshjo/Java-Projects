package com.ArjayAquino;

import com.ArjayAquino.model.Product;
import com.ArjayAquino.service.impl.CartServiceImpl;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;

import java.math.BigDecimal;

import static org.junit.jupiter.api.Assertions.*;

public class CartServiceImplTest {

    private CartServiceImpl cartService;

    @BeforeEach
    public void setUp() {
        cartService = new CartServiceImpl();
    }

    @Test
    public void testAddProductToCart() {
        Product product = new Product("P1", "Apple", 50, 0.99);
        cartService.addProductToCart(product, 5);
        String cartContent = cartService.viewCart();
        assertTrue(cartContent.contains("Apple"));
    }

    @Test
    public void testRemoveProductFromCart() {
        Product product = new Product("P1", "Apple", 50, 0.99);
        cartService.addProductToCart(product, 5);
        cartService.removeProductFromCart("P1", 3);
        String cartContent = cartService.viewCart();
        assertTrue(cartContent.contains("Apple"));
        assertFalse(cartContent.contains("Nothing is here yet"));
    }

    @Test
    public void testRemoveProductFromCartException() {
        assertThrows(IllegalArgumentException.class, () -> cartService.removeProductFromCart("P1", 3));
    }

    @Test
    public void testCalculateTotal() {
        Product product1 = new Product("P1", "Apple", 50, 0.99);
        Product product2 = new Product("P2", "Banana", 60, 0.59);
        cartService.addProductToCart(product1, 5);
        cartService.addProductToCart(product2, 10);
        BigDecimal total = cartService.calculateTotal();
        assertEquals(new BigDecimal("10.85"), total);
    }
}
