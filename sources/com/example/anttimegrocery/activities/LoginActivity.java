package com.example.anttimegrocery.activities;

import android.app.Activity;
import android.content.Intent;
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

public class LoginActivity extends AppCompatActivity {
    private FirebaseAuth auth;
    EditText email;
    EditText password;

    /* access modifiers changed from: protected */
    public void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView((int) R.layout.activity_login);
        this.auth = FirebaseAuth.getInstance();
        this.email = (EditText) findViewById(R.id.email);
        this.password = (EditText) findViewById(R.id.password);
    }

    public void signIn(View view) {
        String userEmail = this.email.getText().toString();
        String userPassword = this.password.getText().toString();
        if (TextUtils.isEmpty(userEmail)) {
            Toast.makeText(this, "Enter Email Address!", 0).show();
        } else if (TextUtils.isEmpty(userPassword)) {
            Toast.makeText(this, "Enter Password!", 0).show();
        } else if (userPassword.length() < 6) {
            Toast.makeText(this, "Password is too short, enter minimum 6 characters", 0).show();
        } else {
            this.auth.signInWithEmailAndPassword(userEmail, userPassword).addOnCompleteListener((Activity) this, new OnCompleteListener<AuthResult>() {
                public void onComplete(Task<AuthResult> task) {
                    if (task.isSuccessful()) {
                        Toast.makeText(LoginActivity.this, "Login Successful", 0).show();
                        LoginActivity.this.startActivity(new Intent(LoginActivity.this, RegistrationActivity.class));
                        return;
                    }
                    Toast.makeText(LoginActivity.this, "Error:" + task.getException(), 0).show();
                }
            });
            startActivity(new Intent(this, MainActivity.class));
        }
    }

    public void signUp(View view) {
    }
}
