package com.example.hit.nhom5.product.fragment;

import android.os.Bundle;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;

import androidx.annotation.NonNull;
import androidx.annotation.Nullable;
import androidx.fragment.app.Fragment;

import com.example.hit.nhom5.product.adapter.OrderAdapter;
import com.example.hit.nhom5.product.databinding.FragmentOrderBinding;
import com.google.android.material.tabs.TabLayoutMediator;

public class OrderFragment extends Fragment {

    private FragmentOrderBinding binding;
    private OrderAdapter adapter;

    @Override
    public View onCreateView(@NonNull LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {
        binding = FragmentOrderBinding.inflate(getLayoutInflater());
        adapter = new OrderAdapter(getActivity());

        return binding.getRoot();
    }

    @Override
    public void onViewCreated(@NonNull View view, @Nullable Bundle savedInstanceState) {
        super.onViewCreated(view, savedInstanceState);

        binding.viewPagerOrder.setAdapter(adapter);

        new TabLayoutMediator(binding.tabLayout, binding.viewPagerOrder, (tab, position) -> {
            switch (position) {
                case 0:
                    tab.setText("First");
                    break;

                case 1:
                    tab.setText("Second");
                    break;
            }
        }).attach();
    }
}