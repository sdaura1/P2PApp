package com.example.shaheed.p2papp;

import android.Manifest;
import android.content.Intent;
import android.content.pm.PackageManager;
import android.net.Uri;
import android.support.design.widget.Snackbar;
import android.support.v4.app.ActivityCompat;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.ImageView;
import android.widget.TabHost;

public class Resources extends AppCompatActivity {

    Button callBtn;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_resources);

        callBtn = findViewById(R.id.callPoliceBtn);

        callBtn.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent i = new Intent();
                i.setAction(Intent.ACTION_CALL);
                i.putExtra(Intent.EXTRA_PHONE_NUMBER, Uri.parse("08066930554"));

                if (ActivityCompat.checkSelfPermission(getApplicationContext(), Manifest.permission.CALL_PHONE) != PackageManager.PERMISSION_GRANTED){
                    ActivityCompat.requestPermissions(Resources.this,
                            new String[]{Manifest.permission.CALL_PHONE}, 10);
                }else {
                    try {
                        startActivity(i);
                    }catch (android.content.ActivityNotFoundException ex){
                        Snackbar.make(v, "Check Permission!", Snackbar.LENGTH_LONG).show();
                    }
                }
            }
        });


    }
}
