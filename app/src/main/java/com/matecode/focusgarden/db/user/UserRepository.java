package com.matecode.focusgarden.db.user;

import java.util.List;

public class UserRepository {
    private final UserDao userDao;

    public UserRepository(UserDao userDao) {
        this.userDao = userDao;
    }

    public void insert(User user) {
        userDao.insert(user);
    }

    public List<User> getAll() {
        return userDao.getAll();
    }

    public User getUserByName(String name) { return userDao.getUserByName(name); }
    public User getUserById(String id) { return userDao.getUserById(id); }

    public void deleteAll() {
        userDao.deleteAll();
    }
}
