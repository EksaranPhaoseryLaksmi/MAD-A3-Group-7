package com.example.fashionstore;

import android.app.Application;
import androidx.annotation.NonNull;
import androidx.lifecycle.AndroidViewModel;
import androidx.lifecycle.LiveData;

import java.util.List;

public class CategoryViewModel extends AndroidViewModel {

    private final LiveData<List<Category>> allCategories;
    private final CategoryDao categoryDao;

    public CategoryViewModel(@NonNull Application application) {
        super(application);
        CategoryDatabase db = CategoryDatabase.getDatabase(application);
        categoryDao = db.categoryDao();
        allCategories = categoryDao.getAllCategories(); //
    }

    public LiveData<List<Category>> getAllCategories() {
        return allCategories;
    }

    public void insert(Category category) {
        CategoryDatabase.databaseWriteExecutor.execute(() -> categoryDao.insert(category));
    }
}


