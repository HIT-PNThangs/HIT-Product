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
import com.example.hit.nhom5.product.model.Delivered;

import java.util.List;

public class DeliveredAdapter extends RecyclerView.Adapter<DeliveredAdapter.ViewHolder> {
    private Context context;
    private List<Delivered> delivereds;

    public DeliveredAdapter( List<Delivered> list) {
//        this.context = context;
        this.delivereds = list;
    }

    @NonNull
    @Override
    public ViewHolder onCreateViewHolder(@NonNull ViewGroup parent, int viewType) {
        View view= LayoutInflater.from(parent.getContext()).inflate(R.layout.item_delivered,parent,false);
        return new ViewHolder(view);
    }

    @Override
    public void onBindViewHolder(@NonNull ViewHolder holder, int position) {
        Delivered delivered=delivereds.get(position);
        if(delivered==null) return;

        holder.imageView.setImageResource(delivered.getResoureId());
        holder.tvType.setText(delivered.getTypeFood());
        holder.tvName.setText(delivered.getName());
        holder.tvAddress.setText(delivered.getAddress());
        holder.tvPrice.setText(delivered.getPrice());
    }

    @Override
    public int getItemCount() {
        return delivereds.size();
    }

    public class ViewHolder extends RecyclerView.ViewHolder {
        TextView tvType,tvName,tvAddress,tvPrice;
        ImageView imageView;
        public ViewHolder(@NonNull View itemView) {
            super(itemView);
            imageView=(ImageView) itemView.findViewById(R.id.image_delivered);
            tvType=itemView.findViewById(R.id.type);
            tvName=itemView.findViewById(R.id.name);
            tvAddress=itemView.findViewById(R.id.address_delivered);
            tvPrice=itemView.findViewById(R.id.price_delivered);
        }
    }
}
