package com.example.fashionstore.adapters;

import android.content.Context;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.Button;
import android.widget.ImageView;
import android.widget.TextView;

import androidx.annotation.NonNull;
import androidx.recyclerview.widget.RecyclerView;
import com.bumptech.glide.Glide;
import com.example.fashionstore.R;
import com.example.fashionstore.models.OrderResponse;

import java.util.List;

public class OrderAdapter extends RecyclerView.Adapter<OrderAdapter.OrderViewHolder> {

    private List<OrderResponse> orderList;
    private Context context;

    public OrderAdapter(List<OrderResponse> orderList, Context context) {
        this.orderList = orderList;
        this.context = context;
    }

    @NonNull
    @Override
    public OrderViewHolder onCreateViewHolder(@NonNull ViewGroup parent, int viewType) {
        View view = LayoutInflater.from(context).inflate(R.layout.item_order, parent, false);
        return new OrderViewHolder(view);
    }

    @Override
    public void onBindViewHolder(@NonNull OrderViewHolder holder, int position) {
        OrderResponse order = orderList.get(position);
        holder.txtProductName.setText(order.getProductName());
        holder.txtTotal.setText("Total: $" + order.getTotalAmount());
        holder.txtQty.setText("Qty: " + order.getQuantity());
        holder.txtAddress.setText("" + order.getShoppingAddress());
        holder.txtPhone.setText("" + order.getPhone());
        holder.txtDate.setText("Date: " + order.getOrderDate());

        Glide.with(context).load(order.getImageURL()).into(holder.imageView);

        if ("Ordered".equalsIgnoreCase(order.getOrderStatus())) {
            holder.btnCancel.setVisibility(View.VISIBLE);
        } else {
            holder.btnCancel.setVisibility(View.GONE);
        }

        holder.btnCancel.setOnClickListener(v -> {
            // Implement cancel order logic here
        });
    }

    @Override
    public int getItemCount() {
        return orderList.size();
    }

    public static class OrderViewHolder extends RecyclerView.ViewHolder {
        ImageView imageView;
        TextView txtProductName, txtTotal, txtQty, txtAddress, txtPhone, txtDate;
        Button btnCancel;

        public OrderViewHolder(@NonNull View itemView) {
            super(itemView);
            imageView = itemView.findViewById(R.id.imageViewOrder);
            txtProductName = itemView.findViewById(R.id.txtProductName);
            txtTotal = itemView.findViewById(R.id.txtTotal);
            txtQty = itemView.findViewById(R.id.txtQty);
            txtAddress = itemView.findViewById(R.id.txtAddress);
            txtPhone = itemView.findViewById(R.id.txtPhone);
            txtDate = itemView.findViewById(R.id.txtDate);
            btnCancel = itemView.findViewById(R.id.btnCancelOrder);
        }
    }
}

