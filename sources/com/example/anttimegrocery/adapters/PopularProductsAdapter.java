package com.example.anttimegrocery.adapters;

import android.content.Context;
import android.content.Intent;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ImageView;
import android.widget.TextView;
import androidx.recyclerview.widget.RecyclerView;
import com.bumptech.glide.Glide;
import com.example.anttimegrocery.R;
import com.example.anttimegrocery.activities.DetailedActivity;
import com.example.anttimegrocery.models.PopularProductsModel;
import java.io.Serializable;
import java.util.List;

public class PopularProductsAdapter extends RecyclerView.Adapter<ViewHolder> {
    /* access modifiers changed from: private */
    public Context context;
    /* access modifiers changed from: private */
    public List<PopularProductsModel> popularProductsModelList;

    public PopularProductsAdapter(Context context2, List<PopularProductsModel> popularProductsModelList2) {
        this.context = context2;
        this.popularProductsModelList = popularProductsModelList2;
    }

    public ViewHolder onCreateViewHolder(ViewGroup parent, int viewType) {
        return new ViewHolder(LayoutInflater.from(parent.getContext()).inflate(R.layout.popular_items, parent, false));
    }

    public void onBindViewHolder(ViewHolder holder, final int position) {
        Glide.with(this.context).load(this.popularProductsModelList.get(position).getImg_url()).into(holder.imageView);
        holder.name.setText(this.popularProductsModelList.get(position).getName());
        holder.price.setText(String.valueOf(this.popularProductsModelList.get(position).getPrice()));
        holder.itemView.setOnClickListener(new View.OnClickListener() {
            public void onClick(View v) {
                Intent intent = new Intent(PopularProductsAdapter.this.context, DetailedActivity.class);
                intent.putExtra("detailed", (Serializable) PopularProductsAdapter.this.popularProductsModelList.get(position));
                PopularProductsAdapter.this.context.startActivity(intent);
            }
        });
    }

    public int getItemCount() {
        return this.popularProductsModelList.size();
    }

    public class ViewHolder extends RecyclerView.ViewHolder {
        ImageView imageView;
        TextView name;
        TextView price;

        public ViewHolder(View itemView) {
            super(itemView);
            this.imageView = (ImageView) itemView.findViewById(R.id.all_img);
            this.name = (TextView) itemView.findViewById(R.id.all_product_name);
            this.price = (TextView) itemView.findViewById(R.id.all_price);
        }
    }
}
