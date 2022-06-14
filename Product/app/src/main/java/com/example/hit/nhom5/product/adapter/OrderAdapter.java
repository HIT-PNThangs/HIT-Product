package com.example.hit.nhom5.product.adapter;

import androidx.annotation.NonNull;
import androidx.annotation.Nullable;
import androidx.fragment.app.Fragment;
import androidx.fragment.app.FragmentActivity;
import androidx.fragment.app.FragmentManager;
import androidx.fragment.app.FragmentStatePagerAdapter;
import androidx.lifecycle.Lifecycle;
import androidx.viewpager2.adapter.FragmentStateAdapter;

import com.example.hit.nhom5.product.fragment.FirstFragment;
import com.example.hit.nhom5.product.fragment.SecondFragment;

public class OrderAdapter extends FragmentStateAdapter {

    public OrderAdapter(@NonNull FragmentActivity fragmentActivity) {
        super(fragmentActivity);
    }

    @NonNull
    @Override
    public Fragment createFragment(int position) {
        switch (position) {
            case 1:
                return new SecondFragment();

            default:
                return new FirstFragment();
        }
    }

    @Override
    public int getItemCount() {
        return 2;
    }
}
