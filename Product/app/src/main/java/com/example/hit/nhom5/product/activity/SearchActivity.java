package com.example.hit.nhom5.product.activity;

import android.app.SearchManager;
import android.content.Context;
import android.content.Intent;
import android.os.Bundle;
import android.os.Parcelable;
import android.text.Editable;
import android.text.TextWatcher;
import android.view.View;

import androidx.annotation.NonNull;
import androidx.appcompat.app.AppCompatActivity;
import androidx.appcompat.widget.SearchView;
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

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        binding = ActivitySearchBinding.inflate(getLayoutInflater());
        setContentView(binding.getRoot());

        init();

        binding.back.setOnClickListener(v -> onBackPressed());
    }

    private void init() {
        // Category
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

        if (category != null) {
            binding.progressBar2.setVisibility(View.VISIBLE);

            ApiServer.apiServer.getDataCategories(category.getId()).enqueue(new Callback<DataCategories>() {
                @Override
                public void onResponse(@NonNull Call<DataCategories> call, @NonNull Response<DataCategories> response) {

                    DataCategories dataCategories = response.body();

                    if (dataCategories != null && response.isSuccessful()) {
                        binding.progressBar2.setVisibility(View.GONE);

                        ProductAdapter adapter = new ProductAdapter(dataCategories.getData(), getApplicationContext());
                        binding.recyclerViewSearch.setAdapter(adapter);

                        binding.inputSearch.clearFocus();
                        SearchManager searchManager = (SearchManager) getSystemService(Context.SEARCH_SERVICE);
                        binding.inputSearch.setSearchableInfo(searchManager.getSearchableInfo(getComponentName()));
                        binding.inputSearch.setMaxWidth(Integer.MAX_VALUE);

                        binding.inputSearch.setOnQueryTextListener(new SearchView.OnQueryTextListener() {
                            @Override
                            public boolean onQueryTextSubmit(String query) {
                                adapter.getFilter().filter(query);
                                return true;
                            }

                            @Override
                            public boolean onQueryTextChange(String newText) {
                                adapter.getFilter().filter(newText);
                                return true;
                            }
                        });

                        adapter.setProductItemOnClick(new ProductItemOnClick() {
                            @Override
                            public void onClickProduct(Product product) {
                                Intent intent = new Intent(getApplicationContext(), ShowDetailActivity.class);
                                intent.putExtra("popularItem", (Parcelable) product);
                                startActivity(intent);
                                overridePendingTransition(0, 0);
                            }
                        });
                    }
                }

                @Override
                public void onFailure(@NonNull Call<DataCategories> call, @NonNull Throwable t) {

                }
            });
        } else {
            binding.progressBar2.setVisibility(View.VISIBLE);

            ApiServer.apiServer.getAllProduct().enqueue(new Callback<AllProduct>() {
                @Override
                public void onResponse(@NonNull Call<AllProduct> call, @NonNull Response<AllProduct> response) {
                    AllProduct allProduct = response.body();

                    if (allProduct != null && response.isSuccessful()) {
                        binding.progressBar2.setVisibility(View.GONE);

                        List<Product> list = allProduct.getData();
                        Collections.sort(list, new Comparator<Product>() {
                            @Override
                            public int compare(Product o1, Product o2) {
                                return o2.getPurchases() - o1.getPurchases();
                            }
                        });

                        ProductAdapter adapter = new ProductAdapter(list, getApplicationContext());
                        binding.recyclerViewSearch.setAdapter(adapter);

                        SearchManager searchManager = (SearchManager) getSystemService(Context.SEARCH_SERVICE);
                        binding.inputSearch.setSearchableInfo(searchManager.getSearchableInfo(getComponentName()));
                        binding.inputSearch.setMaxWidth(Integer.MAX_VALUE);

                        binding.inputSearch.setOnQueryTextListener(new SearchView.OnQueryTextListener() {
                            @Override
                            public boolean onQueryTextSubmit(String query) {
                                adapter.getFilter().filter(query);
                                return false;
                            }

                            @Override
                            public boolean onQueryTextChange(String newText) {
                                adapter.getFilter().filter(newText);
                                return false;
                            }
                        });

                        adapter.setProductItemOnClick(new ProductItemOnClick() {
                            @Override
                            public void onClickProduct(Product product) {
                                Intent intent = new Intent(getApplicationContext(), ShowDetailActivity.class);
                                intent.putExtra("popularItem", product);
                                startActivity(intent);
                                overridePendingTransition(0, 0);
                            }
                        });
                    }
                }

                @Override
                public void onFailure(@NonNull Call<AllProduct> call, @NonNull Throwable t) {
                }
            });
        }
    }

    private List<Category> getListCategory() {
        List<Category> list = new ArrayList<>();

        list.add(new Category(1, R.drawable.pizza_icon, "Pizza", "pizza_icon"));
        list.add(new Category(2, R.drawable.cream_icon, "Cream", "cream_icon"));
        list.add(new Category(3, R.drawable.hotdog_icon, "Hot dog", "hotdog_icon"));
        list.add(new Category(4, R.drawable.burger_icon, "Burger", "burger_icon"));

        return list;
    }

    @Override
    public void onBackPressed() {
        super.onBackPressed();
        overridePendingTransition(0, 0);
        finish();
    }
}