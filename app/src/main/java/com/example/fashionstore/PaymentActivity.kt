package com.example.fashionstore

import android.os.Bundle
import android.widget.Toast // Import Toast for the pop-up message
import androidx.activity.ComponentActivity
import androidx.activity.compose.setContent
import androidx.activity.enableEdgeToEdge
import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.padding
import androidx.compose.material3.Button
import androidx.compose.material3.MaterialTheme
import androidx.compose.material3.Scaffold
import androidx.compose.material3.Surface
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.ui.Modifier
import androidx.compose.ui.platform.LocalContext // Import LocalContext to get context for Toast
import androidx.compose.ui.text.font.FontWeight
import androidx.compose.ui.tooling.preview.Preview
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp
import com.example.fashionstore.ui.theme.ExpenseTrackerTheme // Assuming ExpenseTrackerTheme is the correct theme for your app

class PaymentActivity : ComponentActivity() {
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        enableEdgeToEdge()

        // Retrieve data from the Intent
        val orderedItems = intent.getStringExtra("ordered_items") ?: "No items provided"
        val totalPrice = intent.getDoubleExtra("total_price", 0.0)

        setContent {
            // Use your app's theme. Make sure ExpenseTrackerTheme is correct or replace with your actual theme.
            ExpenseTrackerTheme {
                Scaffold(modifier = Modifier.fillMaxSize()) { innerPadding ->
                    PaymentScreen(
                        orderedItems = orderedItems,
                        totalPrice = totalPrice,
                        modifier = Modifier.padding(innerPadding)
                    )
                }
            }
        }
    }
}

@Composable
fun PaymentScreen(
    orderedItems: String,
    totalPrice: Double,
    modifier: Modifier = Modifier
) {
    val context = LocalContext.current // Get context for Toast

    Surface(
        modifier = modifier.fillMaxSize(),
        color = MaterialTheme.colorScheme.background // Use background color from theme
    ) {
        Column(
            modifier = Modifier
                .fillMaxSize()
                .padding(16.dp),
            verticalArrangement = Arrangement.spacedBy(16.dp)
        ) {
            Text(
                text = "Order Summary",
                fontSize = 24.sp,
                fontWeight = FontWeight.Bold,
                modifier = Modifier.fillMaxWidth()
            )

            Text(
                text = "Items:",
                fontSize = 18.sp,
                fontWeight = FontWeight.Bold
            )
            Text(
                text = orderedItems,
                fontSize = 16.sp,
                modifier = Modifier
                    .fillMaxWidth()
                    .weight(1f) // Makes the items text scrollable if it gets long
            )

            Text(
                text = "Total: $%.2f".format(totalPrice), // Format to 2 decimal places
                fontSize = 22.sp,
                fontWeight = FontWeight.ExtraBold,
                modifier = Modifier.fillMaxWidth()
            )

            Button(
                onClick = {
                    // Show "Transaction Complete" Toast
                    Toast.makeText(context, "Transaction Complete!", Toast.LENGTH_LONG).show()
                    // Optionally, you might finish this activity to go back to the previous screen
                    // (context as? ComponentActivity)?.finish()
                },
                modifier = Modifier.fillMaxWidth()
            ) {
                Text("Pay Now", fontSize = 20.sp)
            }
        }
    }
}
// Ouk Sovannrith
@Preview(showBackground = true)
@Composable
fun PaymentScreenPreview() {
    // Make sure to use the correct theme for your preview
    ExpenseTrackerTheme {
        PaymentScreen(
            orderedItems = "T-Shirt (x1), Jeans (x1), Hat (x1), Shoes (x1)",
            totalPrice = 250.75
        )
    }
}