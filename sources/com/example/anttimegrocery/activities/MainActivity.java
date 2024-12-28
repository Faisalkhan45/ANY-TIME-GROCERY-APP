package com.example.anttimegrocery.activities;

import android.content.Intent;
import android.os.Bundle;
import android.view.Menu;
import android.view.MenuItem;
import androidx.appcompat.app.AppCompatActivity;
import androidx.appcompat.widget.Toolbar;
import androidx.fragment.app.Fragment;
import androidx.fragment.app.FragmentTransaction;
import com.example.anttimegrocery.R;
import com.example.anttimegrocery.fragments.HomeFragment;
import com.google.firebase.auth.FirebaseAuth;

public class MainActivity extends AppCompatActivity {
    FirebaseAuth auth;
    Fragment homeFragment;
    Toolbar toolbar;

    /* access modifiers changed from: protected */
    public void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView((int) R.layout.activity_main);
        this.auth = FirebaseAuth.getInstance();
        Toolbar toolbar2 = (Toolbar) findViewById(R.id.home_toolbar);
        this.toolbar = toolbar2;
        setSupportActionBar(toolbar2);
        getSupportActionBar().setDisplayHomeAsUpEnabled(true);
        getSupportActionBar().setHomeAsUpIndicator((int) R.drawable.ic_baseline_menu_24);
        HomeFragment homeFragment2 = new HomeFragment();
        this.homeFragment = homeFragment2;
        loadFragment(homeFragment2);
    }

    private void loadFragment(Fragment homeFragment2) {
        FragmentTransaction transaction = getSupportFragmentManager().beginTransaction();
        transaction.replace(R.id.home_container, homeFragment2);
        transaction.commit();
    }

    public boolean onCreateOptionsMenu(Menu menu) {
        getMenuInflater().inflate(R.menu.main_menu, menu);
        return true;
    }

    public boolean onOptionsItemSelected(MenuItem item) {
        int id = item.getItemId();
        if (id == R.id.menu_logout) {
            this.auth.signOut();
            startActivity(new Intent(this, RegistrationActivity.class));
            finish();
            return true;
        } else if (id != R.id.menu_my_cart) {
            return true;
        } else {
            startActivity(new Intent(this, CartActivity.class));
            return true;
        }
    }
}
