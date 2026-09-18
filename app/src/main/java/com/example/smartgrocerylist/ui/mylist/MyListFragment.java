package com.example.smartgrocerylist.ui.mylist;

import android.app.AlertDialog;
import android.os.Bundle;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.Button;
import android.widget.TextView;

import androidx.annotation.NonNull;
import androidx.annotation.Nullable;
import androidx.fragment.app.Fragment;
import androidx.recyclerview.widget.LinearLayoutManager;
import androidx.recyclerview.widget.RecyclerView;

import com.example.smartgrocerylist.R;
import com.example.smartgrocerylist.data.PrefsManager;
import com.example.smartgrocerylist.model.GroceryItem;

import java.util.List;

public class MyListFragment extends Fragment implements GroceryListAdapter.OnItemActionListener {

    private PrefsManager prefsManager;
    private RecyclerView recyclerView;
    private TextView tvItemCount;
    private TextView tvEmptyState;

    @Nullable
    @Override
    public View onCreateView(@NonNull LayoutInflater inflater, @Nullable ViewGroup container,
                              @Nullable Bundle savedInstanceState) {
        return inflater.inflate(R.layout.fragment_my_list, container, false);
    }

    @Override
    public void onViewCreated(@NonNull View view, @Nullable Bundle savedInstanceState) {
        super.onViewCreated(view, savedInstanceState);

        prefsManager = new PrefsManager(requireContext());
        recyclerView = view.findViewById(R.id.rv_grocery_list);
        tvItemCount = view.findViewById(R.id.tv_item_count);
        tvEmptyState = view.findViewById(R.id.tv_empty_state);
        Button btnClearAll = view.findViewById(R.id.btn_clear_all);

        recyclerView.setLayoutManager(new LinearLayoutManager(getContext()));

        btnClearAll.setOnClickListener(v -> showClearAllDialog());

        refreshList();
    }

    @Override
    public void onResume() {
        super.onResume();
        // Refresh in case items were added from the Products screen.
        refreshList();
    }

    private void refreshList() {
        List<GroceryItem> items = prefsManager.getGroceryList();
        GroceryListAdapter adapter = new GroceryListAdapter(items, this);
        recyclerView.setAdapter(adapter);
        updateHeader(items.size());
    }

    private void updateHeader(int count) {
        String label = count == 1
                ? getString(R.string.item_count_singular)
                : getString(R.string.item_count_plural, count);
        tvItemCount.setText(label);

        if (count == 0) {
            tvEmptyState.setVisibility(View.VISIBLE);
            recyclerView.setVisibility(View.GONE);
        } else {
            tvEmptyState.setVisibility(View.GONE);
            recyclerView.setVisibility(View.VISIBLE);
        }
    }

    private void showClearAllDialog() {
        new AlertDialog.Builder(requireContext())
                .setTitle(R.string.clear_all_title)
                .setMessage(R.string.clear_all_message)
                .setPositiveButton(R.string.yes, (dialog, which) -> {
                    prefsManager.clearAll();
                    refreshList();
                })
                .setNegativeButton(R.string.cancel, null)
                .show();
    }

    @Override
    public void onPurchasedChanged(String productName, boolean purchased) {
        prefsManager.setPurchased(productName, purchased);
    }

    @Override
    public void onDeleteClicked(String productName) {
        prefsManager.removeItem(productName);
        refreshList();
    }
}
