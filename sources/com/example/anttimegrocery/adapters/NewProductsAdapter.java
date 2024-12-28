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
import com.example.anttimegrocery.models.NewProductsModel;
import java.io.Serializable;
import java.util.List;

public class NewProductsAdapter extends RecyclerView.Adapter<ViewHolder> {
    /* access modifiers changed from: private */
    public Context context;
    /* access modifiers changed from: private */
    public List<NewProductsModel> list;

    public NewProductsAdapter(Context context2, List<NewProductsModel> list2) {
        this.context = context2;
        this.list = list2;
    }

    public ViewHolder onCreateViewHolder(ViewGroup parent, int viewType) {
        return new ViewHolder(LayoutInflater.from(parent.getContext()).inflate(R.layout.new_products, parent, false));
    }

    public void onBindViewHolder(ViewHolder holder, final int position) {
        Glide.with(this.context).load(this.list.get(position).getImg_url()).into(holder.newImg);
        holder.newName.setText(this.list.get(position).getName());
        holder.newPrice.setText(String.valueOf(this.list.get(position).getPrice()));
        holder.itemView.setOnClickListener(new View.OnClickListener() {
            public void onClick(View v) {
                Intent intent = new Intent(NewProductsAdapter.this.context, DetailedActivity.class);
                intent.putExtra("detailed", (Serializable) NewProductsAdapter.this.list.get(position));
                NewProductsAdapter.this.context.startActivity(intent);
            }
        });
    }

    public int getItemCount() {
        return this.list.size();
    }

    public class ViewHolder extends RecyclerView.ViewHolder {
        ImageView newImg;
        TextView newName;
        TextView newPrice;

        public ViewHolder(View itemView) {
            super(itemView);
            this.newImg = (ImageView) itemView.findViewById(R.id.new_img);
            this.newName = (TextView) itemView.findViewById(R.id.new_product_name);
            this.newPrice = (TextView) itemView.findViewById(R.id.new_price);
        }
    }
}
