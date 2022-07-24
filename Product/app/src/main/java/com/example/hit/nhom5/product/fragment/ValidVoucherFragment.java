package com.example.hit.nhom5.product.fragment;

import android.os.Bundle;

import androidx.fragment.app.Fragment;
import androidx.recyclerview.widget.LinearLayoutManager;
import androidx.recyclerview.widget.RecyclerView;

import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;

import com.example.hit.nhom5.product.R;
import com.example.hit.nhom5.product.adapter.ValidVoucherAdapter;
import com.example.hit.nhom5.product.model.ValidVoucher;

import java.util.ArrayList;
import java.util.List;

public class ValidVoucherFragment extends Fragment {
    private RecyclerView recyclerView;
    private ValidVoucherAdapter validVoucherAdapter;

    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {

        View view = inflater.inflate(R.layout.fragment_valid_voucher, container, false);
        recyclerView = view.findViewById(R.id.recy_valid);
        LinearLayoutManager linearLayoutManager = new LinearLayoutManager(getContext(), RecyclerView.VERTICAL, false);
        recyclerView.setLayoutManager(linearLayoutManager);
        validVoucherAdapter = new ValidVoucherAdapter(gegListValidvoucher());
        recyclerView.setAdapter(validVoucherAdapter);
        return view;
    }

    private List<ValidVoucher> gegListValidvoucher() {
        List<ValidVoucher> list = new ArrayList<>();

        list.add(new ValidVoucher());
        list.add(new ValidVoucher());
        list.add(new ValidVoucher());

        return list;
    }
}