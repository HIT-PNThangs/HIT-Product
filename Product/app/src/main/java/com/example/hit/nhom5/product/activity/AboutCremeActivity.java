package com.example.hit.nhom5.product.activity;

import androidx.appcompat.app.AppCompatActivity;

import android.os.Bundle;
import android.view.LayoutInflater;

import com.example.hit.nhom5.product.R;
import com.example.hit.nhom5.product.databinding.ActivityAboutCremeBinding;

public class AboutCremeActivity extends AppCompatActivity {

    ActivityAboutCremeBinding binding;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        binding = ActivityAboutCremeBinding.inflate(getLayoutInflater());
        setContentView(binding.getRoot());

        binding.back.setOnClickListener(v -> {
            onBackPressed();
            overridePendingTransition(0, 0);
            finish();
        });
    }
}