package com.ArjayAquino.service.impl;

import com.ArjayAquino.model.Book;
import com.ArjayAquino.model.EBook;
import com.ArjayAquino.model.MediaItem;
import com.ArjayAquino.service.LibraryService;

import java.util.ArrayList;
import java.util.List;
import java.util.logging.Logger;

/**
 * Library Management System to manage media items.
 */
public class LibraryServiceImpl implements LibraryService {
    private static final Logger logger = Logger.getLogger(LibraryServiceImpl.class.getName());
    private List<MediaItem> items;

    /**
     * Constructor to create a new library.
     */
    public LibraryServiceImpl() {
        items = new ArrayList<>();
    }

    @Override
    public void addItem(MediaItem item) {
        if (item == null) {
            throw new IllegalArgumentException("Media item cannot be null");
        }
        items.add(item);
        logger.info(String.format("Item added successfully: %s", item));
    }

    @Override
    public void removeItem(String title, Class<? extends MediaItem> type) throws Exception {
        MediaItem itemToRemove = null;
        // Iterate through items to find the item to remove
        for (MediaItem item : items) {
            if (item.getTitle().equalsIgnoreCase(title) && type.isInstance(item)) {
                itemToRemove = item;
                break;
            }
        }
        if (itemToRemove != null) {
            items.remove(itemToRemove);
            logger.info(String.format("Item removed successfully: %s", itemToRemove));
        } else {
            logger.warning(String.format("Item not found with title: %s and type: %s", title, type.getSimpleName()));
            throw new Exception("Item not found with title: " + title + " and type: " + type.getSimpleName());
        }
    }

    @Override
    public List<MediaItem> searchItems(String query, Class<? extends MediaItem> type, String criteria) {
        List<MediaItem> foundItems = new ArrayList<>();
        // Iterate through items to find matching items based on criteria
        for (MediaItem item : items) {
            if (type.isInstance(item)) {
                switch (criteria.toLowerCase()) {
                    case "title":
                        if (item.getTitle().equalsIgnoreCase(query)) {
                            foundItems.add(item);
                        }
                        break;
                    case "author":
                        if (item.getAuthor().equalsIgnoreCase(query)) {
                            foundItems.add(item);
                        }
                        break;
                    case "category":
                        if (item.getCategory().equalsIgnoreCase(query)) {
                            foundItems.add(item);
                        }
                        break;
                    case "isbn":
                        if (item instanceof Book) {
                            if (((Book) item).getIsbn().equalsIgnoreCase(query)) {
                                foundItems.add(item);
                            }
                        }
                        break;
                    case "download url":
                        if (item instanceof EBook) {
                            if (((EBook) item).getDownloadUrl().equalsIgnoreCase(query)) {
                                foundItems.add(item);
                            }
                        }
                        break;
                    default:
                        logger.warning(String.format("Invalid search criteria: %s", criteria));
                        break;
                }
            }
        }
        if (foundItems.isEmpty()) {
            logger.warning(String.format("No items found with %s: %s and type: %s", criteria, query, type.getSimpleName()));
        } else {
            for (MediaItem foundItem : foundItems) {
                logger.info(String.format("Item found: %s", foundItem));
            }
        }
        return foundItems;
    }

    @Override
    public void updateItem(String oldTitle, Class<? extends MediaItem> type, String newTitle, String newAuthor, String newCategory, String newIsbn, String newDownloadUrl) throws Exception {
        MediaItem itemToUpdate = null;
        // Iterate through items to find the item to update
        for (MediaItem item : items) {
            if (item.getTitle().equalsIgnoreCase(oldTitle) && type.isInstance(item)) {
                itemToUpdate = item;
                break;
            }
        }
        if (itemToUpdate != null) {
            if (itemToUpdate instanceof Book) {
                Book book = new Book(newTitle, newAuthor, newCategory, newIsbn);
                items.set(items.indexOf(itemToUpdate), book);
            } else if (itemToUpdate instanceof EBook) {
                EBook eBook = new EBook(newTitle, newAuthor, newCategory, newDownloadUrl);
                items.set(items.indexOf(itemToUpdate), eBook);
            } else {
                throw new Exception("Unsupported media item type");
            }
            logger.info(String.format("Item updated successfully: %s", itemToUpdate));
        } else {
            logger.warning(String.format("Item not found with title: %s and type: %s", oldTitle, type.getSimpleName()));
            throw new Exception("Item not found with title: " + oldTitle + " and type: " + type.getSimpleName());
        }
    }

    @Override
    public List<MediaItem> getItems() {
        return items;
    }

    @Override
    public void displayAllItems() {
        // Display Books
        System.out.println("Displaying All Books:");
        if (items.stream().noneMatch(item -> item instanceof Book)) {
            System.out.println("Nothing is here yet");
        } else {
            System.out.printf("%-5s %-30s %-20s %-20s %-20s%n", "Index", "Title", "Author", "Category", "ISBN");
            int index = 1;
            // Iterate through items to display books
            for (MediaItem item : items) {
                if (item instanceof Book) {
                    Book book = (Book) item;
                    System.out.printf("%-5d %-30s %-20s %-20s %-20s%n", index++, book.getTitle(), book.getAuthor(), book.getCategory(), book.getIsbn());
                }
            }
        }

        // Display EBooks
        System.out.println("\nDisplaying All EBooks:");
        if (items.stream().noneMatch(item -> item instanceof EBook)) {
            System.out.println("Nothing is here yet");
        } else {
            System.out.printf("%-5s %-30s %-20s %-20s %-30s%n", "Index", "Title", "Author", "Category", "Download URL");
            int index = 1;
            // Iterate through items to display eBooks
            for (MediaItem item : items) {
                if (item instanceof EBook) {
                    EBook eBook = (EBook) item;
                    System.out.printf("%-5d %-30s %-20s %-20s %-30s%n", index++, eBook.getTitle(), eBook.getAuthor(), eBook.getCategory(), eBook.getDownloadUrl());
                }
            }
        }
    }
}
