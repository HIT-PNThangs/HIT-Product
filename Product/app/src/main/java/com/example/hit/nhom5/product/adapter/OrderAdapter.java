package com.example.hit.nhom5.product.adapter;

import androidx.annotation.NonNull;
import androidx.fragment.app.Fragment;
import androidx.viewpager2.adapter.FragmentStateAdapter;

import com.example.hit.nhom5.product.fragment.EvaluateFragment;
import com.example.hit.nhom5.product.fragment.DeliveredFragment;
import com.example.hit.nhom5.product.fragment.DeliveringFragment;
import com.example.hit.nhom5.product.fragment.OrderFragment;

public class OrderAdapter extends FragmentStateAdapter {
    public OrderAdapter(@NonNull OrderFragment fragmentActivity) {
        super(fragmentActivity);
    }

    @NonNull
    @Override
    public Fragment createFragment(int position) {
        switch (position){
            case 1 :
                return new DeliveredFragment();

            case 2:
                return new EvaluateFragment();

            default:
                return new DeliveringFragment();
        }
    }

    @Override
    public int getItemCount() {
        return 3;
    }
}
