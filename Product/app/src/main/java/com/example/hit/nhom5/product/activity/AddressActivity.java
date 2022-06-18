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

    }
}