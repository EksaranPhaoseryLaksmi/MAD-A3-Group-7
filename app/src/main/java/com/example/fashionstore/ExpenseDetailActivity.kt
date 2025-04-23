package com.example.fashionstore

import android.content.Intent
import android.os.Bundle
import android.widget.Button
import android.widget.TextView
import androidx.appcompat.app.AppCompatActivity

class ExpenseDetailActivity : AppCompatActivity() {

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_expense_detail)

        val amountTextView: TextView = findViewById(R.id.detailAmount)
        val categoryTextView: TextView = findViewById(R.id.detailCategory)
        val remarkTextView: TextView = findViewById(R.id.detailNote)
        val dateTextView: TextView = findViewById(R.id.detailDate)
        val backToHomeButton: Button = findViewById(R.id.backToHomeButton)

        val amount = intent.getStringExtra("amount")
        val category = intent.getStringExtra("category")
        val remark = intent.getStringExtra("remark")
        val date = intent.getStringExtra("date")

        amountTextView.text = "Amount: $amount"
        categoryTextView.text = "Category: $category"
        remarkTextView.text = "Remark: $remark"
        dateTextView.text = "Date: $date"



        backToHomeButton.setOnClickListener {
            val intent = Intent(this, MainActivity::class.java)
            startActivity(intent)
        }
    }
}