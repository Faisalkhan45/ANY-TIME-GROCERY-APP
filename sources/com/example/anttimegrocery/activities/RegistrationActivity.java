package com.example.anttimegrocery.activities;

import android.app.Activity;
import android.content.Intent;
import android.content.SharedPreferences;
import android.os.Bundle;
import android.text.TextUtils;
import android.view.View;
import android.widget.EditText;
import android.widget.Toast;
import androidx.appcompat.app.AppCompatActivity;
import com.example.anttimegrocery.R;
import com.google.android.gms.tasks.OnCompleteListener;
import com.google.android.gms.tasks.Task;
import com.google.firebase.auth.AuthResult;
import com.google.firebase.auth.FirebaseAuth;

public class RegistrationActivity extends AppCompatActivity {
    private FirebaseAuth auth;
    EditText email;
    EditText name;
    EditText password;
    SharedPreferences sharedPreferences;

    /* access modifiers changed from: protected */
    public void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView((int) R.layout.activity_registration);
        FirebaseAuth instance = FirebaseAuth.getInstance();
        this.auth = instance;
        if (instance.getCurrentUser() != null) {
            startActivity(new Intent(this, MainActivity.class));
            finish();
        }
        this.name = (EditText) findViewById(R.id.name);
        this.email = (EditText) findViewById(R.id.email);
        this.password = (EditText) findViewById(R.id.password);
        SharedPreferences sharedPreferences2 = getSharedPreferences("onBoardScreen", 0);
        this.sharedPreferences = sharedPreferences2;
        if (sharedPreferences2.getBoolean("firstTime", true)) {
            SharedPreferences.Editor editor = this.sharedPreferences.edit();
            editor.putBoolean("firstTime", false);
            editor.commit();
            startActivity(new Intent(this, OnBoardingActivity.class));
            finish();
        }
    }

    public void signup(View view) {
        String userName = this.name.getText().toString();
        String userEmail = this.email.getText().toString();
        String userPassword = this.password.getText().toString();
        if (TextUtils.isEmpty(userName)) {
            Toast.makeText(this, "Enter Name!", 0).show();
        }
        if (TextUtils.isEmpty(userEmail)) {
            Toast.makeText(this, "Enter Email Address!", 0).show();
        } else if (TextUtils.isEmpty(userPassword)) {
            Toast.makeText(this, "Enter Password!", 0).show();
        } else if (userPassword.length() < 6) {
            Toast.makeText(this, "Password is too short, enter minimum 6 characters", 0).show();
        } else {
            this.auth.createUserWithEmailAndPassword(userEmail, userPassword).addOnCompleteListener((Activity) this, new OnCompleteListener<AuthResult>() {
                public void onComplete(Task<AuthResult> task) {
                    if (task.isSuccessful()) {
                        Toast.makeText(RegistrationActivity.this, "Successfully Register", 0).show();
                        RegistrationActivity.this.startActivity(new Intent(RegistrationActivity.this, MainActivity.class));
                        return;
                    }
                    Toast.makeText(RegistrationActivity.this, "Registration Failed" + task.getException(), 0).show();
                }
            });
        }
    }

    public void signin(View view) {
        startActivity(new Intent(this, LoginActivity.class));
    }
}
