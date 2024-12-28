package com.example.anttimegrocery.activities;

import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.ImageView;
import android.widget.TextView;
import android.widget.Toast;
import androidx.appcompat.app.AppCompatActivity;
import androidx.appcompat.widget.Toolbar;
import com.bumptech.glide.Glide;
import com.example.anttimegrocery.R;
import com.example.anttimegrocery.models.NewProductsModel;
import com.example.anttimegrocery.models.PopularProductsModel;
import com.example.anttimegrocery.models.ShowAllModel;
import com.google.android.gms.tasks.OnCompleteListener;
import com.google.android.gms.tasks.Task;
import com.google.firebase.auth.FirebaseAuth;
import com.google.firebase.firestore.DocumentReference;
import com.google.firebase.firestore.FirebaseFirestore;
import java.io.Serializable;
import java.text.SimpleDateFormat;
import java.util.Calendar;
import java.util.HashMap;

public class DetailedActivity extends AppCompatActivity {
    ImageView addItems;
    Button addToCart;
    FirebaseAuth auth;
    Button buyNow;
    TextView description;
    ImageView detailedImg;
    private FirebaseFirestore firestore;
    TextView name;
    NewProductsModel newProductsModel = null;
    PopularProductsModel popularProductsModel = null;
    TextView price;
    TextView quantity;
    TextView rating;
    ImageView removeItems;
    ShowAllModel showAllModel = null;
    Toolbar toolbar;
    int totalPrice = 0;
    int totalQuantity = 1;

