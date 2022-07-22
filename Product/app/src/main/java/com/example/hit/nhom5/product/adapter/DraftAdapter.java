package com.example.hit.nhom5.product.adapter;



import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ImageView;
import android.widget.TextView;

import androidx.annotation.NonNull;
import androidx.recyclerview.widget.RecyclerView;

import com.example.hit.nhom5.product.R;
import com.example.hit.nhom5.product.model.Draft;

import java.util.List;

public class DraftAdapter extends RecyclerView.Adapter<DraftAdapter.ViewHolder> {
    private List<Draft> drafts;

    public DraftAdapter(List<Draft> drafts) {
        this.drafts = drafts;
    }

    @NonNull
    @Override
    public ViewHolder onCreateViewHolder(@NonNull ViewGroup parent, int viewType) {
        View view= LayoutInflater.from(parent.getContext()).inflate(R.layout.item_draft,parent,false);

        return new ViewHolder(view);
    }

    @Override
    public void onBindViewHolder(@NonNull ViewHolder holder, int position) {
        Draft draft=drafts.get(position);
        if(draft==null) return;
        holder.tvTypeDraft.setText(draft.getTypeDraft());
        holder.image_Draft.setImageResource(draft.getResoureId());
        holder.tvNameDraft.setText(draft.getNameDraft());
        holder.tvAddressDraft.setText(draft.getAddressDraft());
        holder.tvPriceDraft.setText(draft.getPriceDraft());

    }

    @Override
    public int getItemCount() {
        return drafts.size();
    }

    public class ViewHolder extends RecyclerView.ViewHolder {
        ImageView image_Draft;
        TextView tvTypeDraft,tvNameDraft,tvAddressDraft,tvPriceDraft;
        public ViewHolder(@NonNull View itemView) {
            super(itemView);
            image_Draft=itemView.findViewById(R.id.img_draft);
            tvTypeDraft=itemView.findViewById(R.id.type_draft);
            tvNameDraft=itemView.findViewById(R.id.name_draft);
            tvAddressDraft=itemView.findViewById(R.id.address_draft);
            tvPriceDraft=itemView.findViewById(R.id.price_draft);
        }
    }
}
