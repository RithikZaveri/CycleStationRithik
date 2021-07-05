package com.example.cyclestationrithik;

import androidx.annotation.NonNull;
import androidx.appcompat.app.AppCompatActivity;

import android.app.Activity;
import android.content.Intent;
import android.os.Bundle;
import android.text.TextUtils;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.Toast;

import com.example.cyclestationrithik.Admin.user.User;
import com.google.android.gms.tasks.OnCompleteListener;
import com.google.android.gms.tasks.Task;
import com.google.firebase.auth.AuthResult;
import com.google.firebase.auth.FirebaseAuth;
import com.google.firebase.auth.PhoneAuthCredential;
import com.google.firebase.auth.PhoneAuthProvider;
import com.google.firebase.database.DatabaseReference;
import com.google.firebase.database.FirebaseDatabase;

public class OTPSending extends Activity
{

    EditText etotp;
    Button bsend;
    FirebaseAuth fAuth;
    DatabaseReference dbref;

    private static String uid;

    @Override
    protected void onCreate(Bundle savedInstanceState)
    {

        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_otpsending);

        fAuth=FirebaseAuth.getInstance();

        etotp=findViewById(R.id.et_otp);
        bsend=findViewById(R.id.send);

        Intent ii=getIntent();
        final String vericode=ii.getStringExtra("vericode");
        final String name=ii.getStringExtra("name");
        final String pass=ii.getStringExtra("pass");
        final String email=ii.getStringExtra("email");
        final long phone=Long.parseLong(ii.getStringExtra("phone"));
        final String gen=ii.getStringExtra("gender");
        final String address=ii.getStringExtra("address");




        bsend.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                String otp = etotp.getText().toString();
                etotp.setText("");

                PhoneAuthCredential credential = PhoneAuthProvider.getCredential(vericode, otp);

                fAuth.signInWithCredential(credential)
                        .addOnCompleteListener(new OnCompleteListener<AuthResult>() {
                            @Override
                            public void onComplete(@NonNull Task<AuthResult> task) {
                                if (task.isSuccessful()) {
                                    Toast.makeText(getApplicationContext(), "OTP REGISTERED...", Toast.LENGTH_LONG).show();


                                    fAuth.createUserWithEmailAndPassword(email,pass)
                                            .addOnCompleteListener(new OnCompleteListener<AuthResult>() {
                                                @Override
                                                public void onComplete(@NonNull Task<AuthResult> task)
                                                {
                                                    if(task.isSuccessful())
                                                    {
                                                        Toast.makeText(getApplicationContext(),"USER REGISTRATION SUCCESSFULL",Toast.LENGTH_LONG).show();

                                                        dbref= FirebaseDatabase.getInstance().getReference("user");

                                                        if(TextUtils.isEmpty(uid))
                                                        {
                                                            String id=dbref.push().getKey();
                                                            User u1=new User(id,name,email,phone,gen,address,pass);
                                                            dbref.child(id).setValue(u1);
                                                            Toast.makeText(getApplicationContext(),"USER SUCCESSFULLY ADDED....",Toast.LENGTH_LONG).show();
                                                        }
                                                    }
                                                    else
                                                    {
                                                        Toast.makeText(getApplicationContext(),"USER REGISTRATION FAILED",Toast.LENGTH_LONG).show();
                                                    }
                                                }
                                            });
                                }
                                else {
                                    Toast.makeText(getApplicationContext(), "INVALID OTP...", Toast.LENGTH_LONG).show();
                                }
                            }
                        });

                /*String mail=etma.getText().toString();
                String pass=etpa.getText().toString();

                etma.setText("");
                etpa.setText("");*/


            }
        });
    }
}
