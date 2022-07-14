package com.example.hit.nhom5.product.adapter;

import android.content.Context;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ImageView;
import android.widget.TextView;

import androidx.annotation.NonNull;
import androidx.cardview.widget.CardView;
import androidx.recyclerview.widget.RecyclerView;

import com.bumptech.glide.Glide;
import com.example.hit.nhom5.product.R;
import com.example.hit.nhom5.product.model.Product;
import com.example.hit.nhom5.product.my_interface.PopularItemOnClick;

import java.util.List;

public class PopularAdapter extends RecyclerView.Adapter<PopularAdapter.PopularViewHolder> {
    List<Product> list;
    Context context;
    PopularItemOnClick popularItemOnClick;

    public PopularAdapter(List<Product> list, Context context) {
        this.list = list;
        this.context = context;
    }

    public void setPopularItemOnClick(PopularItemOnClick popularItemOnClick) {
        this.popularItemOnClick = popularItemOnClick;
    }

    @NonNull
    @Override
    public PopularViewHolder onCreateViewHolder(@NonNull ViewGroup parent, int viewType) {
        View view = LayoutInflater.from(parent.getContext()).inflate(R.layout.popular_item, parent, false);

        return new PopularViewHolder(view);
    }

    @Override
    public void onBindViewHolder(@NonNull PopularViewHolder holder, int position) {
        holder.name.setText(list.get(position).getProductName());
        Glide.with(context).load(list.get(position).getImage()).into(holder.image);
        holder.price.setText(list.get(position).getPrice());
        holder.cardView.setOnClickListener(v -> popularItemOnClick.onClickItemPopular(list.get(position)));
    }

    @Override
    public int getItemCount() {
        return list == null ? 0 : list.size();
    }

    public static class PopularViewHolder extends RecyclerView.ViewHolder {
        TextView name, price;
        ImageView image;
        CardView cardView;

        public PopularViewHolder(@NonNull View itemView) {
            super(itemView);

            name = itemView.findViewById(R.id.name_popular);
            image = itemView.findViewById(R.id.img_popular);
            price = itemView.findViewById(R.id.price_popular);
            cardView = itemView.findViewById(R.id.popular);
        }
    }
}
