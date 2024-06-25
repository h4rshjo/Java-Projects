package org.example;

import org.example.library.Book;
import org.example.library.EBook;
import org.example.library.Library;
import org.example.library.MediaItem;
import org.junit.Before;
import org.junit.Test;

import java.util.List;

import static org.junit.Assert.*;

public class LibraryTest {

    private Library library;

    @Before
    public void setUp() {
        library = new Library();
    }

    @Test
    public void testAddBook() {
        Book book = new Book("Title1", "Author1", "Category1", "ISBN1");
        library.addItem(book);
        List<MediaItem> items = library.getItems();
        assertEquals(1, items.size());
        assertTrue(items.contains(book));
    }

    @Test
    public void testAddEBook() {
        EBook eBook = new EBook("Title2", "Author2", "Category2", "URL1");
        library.addItem(eBook);
        List<MediaItem> items = library.getItems();
        assertEquals(1, items.size());
        assertTrue(items.contains(eBook));
    }

    @Test
    public void testRemoveBook() throws Exception {
        Book book = new Book("Title1", "Author1", "Category1", "ISBN1");
        library.addItem(book);
        library.removeItem("Title1", Book.class);
        List<MediaItem> items = library.getItems();
        assertEquals(0, items.size());
    }

    @Test
    public void testRemoveEBook() throws Exception {
        EBook eBook = new EBook("Title2", "Author2", "Category2", "URL1");
        library.addItem(eBook);
        library.removeItem("Title2", EBook.class);
        List<MediaItem> items = library.getItems();
        assertEquals(0, items.size());
    }

    @Test
    public void testSearchBook() {
        Book book = new Book("Title1", "Author1", "Category1", "ISBN1");
        library.addItem(book);
        List<MediaItem> foundItems = library.searchItemsByTitle("Title1", Book.class);
        assertEquals(1, foundItems.size());
        assertEquals(book, foundItems.get(0));
    }

    @Test
    public void testSearchEBook() {
        EBook eBook = new EBook("Title2", "Author2", "Category2", "URL1");
        library.addItem(eBook);
        List<MediaItem> foundItems = library.searchItemsByTitle("Title2", EBook.class);
        assertEquals(1, foundItems.size());
        assertEquals(eBook, foundItems.get(0));
    }

    @Test
    public void testUpdateBook() throws Exception {
        Book book = new Book("Title1", "Author1", "Category1", "ISBN1");
        library.addItem(book);
        library.updateItem("Title1", Book.class, "NewTitle", "NewAuthor", "NewCategory", "NewISBN", null);
        List<MediaItem> foundItems = library.searchItemsByTitle("NewTitle", Book.class);
        assertEquals(1, foundItems.size());
        Book updatedBook = (Book) foundItems.get(0);
        assertEquals("NewAuthor", updatedBook.getAuthor());
        assertEquals("NewCategory", updatedBook.getCategory());
        assertEquals("NewISBN", updatedBook.getIsbn());
    }

    @Test
    public void testUpdateEBook() throws Exception {
        EBook eBook = new EBook("Title2", "Author2", "Category2", "URL1");
        library.addItem(eBook);
        library.updateItem("Title2", EBook.class, "NewTitle", "NewAuthor", "NewCategory", null, "NewURL");
        List<MediaItem> foundItems = library.searchItemsByTitle("NewTitle", EBook.class);
        assertEquals(1, foundItems.size());
        EBook updatedEBook = (EBook) foundItems.get(0);
        assertEquals("NewAuthor", updatedEBook.getAuthor());
        assertEquals("NewCategory", updatedEBook.getCategory());
        assertEquals("NewURL", updatedEBook.getDownloadUrl());
    }
}