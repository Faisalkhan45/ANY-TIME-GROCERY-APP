package com.example.anttimegrocery.adapters;

import android.content.Context;
import android.content.Intent;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.TextView;
import androidx.localbroadcastmanager.content.LocalBroadcastManager;
import androidx.recyclerview.widget.RecyclerView;
import com.example.anttimegrocery.R;
import com.example.anttimegrocery.models.MyCartModel;
import java.util.List;

public class MyCartAdapter extends RecyclerView.Adapter<ViewHolder> {
    Context context;
    List<MyCartModel> list;
    int totalAmount = 0;

    public MyCartAdapter(Context context2, List<MyCartModel> list2) {
        this.context = context2;
        this.list = list2;
    }

    public ViewHolder onCreateViewHolder(ViewGroup parent, int viewType) {
        return new ViewHolder(LayoutInflater.from(parent.getContext()).inflate(R.layout.my_cart_item, parent, false));
    }

    public void onBindViewHolder(ViewHolder holder, int position) {
        holder.date.setText(this.list.get(position).getCurrentDate());
        holder.time.setText(this.list.get(position).getCurrentTime());
        holder.price.setText(this.list.get(position).getProductPrice() + "$");
        holder.name.setText(this.list.get(position).getProductName());
        holder.totalPrice.setText(String.valueOf(this.list.get(position).getTotalPrice()));
        holder.totalQuantity.setText(this.list.get(position).getTotalQuantity());
        this.totalAmount += this.list.get(position).getTotalPrice();
        Intent intent = new Intent("MyTotalAmount");
        intent.putExtra("totalAmount", this.totalAmount);
        LocalBroadcastManager.getInstance(this.context).sendBroadcast(intent);
    }

    public int getItemCount() {
        return this.list.size();
    }

    public class ViewHolder extends RecyclerView.ViewHolder {
        TextView date;
        TextView name;
        TextView price;
        TextView time;
        TextView totalPrice;
        TextView totalQuantity;

        public ViewHolder(View itemView) {
            super(itemView);
            this.name = (TextView) itemView.findViewById(R.id.product_name);
            this.price = (TextView) itemView.findViewById(R.id.product_price);
            this.date = (TextView) itemView.findViewById(R.id.current_date);
            this.time = (TextView) itemView.findViewById(R.id.current_time);
            this.totalQuantity = (TextView) itemView.findViewById(R.id.total_quantity);
            this.totalPrice = (TextView) itemView.findViewById(R.id.total_price);
        }
    }
}
