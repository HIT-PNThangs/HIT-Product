package com.example.hit.nhom5.product.fragment;

import android.content.Intent;
import android.os.Bundle;
import android.os.Parcelable;
import android.util.Log;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ProgressBar;
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
import com.example.hit.nhom5.product.databinding.FragmentHomeBinding;
import com.example.hit.nhom5.product.model.AllProduct;
import com.example.hit.nhom5.product.model.Category;
import com.example.hit.nhom5.product.model.Firebase;
import com.example.hit.nhom5.product.model.Product;
import com.example.hit.nhom5.product.model.User;
import com.example.hit.nhom5.product.my_interface.CategoryItemOnClick;
import com.example.hit.nhom5.product.my_interface.PopularItemOnClick;
import com.google.firebase.auth.FirebaseAuth;
import com.google.firebase.database.DataSnapshot;
import com.google.firebase.database.DatabaseError;
import com.google.firebase.database.DatabaseReference;
import com.google.firebase.database.FirebaseDatabase;
import com.google.firebase.database.ValueEventListener;

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
        FragmentHomeBinding binding = FragmentHomeBinding.inflate(inflater, container, false);

        FirebaseAuth auth = FirebaseAuth.getInstance();
        FirebaseDatabase database = FirebaseDatabase.getInstance();
        DatabaseReference reference = database.getReference().child("Users").child(auth.getUid().toString());
        reference.addValueEventListener(new ValueEventListener() {
            @Override
            public void onDataChange(@NonNull DataSnapshot snapshot) {
                Firebase firebase = snapshot.getValue(Firebase.class);

                if(firebase != null) {
                    StringBuilder fullName = new StringBuilder();

                    fullName.append(firebase.getFirstName());
                    fullName.append(" ");
                    fullName.append(firebase.getLastName());

                    binding.txtName.setText(fullName.toString());
                }
            }

            @Override
            public void onCancelled(@NonNull DatabaseError error) {
                Log.d("Home: ", error.toString());
            }
        });

        binding.txtName.setOnClickListener(v -> startActivity(new Intent(getActivity(), PersonFragment.class)));
        binding.image.setOnClickListener(v -> startActivity(new Intent(getActivity(), PersonFragment.class)));

        // Search
        binding.search.setOnClickListener(v -> {
            startActivity(new Intent(getContext(), SearchActivity.class));
            getActivity().overridePendingTransition(0, 0);
        });

        // Category
        binding.recyclerCategory.setLayoutManager(new
                LinearLayoutManager(getContext(),
                RecyclerView.HORIZONTAL,
                false));

        CategoryAdapter categoryAdapter = new CategoryAdapter(getListCategory());
        binding.recyclerCategory.setAdapter(categoryAdapter);
        categoryAdapter.setOnClickCategory(new CategoryItemOnClick() {
            @Override
            public void onClickItemCategory(Category category) {
                Intent intent = new Intent(getActivity(), SearchActivity.class);
                intent.putExtra("categoryItem", (Parcelable) category);
                startActivity(intent);
                getActivity().overridePendingTransition(0, 0);
            }
        });

        // Popular
         binding.recyclerCategory.setLayoutManager(new
                LinearLayoutManager(getContext(),
                RecyclerView.HORIZONTAL,
                false));

        binding.progressBar.setVisibility(View.VISIBLE);
        ApiServer.apiServer.getAllProduct().enqueue(new Callback<AllProduct>() {
            @Override
            public void onResponse(@NonNull Call<AllProduct> call, @NonNull Response<AllProduct> response) {
                AllProduct allProduct = response.body();

                if (allProduct != null && response.isSuccessful()) {
                    binding.progressBar.setVisibility(View.GONE);

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

                    binding.recyclerPopular.setAdapter(adapter);

                    adapter.setPopularItemOnClick(new PopularItemOnClick() {
                        @Override
                        public void onClickItemPopular(Product product) {
                            Intent intent = new Intent(getActivity(), ShowDetailActivity.class);
                            intent.putExtra("popularItem", (Parcelable) product);
                            startActivity(intent);
                            getActivity().overridePendingTransition(0, 0);
                        }
                    });
                }
            }

            @Override
            public void onFailure(Call<AllProduct> call, Throwable t) {

            }
        });

        return binding.getRoot();
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