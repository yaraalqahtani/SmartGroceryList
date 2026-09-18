package com.example.smartgrocerylist.ui.products;

import android.os.Bundle;
import android.view.MenuItem;

import androidx.annotation.Nullable;
import androidx.appcompat.app.AppCompatActivity;
import androidx.appcompat.widget.Toolbar;
import androidx.recyclerview.widget.LinearLayoutManager;
import androidx.recyclerview.widget.RecyclerView;

import com.example.smartgrocerylist.R;
import com.example.smartgrocerylist.data.CategoryData;
import com.example.smartgrocerylist.data.PrefsManager;
import com.example.smartgrocerylist.model.Category;

public class ProductsActivity extends AppCompatActivity {

    public static final String EXTRA_CATEGORY_NAME = "extra_category_name";

    @Override
    protected void onCreate(@Nullable Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_products);

        String categoryName = getIntent().getStringExtra(EXTRA_CATEGORY_NAME);
        Category category = CategoryData.getCategoryByName(categoryName);

        Toolbar toolbar = findViewById(R.id.toolbar_products);
        setSupportActionBar(toolbar);
        if (getSupportActionBar() != null) {
            getSupportActionBar().setDisplayHomeAsUpEnabled(true);
            getSupportActionBar().setTitle(category != null ? category.getName() : categoryName);
        }

        RecyclerView recyclerView = findViewById(R.id.rv_products);
        recyclerView.setLayoutManager(new LinearLayoutManager(this));

        PrefsManager prefsManager = new PrefsManager(this);

        if (category != null) {
            ProductAdapter adapter = new ProductAdapter(category.getProducts(), category.getName(), prefsManager);
            recyclerView.setAdapter(adapter);
        }
    }

    @Override
    public boolean onOptionsItemSelected(MenuItem item) {
        if (item.getItemId() == android.R.id.home) {
            onBackPressed();
            return true;
        }
        return super.onOptionsItemSelected(item);
    }
}
