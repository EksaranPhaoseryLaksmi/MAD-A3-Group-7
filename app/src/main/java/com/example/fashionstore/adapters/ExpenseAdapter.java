package com.example.fashionstore.adapters;

import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.TextView;
import androidx.annotation.NonNull;
import androidx.recyclerview.widget.RecyclerView;

import com.example.fashionstore.ExpenseDetailActivity;
import com.example.fashionstore.models.Expense;
import com.example.fashionstore.R;
import java.util.List;
import android.content.Intent;

public class ExpenseAdapter extends RecyclerView.Adapter<ExpenseAdapter.ExpenseViewHolder> {

    private List<Expense> expenseList;

    public ExpenseAdapter(List<Expense> expenseList) {
        this.expenseList = expenseList;
    }

    @NonNull
    @Override
    public ExpenseViewHolder onCreateViewHolder(@NonNull ViewGroup parent, int viewType) {
        View view = LayoutInflater.from(parent.getContext())
                .inflate(R.layout.item_expense, parent, false);
        return new ExpenseViewHolder(view);
    }

    @Override
    public void onBindViewHolder(@NonNull ExpenseViewHolder holder, int position) {
        Expense expense = expenseList.get(position);
        holder.categoryText.setText(expense.getCategory());
        holder.amountText.setText(String.valueOf(expense.getAmount()));
        holder.noteText.setText(expense.getRemark());
        holder.dateText.setText(expense.getCreatedDate());
        holder.itemView.setOnClickListener(v -> {
            Intent intent = new Intent(v.getContext(), ExpenseDetailActivity.class);
            intent.putExtra("category", expense.getCategory());
            intent.putExtra("amount", String.valueOf(expense.getAmount()));
            intent.putExtra("note", expense.getRemark());
            intent.putExtra("date", expense.getCreatedDate());
            v.getContext().startActivity(intent);
        });

    }

    @Override
    public int getItemCount() {
        return expenseList.size();
    }

    public void updateExpenses(List<Expense> newExpenses) {
        this.expenseList.clear();
        this.expenseList.addAll(newExpenses);
        notifyDataSetChanged();
    }

    static class ExpenseViewHolder extends RecyclerView.ViewHolder {

        TextView categoryText, amountText, noteText, dateText;

        public ExpenseViewHolder(@NonNull View itemView) {
            super(itemView);
            categoryText = itemView.findViewById(R.id.textCategory);
            amountText = itemView.findViewById(R.id.textAmount);
            noteText = itemView.findViewById(R.id.textNote);
            dateText = itemView.findViewById(R.id.textDate);
        }
    }
}
