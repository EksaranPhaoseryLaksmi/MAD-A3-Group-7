package com.example.fashionstore;

import java.util.List;

import retrofit2.Call;
import retrofit2.http.GET;
import com.example.fashionstore.models.Brand;
public interface BrandApi {
    @GET("brands") Call<List<Brand>> getAllBrands();
}
