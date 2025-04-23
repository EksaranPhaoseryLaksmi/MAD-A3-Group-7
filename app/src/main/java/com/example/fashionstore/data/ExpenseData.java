package com.example.fashionstore.data;
import java.util.ArrayList;
import java.util.List;

import com.example.fashionstore.models.Expense;
public class ExpenseData {
    private static final List<Expense> expenses = new ArrayList<>();

    static {
        try {
          } catch (Exception e) {
            e.printStackTrace();
        }
    }

    public static List<Expense> getDummyExpenses() {
        return expenses;
    }

    public static Expense getExpenseById(String id) {
        for (Expense expense : expenses) {
            if (expense.getId().equals(id)) {
                return expense;
            }
        }
        return null;
    }
}