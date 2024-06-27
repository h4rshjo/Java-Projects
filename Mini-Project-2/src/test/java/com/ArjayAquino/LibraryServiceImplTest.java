package com.ArjayAquino.service.impl;

import com.ArjayAquino.model.Book;
import com.ArjayAquino.model.EBook;
import com.ArjayAquino.model.MediaItem;
import com.ArjayAquino.service.LibraryService;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;

import java.util.List;

import static org.junit.jupiter.api.Assertions.*;

class LibraryServiceImplTest {

    private LibraryService libraryService;

    @BeforeEach
    void setUp() {
        libraryService = new LibraryServiceImpl();
    }

    @Test
    void testAddItem() {
        Book book = new Book("Harry Potter", "J.K. Rowling", "Fantasy", "12345");
        libraryService.addItem(book);
        List<MediaItem> items = libraryService.getItems();
        assertEquals(1, items.size());
        assertEquals(book, items.get(0));
    }

    @Test
    void testRemoveItem() throws Exception {
        Book book = new Book("Harry Potter", "J.K. Rowling", "Fantasy", "12345");
        libraryService.addItem(book);
        libraryService.removeItem("Harry Potter", Book.class);
        List<MediaItem> items = libraryService.getItems();
        assertTrue(items.isEmpty());
    }

    @Test
    void testRemoveNonExistingItem() {
        Exception exception = assertThrows(Exception.class, () -> {
            libraryService.removeItem("Non Existing", Book.class);
        });
        String expectedMessage = "Item not found with title: Non Existing and type: Book";
        assertTrue(exception.getMessage().contains(expectedMessage));
    }

    @Test
    void testSearchItemsByTitle() {
        Book book1 = new Book("Harry Potter", "J.K. Rowling", "Fantasy", "12345");
        Book book2 = new Book("The Hobbit", "J.R.R. Tolkien", "Fantasy", "67890");
        libraryService.addItem(book1);
        libraryService.addItem(book2);

        List<MediaItem> foundItems = libraryService.searchItems("Harry Potter", Book.class, "title");
        assertEquals(1, foundItems.size());
        assertEquals(book1, foundItems.get(0));
    }

    @Test
    void testSearchItemsByAuthor() {
        Book book1 = new Book("Harry Potter", "J.K. Rowling", "Fantasy", "12345");
        Book book2 = new Book("The Hobbit", "J.R.R. Tolkien", "Fantasy", "67890");
        libraryService.addItem(book1);
        libraryService.addItem(book2);

        List<MediaItem> foundItems = libraryService.searchItems("J.K. Rowling", Book.class, "author");
        assertEquals(1, foundItems.size());
        assertEquals(book1, foundItems.get(0));
    }

    @Test
    void testSearchItemsByCategory() {
        Book book1 = new Book("Harry Potter", "J.K. Rowling", "Fantasy", "12345");
        Book book2 = new Book("The Hobbit", "J.R.R. Tolkien", "Fantasy", "67890");
        libraryService.addItem(book1);
        libraryService.addItem(book2);

        List<MediaItem> foundItems = libraryService.searchItems("Fantasy", Book.class, "category");
        assertEquals(2, foundItems.size());
    }

    @Test
    void testSearchItemsByIsbn() {
        Book book = new Book("Harry Potter", "J.K. Rowling", "Fantasy", "12345");
        libraryService.addItem(book);

        List<MediaItem> foundItems = libraryService.searchItems("12345", Book.class, "isbn");
        assertEquals(1, foundItems.size());
        assertEquals(book, foundItems.get(0));
    }

    @Test
    void testSearchItemsByDownloadUrl() {
        EBook eBook = new EBook("Harry Potter", "J.K. Rowling", "Fantasy", "http://example.com");
        libraryService.addItem(eBook);

        List<MediaItem> foundItems = libraryService.searchItems("http://example.com", EBook.class, "download url");
        assertEquals(1, foundItems.size());
        assertEquals(eBook, foundItems.get(0));
    }

    @Test
    void testUpdateItem() throws Exception {
        Book book = new Book("Harry Potter", "J.K. Rowling", "Fantasy", "12345");
        libraryService.addItem(book);

        libraryService.updateItem("Harry Potter", Book.class, "Harry Potter and the Philosopher's Stone", "J.K. Rowling", "Fantasy", "54321", null);

        List<MediaItem> items = libraryService.getItems();
        assertEquals(1, items.size());
        assertEquals("Harry Potter and the Philosopher's Stone", items.get(0).getTitle());
        assertEquals("54321", ((Book) items.get(0)).getIsbn());
    }
}
