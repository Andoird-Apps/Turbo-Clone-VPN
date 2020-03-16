package com.turboclone.demovpn.activity;

import android.content.Intent;
import android.content.SharedPreferences;
import android.os.Bundle;
import android.preference.PreferenceManager;

import com.turboclone.demovpn.R;

import agency.tango.materialintroscreen.MaterialIntroActivity;
import agency.tango.materialintroscreen.SlideFragmentBuilder;

public class IntroActivity extends MaterialIntroActivity {
    SharedPreferences prefs;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        prefs = PreferenceManager.getDefaultSharedPreferences(this);
        if (!prefs.getBoolean("firstTime", true)) {
            onFinish();
        }
        else {
            addSlide(new SlideFragmentBuilder()
                    .backgroundColor(R.color.colorPrimaryDark)
                    .buttonsColor(R.color.colorPrimary)
                    .image(R.drawable.ic_048_safe)
                    .title("Easy and Secure")
                    .description("Turbo Lite is very easy,People wont be able to track your online activity")
                    .build());
            addSlide(new SlideFragmentBuilder()
                    .backgroundColor(R.color.colorPrimaryDark)
                    .buttonsColor(R.color.colorPrimary)
                    .image(R.drawable.ic_029_networking)
                    .title("Lots of  Servers")
                    .description("Choose from a lot of servers across the globe.")
                    .build());
            addSlide(new SlideFragmentBuilder()
                    .backgroundColor(R.color.colorPrimaryDark)
                    .buttonsColor(R.color.colorPrimary)
                    .image(R.drawable.ic_044_key)
                    .title("Start Now!! Enjoy")
                    .description("So, what are you waiting guys? Start and unblock the sites and apps that are blocked in your country!")
                    .build());
        } }

    @Override
    public void onFinish() {
            startActivity(new Intent(getApplicationContext(), MainActivity.class));
            SharedPreferences.Editor editor = prefs.edit();
            editor.putBoolean("firstTime", false);
            editor.apply();
            super.onFinish();


        }
    }

