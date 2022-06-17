package com.example.hit.nhom5.product.activity;

import androidx.appcompat.app.AppCompatActivity;
import androidx.fragment.app.FragmentStatePagerAdapter;

import android.os.Bundle;

import com.example.hit.nhom5.product.R;
import com.example.hit.nhom5.product.adapter.VoucherAdapter;
import com.example.hit.nhom5.product.databinding.ActivityVoucherBinding;
import com.google.android.material.tabs.TabLayoutMediator;

public class VoucherActivity extends AppCompatActivity {

    ActivityVoucherBinding binding;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        binding = ActivityVoucherBinding.inflate(getLayoutInflater());
        setContentView(binding.getRoot());

        VoucherAdapter adapter = new VoucherAdapter(this);
        binding.viewPagerVoucher.setAdapter(adapter);

        new TabLayoutMediator(binding.tabLayout, binding.viewPagerVoucher, (tab, position) -> {
            switch (position) {
                case 0:
                    tab.setText("Có hiệu lực");
                    break;

                case 1:
                    tab.setText("Đã sử dụng");
                    break;

                case 2:
                    tab.setText("Hết hiệu lực");
                    break;
            }
        }).attach();

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