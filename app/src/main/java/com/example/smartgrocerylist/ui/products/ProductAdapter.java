package com.example.smartgrocerylist.ui.products;

import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.Button;
import android.widget.TextView;

import androidx.annotation.NonNull;
import androidx.recyclerview.widget.RecyclerView;

import com.example.smartgrocerylist.R;
import com.example.smartgrocerylist.data.PrefsManager;

import java.util.List;

public class ProductAdapter extends RecyclerView.Adapter<ProductAdapter.ProductViewHolder> {

    private final List<String> products;
    private final String categoryName;
    private final PrefsManager prefsManager;

    public ProductAdapter(List<String> products, String categoryName, PrefsManager prefsManager) {
        this.products = products;
        this.categoryName = categoryName;
        this.prefsManager = prefsManager;
    }

    @NonNull
    @Override
    public ProductViewHolder onCreateViewHolder(@NonNull ViewGroup parent, int viewType) {
        View view = LayoutInflater.from(parent.getContext())
                .inflate(R.layout.item_product, parent, false);
        return new ProductViewHolder(view);
    }

    @Override
    public void onBindViewHolder(@NonNull ProductViewHolder holder, int position) {
        String productName = products.get(position);
        holder.name.setText(productName);
        bindAddButton(holder, productName);

        holder.addButton.setOnClickListener(v -> {
            if (!prefsManager.isProductAdded(productName)) {
                prefsManager.addItem(productName, categoryName);
                bindAddButton(holder, productName);
            }
        });
    }

    private void bindAddButton(ProductViewHolder holder, String productName) {
        boolean added = prefsManager.isProductAdded(productName);
        if (added) {
            holder.addButton.setText(R.string.added_label);
            holder.addButton.setBackgroundResource(R.drawable.bg_button_added);
            holder.addButton.setEnabled(false);
        } else {
            holder.addButton.setText(R.string.add_label);
            holder.addButton.setBackgroundResource(R.drawable.bg_button_primary);
            holder.addButton.setEnabled(true);
        }
    }

    @Override
    public int getItemCount() {
        return products.size();
    }

    static class ProductViewHolder extends RecyclerView.ViewHolder {
        TextView name;
        Button addButton;

        ProductViewHolder(@NonNull View itemView) {
            super(itemView);
            name = itemView.findViewById(R.id.tv_product_name);
            addButton = itemView.findViewById(R.id.btn_add_product);
        }
    }
}
