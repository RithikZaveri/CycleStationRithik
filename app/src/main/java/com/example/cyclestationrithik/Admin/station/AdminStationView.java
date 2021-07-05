package com.example.cyclestationrithik.Admin.station;

import androidx.annotation.NonNull;
import androidx.appcompat.app.AppCompatActivity;

import android.app.Activity;
import android.os.Bundle;
import android.widget.ArrayAdapter;
import android.widget.ListView;
import android.widget.Toast;

import com.example.cyclestationrithik.R;
import com.google.firebase.database.DataSnapshot;
import com.google.firebase.database.DatabaseError;
import com.google.firebase.database.DatabaseReference;
import com.google.firebase.database.FirebaseDatabase;
import com.google.firebase.database.ValueEventListener;

import java.util.ArrayList;
import java.util.List;

public class AdminStationView extends Activity {

    DatabaseReference dbref;
    ListView lvAdminStationView;
    List<Station> list = new ArrayList<Station>();
    String data="";

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_admin_station_view);

        lvAdminStationView=findViewById(R.id.lvAdmminStationView);

        dbref = FirebaseDatabase.getInstance().getReference("station");

        dbref.addValueEventListener(new ValueEventListener() {
            @Override
            public void onDataChange(@NonNull DataSnapshot dataSnapshot) {

                for(DataSnapshot shot : dataSnapshot.getChildren())
                {
                    Station s1 = shot.getValue(Station.class);
                  //  data = data + s1.sid+" ,"+s1.stationname+" ,"+s1.openingTime+" ,"+s1.closingTime+","
                   //         +s1.conductedBy+" ,"+s1.noOfcycle+" ,"+s1.availableCycle+"\n";
                    list.add(s1);
                }
                Toast.makeText(getApplicationContext(),data,Toast.LENGTH_LONG).show();

                //ArrayAdapter ad=new ArrayAdapter(getApplicationContext(),android.R.layout.simple_list_item_1,list);
                StationCustomListView scl=new StationCustomListView(AdminStationView.this,list);
                lvAdminStationView.setAdapter(scl);
            }

            @Override
            public void onCancelled(@NonNull DatabaseError databaseError) {

            }
        });
    }
}
