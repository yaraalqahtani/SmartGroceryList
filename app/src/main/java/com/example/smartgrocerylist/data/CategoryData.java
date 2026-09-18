package com.example.smartgrocerylist.data;

import com.example.smartgrocerylist.R;
import com.example.smartgrocerylist.model.Category;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;

/**
 * Static in-memory catalog of all grocery categories and their products.
 * This app does not use any external API or database, so the product
 * catalog is defined directly in code.
 */
public class CategoryData {

    private static List<Category> categories;

    public static List<Category> getCategories() {
        if (categories == null) {
            categories = new ArrayList<>();

            categories.add(new Category("Dairy", R.drawable.ic_cat_dairy, Arrays.asList(
                    "Milk", "Yogurt", "Ayran", "Pudding", "Cheese", "Butter")));

            categories.add(new Category("Beverages", R.drawable.ic_cat_beverages, Arrays.asList(
                    "Tea", "Nescafe", "Nesquik", "Juice", "Water")));

            categories.add(new Category("Canned & Condiments", R.drawable.ic_cat_canned, Arrays.asList(
                    "Tomato Sauce", "Ketchup", "Mustard", "Mayonnaise", "Soy Sauce",
                    "Tuna", "Sweet Corn", "Hummus")));

            categories.add(new Category("Cleaning Supplies", R.drawable.ic_cat_cleaning, Arrays.asList(
                    "Laundry Detergent", "Laundry Gel", "Fabric Softener", "Stain Remover",
                    "Toilet Cleaner", "Floor Cleaner", "Dishwashing Liquid", "Bleach", "Glass Cleaner")));

            categories.add(new Category("Meat & Poultry", R.drawable.ic_cat_meat, Arrays.asList(
                    "Breaded Chicken", "Chicken Nuggets", "Whole Chicken", "Ground Beef",
                    "Burger", "Kofta", "Sausage")));

            categories.add(new Category("Bakery", R.drawable.ic_cat_bakery, Arrays.asList(
                    "Fino Bread", "Breadcrumbs", "Biscuits", "French Bread", "Kaiser Rolls")));

            categories.add(new Category("Sweeteners & Spreads", R.drawable.ic_cat_sweeteners, Arrays.asList(
                    "Jam", "Halawa", "Tahini", "White Honey", "Black Honey", "Nutella")));

            categories.add(new Category("Paper & Household", R.drawable.ic_cat_paper, Arrays.asList(
                    "Kitchen Paper Towels", "Toilet Paper", "Facial Tissues", "Plastic Bags",
                    "Aluminum Foil", "Plastic Wrap", "Dish Scrubber", "Dish Sponge")));

            categories.add(new Category("Spices", R.drawable.ic_cat_spices, Arrays.asList(
                    "Salt", "Black Pepper", "Mixed Spices", "Cumin", "Coriander", "Cardamom",
                    "Cinnamon", "Chicken Stock", "Beef Stock", "Vegetable Stock")));

            categories.add(new Category("Basic Ingredients", R.drawable.ic_cat_basic, Arrays.asList(
                    "Rice", "Pasta", "Fava Beans", "Cornstarch", "White Beans", "Flour",
                    "Popcorn", "Baking Powder", "Yeast", "Yellow Lentils", "Brown Lentils",
                    "Black-Eyed Peas", "Split Fava Beans", "Lupin Beans", "Bulgur")));

            categories.add(new Category("Personal Care", R.drawable.ic_cat_personal, Arrays.asList(
                    "Shampoo", "Hair Cream", "Hand Cream", "Loofah", "Facial Soap",
                    "Sanitary Pads", "Hand Wash", "Toothpaste", "Body Wash", "Toothbrush",
                    "Deodorant", "Shaving Cream", "Cotton Swabs")));

            categories.add(new Category("Baby Products", R.drawable.ic_cat_baby, Arrays.asList(
                    "Baby Formula", "Diapers", "Baby Wipes", "Baby Sunscreen",
                    "Diaper Cream", "Baby Shampoo & Wash")));

            categories.add(new Category("Frozen Vegetables", R.drawable.ic_cat_frozen, Arrays.asList(
                    "Peas", "Green Beans", "Okra", "Molokhia", "Spinach", "French Fries")));

            categories.add(new Category("Fruits & Vegetables", R.drawable.ic_cat_fruits, Arrays.asList(
                    "Apple", "Banana", "Orange", "Grapes", "Strawberry", "Watermelon",
                    "Lemon", "Tomato", "Cucumber", "Potato", "Onion", "Garlic",
                    "Carrot", "Bell Pepper", "Lettuce", "Zucchini")));

            categories.add(new Category("Other", R.drawable.ic_cat_other, Arrays.asList(
                    "Goulash Pastry", "Pastries", "Sambousek", "Pizza Bases")));
        }
        return categories;
    }

    public static Category getCategoryByName(String name) {
        if (name == null) {
            return null;
        }
        for (Category category : getCategories()) {
            if (category.getName().equals(name)) {
                return category;
            }
        }
        return null;
    }
}
