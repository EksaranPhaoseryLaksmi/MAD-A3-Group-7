package com.example.fashionstore.models;
import java.util.UUID;

public class Expense {
    private String id;
    private String remark;
    private double amount;
    private String currency;
    private String category;
    private String createdBy;
    private String createdDate;

    public Expense(double amount, String currency, String category, String remark, String createdBy, String createdAt) {
        this.id = UUID.randomUUID().toString(); // Auto-generate ID
        this.amount = amount;
        this.currency = currency;
        this.category = category;
        this.remark = remark;
        this.createdBy = createdBy;
        this.createdDate = createdAt;
    }

    public String getId() { return id; }
    public String getRemark() { return remark; }
    public double getAmount() { return amount; }
    public String getCurrency() { return currency; }
    public String getCategory() { return category; }
    public String getCreatedBy() {return createdBy;}
    public String getCreatedDate() {return createdDate;};

}
