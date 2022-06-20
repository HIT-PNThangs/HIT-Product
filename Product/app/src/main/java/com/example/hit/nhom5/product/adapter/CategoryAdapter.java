package com.example.hit.nhom5.product.adapter;

import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ImageView;
import android.widget.TextView;

import androidx.annotation.NonNull;
import androidx.cardview.widget.CardView;
import androidx.core.content.ContextCompat;
import androidx.recyclerview.widget.RecyclerView;

import com.bumptech.glide.Glide;
import com.example.hit.nhom5.product.R;
import com.example.hit.nhom5.product.model.Category;
import com.example.hit.nhom5.product.my_interface.CategoryItemOnClick;

import java.util.List;

public class CategoryAdapter extends RecyclerView.Adapter<CategoryAdapter.CategoryViewHolder> {
    private final List<Category> categories;
    private CategoryItemOnClick categoryItemOnClick;

    public CategoryAdapter(List<Category> list) {
        this.categories = list;
    }

    public void setOnClickCategory(CategoryItemOnClick itemOnClick) {
        this.categoryItemOnClick = itemOnClick;
    }

    @NonNull
    @Override
    public CategoryViewHolder onCreateViewHolder(@NonNull ViewGroup parent, int viewType) {
        View view = LayoutInflater.from(parent.getContext()).inflate(R.layout.category_item, parent, false);
        return new CategoryViewHolder(view);
    }

    @Override
    public void onBindViewHolder(@NonNull CategoryViewHolder holder, int position) {
        Category category = categories.get(position);
        if (category == null) return;

        holder.imageCate.setImageResource(category.getResourceId());
        holder.textView.setText(category.getTitle());

        String str = "";
        switch (position) {
            case 0:
                str = "pizza_icon";
                holder.itemCardView.setBackground(ContextCompat.getDrawable(holder.itemView.getContext(), R.drawable.bg_pizza));
                break;

            case 1:
                str = "cream_icon";
                holder.itemCardView.setBackground(ContextCompat.getDrawable(holder.itemView.getContext(), R.drawable.bg_cream));
                break;

            case 2:
                str = "hotdog_icon";
                holder.itemCardView.setBackground(ContextCompat.getDrawable(holder.itemView.getContext(), R.drawable.bg_hotdog));
                break;

            case 3:
                str = "burger_icon";
                holder.itemCardView.setBackground(ContextCompat.getDrawable(holder.itemView.getContext(), R.drawable.bg_burger));
                break;
        }

        int drawableResourceId = holder.itemView.getContext().
                getResources().
                getIdentifier(str, "drawable", holder.itemView.getContext().getPackageName());

        Glide.with(holder.itemView.getContext()).
                load(drawableResourceId).
                into(holder.imageCate);

        holder.itemCardView.setOnClickListener(v -> categoryItemOnClick.onClickItemCategory(category));
    }

    @Override
    public int getItemCount() {
        return categories != null ? categories.size() : 0;
    }

    public static class CategoryViewHolder extends RecyclerView.ViewHolder {
        private ImageView imageCate;
        private TextView textView;
        CardView itemCardView;

        public CategoryViewHolder(@NonNull View itemView) {
            super(itemView);

            imageCate = itemView.findViewById(R.id.img_category);
            textView = itemView.findViewById(R.id.text_category);
            itemCardView = itemView.findViewById(R.id.category_item_cardview);
        }
    }
}
