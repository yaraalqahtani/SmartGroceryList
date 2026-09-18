package com.example.smartgrocerylist.ui.home;

import android.content.Intent;
import android.os.Bundle;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;

import androidx.annotation.NonNull;
import androidx.annotation.Nullable;
import androidx.fragment.app.Fragment;
import androidx.recyclerview.widget.GridLayoutManager;
import androidx.recyclerview.widget.RecyclerView;

import com.example.smartgrocerylist.R;
import com.example.smartgrocerylist.data.CategoryData;
import com.example.smartgrocerylist.model.Category;
import com.example.smartgrocerylist.ui.products.ProductsActivity;

public class HomeFragment extends Fragment {

    @Nullable
    @Override
    public View onCreateView(@NonNull LayoutInflater inflater, @Nullable ViewGroup container,
                              @Nullable Bundle savedInstanceState) {
        return inflater.inflate(R.layout.fragment_home, container, false);
    }

    @Override
    public void onViewCreated(@NonNull View view, @Nullable Bundle savedInstanceState) {
        super.onViewCreated(view, savedInstanceState);

        RecyclerView recyclerView = view.findViewById(R.id.rv_categories);
        recyclerView.setLayoutManager(new GridLayoutManager(getContext(), 2));
        recyclerView.setHasFixedSize(true);

        CategoryAdapter adapter = new CategoryAdapter(CategoryData.getCategories(), this::openCategory);
        recyclerView.setAdapter(adapter);
    }

    private void openCategory(Category category) {
        if (getActivity() == null) {
            return;
        }
        Intent intent = new Intent(getActivity(), ProductsActivity.class);
        intent.putExtra(ProductsActivity.EXTRA_CATEGORY_NAME, category.getName());
        startActivity(intent);
    }
}
