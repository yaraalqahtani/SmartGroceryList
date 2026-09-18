package com.example.smartgrocerylist.model;

public class GroceryItem {

    private String name;
    private String category;
    private boolean purchased;

    public GroceryItem(String name, String category, boolean purchased) {
        this.name = name;
        this.category = category;
        this.purchased = purchased;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public String getCategory() {
        return category;
    }

    public void setCategory(String category) {
        this.category = category;
    }

    public boolean isPurchased() {
        return purchased;
    }

    public void setPurchased(boolean purchased) {
        this.purchased = purchased;
    }
}
