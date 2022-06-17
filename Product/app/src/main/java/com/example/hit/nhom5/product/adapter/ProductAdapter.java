package com.example.hit.nhom5.product.adapter;

import android.annotation.SuppressLint;
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
import com.example.hit.nhom5.product.model.Product;
import com.example.hit.nhom5.product.my_interface.ProductItemOnClick;

import java.util.List;

public class ProductAdapter extends RecyclerView.Adapter<ProductAdapter.ProductViewHolder> {
    List<Product> list;
    Context context;
    ProductItemOnClick productItemOnClick;

    public void setProductItemOnClick(ProductItemOnClick itemOnClick) {
        this.productItemOnClick = itemOnClick;
    }

    public ProductAdapter(List<Product> list) {
        this.list = list;
    }

    public ProductAdapter(List<Product> list, Context context) {
        this.list = list;
        this.context = context;
    }

    @NonNull
    @Override
    public ProductViewHolder onCreateViewHolder(@NonNull ViewGroup parent, int viewType) {
        View view = LayoutInflater.from(parent.getContext()).inflate(R.layout.product_item, parent, false);

        return new ProductViewHolder(view);
    }

    @Override
    public void onBindViewHolder(@NonNull ProductViewHolder holder, int position) {
        holder.productName.setText(list.get(position).getProductName());
        holder.productPrice.setText(list.get(position).getPrice());

        Glide.with(context).load(list.get(position).getImage()).into(holder.productImage);

        StringBuilder str = new StringBuilder();
        if(list.get(position).getPurchases() >= 1000) {
            str.append("999+ đã bán");
        } else {
            str.append(list.get(position).getPurchases().toString());
            str.append(" đã bán");
        }

        holder.productPurchases.setText(str);

        holder.add.setOnClickListener(v -> productItemOnClick.onClickProduct(list.get(position)));
    }

    @Override
    public int getItemCount() {
        return list == null ? 0 : list.size();
    }

    @SuppressLint("NotifyDataSetChanged")
    public void filterList(List<Product> filterList) {
        list = filterList;
        notifyDataSetChanged();
    }

    public static class ProductViewHolder extends RecyclerView.ViewHolder {
        ImageView productImage, add;
        TextView productName, productPurchases, productPrice;

        public ProductViewHolder(@NonNull View itemView) {
            super(itemView);

            productImage = itemView.findViewById(R.id.product_image);
            add = itemView.findViewById(R.id.plusBtn);
            productName = itemView.findViewById(R.id.product_name);
            productPurchases = itemView.findViewById(R.id.product_purchases);
            productPrice = itemView.findViewById(R.id.product_price);
        }
    }
}
