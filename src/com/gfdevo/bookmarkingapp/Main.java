package com.gfdevo.bookmarkingapp;

import com.gfdevo.bookmarkingapp.entities.Bookmark;
import com.gfdevo.bookmarkingapp.entities.User;
import com.gfdevo.bookmarkingapp.managers.BookmarkManager;
import com.gfdevo.bookmarkingapp.managers.UserManager;

import java.util.List;

public class Main {
    private static List<User> users;
    private static List<List<Bookmark>> bookmarks;
    private static void loadData() {
        System.out.println("1. Loading data ...");
        DataStore.loadData();
        users = UserManager.getInstance().getUsers();
        bookmarks = BookmarkManager.getInstance().getBookmarks();

//        System.out.println("Printing data ...");
//        printUserData();
//        printBookmarkData();
    }
    private static void printUserData() {
        for (User user : users) {
            System.out.println(user);
        }
    }

    private static void printBookmarkData() {
        for (List<Bookmark> bookmarkList : bookmarks) {
            for (Bookmark bookmark: bookmarkList) {
                System.out.println(bookmark);
            }
        }
    }

    private static void start() {
//        System.out.println("\n2. Bookmarking ...");
        for (User user : users) {
            View.browse(user, bookmarks);
        }
    }

    public static void main(String[] args) {
        loadData();
        start();
    }
}
