package com.example.cyclestationrithik.Admin.cycle;

import androidx.appcompat.app.AppCompatActivity;

import android.app.Activity;
import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;

import com.example.cyclestationrithik.R;

public class AdminCycleHome extends Activity {

    @Override

    protected void onCreate(Bundle savedInstanceState)
    {
        Button inscy,updcy,delcy,viucy;

        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_admin_cycle_home);

        inscy=findViewById(R.id.admincyclehomeinsert);
        updcy=findViewById(R.id.admincyclehomeupdate);
        delcy=findViewById(R.id.admincyclehomedelete);
        viucy=findViewById(R.id.admincyclehomeview);

        inscy.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent ii=new Intent(getApplicationContext(),InsertNewCycle.class);
                startActivity(ii);
            }
        });

        updcy.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent i6=new Intent(getApplicationContext(),AdminCycleUpdate.class);
                startActivity(i6);
            }
        });

        delcy.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent i1=new Intent(getApplicationContext(),AdminCycleDelete.class);
                startActivity(i1);
            }
        });

        viucy.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent i4=new Intent(getApplicationContext(),AdminCycleView.class);
                startActivity(i4);
            }
        });

    }
}
