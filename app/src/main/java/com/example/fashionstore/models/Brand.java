package com.example.fashionstore.models;
public class Brand {
    private int BrandID;
    private String BrandName;
    private String BrandDescription;
    private String BrandWebsite;

    public Brand(int BrandID, String BrandName, String BrandDescription, String BrandWebsite) {
        this.BrandID = BrandID;
        this.BrandName = BrandName;
        this.BrandDescription = BrandDescription;
        this.BrandWebsite = BrandWebsite;
    }

    public int getBrandID() {
        return BrandID;
    }

    public String getBrandName() {
        return BrandName;
    }

    public String getBrandDescription() {
        return BrandDescription;
    }

    public String getBrandWebsite() {
        return BrandWebsite;
    }

}