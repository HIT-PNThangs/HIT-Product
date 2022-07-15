package com.example.hit.nhom5.product.activity;

import android.annotation.SuppressLint;
import android.content.Intent;
import android.os.Bundle;

import androidx.appcompat.app.AppCompatActivity;

import com.bumptech.glide.Glide;
import com.example.hit.nhom5.product.databinding.ActivityShowDetailBinding;
import com.example.hit.nhom5.product.model.Cart;
import com.example.hit.nhom5.product.model.Product;

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
            if(numberOrder > 1) numberOrder -= 1;

            binding.numberOrderTxt.setText(numberOrder.toString());
        });

        binding.plusBtn.setOnClickListener(v -> {
            numberOrder += 1;
            binding.numberOrderTxt.setText(numberOrder.toString());
        });

        binding.orderNow.setOnClickListener(v -> {
            Cart cart = new Cart(numberOrder, product);



            finish();
            overridePendingTransition(0, 0);
        });

        binding.back.setOnClickListener(v -> onBackPressed());
    }

    @Override
    public void onBackPressed() {
        super.onBackPressed();
        overridePendingTransition(0, 0);
        finish();
    }
}