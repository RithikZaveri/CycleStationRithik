package com.example.cyclestationrithik;

import androidx.annotation.NonNull;

import android.app.Activity;
import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.ProgressBar;
import android.widget.Toast;

import com.example.cyclestationrithik.Admin.AdminHome;
import com.example.cyclestationrithik.Admin.user.UserHomeScreen;
import com.google.android.gms.tasks.OnCompleteListener;
import com.google.android.gms.tasks.Task;
import com.google.firebase.auth.AuthResult;
import com.google.firebase.auth.FirebaseAuth;

public class MainActivity extends Activity
{
    EditText etma,etpa;
    Button breg,blog;
    ProgressBar prog;
    FirebaseAuth fAuth;

    @Override
    protected void onCreate(Bundle savedInstanceState)
    {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        etma=findViewById(R.id.et_email);
        etpa=findViewById(R.id.et_pass);

        breg=findViewById(R.id.bt_Register);
        blog=findViewById(R.id.bt_signin);

        fAuth=FirebaseAuth.getInstance();

        breg.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v)
            {
                Intent i1=new Intent(getApplicationContext(),RegisterNewUser.class);
                startActivity(i1);
/*
                String mail=etma.getText().toString();
                String pass=etpa.getText().toString();

                etma.setText("");
                etpa.setText("");

                fAuth.createUserWithEmailAndPassword(mail,pass)
                        .addOnCompleteListener(new OnCompleteListener<AuthResult>() {
                            @Override
                            public void onComplete(@NonNull Task<AuthResult> task)
                            {
                                if(task.isSuccessful())
                                {
                                    Toast.makeText(getApplicationContext(),"USER REGISTRATION SUCCESSFULL",Toast.LENGTH_LONG).show();
                                }
                                else
                                {
                                    Toast.makeText(getApplicationContext(),"USER REGISTRATION FAILED",Toast.LENGTH_LONG).show();
                                }
                            }
                        });


*/
            }
        });

        blog.setOnClickListener(new View.OnClickListener()
        {
            @Override
            public void onClick(View v)
            {
                final String mail=etma.getText().toString();
                String pass=etpa.getText().toString();

                etma.setText("");
                etpa.setText("");

                if(mail.equals("ri") && pass.equals("ri"))
                {
                    Intent i=new Intent(getApplicationContext(), AdminHome.class);
                    i.putExtra("lemail",mail);
                    startActivity(i);
                }
                else {
                    fAuth.signInWithEmailAndPassword(mail, pass)
                            .addOnCompleteListener(new OnCompleteListener<AuthResult>() {
                                @Override
                                public void onComplete(@NonNull Task<AuthResult> task) {
                                    if (task.isSuccessful()) {
                                        Toast.makeText(getApplicationContext(), "WELCOME AUTHORISED USER", Toast.LENGTH_LONG).show();
                                        Intent i3=new Intent(getApplicationContext(), UserHomeScreen.class);
                                        i3.putExtra("lemail",mail);
                                        startActivity(i3);
                                    } else {
                                        Toast.makeText(getApplicationContext(), "YOU ARE UNAUTHORISED...", Toast.LENGTH_LONG).show();
                                    }
                                }
                            });
                }
            }
        });

    }
}
