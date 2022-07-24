package com.example.hit.nhom5.product.adapter;

import android.content.Context;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ImageView;
import android.widget.TextView;

import androidx.annotation.NonNull;
import androidx.recyclerview.widget.RecyclerView;

import com.example.hit.nhom5.product.R;
import com.example.hit.nhom5.product.model.Card;

import java.util.List;

public class CartAdapter extends RecyclerView.Adapter<CartAdapter.ViewHolder> {
    private Context mContext;
    private List<Card> mCards;

    public CartAdapter(Context context, List<Card> list) {
        this.mContext = context;
        this.mCards = list;
    }

    @NonNull
    @Override
    public ViewHolder onCreateViewHolder(@NonNull ViewGroup parent, int viewType) {
        View view = LayoutInflater.from(parent.getContext()).inflate(R.layout.cart_item, parent, false);

        return new ViewHolder(view);
    }

    @Override
    public void onBindViewHolder(@NonNull ViewHolder holder, int position) {
        Card card = mCards.get(position);
        if (card == null) return;

        holder.imageCart.setImageResource(card.getResourceId());
        holder.tvTitle.setText(card.getTitle());
        holder.tvAddress.setText(card.getAddress());
        holder.tvPrice.setText(card.getPrice());

        holder.btnMinus.setOnClickListener(view -> {
            if(card.getNumberFood() >= 1) card.setNumberFood(card.getNumberFood() - 1);

            holder.tvNumFood.setText(String.valueOf(card.getNumberFood()));
        });

        holder.btnPlus.setOnClickListener(view -> {
            card.setNumberFood(card.getNumberFood() + 1);

            holder.tvNumFood.setText(String.valueOf(card.getNumberFood()));
        });

        holder.tvNumFood.setText(String.valueOf(card.getNumberFood()));

//        holder.btnMinus.setOnClickListener(view -> {
//            if(card.getNumberFood() >= 1) card.setNumberFood(card.getNumberFood() - 1);
//
//            holder.tvNumFood.setText(String.valueOf(card.getNumberFood()));
//        });
//
//        holder.btnPlus.setOnClickListener(view -> {
//            card.setNumberFood(card.getNumberFood() + 1);
//
//            holder.tvNumFood.setText(String.valueOf(card.getNumberFood()));
//        });

//        holder.tvNumFood.setText(String.valueOf(card.getNumberFood()));
   }

    @Override
    public int getItemCount() {
        return mCards == null ? 0 : mCards.size();
    }

    public static class ViewHolder extends RecyclerView.ViewHolder {

        ImageView imageCart, btnMinus, btnPlus;
        TextView tvTitle, tvAddress, tvPrice, tvNumFood;

//        ImageView imageCart;
//        TextView tvTitle, tvAddress, tvPrice;


        public ViewHolder(@NonNull View itemView) {
            super(itemView);

            imageCart = itemView.findViewById(R.id.image_cart);
            tvTitle = itemView.findViewById(R.id.title_cart);
            tvAddress = itemView.findViewById(R.id.address_cart);
            tvPrice = itemView.findViewById(R.id.price_cart);

            tvNumFood = itemView.findViewById(R.id.numberOrder);
            btnMinus = itemView.findViewById(R.id.minusBtn);
            btnPlus = itemView.findViewById(R.id.plusBtn);

        }
    }
}