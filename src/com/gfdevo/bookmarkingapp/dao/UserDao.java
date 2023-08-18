package com.gfdevo.bookmarkingapp.dao;

import com.gfdevo.bookmarkingapp.DataStore;
import com.gfdevo.bookmarkingapp.entities.User;

public class UserDao {
    public User[] getUsers() {
        return DataStore.getUsers();
    }
}
