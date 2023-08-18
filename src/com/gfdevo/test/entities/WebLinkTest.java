package com.gfdevo.test.entities;

import com.gfdevo.bookmarkingapp.entities.WebLink;
import com.gfdevo.bookmarkingapp.managers.BookmarkManager;
import org.junit.jupiter.api.Test;
import static org.junit.jupiter.api.Assertions.*;


class WebLinkTest {

//    Test 1: keyword mature in url -- false
    @Test
    void isKidFriendlyEligible1() {
        WebLink webLink = BookmarkManager.getInstance().createWebLink(2000, "Taming Tiger, Part 2",
                "http://www.javaworld.com/article/2072759/core-java/taming-tiger-mature-part-2.html", "http://www.javaworld.com");

        boolean isKidsFriendlyEligible = webLink.isKidFriendlyEligible();
        assertFalse(isKidsFriendlyEligible, "Test1: For mature in url - must return false");
    }

//   Test 2: keyword mature in title -- false
    @Test
    void isKidFriendlyEligible2() {
        WebLink webLink = BookmarkManager.getInstance().createWebLink(2000, "Taming Tiger,mature Part 2",
                "http://www.javaworld.com/article/2072759/core-java/taming-tiger--part-2.html", "http://www.javaworld.com");

        boolean isKidsFriendlyEligible = webLink.isKidFriendlyEligible();
        assertFalse(isKidsFriendlyEligible, "Test1: For mature in url - must return false");
    }

//    Test 3: keyword adult in host -- false
    @Test
    void isKidFriendlyEligible3() {
        WebLink webLink = BookmarkManager.getInstance().createWebLink(2000, "Taming Tiger Part 2",
                "http://www.javaworld.com/article/2072759/core-java/taming-tiger--part-2.html", "http://www.adult.com");
        boolean isKidsFriendlyEligible = webLink.isKidFriendlyEligible();
        assertFalse(isKidsFriendlyEligible, "Test3: For adult in host - must return false");
    }

//    Test 4: keyword adult in url, but not in host -- true
    @Test
    void isKidFriendlyEligible4() {
        WebLink webLink = BookmarkManager.getInstance().createWebLink(2000, "Taming Tiger Part 2",
                "http://www.javaworld.com/article/2072759/core-java/taming-tiger-adult-part-2.html", "http://www.javaworld.com");
        boolean isKidsFriendlyEligible = webLink.isKidFriendlyEligible();
        assertTrue(isKidsFriendlyEligible, "Test4: For adult in url but not host - must return true");
    }

//    Test 5: keyword adult in title only -- true
    @Test
    void isKidFriendlyEligible5() {
        WebLink webLink = BookmarkManager.getInstance().createWebLink(2000, "Taming Tiger,adult Part 2",
                "http://www.javaworld.com/article/2072759/core-java/taming-tiger--part-2.html", "http://www.javaworld.com");
        boolean isKidsFriendlyEligible = webLink.isKidFriendlyEligible();
        assertTrue(isKidsFriendlyEligible, "Test5: For adult in title - must return true");
    }
}