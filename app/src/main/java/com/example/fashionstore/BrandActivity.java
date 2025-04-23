package com.example.fashionstore;

import android.os.Bundle;
import androidx.appcompat.app.AppCompatActivity;
import androidx.recyclerview.widget.RecyclerView;
import androidx.recyclerview.widget.LinearLayoutManager;
import android.widget.Toast;

import com.example.fashionstore.adapters.BrandAdapter;
import com.example.fashionstore.models.Brand;

import java.util.List;
import java.util.ArrayList;
import retrofit2.Retrofit;
import retrofit2.converter.gson.GsonConverterFactory;
import retrofit2.Call;
import retrofit2.Callback;
import retrofit2.Response;


public class BrandActivity extends AppCompatActivity {
    RecyclerView recyclerView;
    BrandAdapter adapter;
    List<Brand> brandList = new ArrayList<>();

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_brand);

        recyclerView = findViewById(R.id.recyclerViewBrand);
        recyclerView.setLayoutManager(new LinearLayoutManager(this));

        adapter = new BrandAdapter(this, brandList);
        recyclerView.setAdapter(adapter);

        fetchBrands();
    }

    private void fetchBrands() {
        Retrofit retrofit = new Retrofit.Builder()
                .baseUrl("http://127.0.0.1:5000/fashion/") // Replace with actual base URL
                .addConverterFactory(GsonConverterFactory.create())
                .build();

        BrandApi api = retrofit.create(BrandApi.class);

        api.getAllBrands().enqueue(new Callback<List<Brand>>() {
            @Override
            public void onResponse(Call<List<Brand>> call, Response<List<Brand>> response) {
                if (response.isSuccessful() && response.body() != null) {
                    brandList.clear();
                    brandList.addAll(response.body());
                    adapter.notifyDataSetChanged();
                }
            }

            @Override
            public void onFailure(Call<List<Brand>> call, Throwable t) {
                Toast.makeText(BrandActivity.this, "Failed: " + t.getMessage(), Toast.LENGTH_SHORT).show();
            }
        });
    }

}
