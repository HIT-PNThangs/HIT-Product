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

import com.example.hit.nhom5.product.R;
import com.example.hit.nhom5.product.adapter.DeliveredAdapter;
import com.example.hit.nhom5.product.model.Delivered;

import java.util.ArrayList;
import java.util.List;

public class DeliveredFragment extends Fragment {
    private RecyclerView recyclerView;
    private DeliveredAdapter deliveredAdapter;

    @Nullable
    @Override
    public View onCreateView(@NonNull LayoutInflater inflater, @Nullable ViewGroup container, @Nullable Bundle savedInstanceState) {
        View view = inflater.inflate(R.layout.fragment_delivered, container, false);
        recyclerView = view.findViewById(R.id.recy_delivered);
        LinearLayoutManager linearLayoutManager = new LinearLayoutManager(getContext(), LinearLayoutManager.VERTICAL, false);

        recyclerView.setLayoutManager(linearLayoutManager);
        deliveredAdapter = new DeliveredAdapter(getListDelivered());
        recyclerView.setAdapter(deliveredAdapter);

        return view;
    }

    @Override
    public void onViewCreated(@NonNull View view, @Nullable Bundle savedInstanceState) {
        super.onViewCreated(view, savedInstanceState);
    }

    private List<Delivered> getListDelivered() {
        List<Delivered> list = new ArrayList<>();

        list.add(new Delivered(R.drawable.image_slider_1, "Đồ ăn", "Kem trà sữa", "nguyên xá", "30000"));
        list.add(new Delivered(R.drawable.image_slider_1, "Đồ ăn", "Kem trà sữa", "nguyên xá", "30000"));
        list.add(new Delivered(R.drawable.image_slider_1, "Đồ ăn", "Kem trà sữa", "nguyên xá", "30000"));
        list.add(new Delivered(R.drawable.image_slider_1, "Đồ ăn", "Kem trà sữa", "nguyên xá", "30000"));
        list.add(new Delivered(R.drawable.image_slider_1, "Đồ ăn", "Kem trà sữa", "nguyên xá", "30000"));

        return list;
    }
}
