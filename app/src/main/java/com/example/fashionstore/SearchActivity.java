package com.example.fashionstore;

import android.os.Bundle;
import androidx.appcompat.app.AppCompatActivity;
public class SearchActivity extends AppCompatActivity {
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_search);
    }

    public static class Category {
        private String title;
        private int imageResId;

        public Category(String title, int imageResId) {
            this.title = title;
            this.imageResId = imageResId;
        }

        public String getTitle() {
            return title;
        }

        public int getImageResId() {
            return imageResId;
        }
    }
}

