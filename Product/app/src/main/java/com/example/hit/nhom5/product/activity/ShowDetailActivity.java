package com.example.hit.nhom5.product.activity;

import android.annotation.SuppressLint;
import android.content.Intent;
import android.os.Bundle;
import android.util.Log;
import android.widget.Toast;

import androidx.annotation.NonNull;
import androidx.annotation.Nullable;
import androidx.appcompat.app.AppCompatActivity;

import com.bumptech.glide.Glide;
import com.example.hit.nhom5.product.databinding.ActivityShowDetailBinding;
import com.example.hit.nhom5.product.model.Cart;
import com.example.hit.nhom5.product.model.Product;
import com.example.hit.nhom5.product.model.ShoppingSession;
import com.example.hit.nhom5.product.model.User;
import com.google.android.gms.tasks.OnCompleteListener;
import com.google.android.gms.tasks.OnFailureListener;
import com.google.android.gms.tasks.OnSuccessListener;
import com.google.android.gms.tasks.Task;
import com.google.firebase.auth.FirebaseAuth;
import com.google.firebase.database.DataSnapshot;
import com.google.firebase.database.DatabaseError;
import com.google.firebase.database.DatabaseReference;
import com.google.firebase.database.FirebaseDatabase;
import com.google.firebase.database.ValueEventListener;

import java.util.ArrayList;
import java.util.List;
import java.util.Objects;

public class ShowDetailActivity extends AppCompatActivity {
    ActivityShowDetailBinding binding;
    Integer numberOrder = 1;
    Product product;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        binding = ActivityShowDetailBinding.inflate(getLayoutInflater());
        setContentView(binding.getRoot());

        init();

        setListener();
    }

    @SuppressLint("SetTextI18n")
    private void init() {
        product = getIntent().getParcelableExtra("popularItem");

        binding.name.setText(product.getProductName());
        binding.price.setText(product.getPrice());
        binding.purchases.setText("Đã bán: " + product.getPurchases().toString());
        Glide.with(getApplicationContext()).load(product.getImage()).into(binding.pictureFood);
    }

    @SuppressLint("SetTextI18n")
    private void setListener() {
        binding.minusBtn.setOnClickListener(v -> {
            if (numberOrder > 1)
                numberOrder -= 1;

            binding.numberOrderTxt.setText(numberOrder.toString());
        });

        binding.plusBtn.setOnClickListener(v -> {
            numberOrder += 1;
            binding.numberOrderTxt.setText(numberOrder.toString());
        });

        binding.back.setOnClickListener(v -> onBackPressed());

        binding.orderNow.setOnClickListener(v -> {
            FirebaseAuth auth = FirebaseAuth.getInstance();
            FirebaseDatabase database = FirebaseDatabase.getInstance();
            DatabaseReference reference =
                    database.getReference()
                            .child("Users")
                            .child(Objects.requireNonNull(auth.getUid()));

            reference.get().addOnCompleteListener(task -> {
                if (task.isSuccessful()) {
                    User user = task.getResult().getValue(User.class);

                    Cart cart = new Cart(numberOrder, product);

                    if (user != null) {
                        List<Cart> carts = user.getCarts();

                        if (carts == null)
                            carts = new ArrayList<>();

                        carts.add(cart);
                        user.setCarts(carts);

                        reference.setValue(user).addOnSuccessListener(unused -> {
                            Toast.makeText(getApplicationContext(),
                                    "Thêm vào giỏ hàng thành công", Toast.LENGTH_LONG).show();

                            finish();
                            overridePendingTransition(0, 0);
                        });
                    } else {
                        Log.e("firebase", "Error getting data", task.getException());
                    }
                }
            });
        });
    }

    @Override
    public void onBackPressed() {
        super.onBackPressed();
        overridePendingTransition(0, 0);
        finish();
    }
}