package com.example.hit.nhom5.product.activity;

import android.os.Bundle;
import android.widget.ScrollView;
import android.widget.TextView;

import androidx.appcompat.app.AppCompatActivity;
import androidx.recyclerview.widget.LinearLayoutManager;
import androidx.recyclerview.widget.RecyclerView;

import com.example.hit.nhom5.product.R;
import com.example.hit.nhom5.product.adapter.CartAdapter;
import com.example.hit.nhom5.product.model.Card;

import java.util.ArrayList;
import java.util.List;

public class CardActivity extends AppCompatActivity {

//     ActivityCardBinding binding;
     RecyclerView recyclerView;
     ScrollView scrollView;
     CartAdapter cartAdapter;
     ArrayList<Card> cards;
     TextView btnbuy;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
//        binding = ActivityCardBinding.inflate(getLayoutInflater());




        setContentView(R.layout.activity_card);


        initview();
        recyclerCart();






    }

    private void recyclerCart(){
        LinearLayoutManager linearLayoutManager=new LinearLayoutManager(this,LinearLayoutManager.VERTICAL,false);
        recyclerView.setLayoutManager(linearLayoutManager);
        cartAdapter=new CartAdapter(this,getListCart());
        recyclerView.setAdapter(cartAdapter);
    }

    private List<Card> getListCart() {
        List<Card> list= new ArrayList<>();
        list.add(new Card(R.drawable.image_slider_1,"kem","hà nội","10000",1));
        list.add(new Card(R.drawable.image_slider_1,"kem","hà nội","10000",1));
        list.add(new Card(R.drawable.image_slider_1,"kem","hà nội","10000",1));
        list.add(new Card(R.drawable.image_slider_1,"kem","hà nội","10000",1));
        list.add(new Card(R.drawable.image_slider_1,"kem","hà nội","10000",1));
        list.add(new Card(R.drawable.image_slider_1,"kem","hà nội","10000",1));
        return list;
    }

    @Override
    public void onBackPressed() {
        super.onBackPressed();
        overridePendingTransition(0, 0);
        finish();
    }

    private void initview(){
        recyclerView=findViewById(R.id.recy_cart);
        scrollView=findViewById(R.id.scrollcart);
        btnbuy=findViewById(R.id.btnBuy);
    }


}