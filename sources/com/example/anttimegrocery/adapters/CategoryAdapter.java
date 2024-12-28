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
import com.example.anttimegrocery.activities.ShowAllActivity;
import com.example.anttimegrocery.models.CategoryModel;
import java.util.List;

public class CategoryAdapter extends RecyclerView.Adapter<ViewHolder> {
    /* access modifiers changed from: private */
    public Context context;
    /* access modifiers changed from: private */
    public List<CategoryModel> list;

    public CategoryAdapter(Context context2, List<CategoryModel> list2) {
        this.context = context2;
        this.list = list2;
    }

    public ViewHolder onCreateViewHolder(ViewGroup parent, int viewType) {
        return new ViewHolder(LayoutInflater.from(parent.getContext()).inflate(R.layout.category_list, parent, false));
    }

    public void onBindViewHolder(ViewHolder holder, final int position) {
        Glide.with(this.context).load(this.list.get(position).getImg_url()).into(holder.catImg);
        holder.catName.setText(this.list.get(position).getName());
        holder.itemView.setOnClickListener(new View.OnClickListener() {
            public void onClick(View v) {
                Intent intent = new Intent(CategoryAdapter.this.context, ShowAllActivity.class);
                intent.putExtra("type", ((CategoryModel) CategoryAdapter.this.list.get(position)).getType());
                CategoryAdapter.this.context.startActivity(intent);
            }
        });
    }

    public int getItemCount() {
        return this.list.size();
    }

    public static class ViewHolder extends RecyclerView.ViewHolder {
        ImageView catImg;
        TextView catName;

        public ViewHolder(View itemView) {
            super(itemView);
            this.catImg = (ImageView) itemView.findViewById(R.id.cat_img);
            this.catName = (TextView) itemView.findViewById(R.id.cat_name);
        }
    }
}
