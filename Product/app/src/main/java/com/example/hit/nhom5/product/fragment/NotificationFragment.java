package com.example.hit.nhom5.product.fragment;

import android.os.Bundle;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;

import androidx.fragment.app.Fragment;

import com.example.hit.nhom5.product.R;

public class NotificationFragment extends Fragment {
//    private FragmentNotificationBinding binding;


    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container, Bundle savedInstanceState) {
        // Inflate the layout for this fragment
//        binding = FragmentNotificationBinding.inflate(getLayoutInflater());
        View view = inflater.inflate(R.layout.fragment_notification, container, false);


        return view;
    }


}