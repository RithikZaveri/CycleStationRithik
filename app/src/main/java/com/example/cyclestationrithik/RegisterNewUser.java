package com.example.cyclestationrithik;

import androidx.appcompat.app.AppCompatActivity;

import android.app.Activity;
import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.Toast;

import com.google.firebase.FirebaseException;
import com.google.firebase.auth.PhoneAuthCredential;
import com.google.firebase.auth.PhoneAuthProvider;

import java.util.concurrent.TimeUnit;

public class RegisterNewUser extends Activity
{
    EditText un,em,pa,cpa,ph,ge,addr;
    Button reg,can;

    String verificationCode;
    String name,e_mail,pass,confpass,phone,veriphone,gen,address,rol;

    @Override
    protected void onCreate(Bundle savedInstanceState)
    {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_register_new_user);

        un=findViewById(R.id.username);
        em=findViewById(R.id.email);
        pa=findViewById(R.id.pass);
        cpa=findViewById(R.id.conpass);
        ph=findViewById(R.id.phone);
        ge=findViewById(R.id.gender);
        addr=findViewById(R.id.address);

        reg=findViewById(R.id.reguser);
        can=findViewById(R.id.cancel);

        reg.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view)
            {
                name=un.getText().toString();
                e_mail=em.getText().toString();
                pass=pa.getText().toString();
                confpass=cpa.getText().toString();
                veriphone="+91"+ph.getText().toString();
                phone=ph.getText().toString();
                gen=ge.getText().toString();
                address=addr.getText().toString();

                //Empty all edit Textes

                PhoneAuthProvider.OnVerificationStateChangedCallbacks mCallbacks;

                mCallbacks=new PhoneAuthProvider.OnVerificationStateChangedCallbacks() {
                    @Override
                    public void onVerificationCompleted(PhoneAuthCredential phoneAuthCredential) {
                        Toast.makeText(getApplicationContext(), "PHONE VERIFIED...", Toast.LENGTH_LONG).show();
                    }

                    @Override
                    public void onVerificationFailed(FirebaseException e) {
                        Toast.makeText(getApplicationContext(), "PHONE VERIFICATION FAILED...", Toast.LENGTH_LONG).show();
                    }

                    @Override
                    public void onCodeSent(String regcode,PhoneAuthProvider.ForceResendingToken forceResendingToken)
                    {
                        super.onCodeSent(regcode, forceResendingToken);
                        verificationCode=regcode;
                        Toast.makeText(getApplicationContext(),"CODE : "+verificationCode, Toast.LENGTH_LONG).show();

                        if(pass.equals(confpass))
                        {
                            Intent i2=new Intent(getApplicationContext(),OTPSending.class);
                            i2.putExtra("vericode",verificationCode);
                            i2.putExtra("name",name);
                            i2.putExtra("pass",pass);
                            i2.putExtra("email",e_mail);
                            i2.putExtra("phone",phone);
                            i2.putExtra("gender",gen);
                            i2.putExtra("address",address);
                            startActivity(i2);
                        }


                    }
                };

              PhoneAuthProvider.getInstance().verifyPhoneNumber(veriphone,60l,TimeUnit.SECONDS,RegisterNewUser.this,mCallbacks);

            }
        });

    }
}
