package com.example.cyclestationrithik.Admin;

import androidx.appcompat.app.AppCompatActivity;

import android.app.Activity;
import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;

import com.example.cyclestationrithik.Admin.cycle.AdminCycleHome;
import com.example.cyclestationrithik.Admin.station.AdminStationHome;
import com.example.cyclestationrithik.Admin.user.AdminUserHome;
import com.example.cyclestationrithik.Admin.user.InsertNewUser;
import com.example.cyclestationrithik.R;

public class AdminHome extends Activity {

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        Button station, cycle, adhomeuser, compl, feedb, payhis;

        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_admin_home);

        station = findViewById(R.id.bstation);
        cycle = findViewById(R.id.bcycle);
        adhomeuser = findViewById(R.id.badhuser);
        compl = findViewById(R.id.bcomplaint);
        feedb = findViewById(R.id.bfeedback);
        payhis = findViewById(R.id.bpayreturn);

        adhomeuser.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent ii = new Intent(getApplicationContext(), AdminUserHome.class);
                startActivity(ii);

            }
        });

        cycle.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent i4 = new Intent(getApplicationContext(), AdminCycleHome.class);
                startActivity(i4);

            }
        });

        station.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent i5 = new Intent(getApplicationContext(), AdminStationHome.class);
                startActivity(i5);

            }
        });
    }
}