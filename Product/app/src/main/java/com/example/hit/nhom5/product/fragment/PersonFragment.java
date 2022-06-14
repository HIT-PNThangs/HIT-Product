package com.example.hit.nhom5.product.fragment;

import android.content.Intent;
import android.os.Bundle;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.Toast;

import androidx.annotation.NonNull;
import androidx.annotation.Nullable;
import androidx.fragment.app.Fragment;

import com.example.hit.nhom5.product.activity.AboutCremeActivity;
import com.example.hit.nhom5.product.activity.SignInActivity;
import com.example.hit.nhom5.product.activity.SupportCentralActivity;
import com.example.hit.nhom5.product.activity.UpdateInformationActivity;
import com.example.hit.nhom5.product.activity.VoucherActivity;
import com.example.hit.nhom5.product.databinding.FragmentPersonBinding;
import com.google.firebase.auth.FirebaseAuth;

public class PersonFragment extends Fragment {
    private FragmentPersonBinding binding;

    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {
        binding = FragmentPersonBinding.inflate(getLayoutInflater());

        return binding.getRoot();
    }

    @Override
    public void onViewCreated(@NonNull View view, @Nullable Bundle savedInstanceState) {
        super.onViewCreated(view, savedInstanceState);

        setListener();
    }

    private void setListener() {
        binding.imageAvatar.setOnClickListener(v -> onClickImageAvatar());

        binding.updateInfomation.setOnClickListener(v -> startActivity(new Intent(getActivity(), UpdateInformationActivity.class)));

        binding.signUpPerson.setOnClickListener(v -> {
            FirebaseAuth.getInstance().signOut();
            Toast.makeText(getActivity(), "Sign Out Success", Toast.LENGTH_SHORT).show();
            startActivity(new Intent(getActivity(), SignInActivity.class));
        });

        binding.aboutCreme.setOnClickListener(v -> {
            startActivity(new Intent(getActivity(), AboutCremeActivity.class).
                    addFlags(Intent.FLAG_ACTIVITY_CLEAR_TASK | Intent.FLAG_ACTIVITY_CLEAR_TOP));
        });

        binding.voucher.setOnClickListener(v -> {
            startActivity(new Intent(getActivity(), VoucherActivity.class).
                    addFlags(Intent.FLAG_ACTIVITY_CLEAR_TASK | Intent.FLAG_ACTIVITY_CLEAR_TOP));
        });

        binding.support.setOnClickListener(v -> {
            startActivity(new Intent(getActivity(), SupportCentralActivity.class).
                    addFlags(Intent.FLAG_ACTIVITY_CLEAR_TASK | Intent.FLAG_ACTIVITY_CLEAR_TOP));
        });
    }

    private void onClickImageAvatar() {
    }
}