    /* access modifiers changed from: protected */
    public void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView((int) R.layout.activity_detailed);
        Toolbar toolbar2 = (Toolbar) findViewById(R.id.detailed_toolbar);
        this.toolbar = toolbar2;
        setSupportActionBar(toolbar2);
        getSupportActionBar().setDisplayHomeAsUpEnabled(true);
        this.toolbar.setNavigationOnClickListener(new View.OnClickListener() {
            public void onClick(View v) {
                DetailedActivity.this.finish();
            }
        });
        this.firestore = FirebaseFirestore.getInstance();
        this.auth = FirebaseAuth.getInstance();
        Serializable serializableExtra = getIntent().getSerializableExtra("detailed");
        if (serializableExtra instanceof NewProductsModel) {
            this.newProductsModel = (NewProductsModel) serializableExtra;
        } else if (serializableExtra instanceof PopularProductsModel) {
            this.popularProductsModel = (PopularProductsModel) serializableExtra;
        } else if (serializableExtra instanceof ShowAllModel) {
            this.showAllModel = (ShowAllModel) serializableExtra;
        }
        this.detailedImg = (ImageView) findViewById(R.id.detailed_img);
        this.quantity = (TextView) findViewById(R.id.quantity);
        this.name = (TextView) findViewById(R.id.detailed_name);
        this.rating = (TextView) findViewById(R.id.rating);
        this.description = (TextView) findViewById(R.id.detailed_desc);
        this.price = (TextView) findViewById(R.id.detailed_price);
        this.addToCart = (Button) findViewById(R.id.add_to_cart);
        this.buyNow = (Button) findViewById(R.id.buy_now);
        this.addItems = (ImageView) findViewById(R.id.add_item);
        this.removeItems = (ImageView) findViewById(R.id.remove_item);
        if (this.newProductsModel != null) {
            Glide.with(getApplicationContext()).load(this.newProductsModel.getImg_url()).into(this.detailedImg);
            this.name.setText(this.newProductsModel.getName());
            this.rating.setText(this.newProductsModel.getRating());
            this.description.setText(this.newProductsModel.getDescription());
            this.price.setText(String.valueOf(this.newProductsModel.getPrice()));
            this.name.setText(this.newProductsModel.getName());
            this.totalPrice = this.newProductsModel.getPrice() + this.totalQuantity;
        }
        if (this.popularProductsModel != null) {
            Glide.with(getApplicationContext()).load(this.popularProductsModel.getImg_url()).into(this.detailedImg);
            this.name.setText(this.popularProductsModel.getName());
            this.rating.setText(this.popularProductsModel.getRating());
            this.description.setText(this.popularProductsModel.getDescription());
            this.price.setText(String.valueOf(this.popularProductsModel.getPrice()));
            this.name.setText(this.popularProductsModel.getName());
            this.totalPrice = this.popularProductsModel.getPrice() + this.totalQuantity;
        }
        if (this.showAllModel != null) {
            Glide.with(getApplicationContext()).load(this.showAllModel.getImg_url()).into(this.detailedImg);
            this.name.setText(this.showAllModel.getName());
            this.rating.setText(this.showAllModel.getRating());
            this.description.setText(this.showAllModel.getDescription());
            this.price.setText(String.valueOf(this.showAllModel.getPrice()));
            this.name.setText(this.showAllModel.getName());
            this.totalPrice = this.showAllModel.getPrice() * this.totalQuantity;
        }
        this.buyNow.setOnClickListener(new View.OnClickListener() {
            public void onClick(View v) {
                Intent intent = new Intent(DetailedActivity.this, AddressActivity.class);
                if (DetailedActivity.this.newProductsModel != null) {
                    intent.putExtra("item", DetailedActivity.this.newProductsModel);
                }
                if (DetailedActivity.this.popularProductsModel != null) {
                    intent.putExtra("item", DetailedActivity.this.popularProductsModel);
                }
                if (DetailedActivity.this.showAllModel != null) {
                    intent.putExtra("item", DetailedActivity.this.showAllModel);
                }
                DetailedActivity.this.startActivity(intent);
            }
        });
        this.addToCart.setOnClickListener(new View.OnClickListener() {
            public void onClick(View v) {
                DetailedActivity.this.addtoCart();
            }
        });
        this.addItems.setOnClickListener(new View.OnClickListener() {
            public void onClick(View v) {
                if (DetailedActivity.this.totalQuantity < 10) {
                    DetailedActivity.this.totalQuantity++;
                    DetailedActivity.this.quantity.setText(String.valueOf(DetailedActivity.this.totalQuantity));
                    if (DetailedActivity.this.newProductsModel != null) {
                        DetailedActivity detailedActivity = DetailedActivity.this;
                        detailedActivity.totalPrice = detailedActivity.newProductsModel.getPrice() * DetailedActivity.this.totalQuantity;
                    }
                    if (DetailedActivity.this.popularProductsModel != null) {
                        DetailedActivity detailedActivity2 = DetailedActivity.this;
                        detailedActivity2.totalPrice = detailedActivity2.popularProductsModel.getPrice() * DetailedActivity.this.totalQuantity;
                    }
                    if (DetailedActivity.this.showAllModel != null) {
                        DetailedActivity detailedActivity3 = DetailedActivity.this;
                        detailedActivity3.totalPrice = detailedActivity3.showAllModel.getPrice() * DetailedActivity.this.totalQuantity;
                    }
                }
            }
        });
        this.removeItems.setOnClickListener(new View.OnClickListener() {
            public void onClick(View v) {
                if (DetailedActivity.this.totalQuantity > 10) {
                    DetailedActivity detailedActivity = DetailedActivity.this;
                    detailedActivity.totalQuantity--;
                    DetailedActivity.this.quantity.setText(String.valueOf(DetailedActivity.this.totalQuantity));
                }
            }
        });
    }

    /* access modifiers changed from: private */
    public void addtoCart() {
        Calendar calForDate = Calendar.getInstance();
        String saveCurrentDate = new SimpleDateFormat("MM dd, YYYY").format(calForDate.getTime());
        String saveCurrentTime = new SimpleDateFormat("HH:mm:ss a").format(calForDate.getTime());
        HashMap<String, Object> cartMap = new HashMap<>();
        cartMap.put("productName", this.name.getText().toString());
        cartMap.put("productPrice", this.price.getText().toString());
        cartMap.put("currentTime", saveCurrentTime);
        cartMap.put("currentDate", saveCurrentDate);
        cartMap.put("totalQuantity", this.quantity.getText().toString());
        cartMap.put("totalPrice", Integer.valueOf(this.totalPrice));
        this.firestore.collection("AddToCart").document(this.auth.getCurrentUser().getUid()).collection("User").add(cartMap).addOnCompleteListener(new OnCompleteListener<DocumentReference>() {
            public void onComplete(Task<DocumentReference> task) {
                Toast.makeText(DetailedActivity.this, "Added To A Cart", 0).show();
                DetailedActivity.this.finish();
            }
        });
    }
}
