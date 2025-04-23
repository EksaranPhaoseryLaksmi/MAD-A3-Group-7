package com.example.fashionstore;
import java.util.List;
import retrofit2.Call;
import retrofit2.http.Body;
import retrofit2.http.GET;
import retrofit2.http.Header;
import retrofit2.http.POST;
import com.example.fashionstore.models.Expense;

public interface ExpenseApi {

    // Fetch expenses (GET Request)
    @GET("expenses")
    Call<List<Expense>> getExpenses(@Header("X-DB-NAME") String dbName);

    // Add an expense (POST Request)
    // POST request to add an expense
    @POST("expenses")
    Call<Void> addExpense(
            @Header("X-DB-NAME") String dbName,       // Database name header
            @Header("Content-Type") String contentType, // Content-Type header
            @Body Expense expense                      // Expense object in the body
    );

}
