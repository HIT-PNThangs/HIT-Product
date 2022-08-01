package com.example.hit.nhom5.product.activity;

import android.annotation.SuppressLint;
import android.content.Intent;
import android.os.Bundle;
import android.view.MenuItem;
import android.widget.Toast;

import androidx.annotation.NonNull;
import androidx.appcompat.app.AppCompatActivity;
import androidx.fragment.app.FragmentStatePagerAdapter;
import androidx.viewpager.widget.ViewPager;

import com.example.hit.nhom5.product.R;
import com.example.hit.nhom5.product.adapter.NavigationAdapter;
import com.example.hit.nhom5.product.databinding.ActivityMainBinding;
import com.google.android.material.bottomnavigation.BottomNavigationView;

public class MainActivity extends AppCompatActivity {

    private ActivityMainBinding binding;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        binding = ActivityMainBinding.inflate(getLayoutInflater());
        setContentView(binding.getRoot());

        init();

        setListener();
    }

    private void init() {
        binding.navigation.setBackground(null);

        NavigationAdapter adapter = new NavigationAdapter(getSupportFragmentManager(),
                FragmentStatePagerAdapter.BEHAVIOR_RESUME_ONLY_CURRENT_FRAGMENT);
        binding.viewPager.setAdapter(adapter);

        binding.viewPager.addOnPageChangeListener(new ViewPager.OnPageChangeListener() {
            @Override
            public void onPageScrolled(int position, float positionOffset, int positionOffsetPixels) {

            }

            @Override
            public void onPageSelected(int position) {
                switch (position) {
                    case 0:
                        binding.navigation.getMenu().findItem(R.id.home).setChecked(true);
                        break;

                    case 1:
                        binding.navigation.getMenu().findItem(R.id.notification).setChecked(true);
                        break;

                    case 2:
                        binding.navigation.getMenu().findItem(R.id.order).setChecked(true);
                        break;

                    case 3:
                        binding.navigation.getMenu().findItem(R.id.person).setChecked(true);
                        break;
                }
            }

            @Override
            public void onPageScrollStateChanged(int state) {

            }
        });

        binding.navigation.setOnNavigationItemSelectedListener(new BottomNavigationView.OnNavigationItemSelectedListener() {
            @SuppressLint("NonConstantResourceId")
            @Override
            public boolean onNavigationItemSelected(@NonNull MenuItem item) {
                switch (item.getItemId()) {
                    case R.id.home:
                        binding.viewPager.setCurrentItem(0);
                        break;

                    case R.id.notification:
                        binding.viewPager.setCurrentItem(1);
                        break;

                    case R.id.order:
                        binding.viewPager.setCurrentItem(2);
                        break;

                    case R.id.person:
                        binding.viewPager.setCurrentItem(3);
                        break;
                }

                return true;
            }
        });
    }

    private void setListener() {
        binding.btCard.setOnClickListener(v -> {
            startActivity(new Intent(MainActivity.this, CardActivity.class));
            overridePendingTransition(0, 0);
        });
    }

    private Toast mToast;
    private long backPressedTime;

    @Override
    public void onBackPressed() {
        if (backPressedTime + 2000 > System.currentTimeMillis()) {
            mToast.cancel();
            super.onBackPressed();
            return;
        } else {
            mToast = Toast.makeText(getApplicationContext(), "Press back again to exit the application", Toast.LENGTH_LONG);
            mToast.show();
        }

        backPressedTime = System.currentTimeMillis();
    }
}