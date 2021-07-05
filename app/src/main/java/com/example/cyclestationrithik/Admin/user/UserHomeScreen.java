package com.example.cyclestationrithik.Admin.user;

import androidx.appcompat.app.AppCompatActivity;

import android.app.Activity;
import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.TextView;

import com.example.cyclestationrithik.R;

public class UserHomeScreen extends Activity
{
    TextView TvUserRideInfo,TvUserAmountPaid,TvUserTimeLeft;
    Button BtUserVieworUpdateInfo,BtUserLocateNearbyStation,BtUserComplain;

    @Override
    protected void onCreate(Bundle savedInstanceState)
    {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_user_home_screen);

        TvUserRideInfo=findViewById(R.id.TvUserRideInfo);
        TvUserAmountPaid=findViewById(R.id.TvUserAmountPaid);
        TvUserTimeLeft=findViewById(R.id.TvUserTimeLeft);
        BtUserVieworUpdateInfo=findViewById(R.id.BtUserVieworUpdate);
        BtUserLocateNearbyStation=findViewById(R.id.BtUserNearbyStation);
        BtUserComplain=findViewById(R.id.BtUserComplain);

        BtUserLocateNearbyStation.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent ii=new Intent(getApplicationContext(),StationMapActivity.class);
                startActivity(ii);
            }
        });
    }
}
