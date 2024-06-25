package org.example.library;

import java.util.logging.Logger;

/**
 * Represents an e-book in the library.
 */
public class EBook extends MediaItem {
    private static final Logger logger = Logger.getLogger(EBook.class.getName());
    private String downloadUrl;

    /**
     * Constructor to create a new e-book.
     *
     * @param title       The title of the e-book.
     * @param author      The author of the e-book.
     * @param category    The category of the e-book.
     * @param downloadUrl The download URL of the e-book.
     */
    public EBook(String title, String author, String category, String downloadUrl) {
        super(title, author, category);
        if (downloadUrl == null) {
            throw new IllegalArgumentException("Download URL cannot be null");
        }
        this.downloadUrl = downloadUrl;
        logger.info(String.format("EBook created: %s", this));
    }

    // Getter for download URL
    public String getDownloadUrl() {
        return downloadUrl;
    }

    @Override
    public String toString() {
        return String.format("EBook{title='%s', author='%s', category='%s', downloadUrl='%s'}", getTitle(), getAuthor(), getCategory(), downloadUrl);
    }
}
