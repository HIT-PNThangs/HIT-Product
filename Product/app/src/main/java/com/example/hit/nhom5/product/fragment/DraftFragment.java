package com.example.hit.nhom5.product.fragment;

import android.os.Bundle;

import androidx.fragment.app.Fragment;
import androidx.recyclerview.widget.LinearLayoutManager;
import androidx.recyclerview.widget.RecyclerView;

import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;

import com.example.hit.nhom5.product.R;
import com.example.hit.nhom5.product.adapter.DraftAdapter;
import com.example.hit.nhom5.product.model.Delivered;
import com.example.hit.nhom5.product.model.Draft;

import java.util.ArrayList;
import java.util.List;


public class DraftFragment extends Fragment {

    private RecyclerView recyclerView;
    private DraftAdapter draftAdapter;

    @Override
    public void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);

    }

    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {
        // Inflate the layout for this fragment
        View view=inflater.inflate(R.layout.fragment_draft, container, false);
        recyclerView=view.findViewById(R.id.recy_draft);
        LinearLayoutManager linearLayoutManager=new LinearLayoutManager(getContext(),RecyclerView.VERTICAL,false);
        recyclerView.setLayoutManager(linearLayoutManager);
        draftAdapter=new DraftAdapter(getListDraft());
        recyclerView.setAdapter(draftAdapter);
        return view;
    }

    private List<Draft> getListDraft() {
        List<Draft> list= new ArrayList<>();
        list.add(new Draft(R.drawable.image_slider_1,"Đồ ăn","Kem trà sữa","nguyên xá","30000"));
        list.add(new Draft(R.drawable.image_slider_1,"Đồ ăn","Kem trà sữa","nguyên xá","30000"));
        list.add(new Draft(R.drawable.image_slider_1,"Đồ ăn","Kem trà sữa","nguyên xá","30000"));
        list.add(new Draft(R.drawable.image_slider_1,"Đồ ăn","Kem trà sữa","nguyên xá","30000"));
        list.add(new Draft(R.drawable.image_slider_1,"Đồ ăn","Kem trà sữa","nguyên xá","30000"));
        return list;
    }
}