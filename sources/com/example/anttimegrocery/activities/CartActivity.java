package com.example.anttimegrocery.activities;

import android.content.BroadcastReceiver;
import android.content.Context;
import android.content.Intent;
import android.content.IntentFilter;
import android.os.Bundle;
import android.view.View;
import android.widget.TextView;
import androidx.appcompat.app.AppCompatActivity;
import androidx.appcompat.widget.Toolbar;
import androidx.localbroadcastmanager.content.LocalBroadcastManager;
import androidx.recyclerview.widget.LinearLayoutManager;
import androidx.recyclerview.widget.RecyclerView;
import com.example.anttimegrocery.R;
import com.example.anttimegrocery.adapters.MyCartAdapter;
import com.example.anttimegrocery.models.MyCartModel;
import com.google.android.gms.tasks.OnCompleteListener;
import com.google.android.gms.tasks.Task;
import com.google.firebase.auth.FirebaseAuth;
import com.google.firebase.firestore.DocumentSnapshot;
import com.google.firebase.firestore.FirebaseFirestore;
import com.google.firebase.firestore.QuerySnapshot;
import java.util.ArrayList;
import java.util.List;

public class CartActivity extends AppCompatActivity {
    private FirebaseAuth auth;
    MyCartAdapter cartAdapter;
    List<MyCartModel> cartModelList;
    private FirebaseFirestore firestore;
    public BroadcastReceiver mMessageReceiver = new BroadcastReceiver() {
        public void onReceive(Context context, Intent intent) {
            CartActivity.this.overAllAmount.setText("Total Amount : " + intent.getIntExtra("totalAmount", 0) + "$");
        }
    };
    TextView overAllAmount;
    int overAllTotalAmount;
    RecyclerView recyclerView;
    Toolbar toolbar;

    /* access modifiers changed from: protected */
    public void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView((int) R.layout.activity_cart);
        this.auth = FirebaseAuth.getInstance();
        this.firestore = FirebaseFirestore.getInstance();
        Toolbar toolbar2 = (Toolbar) findViewById(R.id.my_cart_toolbar);
        this.toolbar = toolbar2;
        setSupportActionBar(toolbar2);
        getSupportActionBar().setDisplayHomeAsUpEnabled(true);
        this.toolbar.setNavigationOnClickListener(new View.OnClickListener() {
            public void onClick(View v) {
                CartActivity.this.finish();
            }
        });
        LocalBroadcastManager.getInstance(this).registerReceiver(this.mMessageReceiver, new IntentFilter("MyTotalAmount"));
        this.overAllAmount = (TextView) findViewById(R.id.textView3);
        RecyclerView recyclerView2 = (RecyclerView) findViewById(R.id.cart_rec);
        this.recyclerView = recyclerView2;
        recyclerView2.setLayoutManager(new LinearLayoutManager(this));
        this.cartModelList = new ArrayList();
        MyCartAdapter myCartAdapter = new MyCartAdapter(this, this.cartModelList);
        this.cartAdapter = myCartAdapter;
        this.recyclerView.setAdapter(myCartAdapter);
        this.firestore.collection("AddToCart").document(this.auth.getCurrentUser().getUid()).collection("User").get().addOnCompleteListener(new OnCompleteListener<QuerySnapshot>() {
            public void onComplete(Task<QuerySnapshot> task) {
                if (task.isSuccessful()) {
                    for (DocumentSnapshot doc : task.getResult().getDocuments()) {
                        CartActivity.this.cartModelList.add((MyCartModel) doc.toObject(MyCartModel.class));
                        CartActivity.this.cartAdapter.notifyDataSetChanged();
                    }
                }
            }
        });
    }
}
