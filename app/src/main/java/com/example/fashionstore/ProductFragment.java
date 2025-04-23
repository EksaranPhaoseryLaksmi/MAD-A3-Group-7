package com.example.fashionstore;

import android.os.Bundle;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import androidx.appcompat.widget.SearchView;
import android.widget.Toast;
import android.util.Log;
import androidx.annotation.NonNull;
import androidx.annotation.Nullable;
import androidx.fragment.app.Fragment;
import androidx.recyclerview.widget.LinearLayoutManager;
import androidx.recyclerview.widget.RecyclerView;

import com.example.fashionstore.R;
import com.example.fashionstore.adapters.ProductAdapter;
import com.example.fashionstore.models.Product;

import java.util.ArrayList;
import java.util.List;

import retrofit2.Call;
import retrofit2.Callback;
import retrofit2.Response;
import retrofit2.Retrofit;
import retrofit2.converter.gson.GsonConverterFactory;

import androidx.swiperefreshlayout.widget.SwipeRefreshLayout;

public class ProductFragment extends Fragment {

    private RecyclerView recyclerView;
    private ProductAdapter adapter;
    private List<Product> allProducts = new ArrayList<>();
    private SwipeRefreshLayout swipeRefreshLayout;
    private SearchView searchView;

    public ProductFragment() {
        // Required empty public constructor
    }

    @Nullable
    @Override
    public View onCreateView(@NonNull LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {
        View view = inflater.inflate(R.layout.fragment_product, container, false);

        //swipeRefreshLayout = view.findViewById(R.id.swipeRefresh);
        searchView = view.findViewById(R.id.searchView);
        searchView.setQueryHint("Search to order here...");
        // Set up RecyclerView
        recyclerView = view.findViewById(R.id.recyclerViewBrand);
        recyclerView.setLayoutManager(new LinearLayoutManager(getContext()));

        adapter = new ProductAdapter(getContext(), allProducts);
        recyclerView.setAdapter(adapter);

        //swipeRefreshLayout.setOnRefreshListener(this::fetchProducts);

        searchView.setOnQueryTextListener(new SearchView.OnQueryTextListener() {
            @Override
            public boolean onQueryTextSubmit(String query) {
                return false;
            }

            @Override
            public boolean onQueryTextChange(String newText) {
                filterProducts(newText);
                return true;
            }
        });

        fetchProducts();

        return view;
    }

    private void fetchProducts() {

        Retrofit retrofit = new Retrofit.Builder()
                .baseUrl("http://192.168.18.7:5000/fashion/") // Replace with actual base URL
                .addConverterFactory(GsonConverterFactory.create())
                .build();

        ProductApi api = retrofit.create(ProductApi.class);

        api.getProducts().enqueue(new Callback<List<Product>>() {
            @Override
            public void onResponse(Call<List<Product>> call, Response<List<Product>> response) {
                //swipeRefreshLayout.setRefreshing(false);
                if (response.isSuccessful() && response.body() != null) {
                    Log.d("FETCH_PRODUCTS", "Got products: " + response.body().size());
                    allProducts = response.body();
                    adapter.updateList(allProducts);
                }
            }

            @Override
            public void onFailure(Call<List<Product>> call, Throwable t) {
                Toast.makeText(getContext(), "Failed: " + t.getMessage(), Toast.LENGTH_SHORT).show();
            }
        });
    }

    private void filterProducts(String query) {
        List<Product> filtered = new ArrayList<>();
        for (Product product : allProducts) {
            if (product.getProductName().toLowerCase().contains(query.toLowerCase())) {
                filtered.add(product);
            }
        }
        adapter.updateList(filtered);
    }
}

