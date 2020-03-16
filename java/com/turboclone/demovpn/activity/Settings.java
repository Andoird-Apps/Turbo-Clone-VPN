package com.turboclone.demovpn.activity;

import android.content.Intent;
import android.content.SharedPreferences;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.support.v7.widget.Toolbar;
import android.view.View;
import android.widget.CompoundButton;
import android.widget.RadioButton;
import android.widget.Switch;
import android.widget.TextView;
import android.widget.Toast;
import com.turboclone.demovpn.R;

public class Settings extends AppCompatActivity {

    private RadioButton openvpn,ipsec;
    private Switch connectstart,tvnotif;
    private TextView aboutUs;
    private SharedPreferences connec_mode,open_start,notification;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_settings);
        Toolbar toolbar = (Toolbar) findViewById(R.id.toolbarold);
        toolbar.setTitle("Settings");
        setSupportActionBar(toolbar);
        getSupportActionBar().setDisplayShowHomeEnabled(true);
        getSupportActionBar().setDisplayHomeAsUpEnabled(true);
        openvpn=findViewById(R.id.radio_btn_open);
        ipsec=findViewById(R.id.radio_btn_ipsec);
        connectstart=findViewById(R.id.switch_connect_start);
        tvnotif=findViewById(R.id.switch_notific);
        aboutUs=findViewById(R.id.tv_aboutus);

        connec_mode=getSharedPreferences("cmode",MODE_PRIVATE);
        open_start=getSharedPreferences("start",MODE_PRIVATE);
        notification=getSharedPreferences("notifi",MODE_PRIVATE);
        checkConnecMode();
        checkSwitch();
        connectstart.setOnCheckedChangeListener(new CompoundButton.OnCheckedChangeListener() {
            @Override
            public void onCheckedChanged(CompoundButton buttonView, boolean isChecked)
            {
                Toast.makeText(getApplicationContext(), "The Switch is " + (isChecked ? "on" : "off"),
                        Toast.LENGTH_SHORT).show();
                if(isChecked)
                {
                    SharedPreferences.Editor editor=open_start.edit();
                    editor.clear();
                    editor.putString("switch","on");
                    editor.apply();
                }
                else {
                    //do stuff when Switch if OFF
                }
            }
        });
        connectstart.setOnCheckedChangeListener(new CompoundButton.OnCheckedChangeListener() {
            @Override
            public void onCheckedChanged(CompoundButton buttonView, boolean isChecked)
            {
                Toast.makeText(getApplicationContext(), "The Switch is " + (isChecked ? "on" : "off"),
                        Toast.LENGTH_SHORT).show();
                if(isChecked)
                {
                    SharedPreferences.Editor editor=open_start.edit();
                    editor.clear();
                    editor.putString("switch","on");
                    editor.apply();
                }
                else {
                    //do stuff when Switch if OFF
                }
            }
        });
        aboutUs.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
               startActivity(new Intent(getApplicationContext(),AboutUs.class));
            }
        });
    }

    public void onRadioButtonClicked(View view)
    {
        // Is the button now checked?
        boolean checked = ((RadioButton) view).isChecked();
        Toast.makeText(this,"clicked",Toast.LENGTH_SHORT).show();
        // Check which radio button was clicked
        switch(view.getId()) {
            case R.id.radio_btn_open:
                if (checked)
                {
                    SharedPreferences.Editor editor=connec_mode.edit();
                    editor.clear();
                    editor.putString("mode","open");
                    editor.apply();
                }
                break;
            case R.id.radio_btn_ipsec:
                if (checked)
                {
                    SharedPreferences.Editor editor=connec_mode.edit();
                    editor.clear();
                    editor.putString("mode","ip");
                    editor.apply();
                }
                break;
        }
    }

    protected void checkConnecMode()
    {
        String mode=connec_mode.getString("mode",null);
        if (mode!=null)
        {
            if (mode.equals("open"))
            {
                openvpn.setChecked(true);
                ipsec.setChecked(false);
            }
            else if (mode.equals("ip"))
            {
                ipsec.setChecked(true);
                openvpn.setChecked(false);
            }
        }
    }
    protected void checkSwitch()
    {
        String s1=open_start.getString("switch",null);
        if (s1!=null)
        {
            if (s1.equals("on"))
            {
                connectstart.setChecked(true);
            }
            else
            {
                connectstart.setChecked(false);
            }
        }
        String s2=notification.getString("switch",null);
        if (s2!=null)
        {
            if (s2.equals("on"))
            {
                tvnotif.setChecked(true);
            }
            else
            {
                tvnotif.setChecked(false);
            }
        }
    }

}
