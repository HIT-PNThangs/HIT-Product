package com.example.hit.nhom5.product.adapter;

import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;

import androidx.annotation.NonNull;
import androidx.recyclerview.widget.RecyclerView;

import com.example.hit.nhom5.product.R;
import com.example.hit.nhom5.product.model.ValidVoucher;

import java.util.List;

public class ValidVoucherAdapter extends RecyclerView.Adapter<ValidVoucherAdapter.ViewHolder> {
    List<ValidVoucher> validVouchers;

    public ValidVoucherAdapter(List<ValidVoucher> validVouchers) {
        this.validVouchers = validVouchers;
    }

    @NonNull
    @Override
    public ViewHolder onCreateViewHolder(@NonNull ViewGroup parent, int viewType) {
        View view = LayoutInflater.from(parent.getContext()).inflate(R.layout.item_valid_voucher, parent, false);
        return new ViewHolder(view);
    }

    @Override
    public void onBindViewHolder(@NonNull ViewHolder holder, int position) {
        ValidVoucher validVoucher = validVouchers.get(position);
        if (validVoucher == null)
            return;
    }

    @Override
    public int getItemCount() {
        return validVouchers.size();
    }

    public class ViewHolder extends RecyclerView.ViewHolder {

        public ViewHolder(@NonNull View itemView) {
            super(itemView);
        }
    }
}
