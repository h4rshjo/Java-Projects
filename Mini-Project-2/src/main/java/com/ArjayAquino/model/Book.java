package com.ArjayAquino.model;

import java.util.logging.Logger;

/**
 * Represents a book in the library.
 */
public class Book extends MediaItem {
    private static final Logger logger = Logger.getLogger(Book.class.getName());
    private String isbn;

    /**
     * Constructor to create a new book.
     *
     * @param title    The title of the book.
     * @param author   The author of the book.
     * @param category The category of the book.
     * @param isbn     The ISBN of the book.
     */
    public Book(String title, String author, String category, String isbn) {
        super(title, author, category);
        if (isbn == null || isbn.isEmpty()) {
            throw new IllegalArgumentException("ISBN cannot be null or empty");
        }
        this.isbn = isbn;
        logger.info(String.format("Book created: %s", this));
    }

    // Getter for ISBN
    public String getIsbn() {
        return isbn;
    }

    @Override
    public String toString() {
        return String.format("Book{title='%s', author='%s', category='%s', isbn='%s'}", getTitle(), getAuthor(), getCategory(), isbn);
    }
}
