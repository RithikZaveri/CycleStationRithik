package com.example.cyclestationrithik.Admin.user;

import androidx.annotation.NonNull;
import androidx.appcompat.app.AppCompatActivity;

import android.app.Activity;
import android.os.Bundle;
import android.text.TextUtils;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.Toast;

import com.example.cyclestationrithik.R;
import com.google.android.gms.tasks.OnCompleteListener;
import com.google.android.gms.tasks.Task;
import com.google.firebase.FirebaseException;
import com.google.firebase.auth.AuthResult;
import com.google.firebase.auth.FirebaseAuth;
import com.google.firebase.auth.PhoneAuthCredential;
import com.google.firebase.auth.PhoneAuthProvider;
import com.google.firebase.database.DatabaseReference;
import com.google.firebase.database.FirebaseDatabase;

import java.util.concurrent.TimeUnit;

public class InsertNewUser extends Activity
{
    private static String uid;
    EditText nun,nem,nph,nge,naddr,npass;
    Button nuser,ncan;
    DatabaseReference dbref;
    FirebaseAuth fAuth;

    @Override
    protected void onCreate(Bundle savedInstanceState)
    {

        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_insert_new_user);

        nun=findViewById(R.id.newusername);
        nem=findViewById(R.id.newemail);
        nph=findViewById(R.id.newphone);
        nge=findViewById(R.id.newgender);
        naddr=findViewById(R.id.newaddress);
        npass=findViewById(R.id.etInsertNewUserPass);
        nuser=findViewById(R.id.adduser);
        ncan=findViewById(R.id.newcancel);

        fAuth= FirebaseAuth.getInstance();

        dbref= FirebaseDatabase.getInstance().getReference("user");

        nuser.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v)
            {
                final String name=nun.getText().toString();
                final String mail=nem.getText().toString();
                final long phone=Long.parseLong(nph.getText().toString());
                final String gen=nge.getText().toString();
                final String add=naddr.getText().toString();
                final String pass=npass.getText().toString();
                final String veriphone="+91"+nph.getText().toString();

                PhoneAuthProvider.OnVerificationStateChangedCallbacks mCallbacks;

                mCallbacks=new PhoneAuthProvider.OnVerificationStateChangedCallbacks() {
                    @Override
                    public void onVerificationCompleted(PhoneAuthCredential phoneAuthCredential) {
                        Toast.makeText(getApplicationContext(), "PHONE VERIFIED...", Toast.LENGTH_LONG).show();

                        fAuth.createUserWithEmailAndPassword(mail,pass)
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
                                                User u1=new User(id,name,mail,phone,gen,add,pass);
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

                    @Override
                    public void onVerificationFailed(FirebaseException e) {
                        Toast.makeText(getApplicationContext(), "PHONE VERIFICATION FAILED...", Toast.LENGTH_LONG).show();

                    }
                };

                PhoneAuthProvider.getInstance().verifyPhoneNumber(veriphone,60l, TimeUnit.SECONDS,InsertNewUser.this,mCallbacks);


                nun.setText("");
                nem.setText("");
                nph.setText("");
                nge.setText("");
                naddr.setText("");
                npass.setText("");

            }
        });

    }
}
