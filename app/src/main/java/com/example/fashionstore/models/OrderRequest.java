package com.example.fashionstore.models;

public class OrderRequest {
    private String customerId;
    private int productId;
    private int quantity;
    private String address;
    private String phone;
    private String note;
    private double itemprice;
    // Constructor
    public OrderRequest(String customerId, int productId, int quantity, String address, String phone, String note,double itemprice ) {
        this.customerId = customerId;
        this.productId = productId;
        this.quantity = quantity;
        this.address = address;
        this.phone = phone;
        this.note = note;
        this.itemprice = itemprice;
    }

    // Getters and Setters
    public String getCustomerId() {
        return customerId;
    }

    public void setCustomerId(String customerId) {
        this.customerId = customerId;
    }

    public int getProductId() {
        return productId;
    }

    public void setProductId(int productId) {
        this.productId = productId;
    }

    public double getItemPrice(){return itemprice;};

    public void setItemPrice() {this.itemprice = itemprice;}
    public int getQuantity() {
        return quantity;
    }

    public void setQuantity(int quantity) {
        this.quantity = quantity;
    }

    public String getAddress() {
        return address;
    }

    public void setAddress(String address) {
        this.address = address;
    }

    public String getPhone() {
        return phone;
    }

    public void setPhone(String phone) {
        this.phone = phone;
    }

    public String getNote() {
        return note;
    }

    public void setNote(String note) {
        this.note = note;
    }

}

