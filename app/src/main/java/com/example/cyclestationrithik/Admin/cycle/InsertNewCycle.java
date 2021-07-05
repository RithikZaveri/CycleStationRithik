package com.example.cyclestationrithik.Admin.cycle;

import androidx.appcompat.app.AppCompatActivity;

import android.app.Activity;
import android.os.Bundle;
import android.text.TextUtils;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.Toast;

import com.example.cyclestationrithik.R;
import com.google.firebase.database.DatabaseReference;
import com.google.firebase.database.FirebaseDatabase;

public class InsertNewCycle extends Activity
{
    private static String cid;
    EditText cystation,cystatus,cyRegNo,cyImageUrl;
    Button cyadd,cycan;
    DatabaseReference dbref;

    @Override
    protected void onCreate(Bundle savedInstanceState)
    {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_insert_new_cycle);

        cystation=findViewById(R.id.insertnewcyclestation);
        cystatus=findViewById(R.id.insertnewcyclestatus);
        cyRegNo=findViewById(R.id.insertnewcycleRegNo);
        cyImageUrl=findViewById(R.id.insertnewcycleimageUrl);
        cyadd=findViewById(R.id.insertnewcycleAddCycle);
        cycan=findViewById(R.id.insertnewcycleCancel);

        dbref= FirebaseDatabase.getInstance().getReference("cycle");

        cyadd.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v)
            {
                 String station=cystation.getText().toString();
                 String status=cystatus.getText().toString();
                 int cycleregno=Integer.parseInt(cyRegNo.getText().toString());
                 String imageUrl=cyImageUrl.getText().toString();

                if(TextUtils.isEmpty(cid))
                {
                    String id=dbref.push().getKey();
                    Cycle c1=new Cycle(id,station,status,cycleregno,imageUrl);
                    dbref.child(id).setValue(c1);
                    Toast.makeText(getApplicationContext(),"CYCLE SUCCESSFULLY ADDED....",Toast.LENGTH_LONG).show();
                }

                cystation.setText("");
                cystatus.setText("");
                cyRegNo.setText("");
                cyImageUrl.setText("");
            }
        });
    }
}
