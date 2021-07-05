package com.example.cyclestationrithik.Admin.user;

import androidx.appcompat.app.AppCompatActivity;

import android.app.Activity;
import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;

import com.example.cyclestationrithik.R;

public class AdminUserHome extends Activity
{

    @Override
    protected void onCreate(Bundle savedInstanceState)
    {
        Button insu,upu,delu,viu;

        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_admin_user_home);

        insu=findViewById(R.id.insuser);
        upu=findViewById(R.id.upduser);
        delu=findViewById(R.id.deluser);
        viu=findViewById(R.id.viewuser);

        insu.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent ii=new Intent(getApplicationContext(),InsertNewUser.class);
                startActivity(ii);

            }
        });

        upu.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent i6=new Intent(getApplicationContext(),AdminUserUpdate.class);
                startActivity(i6);

            }
        });

        delu.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent i5=new Intent(getApplicationContext(),AdminUserDelete.class);
                startActivity(i5);
            }
        });

        viu.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent i3=new Intent(getApplicationContext(),AdminUserView.class);
                startActivity(i3);

            }
        });

    }
}
