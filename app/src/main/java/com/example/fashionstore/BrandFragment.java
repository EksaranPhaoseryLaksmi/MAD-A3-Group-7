package com.example.fashionstore;

import android.os.Bundle;
import androidx.fragment.app.Fragment;
import androidx.recyclerview.widget.RecyclerView;
import androidx.recyclerview.widget.LinearLayoutManager;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
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

public class BrandFragment extends Fragment {

    private RecyclerView recyclerView;
    private BrandAdapter adapter;
    private List<Brand> brandList = new ArrayList<>();

    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {
        // Inflate the layout for this fragment
        View rootView = inflater.inflate(R.layout.fragment_brand, container, false);

        // Set up RecyclerView
        recyclerView = rootView.findViewById(R.id.recyclerViewBrand);
        recyclerView.setLayoutManager(new LinearLayoutManager(getContext()));

        adapter = new BrandAdapter(getContext(), brandList);
        recyclerView.setAdapter(adapter);

        // Fetch data
        fetchBrands();

        return rootView;
    }

    private void fetchBrands() {
        Retrofit retrofit = new Retrofit.Builder()
                .baseUrl("http://192.168.18.7:5000/fashion/") // Replace with actual base URL
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
                Toast.makeText(getContext(), "Failed: " + t.getMessage(), Toast.LENGTH_SHORT).show();
            }
        });
    }
}
