package com.example.hit.nhom5.product.adapter;

import androidx.annotation.NonNull;
import androidx.fragment.app.Fragment;
import androidx.fragment.app.FragmentManager;
import androidx.fragment.app.FragmentStatePagerAdapter;

import com.example.hit.nhom5.product.fragment.HomeFragment;
import com.example.hit.nhom5.product.fragment.NotificationFragment;
import com.example.hit.nhom5.product.fragment.OrderFragment;
import com.example.hit.nhom5.product.fragment.PersonFragment;

public class NavigationAdapter extends FragmentStatePagerAdapter {
    public NavigationAdapter(@NonNull FragmentManager fm, int behavior) {
        super(fm, behavior);
    }

    @NonNull
    @Override
    public Fragment getItem(int position) {
        switch (position) {
            case 1:
                return new NotificationFragment();

            case 2:
                return new OrderFragment();

            case 3:
                return new PersonFragment();

            default:
                return new HomeFragment();
        }
    }

    @Override
    public int getCount() {
        return 4;
    }
}
