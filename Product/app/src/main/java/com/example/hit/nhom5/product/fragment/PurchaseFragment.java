package com.example.hit.nhom5.product.fragment;

import android.os.Bundle;

import androidx.fragment.app.Fragment;

import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;

import com.example.hit.nhom5.product.R;
<<<<<<< HEAD:Product/app/src/main/java/com/example/hit/nhom5/product/fragment/fragment_purchase.java


public class fragment_purchase extends Fragment {
=======
>>>>>>> 38b874cad22cb82106cb8971865d9005462e493a:Product/app/src/main/java/com/example/hit/nhom5/product/fragment/PurchaseFragment.java


public class PurchaseFragment extends Fragment {

    @Override
    public void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
    }

    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {
        // Inflate the layout for this fragment
        return inflater.inflate(R.layout.fragment_purchase, container, false);
    }
}