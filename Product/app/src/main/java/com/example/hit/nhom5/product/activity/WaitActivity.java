package com.example.hit.nhom5.product.activity;

import android.app.Dialog;
import android.content.Intent;
import android.graphics.Color;
import android.graphics.drawable.ColorDrawable;
import android.os.Bundle;
import android.os.Handler;
import android.provider.Settings;
import android.view.Gravity;
import android.view.Window;
import android.view.WindowManager;
import android.widget.Button;
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
        if (AppUtil.isNetworkAvailable(this)) {
            // Network connected
            Handler handler = new Handler();

            handler.postDelayed(new Runnable() {
                @Override
                public void run() {
                    nextActivity();
                }
            }, 1500);
        } else {
//            Dialog dialog = new Dialog(getApplicationContext());
//
//            dialog.requestWindowFeature(Window.FEATURE_NO_TITLE);
//            dialog.setContentView(R.layout.dialog_internet_connection);
//            dialog.setCanceledOnTouchOutside(false);
//
//            Window window = dialog.getWindow();
//
//            window.setLayout(WindowManager.LayoutParams.MATCH_PARENT, WindowManager.LayoutParams.WRAP_CONTENT);
//            window.setBackgroundDrawable(new ColorDrawable(Color.TRANSPARENT));
//
//            WindowManager.LayoutParams windowAttributes = window.getAttributes();
//            windowAttributes.gravity = Gravity.CENTER;
//            window.setAttributes(windowAttributes);
//
//            dialog.show();
//
//            Button btCancel, btConnection;
//
//            btCancel = dialog.findViewById(R.id.cancel);
//            btConnection = dialog.findViewById(R.id.connection);
//
//            btCancel.setOnClickListener(v -> {
//                dialog.cancel();
//                Toast.makeText(getApplicationContext(), "Network disconnect", Toast.LENGTH_SHORT).show();
//            });
//
//            btConnection.setOnClickListener(v -> startActivity(new Intent(Settings.ACTION_WIFI_SETTINGS)));

            Toast.makeText(this, "Network disconnect", Toast.LENGTH_SHORT).show();
        }
    }

    private void nextActivity() {
        FirebaseUser user = FirebaseAuth.getInstance().getCurrentUser();

        if (user == null) {
            startActivity(new Intent(this, SignInActivity.class));
            overridePendingTransition(0, 0);
        } else {
            startActivity(new Intent(this, MainActivity.class));
            overridePendingTransition(0, 0);
        }

        finish();
    }
}