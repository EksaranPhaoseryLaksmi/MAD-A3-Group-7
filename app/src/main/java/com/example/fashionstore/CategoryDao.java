package com.example.fashionstore;

import androidx.room.Dao;
import androidx.room.Insert;
import androidx.room.Query;
import androidx.lifecycle.LiveData;
import java.util.List;

@Dao
public interface CategoryDao {

    @Insert
    void insert(Category category);

    @Query("SELECT * FROM category_table")
    LiveData<List<Category>> getAllCategories();
}

