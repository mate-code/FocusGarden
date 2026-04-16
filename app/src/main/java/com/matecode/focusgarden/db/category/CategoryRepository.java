package com.matecode.focusgarden.db.category;

import com.matecode.focusgarden.db.category.Category;
import com.matecode.focusgarden.db.category.CategoryDao;

import java.util.List;

public class CategoryRepository {
    private final CategoryDao categoryDao;

    public CategoryRepository(CategoryDao categoryDao) {
        this.categoryDao = categoryDao;
    }

    public void insert(Category category) {
        categoryDao.insert(category);
    }

    public List<Category> getAll() {
        return categoryDao.getAll();
    }

    public void deleteAll() {
        categoryDao.deleteAll();
    }
}
