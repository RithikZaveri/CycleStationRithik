package com.example.cyclestationrithik.Admin.cycle;

import androidx.annotation.NonNull;
import androidx.appcompat.app.AppCompatActivity;

import android.app.Activity;
import android.os.Bundle;
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

public class AdminCycleDelete extends Activity
{
    Spinner SPAdminCycleDelete;
    Button BtAdminCycleDeletedel;
    DatabaseReference dblist,dbdel;
    List<String> listid = new ArrayList<String>();
    List<Cycle> listCycle = new ArrayList<Cycle>();
    String sid;

    @Override
    protected void onCreate(Bundle savedInstanceState)
    {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_admin_cycle_delete);

        SPAdminCycleDelete=findViewById(R.id.SPAdminCycleDeletespinner);
        BtAdminCycleDeletedel=findViewById(R.id.BtAdminCycleDeletedelete);

        dblist=FirebaseDatabase.getInstance().getReference("cycle");

        dblist.addValueEventListener(new ValueEventListener() {
            @Override
            public void onDataChange(@NonNull DataSnapshot dataSnapshot) {
                for(DataSnapshot shot : dataSnapshot.getChildren())
                {
                    Cycle c1 = shot.getValue(Cycle.class);
                    listid.add(c1.cid);
                    listCycle.add(c1);
                }

                ArrayAdapter<String> ad= new ArrayAdapter<String>(getApplicationContext(),android.R.layout.simple_dropdown_item_1line,listid);
                SPAdminCycleDelete.setAdapter(ad);
            }

            @Override
            public void onCancelled(@NonNull DatabaseError databaseError) {

            }
        });

        SPAdminCycleDelete.setOnItemSelectedListener(new AdapterView.OnItemSelectedListener() {
            @Override
            public void onItemSelected(AdapterView<?> parent, View view, int position, long id) {
                sid=SPAdminCycleDelete.getSelectedItem().toString();
            }

            @Override
            public void onNothingSelected(AdapterView<?> parent) {

            }
        });

        BtAdminCycleDeletedel.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                dbdel=FirebaseDatabase.getInstance().getReference();
                Query delq=dbdel.child("cycle").orderByChild("cid").equalTo(sid);

                delq.addListenerForSingleValueEvent(new ValueEventListener() {
                    @Override
                    public void onDataChange(@NonNull DataSnapshot dataSnapshot) {
                        for(DataSnapshot shot : dataSnapshot.getChildren())
                        {
                            shot.getRef().removeValue();
                        }

                        Toast.makeText(getApplicationContext(),"Cycle Deleted Successfully....",Toast.LENGTH_LONG).show();
                    }

                    @Override
                    public void onCancelled(@NonNull DatabaseError databaseError) {

                    }
                });

            }
        });

    }
}
