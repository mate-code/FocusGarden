package com.matecode.focusgarden.db.category;

import androidx.room.Dao;
import androidx.room.Insert;
import androidx.room.Query;

import com.matecode.focusgarden.db.category.Category;

import java.util.List;

@Dao
public interface CategoryDao {
    @Insert
    void insert(Category category);

    @Query("SELECT * FROM Category")
    List<Category> getAll();

    @Query("DELETE FROM Category")
    void deleteAll();
}
