package com.example.hit.nhom5.product.adapter;

import android.annotation.SuppressLint;
import android.content.Context;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.Filter;
import android.widget.ImageView;
import android.widget.TextView;

import androidx.annotation.NonNull;
import androidx.constraintlayout.widget.ConstraintLayout;
import androidx.recyclerview.widget.RecyclerView;

import com.bumptech.glide.Glide;
import com.example.hit.nhom5.product.R;
import com.example.hit.nhom5.product.model.Product;
import com.example.hit.nhom5.product.my_interface.ProductItemOnClick;

import java.text.DecimalFormat;
import java.text.NumberFormat;
import java.util.ArrayList;
import java.util.List;

public class ProductAdapter extends RecyclerView.Adapter<ProductAdapter.ProductViewHolder> {
    List<Product> list;
    Context context;
    ProductItemOnClick productItemOnClick;
    List<Product> listOld;

    public void setProductItemOnClick(ProductItemOnClick itemOnClick) {
        this.productItemOnClick = itemOnClick;
    }

    public ProductAdapter(List<Product> list, Context context) {
        this.list = list;
        this.context = context;
        this.listOld = list;
    }

    @NonNull
    @Override
    public ProductViewHolder onCreateViewHolder(@NonNull ViewGroup parent, int viewType) {
        View view = LayoutInflater.from(parent.getContext()).inflate(R.layout.product_item, parent, false);

        return new ProductViewHolder(view);
    }

    @Override
    public void onBindViewHolder(@NonNull ProductViewHolder holder, int position) {
        NumberFormat numberFormatter = new DecimalFormat("###,###,###VND");

        holder.productName.setText(list.get(position).getProductName());
        holder.productPrice.setText(numberFormatter.format(list.get(position).getRealPrice()));

        Glide.with(context).load(list.get(position).getImage()).into(holder.productImage);

        StringBuilder str = new StringBuilder();
        if(list.get(position).getPurchases() >= 1000) {
            str.append("999+ đã bán");
        } else {
            str.append(list.get(position).getPurchases().toString());
            str.append(" đã bán");
        }

        holder.productPurchases.setText(str);

        holder.layout.setOnClickListener(v -> productItemOnClick.onClickProduct(list.get(position)));
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
        ImageView productImage;
        TextView productName, productPurchases, productPrice;
        ConstraintLayout layout;

        public ProductViewHolder(@NonNull View itemView) {
            super(itemView);

            productImage = itemView.findViewById(R.id.product_image);
            productName = itemView.findViewById(R.id.product_name);
            productPurchases = itemView.findViewById(R.id.product_purchases);
            productPrice = itemView.findViewById(R.id.product_price);
            layout = itemView.findViewById(R.id.productLayout);
        }
    }

    public Filter getFilter() {
        return new Filter() {
            @Override
            protected FilterResults performFiltering(CharSequence charSequence) {
                String str = charSequence.toString();

                if(str.isEmpty()) {
                    list = listOld;
                } else {
                    List<Product> products = new ArrayList<>();

                    for(Product item : listOld) {
                        if(item.getProductName().toLowerCase().contains(str.toLowerCase())) {
                            products.add(item);
                        }
                    }

                    list = products;
                }

                FilterResults filterResults = new FilterResults();
                filterResults.values = list;
                return  filterResults;
            }

            @SuppressLint("NotifyDataSetChanged")
            @Override
            protected void publishResults(CharSequence charSequence, FilterResults filterResults) {
                list = (List<Product>) filterResults.values;
                notifyDataSetChanged();
            }
        };
    }
}
