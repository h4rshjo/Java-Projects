# Mini-Project-3: Cart System

## Overview

This project is a simple e-commerce cart system implemented in Java. It allows users to view available products, add products to a shopping cart, remove products from the cart, view the cart's contents, and finalize the purchase.

## Structure

The project is divided into several packages and classes:

### Packages and Classes

1. **com.ArjayAquino.model**
   - **Cart.java**: Represents the shopping cart and provides methods to add, remove, and calculate the total price of products.
   - **Product.java**: Represents a product with details such as product ID, name, quantity, and price.

2. **com.ArjayAquino.service**
   - **CartService.java**: Interface defining the operations for managing the cart.
   - **ProductService.java**: Interface defining the operations for managing products.

3. **com.ArjayAquino.service.impl**
   - **CartServiceImpl.java**: Implements the `CartService` interface and provides the logic for managing the cart.
   - **ProductServiceImpl.java**: Implements the `ProductService` interface and provides the logic for loading, retrieving, and updating products.
   - **MenuServiceImpl.java**: Provides the menu interface for user interaction, handling actions like viewing items, adding to the cart, removing from the cart, and checking out.

4. **com.ArjayAquino**
   - **Main.java**: Entry point of the application. Initializes the services and displays the menu.

## Usage

1. **View Items**: Displays a list of available products.
2. **Add a Product to Cart**: Prompts the user to enter a product ID and quantity to add to the cart.
3. **Delete a Product from Cart**: Prompts the user to enter a product ID and quantity to remove from the cart.
4. **View Cart**: Displays the current contents of the cart.
5. **Checkout/Finalize Cart**: Calculates the total price of the cart and prompts the user for payment.
6. **Exit**: Exits the application.

## Running the Application

1. **Setup**: Ensure you have Java installed on your system.
2. **Compile**: Compile the Java source files.
3. **Run**: Execute the `Main` class to start the application.
