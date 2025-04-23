package com.example.fashionstore;

import android.os.Bundle;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.Toast;

import androidx.annotation.NonNull;
import androidx.fragment.app.Fragment;
import androidx.recyclerview.widget.LinearLayoutManager;
import androidx.recyclerview.widget.RecyclerView;

import com.example.fashionstore.R;
import com.example.fashionstore.adapters.OrderAdapter;
import com.example.fashionstore.models.OrderResponse;
import com.google.firebase.auth.FirebaseAuth;
import com.google.firebase.auth.FirebaseUser;

import java.util.List;

import retrofit2.Call;
import retrofit2.Callback;
import retrofit2.Response;
import retrofit2.Retrofit;
import retrofit2.converter.gson.GsonConverterFactory;

public class OrderFragment extends Fragment {

    private RecyclerView recyclerView;
    private OrderAdapter orderAdapter;

    public View onCreateView(@NonNull LayoutInflater inflater, ViewGroup container, Bundle savedInstanceState) {
        View root = inflater.inflate(R.layout.fragment_order, container, false);

        recyclerView = root.findViewById(R.id.recyclerViewOrder);
        recyclerView.setLayoutManager(new LinearLayoutManager(getContext()));

        FirebaseUser user = FirebaseAuth.getInstance().getCurrentUser();
        if (user != null) {
            loadOrders(user.getUid());
        } else {
            Toast.makeText(getContext(), "Please login first.", Toast.LENGTH_SHORT).show();
        }

        return root;
    }

    private void loadOrders(String customerId) {
        Retrofit retrofit = new Retrofit.Builder()
                .baseUrl("http://192.168.18.7:5000/fashion/") // Replace with actual base URL
                .addConverterFactory(GsonConverterFactory.create())
                .build();

        OrderApi orderApi = retrofit.create(OrderApi.class);
        orderApi.getOrdersByCustomer(customerId).enqueue(new Callback<List<OrderResponse>>() {
            @Override
            public void onResponse(Call<List<OrderResponse>> call, Response<List<OrderResponse>> response) {
                if (response.isSuccessful() && response.body() != null) {
                    orderAdapter = new OrderAdapter(response.body(), getContext());
                    recyclerView.setAdapter(orderAdapter);
                } else {
                    Toast.makeText(getContext(), "Failed to load orders.", Toast.LENGTH_SHORT).show();
                }
            }

            @Override
            public void onFailure(Call<List<OrderResponse>> call, Throwable t) {
                Toast.makeText(getContext(), "Error: " + t.getMessage(), Toast.LENGTH_LONG).show();
            }
        });
    }
}

