package com.example.cyclestationrithik.Admin.cycle;

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

public class AdminCycleView extends Activity
{
    DatabaseReference dbref;
    ListView lvAdminCycleView;
    String data="";
    List<Cycle> list=new ArrayList<Cycle>();


    @Override
    protected void onCreate(Bundle savedInstanceState)
    {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_admin_cycle_view);

        lvAdminCycleView=findViewById(R.id.lvAdminCycleView);

        dbref= FirebaseDatabase.getInstance().getReference("cycle");

        dbref.addValueEventListener(new ValueEventListener() {
            @Override
            public void onDataChange(@NonNull DataSnapshot dataSnapshot) {
                for(DataSnapshot shot : dataSnapshot.getChildren())
                {
                    Cycle c1 = shot.getValue(Cycle.class);
                    //data=data+c1.cid+" , "+c1.status+" , "+c1.station+" , "+c1.imageUrl+" , "+c1.cycleregno+"\n";
                    list.add(c1);
                }
                Toast.makeText(getApplicationContext(),data,Toast.LENGTH_LONG).show();

                // ArrayAdapter ad=new ArrayAdapter(getApplicationContext(),android.R.layout.simple_list_item_1,list);
                 CycleCustomListView ccl=new CycleCustomListView(AdminCycleView.this,list);
                lvAdminCycleView.setAdapter(ccl);

            }

            @Override
            public void onCancelled(@NonNull DatabaseError databaseError) {

            }
        });
    }
}
