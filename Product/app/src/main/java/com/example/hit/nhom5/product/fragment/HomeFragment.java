package com.example.hit.nhom5.product.fragment;

import android.content.Intent;
import android.os.Bundle;
import android.os.Parcelable;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.TextView;

import androidx.annotation.NonNull;
import androidx.fragment.app.Fragment;
import androidx.recyclerview.widget.LinearLayoutManager;
import androidx.recyclerview.widget.RecyclerView;

import com.example.hit.nhom5.product.R;
import com.example.hit.nhom5.product.activity.SearchActivity;
import com.example.hit.nhom5.product.activity.ShowDetailActivity;
import com.example.hit.nhom5.product.adapter.CategoryAdapter;
import com.example.hit.nhom5.product.adapter.PopularAdapter;
import com.example.hit.nhom5.product.api_interface.ApiServer;
import com.example.hit.nhom5.product.model.AllProduct;
import com.example.hit.nhom5.product.model.Category;
import com.example.hit.nhom5.product.model.Product;
import com.example.hit.nhom5.product.my_interface.CategoryItemOnClick;
import com.example.hit.nhom5.product.my_interface.PopularItemOnClick;

import java.util.ArrayList;
import java.util.Collections;
import java.util.Comparator;
import java.util.List;

import retrofit2.Call;
import retrofit2.Callback;
import retrofit2.Response;

public class HomeFragment extends Fragment {
    public HomeFragment() {
    }

    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container, Bundle savedInstanceState) {
        View view = inflater.inflate(R.layout.fragment_home, container, false);

        // Category
        RecyclerView recyclerView = view.findViewById(R.id.recycler_category);
        recyclerView.setLayoutManager(new
                LinearLayoutManager(getContext(),
                RecyclerView.HORIZONTAL,
                false));

        CategoryAdapter categoryAdapter = new CategoryAdapter(getListCategory());

        recyclerView.setAdapter(categoryAdapter);

        categoryAdapter.setOnClickCategory(new CategoryItemOnClick() {
            @Override
            public void onClickItemCategory(Category category) {
                Intent intent = new Intent(getActivity(), SearchActivity.class);
                intent.putExtra("categoryItem", (Parcelable) category);
                System.out.println("Before " + category.toString());
                startActivity(intent);
            }
        });

        // Popular
        RecyclerView recyclerViewPopular = view.findViewById(R.id.recycler_popular);
        recyclerViewPopular.setLayoutManager(new
                LinearLayoutManager(getContext(),
                RecyclerView.HORIZONTAL,
                false));


        ApiServer.apiServer.getAllProduct().enqueue(new Callback<AllProduct>() {
            @Override
            public void onResponse(@NonNull Call<AllProduct> call, @NonNull Response<AllProduct> response) {
                AllProduct allProduct = response.body();

                if (allProduct != null && response.isSuccessful()) {

                    List<Product> list = allProduct.getData();
                    Collections.sort(list, new Comparator<Product>() {
                        @Override
                        public int compare(Product o1, Product o2) {
                            return o1.getPurchases() - o2.getPurchases();
                        }
                    });

                    List<Product> list1 = new ArrayList<>();
                    for (int i = 0; i < 10; i++) list1.add(list.get(i));

                    PopularAdapter adapter = new PopularAdapter(list1, getActivity());

                    recyclerViewPopular.setAdapter(adapter);

                    adapter.setPopularItemOnClick(new PopularItemOnClick() {
                        @Override
                        public void onClickItemPopular(Product product) {
                            Intent intent = new Intent(getActivity(), ShowDetailActivity.class);
                            intent.putExtra("popularItem", (Parcelable) product);
                            startActivity(intent);
                        }
                    });
                }
            }

            @Override
            public void onFailure(Call<AllProduct> call, Throwable t) {

            }
        });

        // Search
        TextView search = view.findViewById(R.id.search);
        search.setOnClickListener(v -> {
            startActivity(new Intent(getContext(), SearchActivity.class));
        });

        return view;
    }

    public List<Category> getListCategory() {
        List<Category> list = new ArrayList<>();

        list.add(new Category(1, R.drawable.pizza_icon, "Pizza", "pizza_icon"));
        list.add(new Category(2, R.drawable.cream_icon, "Cream", "cream_icon"));
        list.add(new Category(3, R.drawable.hotdog_icon, "Hot dog", "hotdog_icon"));
        list.add(new Category(4, R.drawable.burger_icon, "Burger", "burger_icon"));

        return list;
    }
}