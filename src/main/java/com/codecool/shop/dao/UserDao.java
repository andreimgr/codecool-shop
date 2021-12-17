package com.codecool.shop.dao;

import com.codecool.shop.model.User;

public interface UserDao {

    void addUser(User user);
    User getUserByEmail(String email);

}
