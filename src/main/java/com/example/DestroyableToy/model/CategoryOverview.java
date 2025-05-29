package com.example.DestroyableToy.model;

public class CategoryOverview {
    private String category;
    private int totalStock;
    private float totalValue;
    private float averagePrice;

    public CategoryOverview(String category, int totalStock, float totalValue, float averagePrice) {
        this.category = category;
        this.totalStock = totalStock;
        this.totalValue = totalValue;
        this.averagePrice = averagePrice;
    }

    // Getters y setters
    public String getCategory() {
        return category;
    }

    public void setCategory(String category) {
        this.category = category;
    }

    public int getTotalStock() {
        return totalStock;
    }

    public void setTotalStock(int totalStock) {
        this.totalStock = totalStock;
    }

    public float getTotalValue() {
        return totalValue;
    }

    public void setTotalValue(float totalValue) {
        this.totalValue = totalValue;
    }

    public float getAveragePrice() {
        return averagePrice;
    }

    public void setAveragePrice(float averagePrice) {
        this.averagePrice = averagePrice;
    }
}
