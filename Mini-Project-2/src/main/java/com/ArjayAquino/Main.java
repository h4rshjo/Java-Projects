package com.ArjayAquino;

import com.ArjayAquino.model.Book;
import com.ArjayAquino.model.EBook;
import com.ArjayAquino.model.MediaItem;
import com.ArjayAquino.service.LibraryService;
import com.ArjayAquino.service.impl.LibraryServiceImpl;

import java.util.InputMismatchException;
import java.util.List;
import java.util.Scanner;
import java.util.logging.Logger;

/**
 * Main class to run the Library Management System.
 */
public class Main {
    private static final Logger logger = Logger.getLogger(Main.class.getName());

    public static void main(String[] args) {
        LibraryService library = new LibraryServiceImpl();
        Scanner scanner = new Scanner(System.in);

        while (true) {
            try {
                System.out.println("\nLibrary Management System");
                System.out.println("1. Add Book");
                System.out.println("2. Add E-Book");
                System.out.println("3. Remove Item");
                System.out.println("4. Search Item");
                System.out.println("5. Update Item");
                System.out.println("6. Display All Items");
                System.out.println("7. Exit");
                System.out.print("Enter your choice: ");

                int choice = scanner.nextInt();
                scanner.nextLine();  // Consume newline

                switch (choice) {
                    case 1:
                        System.out.print("Enter book title: ");
                        String bookTitle = scanner.nextLine();
                        if (bookTitle.isEmpty()) {
                            System.out.println("Title cannot be empty.");
                            break;
                        }

                        System.out.print("Enter book author: ");
                        String bookAuthor = scanner.nextLine();
                        if (bookAuthor.isEmpty()) {
                            System.out.println("Author cannot be empty.");
                            break;
                        }

                        System.out.print("Enter book category: ");
                        String bookCategory = scanner.nextLine();
                        if (bookCategory.isEmpty()) {
                            System.out.println("Category cannot be empty.");
                            break;
                        }

                        System.out.print("Enter book ISBN: ");
                        String bookIsbn = scanner.nextLine();
                        Book book = new Book(bookTitle, bookAuthor, bookCategory, bookIsbn);
                        library.addItem(book);
                        break;

                    case 2:
                        System.out.print("Enter e-book title: ");
                        String eBookTitle = scanner.nextLine();
                        if (eBookTitle.isEmpty()) {
                            System.out.println("Title cannot be empty.");
                            break;
                        }

                        System.out.print("Enter e-book author: ");
                        String eBookAuthor = scanner.nextLine();
                        if (eBookAuthor.isEmpty()) {
                            System.out.println("Author cannot be empty.");
                            break;
                        }

                        System.out.print("Enter e-book category: ");
                        String eBookCategory = scanner.nextLine();
                        if (eBookCategory.isEmpty()) {
                            System.out.println("Category cannot be empty.");
                            break;
                        }

                        System.out.print("Enter e-book download link: ");
                        String eBookDownloadLink = scanner.nextLine();
                        EBook eBook = new EBook(eBookTitle, eBookAuthor, eBookCategory, eBookDownloadLink);
                        library.addItem(eBook);
                        break;

                    case 3:
                        System.out.print("Enter item title to remove: ");
                        String titleToRemove = scanner.nextLine();
                        System.out.println("1. Book");
                        System.out.println("2. E-Book");
                        System.out.print("Enter type of item to remove (1 or 2): ");
                        int removeType = scanner.nextInt();
                        scanner.nextLine();  // Consume newline
                        if (removeType == 1) {
                            library.removeItem(titleToRemove, Book.class);
                        } else if (removeType == 2) {
                            library.removeItem(titleToRemove, EBook.class);
                        } else {
                            System.out.println("Invalid type.");
                        }
                        break;

                    case 4:
                        System.out.println("Enter search criteria:");
                        System.out.println("1. Title");
                        System.out.println("2. Author");
                        System.out.println("3. Category");
                        System.out.println("4. ISBN (for books only)");
                        System.out.println("5. Download URL (for e-books only)");
                        System.out.print("Enter your choice: ");
                        int searchCriteria = scanner.nextInt();
                        scanner.nextLine();  // Consume newline
                        String criteria = "";
                        switch (searchCriteria) {
                            case 1:
                                criteria = "title";
                                break;
                            case 2:
                                criteria = "author";
                                break;
                            case 3:
                                criteria = "category";
                                break;
                            case 4:
                                criteria = "isbn";
                                break;
                            case 5:
                                criteria = "download url";
                                break;
                            default:
                                System.out.println("Invalid criteria.");
                                continue;
                        }

                        System.out.print("Enter search query: ");
                        String searchQuery = scanner.nextLine();
                        System.out.println("1. Book");
                        System.out.println("2. E-Book");
                        System.out.print("Enter type of item to search (1 or 2): ");
                        int searchType = scanner.nextInt();
                        scanner.nextLine();  // Consume newline
                        List<MediaItem> foundItems;
                        if (searchType == 1) {
                            foundItems = library.searchItems(searchQuery, Book.class, criteria);
                        } else if (searchType == 2) {
                            foundItems = library.searchItems(searchQuery, EBook.class, criteria);
                        } else {
                            System.out.println("Invalid type.");
                            break;
                        }
                        for (MediaItem item : foundItems) {
                            System.out.println(item);
                        }
                        break;

                    case 5:
                        System.out.print("Enter current title of the item to update: ");
                        String oldTitle = scanner.nextLine();
                        System.out.println("1. Book");
                        System.out.println("2. E-Book");
                        System.out.print("Enter type of item to update (1 or 2): ");
                        int updateType = scanner.nextInt();
                        scanner.nextLine();  // Consume newline
                        System.out.print("Enter new title: ");
                        String newTitle = scanner.nextLine();
                        System.out.print("Enter new author: ");
                        String newAuthor = scanner.nextLine();
                        System.out.print("Enter new category: ");
                        String newCategory = scanner.nextLine();

                        if (updateType == 1) {
                            System.out.print("Enter new ISBN: ");
                            String newIsbn = scanner.nextLine();
                            library.updateItem(oldTitle, Book.class, newTitle, newAuthor, newCategory, newIsbn, null);
                        } else if (updateType == 2) {
                            System.out.print("Enter new download URL: ");
                            String newDownloadUrl = scanner.nextLine();
                            library.updateItem(oldTitle, EBook.class, newTitle, newAuthor, newCategory, null, newDownloadUrl);
                        } else {
                            System.out.println("Invalid type.");
                        }
                        break;

                    case 6:
                        library.displayAllItems();
                        break;

                    case 7:
                        System.out.println("Exiting...");
                        scanner.close();
                        logger.info("Application exited");
                        System.exit(0);

                    default:
                        System.out.println("Invalid choice. Please try again.");
                }
            } catch (InputMismatchException e) {
                System.out.println("Invalid input. Please enter a number.");
                logger.warning("Invalid input: " + e.getMessage());
                scanner.nextLine();  // Consume the invalid input
            } catch (Exception e) {
                logger.severe("Error: " + e.getMessage());
                System.out.println("Error: " + e.getMessage());
            }
        }
    }
}
