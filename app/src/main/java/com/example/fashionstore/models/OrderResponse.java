package com.example.fashionstore.models;

public class OrderResponse {
    private Integer OrderID;
    private String Customer_ID;
    private String OrderDate;
    private Double TotalAmount;
    private String ShoppingAddress;
    private String OrderStatus;
    private Integer Quantity;
    private String Phone;
    private String Note;
    private Integer ProductID;
    private String ProductName;
    private String Description;
    private String Size;
    private String Color;
    private Double Price;
    private String ImageURL;

    public Integer getOrderID() {
        return OrderID;
    }

    public void setOrderID(Integer orderID) {
        OrderID = orderID;
    }

    public String getCustomer_ID() {
        return Customer_ID;
    }

    public void setCustomer_ID(String customer_ID) {
        Customer_ID = customer_ID;
    }

    public String getOrderDate() {
        return OrderDate;
    }

    public void setOrderDate(String orderDate) {
        OrderDate = orderDate;
    }

    public Double getTotalAmount() {
        return TotalAmount;
    }

    public void setTotalAmount(Double totalAmount) {
        TotalAmount = totalAmount;
    }

    public String getShoppingAddress() {
        return ShoppingAddress;
    }

    public void setShoppingAddress(String shoppingAddress) {
        ShoppingAddress = shoppingAddress;
    }

    public String getOrderStatus() {
        return OrderStatus;
    }

    public void setOrderStatus(String orderStatus) {
        OrderStatus = orderStatus;
    }

    public Integer getQuantity() {
        return Quantity;
    }

    public void setQuantity(Integer quantity) {
        Quantity = quantity;
    }

    public String getPhone() {
        return Phone;
    }

    public void setPhone(String phone) {
        Phone = phone;
    }

    public String getNote() {
        return Note;
    }

    public void setNote(String note) {
        Note = note;
    }

    public Integer getProductID() {
        return ProductID;
    }

    public void setProductID(Integer productID) {
        ProductID = productID;
    }

    public String getProductName() {
        return ProductName;
    }

    public void setProductName(String productName) {
        ProductName = productName;
    }

    public String getDescription() {
        return Description;
    }

    public void setDescription(String description) {
        Description = description;
    }

    public String getSize() {
        return Size;
    }

    public void setSize(String size) {
        Size = size;
    }

    public String getColor() {
        return Color;
    }

    public void setColor(String color) {
        Color = color;
    }

    public Double getPrice() {
        return Price;
    }

    public void setPrice(Double price) {
        Price = price;
    }

    public String getImageURL() {
        return ImageURL;
    }

    public void setImageURL(String imageURL) {
        ImageURL = imageURL;
    }
}

