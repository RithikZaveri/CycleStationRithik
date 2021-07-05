package com.example.cyclestationrithik.Admin.station;

import androidx.annotation.NonNull;
import androidx.appcompat.app.AppCompatActivity;

import android.app.Activity;
import android.os.Bundle;
import android.util.Log;
import android.view.View;
import android.widget.AdapterView;
import android.widget.ArrayAdapter;
import android.widget.Button;
import android.widget.Spinner;
import android.widget.Toast;

import com.example.cyclestationrithik.R;
import com.google.firebase.database.DataSnapshot;
import com.google.firebase.database.DatabaseError;
import com.google.firebase.database.DatabaseReference;
import com.google.firebase.database.FirebaseDatabase;
import com.google.firebase.database.Query;
import com.google.firebase.database.ValueEventListener;

import java.util.ArrayList;
import java.util.List;

public class AdminStationDelete extends Activity
{
    Spinner SPAdminStationDelete;
    Button BtAdminStationDeletedel;
    DatabaseReference dblist,dbdel;
    List<String> listsid = new ArrayList<String>();
    List<Station> listStation = new ArrayList<Station>();
    String stid;

    @Override
    protected void onCreate(Bundle savedInstanceState)
    {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_admin_station_delete);

        SPAdminStationDelete=findViewById(R.id.SPAdminStationDeletespinner);
        BtAdminStationDeletedel=findViewById(R.id.BtAdminStationDeletedelete);

        dblist=FirebaseDatabase.getInstance().getReference("station");

        dblist.addValueEventListener(new ValueEventListener() {
            @Override
            public void onDataChange(@NonNull DataSnapshot dataSnapshot) {
                for(DataSnapshot shot : dataSnapshot.getChildren())
                {
Toast.makeText(getApplicationContext(),"inn1 ",Toast.LENGTH_LONG).show();
                    Station s1 = shot.getValue(Station.class);


                    Toast.makeText(getApplicationContext(),"inn2 ",Toast.LENGTH_LONG).show();
                    listsid.add(s1.sid);


                    Toast.makeText(getApplicationContext(),"inn3 ",Toast.LENGTH_LONG).show();
                    listStation.add(s1);
                }

                ArrayAdapter<String> ad= new ArrayAdapter<String>(getApplicationContext(),android.R.layout.simple_dropdown_item_1line,listsid);
                SPAdminStationDelete.setAdapter(ad);
            }

            @Override
            public void onCancelled(@NonNull DatabaseError databaseError) {

            }
        });

        SPAdminStationDelete.setOnItemSelectedListener(new AdapterView.OnItemSelectedListener() {
            @Override
            public void onItemSelected(AdapterView<?> parent, View view, int position, long id) {
                stid=SPAdminStationDelete.getSelectedItem().toString();
            }

            @Override
            public void onNothingSelected(AdapterView<?> parent) {

            }
        });

        BtAdminStationDeletedel.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                dbdel = FirebaseDatabase.getInstance().getReference();
                Query delq = dbdel.child("station").orderByChild("sid").equalTo(stid);

                delq.addListenerForSingleValueEvent(new ValueEventListener() {
                    @Override
                    public void onDataChange(@NonNull DataSnapshot dataSnapshot) {
                        for (DataSnapshot shot : dataSnapshot.getChildren()) {
                            shot.getRef().removeValue();
                        }

                        Toast.makeText(getApplicationContext(), "Station deleted Successfully...", Toast.LENGTH_LONG).show();
                    }

                    @Override
                    public void onCancelled(@NonNull DatabaseError databaseError) {

                    }
                });

            }
        });


    }
}
