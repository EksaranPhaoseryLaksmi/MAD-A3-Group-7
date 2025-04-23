package com.example.fashionstore.adapters;
import android.content.Context;
import android.util.Log;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.Button;
import android.widget.ImageView;
import android.widget.TextView;
import androidx.annotation.NonNull;
import androidx.recyclerview.widget.RecyclerView;
import com.bumptech.glide.Glide;
import java.util.List;
import android.widget.EditText;
import com.google.firebase.auth.FirebaseAuth; import com.google.firebase.auth.FirebaseUser;
import com.example.fashionstore.R;
import com.example.fashionstore.OrderApi;
import com.example.fashionstore.models.Product;
import com.example.fashionstore.models.OrderRequest;
import android.widget.Toast;
import androidx.appcompat.app.AlertDialog;
import retrofit2.converter.gson.GsonConverterFactory;
import retrofit2.Retrofit;
import retrofit2.Call; import retrofit2.Callback; import retrofit2.Response;
import com.google.gson.Gson;
public class ProductAdapter extends RecyclerView.Adapter<ProductAdapter.ProductViewHolder> {
    private Context context;
    private List<Product> productList;

    public ProductAdapter(Context context, List<Product> productList) {
        this.context = context;
        this.productList = productList;
    }

    public static class ProductViewHolder extends RecyclerView.ViewHolder {
        ImageView image;
        TextView name, description, price;
        Button orderButton;

        public ProductViewHolder(View itemView) {
            super(itemView);
            image = itemView.findViewById(R.id.productImage);
            name = itemView.findViewById(R.id.productName);
            description = itemView.findViewById(R.id.productDescription);
            price = itemView.findViewById(R.id.productPrice);
            orderButton = itemView.findViewById(R.id.orderButton);
        }
    }

    @NonNull
    @Override
    public ProductViewHolder onCreateViewHolder(@NonNull ViewGroup parent, int viewType) {
        View view = LayoutInflater.from(context).inflate(R.layout.item_product, parent, false);
        return new ProductViewHolder(view);
    }

    @Override
    public void onBindViewHolder(@NonNull ProductViewHolder holder, int position) {
        Product product = productList.get(position);
        holder.name.setText(product.getProductName());
        holder.description.setText(product.getDescription());
        holder.price.setText("$" + product.getPrice());

        Glide.with(context)
                .load(product.getImageUrl())
                .into(holder.image);

        holder.orderButton.setOnClickListener(v -> {
            // Handle order button click
            FirebaseUser user = FirebaseAuth.getInstance().getCurrentUser();
            if (user == null) {
                Toast.makeText(v.getContext(), "Please log in to order.", Toast.LENGTH_SHORT).show();
                return;
            }
            AlertDialog.Builder builder = new AlertDialog.Builder(v.getContext());
            View dialogView = LayoutInflater.from(v.getContext()).inflate(R.layout.dialog_order, null);
            builder.setView(dialogView);
            AlertDialog dialog = builder.create();
            dialog.show();

            EditText etQuantity = dialogView.findViewById(R.id.etQuantity);
            EditText etAddress = dialogView.findViewById(R.id.etAddress);
            EditText etPhone = dialogView.findViewById(R.id.etPhone);
            EditText etNote = dialogView.findViewById(R.id.etNote);
            Button btnConfirm = dialogView.findViewById(R.id.btnConfirmOrder);

            btnConfirm.setOnClickListener(view -> {
                String customerId = user.getUid();
                String quantityStr = etQuantity.getText().toString().trim();
                String address = etAddress.getText().toString().trim();
                String phone = etPhone.getText().toString().trim();
                String note = etNote.getText().toString().trim();

                if (quantityStr.isEmpty() || address.isEmpty() || phone.isEmpty()) {
                    Toast.makeText(view.getContext(), "All fields except note are required", Toast.LENGTH_SHORT).show();
                    return;
                }

                int quantity = Integer.parseInt(quantityStr);

                OrderRequest orderRequest = new OrderRequest(customerId, product.getProductID(), quantity, address, phone, note,product.getPrice());

                Retrofit retrofit = new Retrofit.Builder()
                        .baseUrl("http://192.168.18.7:5000/fashion/") // Replace as needed
                        .addConverterFactory(GsonConverterFactory.create())
                        .build();

                OrderApi api = retrofit.create(OrderApi.class);
                api.placeOrder(customerId,
                        product.getProductID(),
                        quantity,
                        product.getPrice(),
                        address,
                        phone,
                        note).enqueue(new Callback<Void>() {
                    @Override
                    public void onResponse(Call<Void> call, Response<Void> response) {
                        Gson gson = new Gson();
                        Log.d("ORDER_DEBUG", "Request Body: " + gson.toJson(orderRequest));
                        Toast.makeText(view.getContext(), "Order placed!", Toast.LENGTH_SHORT).show();
                        dialog.dismiss();
                    }

                    @Override
                    public void onFailure(Call<Void> call, Throwable t) {
                        Toast.makeText(view.getContext(), "Order failed: " + t.getMessage(), Toast.LENGTH_SHORT).show();
                    }
                });
            });
        });
    }

    @Override
    public int getItemCount() {
        return productList.size();
    }
    public void updateList(List<Product> filteredList) {
        productList = filteredList;
        notifyDataSetChanged();
    }

}

