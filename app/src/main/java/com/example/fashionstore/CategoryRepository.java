package com.example.fashionstore;
import android.app.Application;

import androidx.lifecycle.LiveData;

import java.util.List;

public class CategoryRepository {

    private CategoryDao categoryDao;
    private LiveData<List<Category>> allCategories;

    public CategoryRepository(Application application) {
        CategoryDatabase db = CategoryDatabase.getDatabase(application);

        categoryDao = db.categoryDao();
        allCategories = categoryDao.getAllCategories();
    }

    public void insert(Category category) {
        CategoryDatabase.databaseWriteExecutor.execute(() -> categoryDao.insert(category));
    }

    public LiveData<List<Category>> getAllCategories() {
        return allCategories;
    }
}

