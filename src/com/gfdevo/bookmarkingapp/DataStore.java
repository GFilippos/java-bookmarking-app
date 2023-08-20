package com.gfdevo.bookmarkingapp;

import com.gfdevo.bookmarkingapp.constants.BookGenre;
import com.gfdevo.bookmarkingapp.constants.Gender;
import com.gfdevo.bookmarkingapp.constants.MovieGenre;
import com.gfdevo.bookmarkingapp.constants.UserType;
import com.gfdevo.bookmarkingapp.entities.Book;
import com.gfdevo.bookmarkingapp.entities.Bookmark;
import com.gfdevo.bookmarkingapp.entities.User;
import com.gfdevo.bookmarkingapp.entities.UserBookmark;
import com.gfdevo.bookmarkingapp.managers.BookmarkManager;
import com.gfdevo.bookmarkingapp.managers.UserManager;
import com.gfdevo.bookmarkingapp.util.IOUtil;

import java.util.ArrayList;
import java.util.List;

public class DataStore {

    private static List<User> users = new ArrayList<>();
    private static List<List<Bookmark>> bookmarks = new ArrayList<>();
    private static List<UserBookmark> userBookmarks = new ArrayList<>();

    public static List<User> getUsers() {
        return users;
    }

    public static List<List<Bookmark>> getBookmarks() {
        return bookmarks;
    }
    private static int bookmarkIndex;

    public static void loadData() {
        loadUsers();
        loadWebLinks();
        loadMovies();
        loadBooks();
    }

    private static void loadUsers() {
        List<String> data = new ArrayList<>();
        IOUtil.read(data, "User+");
        for (String row : data) {
            String[] values = row.split("\t");

            int gender = Gender.MALE;
            if (values[5].equals("f")) {
                gender = Gender.FEMALE;
            } else if (values[5].equals("t")) {
                gender = Gender.TRANSGENDER;
            }

            User user = UserManager.getInstance().createUser(Long.parseLong(values[0]), values[1], values[2], values[3], values[4], gender, values[6]);
            users.add(user);
        }
    }

    private static void loadWebLinks() {
        List<String> data = new ArrayList<>();
        IOUtil.read(data, "Web+Link+");
        List<Bookmark> bookmarkList = new ArrayList<>();
        for (String row : data) {
            String[] values = row.split("\t");
            Bookmark bookmark = BookmarkManager.getInstance().createWebLink(Long.parseLong(values[0]), values[1], values[2], values[3]/*, values[4]*/);
            bookmarkList.add(bookmark);
        }

        bookmarks.add(bookmarkList);
    }

    private static void loadMovies() {
        List<String> data = new ArrayList<>();
        IOUtil.read(data, "Movie+");
        List<Bookmark> bookmarkList = new ArrayList<>();
        for (String row : data) {
            String[] values = row.split("\t");
            String[] cast = values[3].split(",");
            String[] directors = values[4].split(",");
            Bookmark bookmark = BookmarkManager.getInstance().createMovie(Long.parseLong(values[0]), values[1], "", Integer.parseInt(values[2]), cast, directors, values[5], Double.parseDouble(values[6])/*, values[7]*/);
            bookmarkList.add(bookmark);
        }
    }

    private static void loadBooks() {
        List<String> data = new ArrayList<>();
        IOUtil.read(data, "Book+");
        List<Bookmark> bookmarkList = new ArrayList<>();
        for (String row : data) {
            String[] values = row.split("\t");
            String[] authors = values[4].split(",");
            Bookmark bookmark = BookmarkManager.getInstance().createBook(Long.parseLong(values[0]), values[1], Integer.parseInt(values[2]), values[3], authors, values[5], Double.parseDouble(values[6])/*, values[7]*/);
            bookmarkList.add(bookmark);
        }
    }

    public static void add(UserBookmark userBookmark) {
        userBookmarks.add(userBookmark);
    }
}
