package com.example.smartgrocerylist.model;

import java.util.List;

public class Category {

    private final String name;
    private final int iconResId;
    private final List<String> products;

    public Category(String name, int iconResId, List<String> products) {
        this.name = name;
        this.iconResId = iconResId;
        this.products = products;
    }

    public String getName() {
        return name;
    }

    public int getIconResId() {
        return iconResId;
    }

    public List<String> getProducts() {
        return products;
    }
}
