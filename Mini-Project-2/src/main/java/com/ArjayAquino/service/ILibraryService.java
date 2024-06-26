package com.ArjayAquino.service;

import com.ArjayAquino.model.MediaItem;

import java.util.List;

public interface ILibraryService {
    void addItem(MediaItem item);
    void removeItem(String title, Class<? extends MediaItem> type) throws Exception;
    List<MediaItem> searchItems(String query, Class<? extends MediaItem> type, String criteria);
    void updateItem(String oldTitle, Class<? extends MediaItem> type, String newTitle, String newAuthor, String newCategory, String newIsbn, String newDownloadUrl) throws Exception;
    List<MediaItem> getItems();
    void displayAllItems();
}
