package com.gfdevo.bookmarkingapp.dao;

import com.gfdevo.bookmarkingapp.DataStore;
import com.gfdevo.bookmarkingapp.entities.User;

import java.util.List;

public class UserDao {
    public List<User> getUsers() {
        return DataStore.getUsers();
    }
}
