package com.example.cyclestationrithik.Admin.cycle;

import androidx.annotation.NonNull;
import androidx.appcompat.app.AppCompatActivity;

import android.app.Activity;
import android.os.Bundle;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ArrayAdapter;
import android.widget.Button;
import android.widget.TextView;
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

public class CycleCustomListView extends ArrayAdapter
{
    Activity context;
    List<Cycle> list = new ArrayList<Cycle>();
    DatabaseReference dbdel;

    CycleCustomListView(Activity context, List list)
    {
        super(context,R.layout.activity_admin_cycle_view,list);
        this.context=context;
        this.list=list;
    }

    @Override
    public View getView(final int position, View view, ViewGroup parent) {
        LayoutInflater inflater = context.getLayoutInflater();

        View rowView = inflater.inflate(R.layout.activity_cycle_custom_list_view, null, false);


        TextView tvStation = rowView.findViewById(R.id.tvAdminCycleViewStation);
        TextView tvStatus = rowView.findViewById(R.id.tvAdminCycleViewStatus);
        TextView tvRegNo = rowView.findViewById(R.id.tvAdminCycleViewCycleregno);
        TextView tvImageUrl = rowView.findViewById(R.id.tvAdminCycleViewImageUrl);
        Button btcycledel = rowView.findViewById(R.id.BtAdminCycleViewDel);


        tvStation.setText("STATION : " + list.get(position).station);
        tvStatus.setText("STATUS : " + list.get(position).status);
        tvRegNo.setText("REG. NO : " + list.get(position).cycleregno);
        tvImageUrl.setText("IMAGE URL : " + list.get(position).imageUrl);

        btcycledel.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                dbdel= FirebaseDatabase.getInstance().getReference();
                Query delq=dbdel.child("cycle").orderByChild("cid").equalTo(list.get(position).cid);

                delq.addListenerForSingleValueEvent(new ValueEventListener() {
                    @Override
                    public void onDataChange(@NonNull DataSnapshot dataSnapshot) {
                        for(DataSnapshot shot : dataSnapshot.getChildren())
                        {
                            shot.getRef().removeValue();
                        }
                    }

                    @Override
                    public void onCancelled(@NonNull DatabaseError databaseError) {

                    }
                });



                list.remove(position);
                Toast.makeText(context.getApplicationContext(), "Cycle Deleted", Toast.LENGTH_LONG).show();

            }
        });

        return rowView;

    }

}
