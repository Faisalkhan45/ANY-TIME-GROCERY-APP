package com.example.anttimegrocery.activities;

import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.Toast;
import androidx.appcompat.app.AppCompatActivity;
import androidx.appcompat.widget.Toolbar;
import com.example.anttimegrocery.R;
import com.google.android.gms.tasks.OnCompleteListener;
import com.google.android.gms.tasks.Task;
import com.google.firebase.auth.FirebaseAuth;
import com.google.firebase.firestore.DocumentReference;
import com.google.firebase.firestore.FirebaseFirestore;
import java.util.HashMap;
import java.util.Map;

public class AddAddressActivity extends AppCompatActivity {
    Button addAddressBtn;
    EditText address;
    FirebaseAuth auth;
    EditText city;
    FirebaseFirestore firestore;
    EditText name;
    EditText phoneNumber;
    EditText postalCode;
    Toolbar toolbar;

    /* access modifiers changed from: protected */
    public void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView((int) R.layout.activity_add_address);
        Toolbar toolbar2 = (Toolbar) findViewById(R.id.add_address_toolbar);
        this.toolbar = toolbar2;
        setSupportActionBar(toolbar2);
        getSupportActionBar().setDisplayHomeAsUpEnabled(true);
        this.toolbar.setNavigationOnClickListener(new View.OnClickListener() {
            public void onClick(View v) {
                AddAddressActivity.this.finish();
            }
        });
        this.auth = FirebaseAuth.getInstance();
        this.firestore = FirebaseFirestore.getInstance();
        this.name = (EditText) findViewById(R.id.ad_name);
        this.address = (EditText) findViewById(R.id.ad_address);
        this.city = (EditText) findViewById(R.id.ad_city);
        this.phoneNumber = (EditText) findViewById(R.id.ad_phone);
        this.postalCode = (EditText) findViewById(R.id.ad_code);
        Button button = (Button) findViewById(R.id.ad_add_address);
        this.addAddressBtn = button;
        button.setOnClickListener(new View.OnClickListener() {
            public void onClick(View v) {
                String userName = AddAddressActivity.this.name.getText().toString();
                String userCity = AddAddressActivity.this.city.getText().toString();
                String userAddress = AddAddressActivity.this.address.getText().toString();
                String userCode = AddAddressActivity.this.postalCode.getText().toString();
                String userNumber = AddAddressActivity.this.phoneNumber.getText().toString();
                String final_address = "";
                if (!userName.isEmpty()) {
                    final_address = final_address + userName;
                }
                if (!userCity.isEmpty()) {
                    final_address = final_address + userCity;
                }
                if (!userAddress.isEmpty()) {
                    final_address = final_address + userAddress;
                }
                if (!userCode.isEmpty()) {
                    final_address = final_address + userCode;
                }
                if (!userNumber.isEmpty()) {
                    final_address = final_address + userNumber;
                }
                if (userName.isEmpty() || userCity.isEmpty() || userAddress.isEmpty() || userCode.isEmpty() || userNumber.isEmpty()) {
                    Toast.makeText(AddAddressActivity.this, "Kindly Fill All Field", 0).show();
                    return;
                }
                Map<String, String> map = new HashMap<>();
                map.put("userAddress", final_address);
                AddAddressActivity.this.firestore.collection("CurrentUser").document(AddAddressActivity.this.auth.getCurrentUser().getUid()).collection("Address").add(map).addOnCompleteListener(new OnCompleteListener<DocumentReference>() {
                    public void onComplete(Task<DocumentReference> task) {
                        if (task.isSuccessful()) {
                            Toast.makeText(AddAddressActivity.this, "Address Added", 0).show();
                            AddAddressActivity.this.startActivity(new Intent(AddAddressActivity.this, DetailedActivity.class));
                            AddAddressActivity.this.finish();
                        }
                    }
                });
            }
        });
    }
}
