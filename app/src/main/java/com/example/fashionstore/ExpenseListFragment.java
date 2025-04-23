package com.example.fashionstore;

import android.os.Bundle;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import androidx.annotation.NonNull;
import androidx.annotation.Nullable;
import androidx.fragment.app.Fragment;
import androidx.recyclerview.widget.LinearLayoutManager;
import androidx.recyclerview.widget.RecyclerView;
import android.util.Log;

import java.nio.charset.StandardCharsets;
import java.security.MessageDigest;
import java.security.NoSuchAlgorithmException;
import java.util.List;
import java.util.UUID;

import retrofit2.Call;
import retrofit2.Callback;
import retrofit2.Response;
import com.example.fashionstore.adapters.ExpenseAdapter;
import com.example.fashionstore.models.Expense;
import com.google.firebase.auth.FirebaseAuth;
import java.util.ArrayList;

public class ExpenseListFragment extends Fragment {

    private RecyclerView recyclerView;
    private ExpenseAdapter adapter;
    private ExpenseApi expenseApi;

    @Nullable
    @Override
    public View onCreateView(@NonNull LayoutInflater inflater,
                             @Nullable ViewGroup container,
                             @Nullable Bundle savedInstanceState) {
        return inflater.inflate(R.layout.fragment_expense_list, container, false);
    }

    @Override
    public void onViewCreated(@NonNull View view,
                              @Nullable Bundle savedInstanceState) {
        super.onViewCreated(view, savedInstanceState);

        // Initialize RecyclerView
        recyclerView = view.findViewById(R.id.recyclerView);
        recyclerView.setLayoutManager(new LinearLayoutManager(requireContext()));

        // Setup adapter with empty list first
        adapter = new ExpenseAdapter(new ArrayList<>());
        recyclerView.setAdapter(adapter);
        // Setup Retrofit API
        expenseApi = RetrofitClient.getClient().create(ExpenseApi.class);

        // Fetch expenses
        fetchExpenses();
    }

    private void fetchExpenses() {
        String userId = FirebaseAuth.getInstance().getCurrentUser().getUid();
        String dbName = convertUIDToGUID(userId);
        Call<List<Expense>> call = expenseApi.getExpenses(dbName); // Replace with actual db name
        call.enqueue(new Callback<List<Expense>>() {
            @Override
            public void onResponse(@NonNull Call<List<Expense>> call,
                                   @NonNull Response<List<Expense>> response) {
                if (response.isSuccessful() && response.body() != null) {
                    adapter.updateExpenses(response.body());
                } else {
                    Log.e("ExpenseListFragment", "Response failed: " + response.code());
                }
            }

            @Override
            public void onFailure(@NonNull Call<List<Expense>> call, @NonNull Throwable t) {
                Log.e("ExpenseListFragment", "API call failed: " + t.getMessage());
            }
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
}
