package com.example.hit.nhom5.product.activity;

import androidx.appcompat.app.AppCompatActivity;

import android.os.Bundle;

import com.example.hit.nhom5.product.R;
import com.example.hit.nhom5.product.databinding.ActivitySettingBinding;

public class SettingActivity extends AppCompatActivity {
    ActivitySettingBinding binding;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        binding = ActivitySettingBinding.inflate(getLayoutInflater());
        setContentView(binding.getRoot());
    }

    @Override
    public void onBackPressed() {
        super.onBackPressed();
        overridePendingTransition(0, 0);
        finish();
    }
}