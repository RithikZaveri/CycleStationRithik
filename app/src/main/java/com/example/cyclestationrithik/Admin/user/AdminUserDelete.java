package com.example.cyclestationrithik.Admin.user;

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

public class AdminUserDelete extends Activity
{
    Spinner SPAdminUserDelete;
    Button BtAdminUserDeletedel;
    DatabaseReference dblist,dbdel;
    List<String> listid = new ArrayList<String>();
    List<User> listUser = new ArrayList<User>();
    String sid;

    @Override
    protected void onCreate(Bundle savedInstanceState)
    {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_admin_user_delete);

        SPAdminUserDelete=findViewById(R.id.SPAdminUserDeletespinner);
        BtAdminUserDeletedel=findViewById(R.id.BtAdminUserDeletedelete);

        dblist= FirebaseDatabase.getInstance().getReference("user");

        dblist.addValueEventListener(new ValueEventListener() {
            @Override
            public void onDataChange(@NonNull DataSnapshot dataSnapshot) {
                for(DataSnapshot shot : dataSnapshot.getChildren())
                {
                    User u1=shot.getValue(User.class);
                    listid.add(u1.uid);
                    listUser.add(u1);
                }

                ArrayAdapter<String> ad=new ArrayAdapter<String>(getApplicationContext(),android.R.layout.simple_dropdown_item_1line,listid);
                SPAdminUserDelete.setAdapter(ad);
            }

            @Override
            public void onCancelled(@NonNull DatabaseError databaseError) {

            }
        });

        SPAdminUserDelete.setOnItemSelectedListener(new AdapterView.OnItemSelectedListener() {
            @Override
            public void onItemSelected(AdapterView<?> parent, View view, int position, long id) {
                sid = SPAdminUserDelete.getSelectedItem().toString();
            }

            @Override
            public void onNothingSelected(AdapterView<?> parent) {

            }
        });

        BtAdminUserDeletedel.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                dbdel=FirebaseDatabase.getInstance().getReference();
                Query delq=dbdel.child("user").orderByChild("uid").equalTo(sid);

                delq.addListenerForSingleValueEvent(new ValueEventListener() {
                    @Override
                    public void onDataChange(@NonNull DataSnapshot dataSnapshot) {

                        for(DataSnapshot shot : dataSnapshot.getChildren())
                        {
                            shot.getRef().removeValue();
                        }

                        Toast.makeText(getApplicationContext(),"User Deleted Sucessfully...",Toast.LENGTH_LONG).show();
                    }

                    @Override
                    public void onCancelled(@NonNull DatabaseError databaseError) {

                    }
                });


            }
        });
    }
}
