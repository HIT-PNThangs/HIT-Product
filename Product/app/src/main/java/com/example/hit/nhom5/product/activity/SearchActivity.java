package com.example.hit.nhom5.product.activity;

import android.app.ProgressDialog;
import android.content.Intent;
import android.os.Bundle;
import android.os.Parcelable;

import androidx.annotation.NonNull;
import androidx.appcompat.app.AppCompatActivity;
import androidx.recyclerview.widget.LinearLayoutManager;
import androidx.recyclerview.widget.RecyclerView;

import com.example.hit.nhom5.product.R;
import com.example.hit.nhom5.product.adapter.CategoryAdapter;
import com.example.hit.nhom5.product.adapter.ProductAdapter;
import com.example.hit.nhom5.product.api_interface.ApiServer;
import com.example.hit.nhom5.product.databinding.ActivitySearchBinding;
import com.example.hit.nhom5.product.model.AllProduct;
import com.example.hit.nhom5.product.model.Category;
import com.example.hit.nhom5.product.model.DataCategories;
import com.example.hit.nhom5.product.model.Product;
import com.example.hit.nhom5.product.my_interface.CategoryItemOnClick;
import com.example.hit.nhom5.product.my_interface.ProductItemOnClick;

import java.util.ArrayList;
import java.util.Collections;
import java.util.Comparator;
import java.util.List;

import retrofit2.Call;
import retrofit2.Callback;
import retrofit2.Response;

public class SearchActivity extends AppCompatActivity {

    ActivitySearchBinding binding;
    ProgressDialog dialog;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        binding = ActivitySearchBinding.inflate(getLayoutInflater());
        setContentView(binding.getRoot());

        init();

        setListener();
    }

    private void init() {
        dialog = new ProgressDialog(this);

        binding.recyclerviewCategory.setLayoutManager(new LinearLayoutManager(getApplicationContext(),
                RecyclerView.HORIZONTAL,
                false));

        CategoryAdapter categoryAdapter = new CategoryAdapter(getListCategory());
        binding.recyclerviewCategory.setAdapter(categoryAdapter);
        categoryAdapter.setOnClickCategory(new CategoryItemOnClick() {
            @Override
            public void onClickItemCategory(Category category) {
                Intent intent = new Intent(getApplicationContext(), SearchActivity.class);
                intent.putExtra("categoryItem", (Parcelable) category);
                startActivity(intent);
                overridePendingTransition(0, 0);
                finish();
            }
        });

        Category category = getIntent().getParcelableExtra("categoryItem");
        binding.recyclerViewSearch.setLayoutManager(new LinearLayoutManager(getApplicationContext(),
                RecyclerView.VERTICAL,
                false));

        if(category != null) {
            ApiServer.apiServer.getDataCategories(category.getId()).enqueue(new Callback<DataCategories>() {
                @Override
                public void onResponse(Call<DataCategories> call, Response<DataCategories> response) {

                    DataCategories dataCategories = response.body();

                    if(dataCategories != null && response.isSuccessful()) {
                        dialog.dismiss();

                        ProductAdapter adapter = new ProductAdapter(dataCategories.getData(), getApplicationContext());
                        binding.recyclerViewSearch.setAdapter(adapter);

                        adapter.setProductItemOnClick(new ProductItemOnClick() {
                            @Override
                            public void onClickProduct(Product product) {
                                Intent intent = new Intent(getApplicationContext(), ShowDetailActivity.class);
                                intent.putExtra("categoryItem", (Parcelable) category);
                                startActivity(intent);
                                overridePendingTransition(0, 0);
                            }
                        });
                    }
                }

                @Override
                public void onFailure(Call<DataCategories> call, Throwable t) {

                }
            });
        } else {
            dialog.show();

            ApiServer.apiServer.getAllProduct().enqueue(new Callback<AllProduct>() {
                @Override
                public void onResponse(@NonNull Call<AllProduct> call, @NonNull Response<AllProduct> response) {
                    AllProduct allProduct = response.body();

                    if(allProduct != null && response.isSuccessful()) {
                        dialog.dismiss();

                        List<Product> list = allProduct.getData();
                        Collections.sort(list, new Comparator<Product>() {
                            @Override
                            public int compare(Product o1, Product o2) {
                                return o2.getPurchases() - o1.getPurchases();
                            }
                        });

                        ProductAdapter adapter = new ProductAdapter(list, getApplicationContext());

                        binding.recyclerViewSearch.setAdapter(adapter);

                        adapter.setProductItemOnClick(new ProductItemOnClick() {
                            @Override
                            public void onClickProduct(Product product) {
                                Intent intent = new Intent(getApplicationContext(), ShowDetailActivity.class);
                                startActivity(intent);
                                overridePendingTransition(0, 0);
                            }
                        });
                    }
                }

                @Override
                public void onFailure(Call<AllProduct> call, Throwable t) {

                }
            });
        }
    }

    private void setListener() {
        binding.back.setOnClickListener(v -> {
            onBackPressed();
            finish();
        });
    }

    private List<Category> getListCategory() {
        List<Category> list = new ArrayList<>();

        list.add(new Category(1, R.drawable.pizza_icon, "Pizza", "pizza_icon"));
        list.add(new Category(2, R.drawable.cream_icon, "Cream", "cream_icon"));
        list.add(new Category(3, R.drawable.hotdog_icon, "Hot dog", "hotdog_icon"));
        list.add(new Category(4, R.drawable.burger_icon, "Burger", "burger_icon"));

        return list;
    }
}