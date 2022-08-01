package com.example.hit.nhom5.product.activity;

import androidx.appcompat.app.AppCompatActivity;

import android.os.Bundle;

import com.example.hit.nhom5.product.databinding.ActivityAddressBinding;

public class AddressActivity extends AppCompatActivity {
    ActivityAddressBinding binding;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        binding = ActivityAddressBinding.inflate(getLayoutInflater());
        setContentView(binding.getRoot());

        binding.back.setOnClickListener(view -> onBackPressed());
    }

    @Override
    public void onBackPressed() {
        super.onBackPressed();
        overridePendingTransition(0, 0);
        finish();
    }
}