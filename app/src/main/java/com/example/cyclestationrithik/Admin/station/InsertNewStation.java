package com.example.cyclestationrithik.Admin.station;

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

public class InsertNewStation extends Activity
{
    private static String sid;
    EditText staname,stalati,stalongi,stadesc,staoptime,staclotime,staconductby,stanoofcycle,staavailcycle;
    Button staadd,stacan;
    DatabaseReference dbref;

    @Override
    protected void onCreate(Bundle savedInstanceState)
    {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_insert_new_station);

        staname = findViewById(R.id.insertnewstationname);
        stalati = findViewById(R.id.insertnewstationlati);
        stalongi = findViewById(R.id.insertnewstationlongi);
        stadesc = findViewById(R.id.insertnewstationdesc);
        staoptime = findViewById(R.id.insertnewstationopenTime);
        staclotime = findViewById(R.id.insertnewstationcloseTime);
        staconductby = findViewById(R.id.insertnewstationconductedby);
        stanoofcycle = findViewById(R.id.insertnewstationnoOfCycle);
        staavailcycle = findViewById(R.id.insertnewstationavailCycle);
        staadd=findViewById(R.id.insertnewstationAddStation);
        stacan=findViewById(R.id.insertnewstationCancel);

        dbref = FirebaseDatabase.getInstance().getReference("station");

        staadd.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                 String stationname=staname.getText().toString();
                 double latitude=Double.parseDouble(stalati.getText().toString());
                 double longitude=Double.parseDouble(stalongi.getText().toString());;
                 String description=stadesc.getText().toString();
                 String openingTime=staoptime.getText().toString();
                 String closingTime=staclotime.getText().toString();
                 String conductedBy=staconductby.getText().toString();
                 int noOfcycle=Integer.parseInt(stanoofcycle.getText().toString());
                 int availableCycle=Integer.parseInt(staavailcycle.getText().toString());

                if(TextUtils.isEmpty(sid))
                {
                    String id=dbref.push().getKey();
                    Station s1=new Station(sid,stationname,latitude,longitude,description,openingTime,closingTime,
                            conductedBy,noOfcycle,availableCycle);
                    dbref.child(id).setValue(s1);
                    Toast.makeText(getApplicationContext(),"STATION SUCCESSFULLY ADDED....",Toast.LENGTH_LONG).show();
                }

                staname.setText("");
                stalati.setText("");
                stalongi.setText("");
                stadesc.setText("");
                staoptime.setText("");
                staclotime.setText("");
                staavailcycle.setText("");
                stanoofcycle.setText("");
                staconductby.setText("");

            }
        });


    }
}
