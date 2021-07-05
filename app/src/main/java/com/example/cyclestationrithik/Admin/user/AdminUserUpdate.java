package com.example.cyclestationrithik.Admin.user;

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

public class AdminUserUpdate extends Activity
{
    EditText SPAdminUserUpdateName,SPAdminUserUpdateEmail,SPAdminUserUpdatePhone,SPAdminUserUpdateGender,SPAdminUserUpdateAddr,
            SPAdminUserUpdatePass;
    Button SPAdminUserUpdateupdateuser,SPAdminUserUpdatecancel;
    Spinner SPAdminUserUpdateSpinner;
    DatabaseReference dblist,dbref,dbupd;
    List<String> listid = new ArrayList<String>();
    List<User> listUser = new ArrayList<User>();

    @Override
    protected void onCreate(Bundle savedInstanceState)
    {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_admin_user_update);

        SPAdminUserUpdateSpinner=findViewById(R.id.SPAdminUserUpdateSpinner);
        SPAdminUserUpdateName=findViewById(R.id.SPAdminUserUpdateUsername);
        SPAdminUserUpdateEmail=findViewById(R.id.SPAdminUserUpdateemail);
        SPAdminUserUpdatePhone=findViewById(R.id.SPAdminUserUpdatephone);
        SPAdminUserUpdateGender=findViewById(R.id.SPAdminUserUpdategender);
        SPAdminUserUpdateAddr=findViewById(R.id.SPAdminUserUpdateaddress);
        SPAdminUserUpdatePass=findViewById(R.id.SPAdminUserUpdatePass);
        SPAdminUserUpdateupdateuser=findViewById(R.id.SPAdminUserUpdateupdateuser);
        SPAdminUserUpdatecancel=findViewById(R.id.SPAdminUserUpdatecancel);

        dblist= FirebaseDatabase.getInstance().getReference("user");

        dblist.addValueEventListener(new ValueEventListener() {
            @Override
            public void onDataChange(@NonNull DataSnapshot dataSnapshot)
            {
                for(DataSnapshot shot : dataSnapshot.getChildren())
                {
                    User u1=shot.getValue(User.class);
                    listid.add(u1.uid);
                    listUser.add(u1);
                }

                ArrayAdapter<String> ad=new ArrayAdapter<String>(getApplicationContext(),android.R.layout.simple_dropdown_item_1line,listid);
                SPAdminUserUpdateSpinner.setAdapter(ad);
            }

            @Override
            public void onCancelled(@NonNull DatabaseError databaseError) {

            }
        });

        SPAdminUserUpdateSpinner.setOnItemSelectedListener(new AdapterView.OnItemSelectedListener()
        {
            @Override
            public void onItemSelected(AdapterView<?> parent, View view, int position, long id) {
                SPAdminUserUpdateName.setText(listUser.get(position).getName());
                SPAdminUserUpdateEmail.setText(listUser.get(position).getEmailId());
                SPAdminUserUpdatePhone.setText(""+listUser.get(position).getPhone());
                SPAdminUserUpdateAddr.setText(listUser.get(position).getAddress());
                SPAdminUserUpdateGender.setText(listUser.get(position).getGender());
                SPAdminUserUpdatePass.setText(listUser.get(position).getPass());

            }

            @Override
            public void onNothingSelected(AdapterView<?> parent) {

            }
         });


        SPAdminUserUpdateupdateuser.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v)
            {
                final String id = SPAdminUserUpdateSpinner.getSelectedItem().toString();
                final String name=SPAdminUserUpdateName.getText().toString();
                final String email=SPAdminUserUpdateEmail.getText().toString();
                final long phone=Long.parseLong(SPAdminUserUpdatePhone.getText().toString());
                final String addr=SPAdminUserUpdateAddr.getText().toString();
                final String gen=SPAdminUserUpdateGender.getText().toString();
                final String role=SPAdminUserUpdatePass.getText().toString();

                dbref= FirebaseDatabase.getInstance().getReference("user");

                dbref.addValueEventListener(new ValueEventListener() {
                    @Override
                    public void onDataChange(@NonNull DataSnapshot dataSnapshot) {

                        for(DataSnapshot shot : dataSnapshot.getChildren())
                        {
                            User u2=shot.getValue(User.class);
                            if(u2.uid.equals(id))
                            {
                                dbupd=FirebaseDatabase.getInstance().getReference("user").child(id);
                                User us=new User(id,name,email,phone,addr,gen,role);
                                dbupd.setValue(us);
                                break;
                            }
                        }
                        Toast.makeText(getApplicationContext(),"USER UPDATED SUCCESSFULLY......",Toast.LENGTH_LONG).show();

                        SPAdminUserUpdateName.setText("");
                        SPAdminUserUpdateEmail.setText("");
                        SPAdminUserUpdatePhone.setText("");
                        SPAdminUserUpdateGender.setText("");
                        SPAdminUserUpdateAddr.setText("");
                        SPAdminUserUpdatePass.setText("");
                    }

                    @Override
                    public void onCancelled(@NonNull DatabaseError databaseError) {

                    }
                });
            }
        });


    }
}
