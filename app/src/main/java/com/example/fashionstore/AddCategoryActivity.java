package com.example.fashionstore;

import android.os.Bundle;
import android.widget.Button;
import android.widget.EditText;
import android.widget.Toast;

import androidx.appcompat.app.AppCompatActivity;

import androidx.lifecycle.ViewModelProvider;

public class AddCategoryActivity extends AppCompatActivity {

    private EditText categoryEditText;
    private Button addCategoryButton;
    private CategoryViewModel categoryViewModel;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_add_category);

        categoryEditText = findViewById(R.id.category_edit_text);
        addCategoryButton = findViewById(R.id.add_category_button);

        // Initialize ViewModel
        categoryViewModel = new ViewModelProvider(this).get(CategoryViewModel.class);

        // Add new category
        addCategoryButton.setOnClickListener(v -> {
            String categoryName = categoryEditText.getText().toString().trim();

            if (categoryName.isEmpty()) {
                Toast.makeText(AddCategoryActivity.this, "Category name cannot be empty", Toast.LENGTH_SHORT).show();
            } else {
                Category category = new Category(categoryName);
                categoryViewModel.insert(category);
                Toast.makeText(AddCategoryActivity.this, "Category added", Toast.LENGTH_SHORT).show();
                finish(); // Close activity and return to the previous screen
            }
        });
    }
}

