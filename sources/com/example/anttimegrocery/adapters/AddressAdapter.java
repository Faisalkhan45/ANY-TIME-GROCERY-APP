package com.example.anttimegrocery.adapters;

import android.content.Context;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.RadioButton;
import android.widget.TextView;
import androidx.recyclerview.widget.RecyclerView;
import com.example.anttimegrocery.R;
import com.example.anttimegrocery.models.AddressModel;
import java.util.List;

public class AddressAdapter extends RecyclerView.Adapter<ViewHolder> {
    List<AddressModel> addressModelList;
    Context context;
    SelectedAddress selectedAddress;
    /* access modifiers changed from: private */
    public RadioButton selectedRadioBtn;

    public interface SelectedAddress {
        void setAddress(String str);
    }

    public AddressAdapter(Context context2, List<AddressModel> addressModelList2, SelectedAddress selectedAddress2) {
        this.context = context2;
        this.addressModelList = addressModelList2;
        this.selectedAddress = selectedAddress2;
    }

    public ViewHolder onCreateViewHolder(ViewGroup parent, int viewType) {
        return new ViewHolder(LayoutInflater.from(parent.getContext()).inflate(R.layout.address_item, parent, false));
    }

    public void onBindViewHolder(ViewHolder holder, final int position) {
        holder.address.setText(this.addressModelList.get(position).getUserAddress());
        holder.radioButton.setOnClickListener(new View.OnClickListener() {
            public void onClick(View v) {
                for (AddressModel address : AddressAdapter.this.addressModelList) {
                    address.setSelected(false);
                }
                AddressAdapter.this.addressModelList.get(position).setSelected(true);
                if (AddressAdapter.this.selectedRadioBtn != null) {
                    AddressAdapter.this.selectedRadioBtn.setChecked(false);
                }
                RadioButton unused = AddressAdapter.this.selectedRadioBtn = (RadioButton) v;
                AddressAdapter.this.selectedRadioBtn.setChecked(true);
                AddressAdapter.this.selectedAddress.setAddress(AddressAdapter.this.addressModelList.get(position).getUserAddress());
            }
        });
    }

    public int getItemCount() {
        return this.addressModelList.size();
    }

    public class ViewHolder extends RecyclerView.ViewHolder {
        TextView address;
        RadioButton radioButton;

        public ViewHolder(View itemView) {
            super(itemView);
            this.address = (TextView) itemView.findViewById(R.id.address_add);
            this.radioButton = (RadioButton) itemView.findViewById(R.id.select_address);
        }
    }
}
