package com.example.smartgrocerylist.ui.mylist;

import android.graphics.Paint;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.CheckBox;
import android.widget.ImageButton;
import android.widget.TextView;

import androidx.annotation.NonNull;
import androidx.recyclerview.widget.RecyclerView;

import com.example.smartgrocerylist.R;
import com.example.smartgrocerylist.model.GroceryItem;

import java.util.List;

public class GroceryListAdapter extends RecyclerView.Adapter<GroceryListAdapter.GroceryViewHolder> {

    public interface OnItemActionListener {
        void onPurchasedChanged(String productName, boolean purchased);
        void onDeleteClicked(String productName);
    }

    private final List<GroceryItem> items;
    private final OnItemActionListener listener;

    public GroceryListAdapter(List<GroceryItem> items, OnItemActionListener listener) {
        this.items = items;
        this.listener = listener;
    }

    @NonNull
    @Override
    public GroceryViewHolder onCreateViewHolder(@NonNull ViewGroup parent, int viewType) {
        View view = LayoutInflater.from(parent.getContext())
                .inflate(R.layout.item_grocery, parent, false);
        return new GroceryViewHolder(view);
    }

    @Override
    public void onBindViewHolder(@NonNull GroceryViewHolder holder, int position) {
        GroceryItem item = items.get(position);
        holder.name.setText(item.getName());

        holder.checkBox.setOnCheckedChangeListener(null);
        holder.checkBox.setChecked(item.isPurchased());
        applyPurchasedStyle(holder, item.isPurchased());

        holder.checkBox.setOnCheckedChangeListener((buttonView, isChecked) -> {
            item.setPurchased(isChecked);
            applyPurchasedStyle(holder, isChecked);
            if (listener != null) {
                listener.onPurchasedChanged(item.getName(), isChecked);
            }
        });

        holder.deleteButton.setOnClickListener(v -> {
            if (listener != null) {
                listener.onDeleteClicked(item.getName());
            }
        });
    }

    private void applyPurchasedStyle(GroceryViewHolder holder, boolean purchased) {
        if (purchased) {
            holder.name.setPaintFlags(holder.name.getPaintFlags() | Paint.STRIKE_THRU_TEXT_FLAG);
            holder.name.setAlpha(0.5f);
        } else {
            holder.name.setPaintFlags(holder.name.getPaintFlags() & (~Paint.STRIKE_THRU_TEXT_FLAG));
            holder.name.setAlpha(1f);
        }
    }

    @Override
    public int getItemCount() {
        return items.size();
    }

    static class GroceryViewHolder extends RecyclerView.ViewHolder {
        CheckBox checkBox;
        TextView name;
        ImageButton deleteButton;

        GroceryViewHolder(@NonNull View itemView) {
            super(itemView);
            checkBox = itemView.findViewById(R.id.cb_purchased);
            name = itemView.findViewById(R.id.tv_item_name);
            deleteButton = itemView.findViewById(R.id.btn_delete_item);
        }
    }
}
