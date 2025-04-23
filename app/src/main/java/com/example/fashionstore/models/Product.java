package com.example.fashionstore.models;
public class Product {

    private int ProductID;
    private String ProductName;
    private String Description;
    private String Color;
    private String Size;
    private String ImageURL;
    private double Price;
    public Product(int ProductID, String ProductName, String Description, String Color,
                   String Size,String ImageURL,double Price) {
        this.ProductID = ProductID;
        this.ProductName = ProductName;
        this.Description = Description;
        this.Color = Color;
        this.Size = Size;
        this.ImageURL = ImageURL;
        this.Price=Price;
    }
    public int getProductID() { return ProductID; }
    public String getProductName() { return ProductName; }
    public String getDescription() { return Description; }
    public String getImageUrl() { return ImageURL; }
    public String getColor() { return Color; }
    public String getSize() { return Size; }
    public double getPrice() { return Price; }
}

