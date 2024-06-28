package com.ArjayAquino.service.impl;

import com.ArjayAquino.model.Product;
import com.ArjayAquino.service.CartService;
import com.ArjayAquino.service.ProductService;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

import java.math.BigDecimal;
import java.util.InputMismatchException;
import java.util.List;
import java.util.Scanner;

/**
 * Service for handling menu operations.
 */
public class MenuServiceImpl {
    private static final Logger logger = LoggerFactory.getLogger(MenuServiceImpl.class);
    private final ProductService productService;
    private final CartService cartService;
    private final Scanner scanner;

    public MenuServiceImpl(ProductService productService, CartService cartService) {
        this.productService = productService;
        this.cartService = cartService;
        this.scanner = new Scanner(System.in);
        logger.info("MenuServiceImpl initialized.");
    }

    /**
     * Displays the menu and handles user choices.
     */
    public void displayMenu() {
        int choice = 0;
        do {
            System.out.println("1. View Items");
            System.out.println("2. Add a Product to Cart");
            System.out.println("3. Delete a Product from Cart");
            System.out.println("4. View Cart");
            System.out.println("5. Checkout/Finalize cart");
            System.out.println("6. Exit");
            System.out.print("Enter your choice: ");

            try {
                String input = scanner.nextLine().trim();
                if (input.isEmpty() || !input.matches("[1-6]")) { // Validate input
                    throw new IllegalArgumentException("Invalid input. Please enter a number between 1 and 6.");
                }
                choice = Integer.parseInt(input);

                switch (choice) {
                    case 1:
                        viewItems();
                        break;
                    case 2:
                        addProductToCart();
                        break;
                    case 3:
                        removeProductFromCart();
                        break;
                    case 4:
                        viewCart();
                        break;
                    case 5:
                        checkout();
                        break;
                    case 6:
                        System.out.println("Exiting...");
                        logger.info("User exited the menu.");
                        break;
                    default:
                        System.out.println("Invalid choice. Please try again.");
                }
            } catch (IllegalArgumentException | InputMismatchException e) {
                logger.error("Error in menu selection: {}", e.getMessage());
                System.out.println(e.getMessage());
            }
        } while (choice != 6);
    }

    /**
     * Displays the list of available products.
     */
    private void viewItems() {
        List<Product> products = productService.loadProducts("products.csv");
        System.out.printf("%-10s %-15s %-10s %-10s%n", "ID", "Name", "Quantity", "Price");
        products.forEach(System.out::println);
        logger.info("Displayed list of available products.");
    }

    /**
     * Adds a product to the cart.
     */
    private void addProductToCart() {
        System.out.print("Enter Product ID to add: ");
        String productId = scanner.nextLine().trim();
        Product product = productService.getProductById(productId);
        if (product != null) { // Check if product is found
            System.out.print("Enter quantity to add: ");
            int quantity = scanner.nextInt();
            scanner.nextLine();  // Consume newline
            if (quantity > 0 && quantity <= product.getQuantity()) { // Validate quantity
                cartService.addProductToCart(product, quantity);
                product.setQuantity(product.getQuantity() - quantity);
                productService.updateProduct(product);
                System.out.println("Product added to cart.");
                logger.info("Added product to cart: {} - quantity: {}", productId, quantity);
            } else {
                System.out.println("Invalid quantity.");
                logger.warn("Attempted to add invalid quantity: {} for product: {}", quantity, productId);
            }
        } else {
            System.out.println("Product not found.");
            logger.warn("Product not found: {}", productId);
        }
    }

    /**
     * Removes a product from the cart.
     */
    private void removeProductFromCart() {
        System.out.print("Enter Product ID to remove: ");
        String productId = scanner.nextLine().trim();
        Product product = productService.getProductById(productId);
        if (product != null) { // Check if product is found
            System.out.print("Enter quantity to remove: ");
            int quantity = scanner.nextInt();
            scanner.nextLine();  // Consume newline
            try {
                cartService.removeProductFromCart(productId, quantity);
                product.setQuantity(product.getQuantity() + quantity);
                productService.updateProduct(product);
                System.out.println("Product removed from cart.");
                logger.info("Removed product from cart: {} - quantity: {}", productId, quantity);
            } catch (IllegalArgumentException e) {
                System.out.println(e.getMessage());
                logger.error("Error removing product from cart: {}", e.getMessage());
            }
        } else {
            System.out.println("Product not found.");
            logger.warn("Product not found: {}", productId);
        }
    }

    /**
     * Displays the contents of the cart.
     */
    private void viewCart() {
        System.out.println(cartService.viewCart());
        logger.info("Displayed cart contents.");
    }

    /**
     * Handles the checkout process.
     */
    private void checkout() {
        System.out.println("Final Cart:");
        System.out.println(cartService.viewCart());
        BigDecimal total = cartService.calculateTotal();
        System.out.println("Total: " + total);
        System.out.print("Enter payment amount: ");
        BigDecimal payment = scanner.nextBigDecimal();
        scanner.nextLine();  // Consume newline
        if (payment.compareTo(total) >= 0) { // Check if payment is sufficient
            BigDecimal change = payment.subtract(total);
            System.out.println("Payment successful. Change: " + change);
            logger.info("Checkout successful. Total: {} - Payment: {} - Change: {}", total, payment, change);
        } else {
            System.out.println("Insufficient payment. Please try again.");
            logger.warn("Insufficient payment. Total: {} - Payment: {}", total, payment);
        }
    }
}
