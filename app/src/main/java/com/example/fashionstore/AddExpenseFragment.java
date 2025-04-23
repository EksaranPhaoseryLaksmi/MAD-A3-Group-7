package com.example.fashionstore;

import android.os.Bundle;
import androidx.recyclerview.widget.RecyclerView;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.Button;
import android.widget.EditText;
import androidx.annotation.NonNull;
import androidx.annotation.Nullable;
import androidx.fragment.app.Fragment;
import android.util.Log;
import android.widget.Toast;
import com.google.firebase.auth.FirebaseAuth;
import retrofit2.Call;
import retrofit2.Callback;
import retrofit2.Response;
import java.text.SimpleDateFormat;
import java.util.Date;
import java.util.Locale;
import com.example.fashionstore.models.Expense;
import com.example.fashionstore.adapters.ExpenseAdapter;
import java.util.List;
import java.util.ArrayList;
import androidx.recyclerview.widget.LinearLayoutManager;
import java.nio.charset.StandardCharsets;
import java.security.MessageDigest;
import java.security.NoSuchAlgorithmException;
import java.util.UUID;

import android.widget.ArrayAdapter;
import android.widget.Spinner;
import android.content.Intent;

import androidx.lifecycle.ViewModelProvider;
import com.example.fashionstore.R;
import android.widget.ImageButton;


public class AddExpenseFragment extends Fragment {
    private EditText amountInput, currencyInput, remarkInput;
    private Spinner categoryInput;
    private RecyclerView recyclerView;
    private ExpenseAdapter adapter;
    private List<Expense> expenseList = new ArrayList<>();
    private Spinner categorySpinner;
    private CategoryViewModel categoryViewModel;
    private Button addExpenseButton;
    @Nullable
    @Override
    public View onCreateView(@NonNull LayoutInflater inflater,
                             @Nullable ViewGroup container,
                             @Nullable Bundle savedInstanceState) {
        View view = inflater.inflate(R.layout.fragment_add_expense, container, false);

        categorySpinner = view.findViewById(R.id.category_spinner);

        categoryViewModel = new ViewModelProvider(this).get(CategoryViewModel.class);

        categoryViewModel.getAllCategories().observe(getViewLifecycleOwner(), categories -> {
            List<String> categoryNames = new ArrayList<>();
            for (Category c : categories) {
                categoryNames.add(c.getName());
            }

            ArrayAdapter<String> adapter = new ArrayAdapter<>(
                    getContext(),
                    android.R.layout.simple_spinner_item,
                    categoryNames
            );
            adapter.setDropDownViewResource(android.R.layout.simple_spinner_dropdown_item);
            categorySpinner.setAdapter(adapter);
        });
        return view;
    }
    public void onAddCategoryClicked(View view) {
        Intent intent = new Intent(getActivity(), AddCategoryActivity.class);
        startActivity(intent);
    }

    @Override
    public void onViewCreated(@NonNull View view, @Nullable Bundle savedInstanceState) {
        super.onViewCreated(view, savedInstanceState);

        // Initialize UI components using the view parameter
        amountInput = view.findViewById(R.id.amount_input);
        currencyInput = view.findViewById(R.id.currency_input);
        //categoryInput = view.findViewById(R.id.category_input);
        categoryInput = view.findViewById(R.id.category_spinner);
        remarkInput = view.findViewById(R.id.remark_input);
        addExpenseButton = view.findViewById(R.id.add_expense_button);

        // Initialize RecyclerView and set up the adapter
        recyclerView = view.findViewById(R.id.recyclerView);
        recyclerView.setLayoutManager(new LinearLayoutManager(getContext()));
        adapter = new ExpenseAdapter(expenseList);
        recyclerView.setAdapter(adapter);
        ImageButton btnAddCategory = view.findViewById(R.id.btn_add_category);
        btnAddCategory.setOnClickListener(v -> {
            Intent intent = new Intent(getActivity(), AddCategoryActivity.class);
            startActivity(intent);
        });

        addExpenseButton.setOnClickListener(v -> {
            addExpense();
        });

    }

    private String convertUIDToGUID(String uid) {
        try {
            MessageDigest md = MessageDigest.getInstance("SHA-256");
            byte[] hash = md.digest(uid.getBytes(StandardCharsets.UTF_8));
            UUID uuid = UUID.nameUUIDFromBytes(hash);
            return uuid.toString();
        } catch (NoSuchAlgorithmException e) {
            e.printStackTrace();
            return null;
        }
    }
    private void addExpense() {
        String amountStr = amountInput.getText().toString();
        String currency = currencyInput.getText().toString();
        String selectedCategory = categoryInput.getSelectedItem().toString();
        String category = selectedCategory;
        String remark = remarkInput.getText().toString();

        if (amountStr.isEmpty() || currency.isEmpty() || category.isEmpty()) {
            Toast.makeText(getContext(), "Please fill all required fields", Toast.LENGTH_SHORT).show();
            return;
        }

        double amount = Double.parseDouble(amountStr);
        String userId = FirebaseAuth.getInstance().getCurrentUser().getUid();
        String createdAt = new SimpleDateFormat("yyyy-MM-dd'T'HH:mm:ss'Z'", Locale.getDefault()).format(new Date());

        Expense expense = new Expense(amount, currency, category, remark, convertUIDToGUID(userId), createdAt);

        ExpenseApi apiService = RetrofitClient.getClient().create(ExpenseApi.class);
        String dbName = convertUIDToGUID(userId); // Replace with actual value
        String type = "application/json";
        Log.d("API", "Headers: DB_NAME=" + dbName + ", Content-Type=" + type);
        Call<Void> call = apiService.addExpense(dbName, type,expense);
        call.enqueue(new Callback<Void>() {
            @Override
            public void onResponse(Call<Void> call, Response<Void> response) {
                if (response.isSuccessful()) {
                    Toast.makeText(getContext(), "Expense added successfully!", Toast.LENGTH_SHORT).show();
                } else {
                    Log.e("Retrofit Error", "Failed to add expense" + convertUIDToGUID(userId) + response);
                }
            }

            @Override
            public void onFailure(Call<Void> call, Throwable t) {
                Log.e("Retrofit Error", "Request failed", t);
            }
        });
    }
}
