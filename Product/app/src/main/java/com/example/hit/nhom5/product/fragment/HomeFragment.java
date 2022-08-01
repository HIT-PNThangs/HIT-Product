package com.example.hit.nhom5.product.fragment;

import android.content.Intent;
import android.os.Bundle;
import android.util.Log;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.Toast;

import androidx.annotation.NonNull;
import androidx.fragment.app.Fragment;
import androidx.recyclerview.widget.LinearLayoutManager;
import androidx.recyclerview.widget.RecyclerView;

import com.bumptech.glide.Glide;
import com.example.hit.nhom5.product.R;
import com.example.hit.nhom5.product.activity.SearchActivity;
import com.example.hit.nhom5.product.activity.ShowDetailActivity;
import com.example.hit.nhom5.product.adapter.CategoryAdapter;
import com.example.hit.nhom5.product.adapter.PopularAdapter;
import com.example.hit.nhom5.product.api_interface.ApiServer;
import com.example.hit.nhom5.product.databinding.FragmentHomeBinding;
import com.example.hit.nhom5.product.model.AllProduct;
import com.example.hit.nhom5.product.model.Category;
import com.example.hit.nhom5.product.model.Product;
import com.example.hit.nhom5.product.model.User;
import com.google.firebase.auth.FirebaseAuth;
import com.google.firebase.database.DataSnapshot;
import com.google.firebase.database.DatabaseError;
import com.google.firebase.database.DatabaseReference;
import com.google.firebase.database.FirebaseDatabase;
import com.google.firebase.database.ValueEventListener;

import java.util.ArrayList;
import java.util.Comparator;
import java.util.List;
import java.util.Objects;

import retrofit2.Call;
import retrofit2.Callback;
import retrofit2.Response;

public class HomeFragment extends Fragment {
    public HomeFragment() {
    }

    @Override
    public View onCreateView(@NonNull LayoutInflater inflater, ViewGroup container, Bundle savedInstanceState) {
        FragmentHomeBinding binding = FragmentHomeBinding.inflate(inflater, container, false);

        FirebaseAuth auth = FirebaseAuth.getInstance();
        FirebaseDatabase database = FirebaseDatabase.getInstance();
        DatabaseReference reference = database.getReference().child("Users")
                .child(Objects.requireNonNull(auth.getUid()));

        reference.addValueEventListener(new ValueEventListener() {
            @Override
            public void onDataChange(@NonNull DataSnapshot snapshot) {
                User user = snapshot.getValue(User.class);

                if (user != null) {
                    binding.txtName.setText(user.getName());
                    if(user.getAvt() != null) {
                        Glide.with(requireContext()).load(user.getAvt()).into(binding.image);
                    }
                }
            }

            @Override
            public void onCancelled(@NonNull DatabaseError error) {
                Log.d("Home: ", error.toString());
            }
        });

        // Search
        binding.search.setOnClickListener(v -> {
            startActivity(new Intent(getContext(), SearchActivity.class));
            requireActivity().overridePendingTransition(0, 0);
        });

        // Category
        binding.recyclerCategory.setLayoutManager(new
                LinearLayoutManager(getContext(),
                RecyclerView.HORIZONTAL,
                false));

        CategoryAdapter categoryAdapter = new CategoryAdapter(getListCategory());
        binding.recyclerCategory.setAdapter(categoryAdapter);
        categoryAdapter.setOnClickCategory(category -> {
            Intent intent = new Intent(getActivity(), SearchActivity.class);
            intent.putExtra("categoryItem", category);
            startActivity(intent);
            requireActivity().overridePendingTransition(0, 0);
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
                    list.sort(Comparator.comparingInt(Product::getPurchases));

                    List<Product> list1 = new ArrayList<>();
                    for (int i = 0; i < 10; i++)
                        list1.add(list.get(i));

                    PopularAdapter adapter = new PopularAdapter(list1, getActivity().getApplicationContext());

                    binding.recyclerPopular.setAdapter(adapter);

                    adapter.setPopularItemOnClick(product -> {
                        Intent intent = new Intent(getActivity(), ShowDetailActivity.class);
                        intent.putExtra("popularItem", product);
                        startActivity(intent);
                        requireActivity().overridePendingTransition(0, 0);
                    });
                } else {
                    Toast.makeText(requireActivity().getApplicationContext(), "Popular: " +
                            response.code() + ": " + response.message(), Toast.LENGTH_LONG).show();
                    Log.d("Popular: ", String.valueOf(response.code()));
                    Log.d("Popular: ", response.message());
                }
            }

            @Override
            public void onFailure(@NonNull Call<AllProduct> call, @NonNull Throwable t) {
                Toast.makeText(requireActivity().getApplicationContext(),
                        "Popular: " + t.getMessage(), Toast.LENGTH_LONG).show();
                Log.d("Popular: ", t.getMessage());
            }
        });

//        binding.txtName.setOnClickListener(view ->
//                startActivity(new Intent(getActivity(), PersonFragment.class)
//                .addFlags(Intent.FLAG_ACTIVITY_NEW_TASK | Intent.FLAG_ACTIVITY_CLEAR_TASK))
//        );
//
//        binding.image.setOnClickListener(view ->
//                startActivity(new Intent(getActivity(), PersonFragment.class)
//                .addFlags(Intent.FLAG_ACTIVITY_NEW_TASK | Intent.FLAG_ACTIVITY_CLEAR_TASK))
//        );

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