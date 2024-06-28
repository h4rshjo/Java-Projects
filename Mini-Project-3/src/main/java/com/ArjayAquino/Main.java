package com.ArjayAquino;

import com.ArjayAquino.service.impl.CartServiceImpl;
import com.ArjayAquino.service.impl.MenuServiceImpl;
import com.ArjayAquino.service.impl.ProductServiceImpl;
import com.ArjayAquino.service.CartService;
import com.ArjayAquino.service.ProductService;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

/**
 * Entry point of the e-commerce cart system application.
 */
public class Main {
    private static final Logger logger = LoggerFactory.getLogger(Main.class);

    public static void main(String[] args) {
        logger.info("Application started.");
        ProductService productService = new ProductServiceImpl();
        CartService cartService = new CartServiceImpl();
        MenuServiceImpl menuServiceImpl = new MenuServiceImpl(productService, cartService);

        menuServiceImpl.displayMenu();
        logger.info("Application ended.");
    }
}
