package com.gfdevo.bookmarkingapp.dao;

import com.gfdevo.bookmarkingapp.DataStore;
import com.gfdevo.bookmarkingapp.entities.Bookmark;
import com.gfdevo.bookmarkingapp.entities.UserBookmark;

import java.util.List;

public class BookmarkDao {

    public List<List<Bookmark>> getBookmarks() {
        return DataStore.getBookmarks();
    }

    public void saveUserBookmark(UserBookmark userBookmark) {
        DataStore.add(userBookmark);
    }
}
