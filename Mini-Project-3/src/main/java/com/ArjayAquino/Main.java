package com.ArjayAquino;

import com.ArjayAquino.service.impl.CartServiceImpl;
import com.ArjayAquino.service.impl.MenuServiceImpl;
import com.ArjayAquino.service.impl.ProductServiceImpl;
import com.ArjayAquino.service.CartService;
import com.ArjayAquino.service.ProductService;

/**
 * Entry point of the e-commerce cart system application.
 */
public class Main {
    public static void main(String[] args) {
        ProductService productService = new ProductServiceImpl();
        CartService cartService = new CartServiceImpl();
        MenuServiceImpl menuServiceImpl = new MenuServiceImpl(productService, cartService);

        menuServiceImpl.displayMenu();
    }
}
