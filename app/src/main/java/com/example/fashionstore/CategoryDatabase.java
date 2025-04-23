package com.example.fashionstore;

import android.content.Context;
import java.util.concurrent.ExecutorService;
import java.util.concurrent.Executors;
import androidx.room.Database;
import androidx.room.Room;
import androidx.room.RoomDatabase;


@Database(entities = {Category.class}, version = 1)
public abstract class CategoryDatabase extends RoomDatabase {

    public static CategoryDatabase INSTANCE;

    public abstract CategoryDao categoryDao();

    public static CategoryDatabase getDatabase(final Context context) {
        if (INSTANCE == null) {
            synchronized (CategoryDatabase.class) {
                if (INSTANCE == null) {
                    INSTANCE = Room.databaseBuilder(context.getApplicationContext(),
                                    CategoryDatabase.class, "category_database")
                            .fallbackToDestructiveMigration()
                            .build();
                }
            }
        }
        return INSTANCE;
    }
    // Create an ExecutorService to handle background database operations
    public static final ExecutorService databaseWriteExecutor = Executors.newFixedThreadPool(4);

    // Use this executor to run background operations in your DAO or ViewModel
    public static ExecutorService getDatabaseWriteExecutor() {
        return databaseWriteExecutor;
    }
}

