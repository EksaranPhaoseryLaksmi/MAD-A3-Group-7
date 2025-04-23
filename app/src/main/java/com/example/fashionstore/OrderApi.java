package com.example.fashionstore;
import com.example.fashionstore.models.OrderResponse;
import retrofit2.Call;
import retrofit2.http.POST;
import retrofit2.http.GET;
import retrofit2.http.Query;
import java.util.List;

public interface OrderApi {
    //@POST("orders") // Update this path if your endpoint is different (e.g., "orders/create")
    //Call<Void> placeOrder(@Body OrderRequest orderRequest);
    @POST("orders")
    Call<Void> placeOrder(
            @Query("customerId") String customerId,
            @Query("productId") int productId,
            @Query("quantity") int quantity,
            @Query("itemprice") double itemPrice,
            @Query("address") String address,
            @Query("phone") String phone,
            @Query("note") String note
    );

    @GET("orders")
    Call<List<OrderResponse>> getOrdersByCustomer(@Query("customerId") String customerId);

}