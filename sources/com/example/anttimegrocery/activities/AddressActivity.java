package com.example.anttimegrocery.activities;

import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import androidx.appcompat.app.AppCompatActivity;
import androidx.appcompat.widget.Toolbar;
import androidx.recyclerview.widget.LinearLayoutManager;
import androidx.recyclerview.widget.RecyclerView;
import com.example.anttimegrocery.R;
import com.example.anttimegrocery.adapters.AddressAdapter;
import com.example.anttimegrocery.models.AddressModel;
import com.example.anttimegrocery.models.NewProductsModel;
import com.example.anttimegrocery.models.PopularProductsModel;
import com.example.anttimegrocery.models.ShowAllModel;
import com.google.android.gms.tasks.OnCompleteListener;
import com.google.android.gms.tasks.Task;
import com.google.firebase.auth.FirebaseAuth;
import com.google.firebase.firestore.DocumentSnapshot;
import com.google.firebase.firestore.FirebaseFirestore;
import com.google.firebase.firestore.QuerySnapshot;
import java.util.ArrayList;
import java.util.List;

public class AddressActivity extends AppCompatActivity implements AddressAdapter.SelectedAddress {
    Button addAddress;
    /* access modifiers changed from: private */
    public AddressAdapter addressAdapter;
    /* access modifiers changed from: private */
    public List<AddressModel> addressModelList;
    FirebaseAuth auth;
    FirebaseFirestore firestore;
    String mAddress = "";
    Button paymentBtn;
    RecyclerView recyclerView;
    Toolbar toolbar;

    /* access modifiers changed from: protected */
    public void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView((int) R.layout.activity_address);
        Toolbar toolbar2 = (Toolbar) findViewById(R.id.address_toolbar);
        this.toolbar = toolbar2;
        setSupportActionBar(toolbar2);
        getSupportActionBar().setDisplayHomeAsUpEnabled(true);
        this.toolbar.setNavigationOnClickListener(new View.OnClickListener() {
            public void onClick(View v) {
                AddressActivity.this.finish();
            }
        });
        final Object obj = getIntent().getSerializableExtra("item");
        this.firestore = FirebaseFirestore.getInstance();
        this.auth = FirebaseAuth.getInstance();
        this.recyclerView = (RecyclerView) findViewById(R.id.address_recycler);
        this.paymentBtn = (Button) findViewById(R.id.payment_btn);
        this.addAddress = (Button) findViewById(R.id.add_address_btn);
        this.recyclerView.setLayoutManager(new LinearLayoutManager(getApplicationContext()));
        this.addressModelList = new ArrayList();
        AddressAdapter addressAdapter2 = new AddressAdapter(getApplicationContext(), this.addressModelList, this);
        this.addressAdapter = addressAdapter2;
        this.recyclerView.setAdapter(addressAdapter2);
        this.firestore.collection("CurrentUser").document(this.auth.getCurrentUser().getUid()).collection("Address").get().addOnCompleteListener(new OnCompleteListener<QuerySnapshot>() {
            public void onComplete(Task<QuerySnapshot> task) {
                if (task.isSuccessful()) {
                    for (DocumentSnapshot doc : task.getResult().getDocuments()) {
                        AddressActivity.this.addressModelList.add((AddressModel) doc.toObject(AddressModel.class));
                        AddressActivity.this.addressAdapter.notifyDataSetChanged();
                    }
                }
            }
        });
        this.paymentBtn.setOnClickListener(new View.OnClickListener() {
            public void onClick(View v) {
                double amount = 0.0d;
                Object obj = obj;
                if (obj instanceof NewProductsModel) {
                    amount = (double) ((NewProductsModel) obj).getPrice();
                }
                Object obj2 = obj;
                if (obj2 instanceof PopularProductsModel) {
                    amount = (double) ((PopularProductsModel) obj2).getPrice();
                }
                Object obj3 = obj;
                if (obj3 instanceof ShowAllModel) {
                    amount = (double) ((ShowAllModel) obj3).getPrice();
                }
                Intent intent = new Intent(AddressActivity.this, PaymentActivity.class);
                intent.putExtra("amount", amount);
                AddressActivity.this.startActivity(intent);
            }
        });
        Button button = (Button) findViewById(R.id.add_address_btn);
        this.addAddress = button;
        button.setOnClickListener(new View.OnClickListener() {
            public void onClick(View v) {
                AddressActivity.this.startActivity(new Intent(AddressActivity.this, AddAddressActivity.class));
            }
        });
    }

    public void setAddress(String address) {
        this.mAddress = address;
    }
}
