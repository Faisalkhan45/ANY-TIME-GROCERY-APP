package com.example.anttimegrocery.activities;

import android.os.Bundle;
import android.util.Log;
import android.view.View;
import android.widget.Button;
import android.widget.TextView;
import android.widget.Toast;
import androidx.appcompat.app.AppCompatActivity;
import androidx.appcompat.widget.Toolbar;
import com.example.anttimegrocery.R;
import com.razorpay.Checkout;
import com.razorpay.PaymentResultListener;
import org.json.JSONObject;

public class PaymentActivity extends AppCompatActivity implements PaymentResultListener {
    double amount = 0.0d;
    TextView discount;
    Button paymentBtn;
    TextView shipping;
    TextView subTotal;
    Toolbar toolbar;
    TextView total;

    /* access modifiers changed from: protected */
    public void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView((int) R.layout.activity_payment);
        Toolbar toolbar2 = (Toolbar) findViewById(R.id.payment_toolbar);
        this.toolbar = toolbar2;
        setSupportActionBar(toolbar2);
        getSupportActionBar().setDisplayHomeAsUpEnabled(true);
        this.toolbar.setNavigationOnClickListener(new View.OnClickListener() {
            public void onClick(View v) {
                PaymentActivity.this.finish();
            }
        });
        this.amount = getIntent().getDoubleExtra("amount", 0.0d);
        this.subTotal = (TextView) findViewById(R.id.sub_total);
        this.discount = (TextView) findViewById(R.id.textView17);
        this.shipping = (TextView) findViewById(R.id.textView18);
        this.total = (TextView) findViewById(R.id.total_amt);
        this.paymentBtn = (Button) findViewById(R.id.pay_btn);
        this.subTotal.setText(this.amount + "$");
        this.paymentBtn.setOnClickListener(new View.OnClickListener() {
            public void onClick(View v) {
                PaymentActivity.this.paymentMethod();
            }
        });
    }

    /* access modifiers changed from: private */
    public void paymentMethod() {
        Checkout checkout = new Checkout();
        checkout.setKeyID("rzp_test_ouqaHXfpYGPmEd");
        try {
            JSONObject options = new JSONObject();
            options.put("name", "My Any Time Grocery");
            options.put("description", "Reference No. #123456");
            options.put("image", "https://s3.amazonaws.com/rzp-mobile/images/rzp.png");
            options.put("currency", "USD");
            double d = this.amount * 100.0d;
            this.amount = d;
            options.put("amount", d);
            JSONObject preFill = new JSONObject();
            preFill.put("email", "faisalkhanattari34@gmail.com");
            preFill.put("contact", "8374452927");
            options.put("prefill", preFill);
            checkout.open(this, options);
        } catch (Exception e) {
            Log.e("TAG", "Error in starting Razorpay Checkout", e);
        }
    }

    public void onPaymentSuccess(String s) {
        Toast.makeText(this, "Payment Successful", 0).show();
    }

    public void onPaymentError(int i, String s) {
        Toast.makeText(this, "Payment Cancel", 0).show();
    }
}
