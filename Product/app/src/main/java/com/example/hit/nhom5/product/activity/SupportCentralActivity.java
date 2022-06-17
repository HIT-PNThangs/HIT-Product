package com.example.hit.nhom5.product.activity;

import androidx.appcompat.app.AppCompatActivity;

import android.os.Bundle;

import com.example.hit.nhom5.product.R;
import com.example.hit.nhom5.product.databinding.ActivitySupportCentralBinding;

public class SupportCentralActivity extends AppCompatActivity {

    ActivitySupportCentralBinding binding;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        binding = ActivitySupportCentralBinding.inflate(getLayoutInflater());
        setContentView(binding.getRoot());

        setListener();
    }

    private void setListener() {
        binding.back.setOnClickListener(v -> {
            onBackPressed();
            overridePendingTransition(0, 0);
            finish();
        });
    }

    @Override
    public void onBackPressed() {
        super.onBackPressed();
        overridePendingTransition(0, 0);
        finish();
    }
}