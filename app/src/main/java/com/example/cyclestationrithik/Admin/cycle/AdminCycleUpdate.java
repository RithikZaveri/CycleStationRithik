package com.example.cyclestationrithik.Admin.cycle;

import androidx.annotation.NonNull;
import androidx.appcompat.app.AppCompatActivity;

import android.app.Activity;
import android.icu.util.Freezable;
import android.os.Bundle;
import android.view.View;
import android.widget.AdapterView;
import android.widget.ArrayAdapter;
import android.widget.Button;
import android.widget.EditText;
import android.widget.Spinner;
import android.widget.Toast;

import com.example.cyclestationrithik.R;
import com.google.firebase.database.DataSnapshot;
import com.google.firebase.database.DatabaseError;
import com.google.firebase.database.DatabaseReference;
import com.google.firebase.database.FirebaseDatabase;
import com.google.firebase.database.ValueEventListener;

import java.util.ArrayList;
import java.util.List;

public class AdminCycleUpdate extends Activity
{
    EditText EtAdminCycleUpdatecystation,EtAdminCycleUpdatecystatus,EtAdminCycleUpdatecyRegNo,EtAdminCycleUpdatecyImageUrl;
    Button BtAdminCycleUpdatecyupdate,BtAdminCycleUpdatecycan;
    DatabaseReference dblist,dbref,dbupd;
    Spinner SPAdminCycleUpdateSpinner;
    List<String> listid = new ArrayList<String>();
    List<Cycle> listUser = new ArrayList<Cycle>();

    @Override
    protected void onCreate(Bundle savedInstanceState)
    {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_admin_cycle_update);

        SPAdminCycleUpdateSpinner=findViewById(R.id.SPAdminCycleUpdateSpinner);
        EtAdminCycleUpdatecystation=findViewById(R.id.etAdminCycleUpdatestation);
        EtAdminCycleUpdatecystatus=findViewById(R.id.etAdminCycleUpdatestatus);
        EtAdminCycleUpdatecyRegNo=findViewById(R.id.etAdminCycleUpdateCycleRegNo);
        EtAdminCycleUpdatecyImageUrl=findViewById(R.id.etAdminCycleUpdateImageUrl);
        BtAdminCycleUpdatecyupdate=findViewById(R.id.etAdminCycleUpdateUpdateCycle);
        BtAdminCycleUpdatecycan=findViewById(R.id.etAdminCycleUpdateCancel);

        dblist= FirebaseDatabase.getInstance().getReference("cycle");

        dblist.addValueEventListener(new ValueEventListener() {
            @Override
            public void onDataChange(@NonNull DataSnapshot dataSnapshot)
            {
                for(DataSnapshot shot : dataSnapshot.getChildren())
                {
                    Cycle c1=shot.getValue(Cycle.class);
                    listid.add(c1.cid);
                    listUser.add(c1);
                }

                ArrayAdapter<String> ad=new ArrayAdapter<String>(getApplicationContext(),android.R.layout.simple_dropdown_item_1line,listid);
                SPAdminCycleUpdateSpinner.setAdapter(ad);
            }

            @Override
            public void onCancelled(@NonNull DatabaseError databaseError) {

            }
        });

        SPAdminCycleUpdateSpinner.setOnItemSelectedListener(new AdapterView.OnItemSelectedListener() {
            @Override
            public void onItemSelected(AdapterView<?> parent, View view, int position, long id) {
                EtAdminCycleUpdatecystation.setText(listUser.get(position).getStation());
                EtAdminCycleUpdatecystatus.setText(listUser.get(position).getStatus());
                EtAdminCycleUpdatecyRegNo.setText(""+listUser.get(position).getCycleregno());
                EtAdminCycleUpdatecyImageUrl.setText(listUser.get(position).getImageUrl());

            }

            @Override
            public void onNothingSelected(AdapterView<?> parent) {

            }
        });

        BtAdminCycleUpdatecyupdate.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                final String id = SPAdminCycleUpdateSpinner.getSelectedItem().toString();
                final String Station=EtAdminCycleUpdatecystation.getText().toString();
                final String Status=EtAdminCycleUpdatecystatus.getText().toString();
                final int regno=Integer.parseInt(EtAdminCycleUpdatecyRegNo.getText().toString());
                final String ImgUrl=EtAdminCycleUpdatecyImageUrl.getText().toString();

                dbref= FirebaseDatabase.getInstance().getReference("cycle");

                dbref.addValueEventListener(new ValueEventListener() {
                    @Override
                    public void onDataChange(@NonNull DataSnapshot dataSnapshot) {
                        for(DataSnapshot shot : dataSnapshot.getChildren())
                        {
                            Cycle c2 = shot.getValue(Cycle.class);
                            if(c2.cid.equals(id))
                            {
                                dbupd=FirebaseDatabase.getInstance().getReference("cycle").child(id);
                                Cycle cy=new Cycle(id,Station,Status,regno,ImgUrl);
                                dbupd.setValue(cy);
                                break;
                            }
                        }
                        Toast.makeText(getApplicationContext(),"CYCLE UPDATED SUCCESSFULLY......",Toast.LENGTH_LONG).show();

                        EtAdminCycleUpdatecystation.setText("");
                        EtAdminCycleUpdatecystatus.setText("");
                        EtAdminCycleUpdatecyRegNo.setText("");
                        EtAdminCycleUpdatecyImageUrl.setText("");

                    }

                    @Override
                    public void onCancelled(@NonNull DatabaseError databaseError) {

                    }
                });

            }
        });
    }
}









