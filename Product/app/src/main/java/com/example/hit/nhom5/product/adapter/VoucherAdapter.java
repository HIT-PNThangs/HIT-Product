package com.example.hit.nhom5.product.adapter;

import androidx.annotation.NonNull;
import androidx.fragment.app.Fragment;
import androidx.fragment.app.FragmentActivity;
import androidx.viewpager2.adapter.FragmentStateAdapter;

import com.example.hit.nhom5.product.fragment.ExpireVoucherFragment;
import com.example.hit.nhom5.product.fragment.UsedVoucherFragment;
import com.example.hit.nhom5.product.fragment.ValidVoucherFragment;

public class VoucherAdapter extends FragmentStateAdapter {

    public VoucherAdapter(@NonNull FragmentActivity fragmentActivity) {
        super(fragmentActivity);
    }

    @NonNull
    @Override
    public Fragment createFragment(int position) {
        switch (position) {
            case 1:
                return new UsedVoucherFragment();

            case 2:
                return new ExpireVoucherFragment();

            default:
                return new ValidVoucherFragment();
        }
    }

    @Override
    public int getItemCount() {
        return 3;
    }
}
