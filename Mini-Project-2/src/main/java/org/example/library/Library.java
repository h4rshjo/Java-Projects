package org.example.library;

import java.util.ArrayList;
import java.util.List;
import java.util.logging.Logger;

/**
 * Library Management System to manage media items.
 */
public class Library {
    private static final Logger logger = Logger.getLogger(Library.class.getName());
    private List<MediaItem> items;

    /**
     * Constructor to create a new library.
     */
    public Library() {
        items = new ArrayList<>();
    }

    /**
     * Adds a media item to the library.
     *
     * @param item The media item to add.
     */
    public void addItem(MediaItem item) {
        if (item == null) {
            throw new IllegalArgumentException("Media item cannot be null");
        }
        items.add(item);
        logger.info(String.format("Item added successfully: %s", item));
    }

    /**
     * Removes a media item from the library.
     *
     * @param title The title of the media item to remove.
     * @param type  The type of the media item to remove (Book or EBook).
     * @throws Exception if the media item is not found.
     */
    public void removeItem(String title, Class<? extends MediaItem> type) throws Exception {
        MediaItem itemToRemove = null;
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

    /**
     * Searches for media items in the library by title and type.
     *
     * @param title The title of the media items to search for.
     * @param type  The type of the media items to search for (Book or EBook).
     * @return The list of media items with the matching title and type.
     */
    public List<MediaItem> searchItemsByTitle(String title, Class<? extends MediaItem> type) {
        List<MediaItem> foundItems = new ArrayList<>();
        for (MediaItem item : items) {
            if (item.getTitle().equalsIgnoreCase(title) && type.isInstance(item)) {
                foundItems.add(item);
            }
        }
        if (foundItems.isEmpty()) {
            logger.warning(String.format("No items found with title: %s and type: %s", title, type.getSimpleName()));
        } else {
            for (MediaItem foundItem : foundItems) {
                logger.info(String.format("Item found: %s", foundItem));
            }
        }
        return foundItems;
    }

    /**
     * Updates the details of a media item in the library.
     *
     * @param oldTitle       The current title of the media item to update.
     * @param type           The type of the media item to update (Book or EBook).
     * @param newTitle       The new title of the media item.
     * @param newAuthor      The new author of the media item.
     * @param newCategory    The new category of the media item.
     * @param newIsbn        The new ISBN of the media item (if applicable).
     * @param newDownloadUrl The new download URL of the media item (if applicable).
     * @throws Exception if the media item is not found.
     */
    public void updateItem(String oldTitle, Class<? extends MediaItem> type, String newTitle, String newAuthor, String newCategory, String newIsbn, String newDownloadUrl) throws Exception {
        MediaItem itemToUpdate = null;
        for (MediaItem item : items) {
            if (item.getTitle().equalsIgnoreCase(oldTitle) && type.isInstance(item)) {
                itemToUpdate = item;
                break;
            }
        }
        if (itemToUpdate != null) {
            if (itemToUpdate instanceof Book) {
                Book book = (Book) itemToUpdate;
                book = new Book(newTitle, newAuthor, newCategory, newIsbn);
                items.set(items.indexOf(itemToUpdate), book);
            } else if (itemToUpdate instanceof EBook) {
                EBook eBook = (EBook) itemToUpdate;
                eBook = new EBook(newTitle, newAuthor, newCategory, newDownloadUrl);
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

    /**
     * Returns the list of media items in the library.
     *
     * @return The list of media items.
     */
    public List<MediaItem> getItems() {
        return items;
    }
}