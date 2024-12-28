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
import com.example.anttimegrocery.models.ShowAllModel;
import java.io.Serializable;
import java.util.List;

public class ShowAllAdapter extends RecyclerView.Adapter<viewHolder> {
    /* access modifiers changed from: private */
    public Context context;
    /* access modifiers changed from: private */
    public List<ShowAllModel> list;

    public ShowAllAdapter(Context context2, List<ShowAllModel> list2) {
        this.context = context2;
        this.list = list2;
    }

    public viewHolder onCreateViewHolder(ViewGroup parent, int viewType) {
        return new viewHolder(LayoutInflater.from(parent.getContext()).inflate(R.layout.show_all_item, parent, false));
    }

    public void onBindViewHolder(viewHolder holder, final int position) {
        Glide.with(this.context).load(this.list.get(position).getImg_url()).into(holder.mItemImage);
        holder.mCost.setText("$" + this.list.get(position).getPrice());
        holder.mName.setText(this.list.get(position).getName());
        holder.itemView.setOnClickListener(new View.OnClickListener() {
            public void onClick(View v) {
                Intent intent = new Intent(ShowAllAdapter.this.context, DetailedActivity.class);
                intent.putExtra("detailed", (Serializable) ShowAllAdapter.this.list.get(position));
                ShowAllAdapter.this.context.startActivity(intent);
            }
        });
    }

    public int getItemCount() {
        return this.list.size();
    }

    public class viewHolder extends RecyclerView.ViewHolder {
        /* access modifiers changed from: private */
        public TextView mCost;
        /* access modifiers changed from: private */
        public ImageView mItemImage;
        /* access modifiers changed from: private */
        public TextView mName;

        public viewHolder(View itemView) {
            super(itemView);
            this.mItemImage = (ImageView) itemView.findViewById(R.id.item_image);
            this.mCost = (TextView) itemView.findViewById(R.id.item_cost);
            this.mName = (TextView) itemView.findViewById(R.id.item_nam);
        }
    }
}
