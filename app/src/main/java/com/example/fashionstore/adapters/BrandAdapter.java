package com.example.fashionstore.adapters;
import android.content.Context;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ImageView;
import android.widget.TextView;
import androidx.annotation.NonNull;
import androidx.recyclerview.widget.RecyclerView;
import com.bumptech.glide.Glide;
import com.example.fashionstore.R;
import com.example.fashionstore.models.Brand;
import java.util.List;
public class BrandAdapter extends RecyclerView.Adapter<BrandAdapter.BrandViewHolder> {
private final Context context;
private final List<Brand> brandList;
// Constructor must be inside the class
public BrandAdapter(Context context, List<Brand> brandList) {
    this.context = context;
    this.brandList = brandList;
}

@NonNull
@Override
public BrandViewHolder onCreateViewHolder(@NonNull ViewGroup parent, int viewType) {
    View view = LayoutInflater.from(context).inflate(R.layout.item_brand, parent, false);
    return new BrandViewHolder(view);
}

@Override
public void onBindViewHolder(@NonNull BrandViewHolder holder, int position) {
    Brand brand = brandList.get(position);
    holder.txtName.setText(brand.getBrandName());
    holder.txtDescription.setText(brand.getBrandDescription());
    Glide.with(context).load(brand.getBrandWebsite()).into(holder.imgLogo);
}

@Override
public int getItemCount() {
    return brandList.size();
}

public static class BrandViewHolder extends RecyclerView.ViewHolder {
    TextView txtName, txtDescription;
    ImageView imgLogo;

    public BrandViewHolder(@NonNull View itemView) {
        super(itemView);
        txtName = itemView.findViewById(R.id.txtBrandName);
        txtDescription = itemView.findViewById(R.id.txtBrandDescription);
        imgLogo = itemView.findViewById(R.id.imgBrandLogo);
    }
}
}
