package com.example.hit.nhom5.product.activity;

import android.content.Intent;
import android.os.Bundle;
import android.os.Handler;
import android.widget.Toast;

import androidx.appcompat.app.AppCompatActivity;

import com.example.hit.nhom5.product.R;
import com.example.hit.nhom5.product.databinding.ActivityWaitBinding;
import com.example.hit.nhom5.product.util.AppUtil;
import com.google.firebase.auth.FirebaseAuth;
import com.google.firebase.auth.FirebaseUser;

public class WaitActivity extends AppCompatActivity {
    ActivityWaitBinding binding;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        binding = ActivityWaitBinding.inflate(getLayoutInflater());
        setContentView(binding.getRoot());

        loadData();
    }

    private void loadData() {
        if(AppUtil.isNetworkAvailable(this)) {
            // Network connected
            Handler handler = new Handler();

            handler.postDelayed(new Runnable() {
                @Override
                public void run() {
                    nextActivity();
                }
            }, 1500);
        } else {
            // Network disconnect
            Toast.makeText(this, "Network disconnect", Toast.LENGTH_SHORT).show();
        }
    }

    private void nextActivity() {
        FirebaseUser user = FirebaseAuth.getInstance().getCurrentUser();

        if(user == null) {
            startActivity(new Intent(this, SignInActivity.class));
            overridePendingTransition(0, 0);
        } else {
            startActivity(new Intent(this, MainActivity.class));
            overridePendingTransition(0, 0);
        }

        finish();
    }
}