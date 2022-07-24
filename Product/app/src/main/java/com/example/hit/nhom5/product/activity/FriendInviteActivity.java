package com.example.hit.nhom5.product.activity;

import androidx.appcompat.app.AppCompatActivity;

import android.os.Bundle;

import com.example.hit.nhom5.product.R;
import com.example.hit.nhom5.product.databinding.ActivityFriendInviteBinding;

public class FriendInviteActivity extends AppCompatActivity {
    ActivityFriendInviteBinding binding;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        binding = ActivityFriendInviteBinding.inflate(getLayoutInflater());
        setContentView(binding.getRoot());

        binding.back.setOnClickListener(v -> onBackPressed());
    }

    @Override
    public void onBackPressed() {
        super.onBackPressed();
        overridePendingTransition(0, 0);
        finish();
    }
}