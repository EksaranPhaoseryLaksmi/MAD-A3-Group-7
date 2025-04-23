package com.example.fashionstore;
import java.util.List;
import retrofit2.Call;
import retrofit2.http.GET;
import com.example.fashionstore.models.Product;

public interface ProductApi {
    @GET("products") // Replace with your endpoint
    Call<List<Product>> getProducts();
}
