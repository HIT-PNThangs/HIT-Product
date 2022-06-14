package com.example.hit.nhom5.product.activity;

import android.os.Bundle;

import androidx.appcompat.app.AppCompatActivity;

import com.example.hit.nhom5.product.databinding.ActivityCardBinding;
import com.example.hit.nhom5.product.model.Product;

public class CardActivity extends AppCompatActivity {

    private ActivityCardBinding binding;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        binding = ActivityCardBinding.inflate(getLayoutInflater());
        setContentView(binding.getRoot());
    }

    @Override
    public void onBackPressed() {
        super.onBackPressed();
        overridePendingTransition(0, 0);
        finish();
    }
}