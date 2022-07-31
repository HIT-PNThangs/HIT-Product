package com.example.hit.nhom5.product.adapter;

import android.content.Context;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ImageView;
import android.widget.TextView;

import androidx.annotation.NonNull;
import androidx.recyclerview.widget.RecyclerView;

import com.bumptech.glide.Glide;
import com.example.hit.nhom5.product.R;
import com.example.hit.nhom5.product.model.Cart;

import java.text.DecimalFormat;
import java.text.NumberFormat;
import java.util.List;

public class CartAdapter extends RecyclerView.Adapter<CartAdapter.ViewHolder> {
    private Context mContext;
    private List<Cart> mCards;

    public CartAdapter(Context context, List<Cart> list) {
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
        Cart cart = mCards.get(position);
        if (cart == null)
            return;

        NumberFormat numberFormatter = new DecimalFormat("###,###,###VND");

        holder.tvTitle.setText(cart.getProduct().getProductName());
        holder.tvPrice.setText(numberFormatter.format(
                (long) cart.getProduct().getRealPrice() * cart.getSoLuong()
        ));

        Glide.with(mContext).load(cart.getProduct().getImage()).into(holder.imageCart);

//        holder.tvAddress.setText(card.getAddress());

        holder.btnMinus.setOnClickListener(view -> {
            if (cart.getSoLuong() > 1)
                cart.setSoLuong(cart.getSoLuong() - 1);

            holder.tvNumFood.setText(String.valueOf(cart.getSoLuong()));
            holder.tvPrice.setText(numberFormatter.format(
                    (long) cart.getProduct().getRealPrice() * cart.getSoLuong()));
        });

        holder.btnPlus.setOnClickListener(view -> {
            cart.setSoLuong(cart.getSoLuong() + 1);

            holder.tvNumFood.setText(String.valueOf(cart.getSoLuong()));
            holder.tvPrice.setText(numberFormatter.format(
                    (long) cart.getProduct().getRealPrice() * cart.getSoLuong()));
        });

        holder.tvNumFood.setText(String.valueOf(cart.getSoLuong()));
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
//            tvAddress = itemView.findViewById(R.id.address_cart);
            tvPrice = itemView.findViewById(R.id.price_cart);

            tvNumFood = itemView.findViewById(R.id.numberOrder);
            btnMinus = itemView.findViewById(R.id.minusBtn);
            btnPlus = itemView.findViewById(R.id.plusBtn);
        }
    }
}