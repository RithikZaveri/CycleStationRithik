package com.example.cyclestationrithik.Admin.station;

import androidx.appcompat.app.AppCompatActivity;

import android.app.Activity;
import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;

import com.example.cyclestationrithik.R;

public class AdminStationHome extends Activity
{

    @Override
    protected void onCreate(Bundle savedInstanceState)
    {
        Button insta,updsta,delsta,viusta;

        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_admin_station_home);

        insta=findViewById(R.id.adminstationhomeinsert);
        updsta=findViewById(R.id.adminstationhomeupdate);
        delsta=findViewById(R.id.adminstationhomedelete);
        viusta=findViewById(R.id.adminstationhomeview);

        insta.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent ii=new Intent(getApplicationContext(),InsertNewStation.class);
                startActivity(ii);
            }
        });

        delsta.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent i1=new Intent(getApplicationContext(),AdminStationDelete.class);
                startActivity(i1);
            }
        });

        viusta.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent i5=new Intent(getApplicationContext(),AdminStationView.class);
                startActivity(i5);
            }
        });
    }
}