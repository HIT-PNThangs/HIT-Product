package com.example.hit.nhom5.product.adapter;

import android.os.Bundle;

import androidx.annotation.NonNull;
import androidx.fragment.app.Fragment;
import androidx.fragment.app.FragmentActivity;
import androidx.viewpager2.adapter.FragmentStateAdapter;

import com.example.hit.nhom5.product.model.Photo;
import com.example.hit.nhom5.product.fragment.HomeFragment;

import java.util.List;

public class PhotoViewPagerAdapter extends FragmentStateAdapter {

    private List<Photo> photoList;

    public PhotoViewPagerAdapter(@NonNull FragmentActivity fragmentActivity, List<Photo> listPhoto) {
        super(fragmentActivity);
    }

    @NonNull
    @Override
    public Fragment createFragment(int position) {
        Photo photo = photoList.get(position);

        Bundle bundle = new Bundle();
        bundle.putSerializable("object_photo", photo);

        HomeFragment homeFragment = new HomeFragment();
        homeFragment.setArguments(bundle);
        return homeFragment;
    }

    @Override
    public int getItemCount() {
        return photoList == null ? 0 : photoList.size();
    }
}
