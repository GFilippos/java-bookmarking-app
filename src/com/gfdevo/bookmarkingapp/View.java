package com.gfdevo.bookmarkingapp;

import com.gfdevo.bookmarkingapp.constants.KidFriendlyStatus;
import com.gfdevo.bookmarkingapp.constants.UserType;
import com.gfdevo.bookmarkingapp.controllers.BookmarkController;
import com.gfdevo.bookmarkingapp.entities.Bookmark;
import com.gfdevo.bookmarkingapp.entities.User;
import com.gfdevo.bookmarkingapp.partner.Shareable;

import javax.xml.crypto.Data;

public class View {
//    public static void bookmark(User user, Bookmark[][] bookmarks) {
//        System.out.println("\n" + user.getEmail() + " is bookmarking");
//        for (int i = 0; i < DataStore.USER_BOOKMARK_LIMIT; i++) {
//            int typeOffset = (int)(Math.random() * DataStore.BOOKMARK_TYPES_COUNT);
//            int bookmarkOffset = (int)(Math.random() * DataStore.BOOKMARK_COUNT_PER_TYPE);
//            Bookmark bookmark = bookmarks[typeOffset][bookmarkOffset];
//            BookmarkController.getInstance().saveUserBookmark(user, bookmark);
//            System.out.println(bookmark);
//        }
//    }

    public static void browse(User user, Bookmark[][] bookmarks) {
        System.out.println("\n" + user.getEmail() + " is browsing items ...");
        int bookmarkCount = 0;
        for (Bookmark [] bookmarksList : bookmarks) {
            for (Bookmark bookmark : bookmarksList) {
                if(bookmarkCount < DataStore.USER_BOOKMARK_LIMIT) {
                    boolean isBookmarked = getBookmarkDecision(bookmark);
                    if (isBookmarked) {
                        BookmarkController.getInstance().saveUserBookmark(user, bookmark);
                        System.out.println("New Item Bookmarked --" + bookmark);
                        bookmarkCount++;
                    }
                }

                // Mark as kid friendly
                if (user.getUserType().equals(UserType.EDITOR) || user.getUserType().equals(UserType.CHIEF_EDITOR)) {

                    // Mark as kid friendly
                    if (bookmark.isKidFriendlyEligible() &&
                            bookmark.getKidFriendlyStatus().equals(KidFriendlyStatus.UNKNOWN)) {
                        String kidFriendlyStatus = getKidFriendlyStatusDecision(bookmark);
                        if (!kidFriendlyStatus.equals(KidFriendlyStatus.UNKNOWN)) {
                            BookmarkController.getInstance().setKidFriendlyStatus(user, kidFriendlyStatus, bookmark);
                        }
                    }

                    // Sharing bookmark
                    if (bookmark.getKidFriendlyStatus().equals(KidFriendlyStatus.APPROVED)
                            && bookmark instanceof Shareable) {
                        boolean isDecisionYes = getShareDecision();
                        if (isDecisionYes) {
                            BookmarkController.getInstance().share(user, bookmark);
                        }
                    }
                }
            }
        }
    }

    private static boolean getShareDecision() {
        return Math.random() < 0.5 ? true : false;
    }

    private static String getKidFriendlyStatusDecision(Bookmark bookmark) {
        double randomVal = Math.random();
        return randomVal < 0.4 ? KidFriendlyStatus.APPROVED :
                randomVal < 0.8 ? KidFriendlyStatus.REJECTED :
                        KidFriendlyStatus.UNKNOWN;
    }

    private static boolean getBookmarkDecision(Bookmark bookmark) {
        return Math.random() < 0.5 ? true : false;
    }
}
