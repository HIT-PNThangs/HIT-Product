package com.example.hit.nhom5.product.fragment;

import android.os.Bundle;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;

import androidx.annotation.NonNull;
import androidx.annotation.Nullable;
import androidx.fragment.app.Fragment;
import androidx.recyclerview.widget.LinearLayoutManager;
import androidx.recyclerview.widget.RecyclerView;
import androidx.viewpager.widget.ViewPager;
import androidx.viewpager2.widget.ViewPager2;

import com.example.hit.nhom5.product.R;
import com.example.hit.nhom5.product.adapter.CartAdapter;
import com.example.hit.nhom5.product.adapter.DeliveredAdapter;
import com.example.hit.nhom5.product.adapter.OrderAdapter;
import com.example.hit.nhom5.product.databinding.FragmentOrderBinding;
import com.example.hit.nhom5.product.model.Delivered;
import com.google.android.material.tabs.TabLayout;
import com.google.android.material.tabs.TabLayoutMediator;

import java.util.ArrayList;
import java.util.List;

public class OrderFragment extends Fragment {
    private TabLayout tabLayout;
    private FragmentOrderBinding binding;
    private ViewPager2 viewPager2;
    private OrderAdapter orderAdapter;
    private RecyclerView recyclerView;
    private DeliveredAdapter deliveredAdapter;

    @Override
    public View onCreateView(@NonNull LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {
        binding = FragmentOrderBinding.inflate(getLayoutInflater());
        orderAdapter = new OrderAdapter(this);








        return binding.getRoot();
    }

    @Override
    public void onViewCreated(@NonNull View view, @Nullable Bundle savedInstanceState) {
        super.onViewCreated(view, savedInstanceState);
//        recyclerView=view.findViewById(R.id.recy_delivered);

//        recyclerDelivered();





        binding.viewPagerOrder.setAdapter(orderAdapter);

        new TabLayoutMediator(binding.tabLayout, binding.viewPagerOrder, (tab, position) -> {
            switch (position) {
                case 0:
                    tab.setText("Đang giao");
                    break;

                case 1:
                    tab.setText("Đã giao");
                    break;
                    case 2:
                    tab.setText("Đánh giá");
                    break;
                    case 3:
                    tab.setText("Đơn nháp");
                    break;
            }
        }).attach();

    }



}