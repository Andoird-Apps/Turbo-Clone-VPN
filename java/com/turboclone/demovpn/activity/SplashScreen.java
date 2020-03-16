package com.turboclone.demovpn.activity;

import android.content.Intent;
import android.support.design.widget.CoordinatorLayout;
import android.support.design.widget.Snackbar;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;

import com.turboclone.demovpn.R;

public class SplashScreen extends AppCompatActivity {
    CoordinatorLayout coordinatorLayout;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_splash_screen);
        coordinatorLayout = findViewById(R.id.cordi);

        if (!Utility.isOnline(getApplicationContext())) {


            Snackbar snackbar = Snackbar
                    .make(coordinatorLayout, "Check internet connection", Snackbar.LENGTH_LONG);
            snackbar.show();


        } else {
            startActivity(new Intent(this, IntroActivity.class));
            finish();
        }

    }
}
