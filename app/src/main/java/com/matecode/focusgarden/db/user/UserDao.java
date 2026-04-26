package com.matecode.focusgarden.db.user;

import androidx.room.Dao;
import androidx.room.Insert;
import androidx.room.Query;

import java.util.List;

@Dao
public interface UserDao {

    @Insert
    void insert(User user);

    @Query("SELECT * FROM User")
    List<User> getAll();

    @Query("SELECT * FROM User WHERE username = :name")
    User getUserByName(String name);

    @Query("SELECT * FROM User WHERE id = :id")
    User getUserById(String id);

    @Query("DELETE FROM User")
    void deleteAll();
}