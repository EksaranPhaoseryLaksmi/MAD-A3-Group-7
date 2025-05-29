package com.example.fashionstore;

import android.content.Intent;
import android.os.Bundle;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.Button; // Import Button class

import androidx.annotation.NonNull;
import androidx.annotation.Nullable;
import androidx.fragment.app.Fragment;

// Import PaymentActivity class. Adjust the package if PaymentActivity is in a different location.
import com.example.fashionstore.PaymentActivity;

public class HomeFragment extends Fragment {

    @Nullable
    @Override
    public View onCreateView(@NonNull LayoutInflater inflater,
                             @Nullable ViewGroup container,
                             @Nullable Bundle savedInstanceState) {

        // Inflate the new homepage layout
        return inflater.inflate(R.layout.fragment_home, container, false);
    }

    @Override
    public void onViewCreated(@NonNull View view, @Nullable Bundle savedInstanceState) {
        super.onViewCreated(view, savedInstanceState);

        // Find the button in the fragment's inflated view
        Button navigateToPaymentButton = view.findViewById(R.id.buttonNavigateToPayment);

        // Set the OnClickListener for the button
        navigateToPaymentButton.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                // Create an Intent to start PaymentActivity
                Intent intent = new Intent(requireActivity(), PaymentActivity.class);

                // Pass dummy data for now
                // In a real app, you would pass actual ordered items and total price
                intent.putExtra("ordered_items", "Example Item 1 (x2), Example Item 2 (x1)");
                intent.putExtra("total_price", 75.50); // Example total price

                // Start the PaymentActivity
                startActivity(intent);
            }
        });
    }
}