package org.example.library;

import java.util.logging.Logger;

/**
 * Represents a generic media item in the library.
 */
public class MediaItem {
    private static final Logger logger = Logger.getLogger(MediaItem.class.getName());

    private String title;
    private String author;
    private String category;

    /**
     * Constructor to create a new media item.
     *
     * @param title    The title of the media item.
     * @param author   The author of the media item.
     * @param category The category of the media item.
     */
    public MediaItem(String title, String author, String category) {
        if (title == null || author == null || category == null) {
            throw new IllegalArgumentException("Title, author, and category cannot be null");
        }
        this.title = title;
        this.author = author;
        this.category = category;
        logger.info(String.format("MediaItem created: %s", this));
    }

    // Getters for media item attributes
    public String getTitle() {
        return title;
    }

    public String getAuthor() {
        return author;
    }

    public String getCategory() {
        return category;
    }

    @Override
    public String toString() {
        return String.format("MediaItem{title='%s', author='%s', category='%s'}", title, author, category);
    }
}
