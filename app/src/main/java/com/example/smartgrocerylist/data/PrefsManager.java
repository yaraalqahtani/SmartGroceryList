package com.example.smartgrocerylist.data;

import android.content.Context;
import android.content.SharedPreferences;

import com.example.smartgrocerylist.model.GroceryItem;

import org.json.JSONArray;
import org.json.JSONException;
import org.json.JSONObject;

import java.util.ArrayList;
import java.util.List;

/**
 * Handles all local storage for the shopping list using SharedPreferences.
 * The list of items is serialized to a JSON array string and stored under a single key.
 * No external database, API or backend is used.
 */
public class PrefsManager {

    private static final String PREFS_NAME = "smart_grocery_prefs";
    private static final String KEY_LIST = "grocery_list";

    private static final String KEY_NAME = "name";
    private static final String KEY_CATEGORY = "category";
    private static final String KEY_PURCHASED = "purchased";

    private final SharedPreferences prefs;

    public PrefsManager(Context context) {
        prefs = context.getApplicationContext()
                .getSharedPreferences(PREFS_NAME, Context.MODE_PRIVATE);
    }

    public List<GroceryItem> getGroceryList() {
        List<GroceryItem> items = new ArrayList<>();
        String json = prefs.getString(KEY_LIST, "[]");
        try {
            JSONArray array = new JSONArray(json);
            for (int i = 0; i < array.length(); i++) {
                JSONObject obj = array.getJSONObject(i);
                String name = obj.getString(KEY_NAME);
                String category = obj.optString(KEY_CATEGORY, "");
                boolean purchased = obj.optBoolean(KEY_PURCHASED, false);
                items.add(new GroceryItem(name, category, purchased));
            }
        } catch (JSONException e) {
            e.printStackTrace();
        }
        return items;
    }

    private void saveGroceryList(List<GroceryItem> items) {
        JSONArray array = new JSONArray();
        try {
            for (GroceryItem item : items) {
                JSONObject obj = new JSONObject();
                obj.put(KEY_NAME, item.getName());
                obj.put(KEY_CATEGORY, item.getCategory());
                obj.put(KEY_PURCHASED, item.isPurchased());
                array.put(obj);
            }
        } catch (JSONException e) {
            e.printStackTrace();
        }
        prefs.edit().putString(KEY_LIST, array.toString()).apply();
    }

    public boolean isProductAdded(String productName) {
        for (GroceryItem item : getGroceryList()) {
            if (item.getName().equalsIgnoreCase(productName)) {
                return true;
            }
        }
        return false;
    }

    public void addItem(String productName, String category) {
        List<GroceryItem> items = getGroceryList();
        for (GroceryItem item : items) {
            if (item.getName().equalsIgnoreCase(productName)) {
                // Prevent duplicate products in the shopping list.
                return;
            }
        }
        items.add(new GroceryItem(productName, category, false));
        saveGroceryList(items);
    }

    public void removeItem(String productName) {
        List<GroceryItem> items = getGroceryList();
        List<GroceryItem> updated = new ArrayList<>();
        for (GroceryItem item : items) {
            if (!item.getName().equalsIgnoreCase(productName)) {
                updated.add(item);
            }
        }
        saveGroceryList(updated);
    }

    public void setPurchased(String productName, boolean purchased) {
        List<GroceryItem> items = getGroceryList();
        for (GroceryItem item : items) {
            if (item.getName().equalsIgnoreCase(productName)) {
                item.setPurchased(purchased);
                break;
            }
        }
        saveGroceryList(items);
    }

    public void clearAll() {
        saveGroceryList(new ArrayList<GroceryItem>());
    }

    public int getItemCount() {
        return getGroceryList().size();
    }
}
