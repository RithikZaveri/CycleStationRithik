package com.example.cyclestationrithik.Admin.station;

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

import java.util.ArrayList;
import java.util.List;

public class StationCustomListView extends ArrayAdapter<Station> {

    Activity context;
    List<Station> list = new ArrayList<Station>();

    StationCustomListView(Activity context, List list)
    {
        super(context, R.layout.activity_admin_station_view,list);
        this.context=context;
        this.list=list;
    }

    @Override
    public View getView(final int position, View view, ViewGroup parent)
    {
        LayoutInflater inflater=context.getLayoutInflater();

        View rowView=inflater.inflate(R.layout.activity_station_custom_list_view,null,false);


        TextView tvStationName=rowView.findViewById(R.id.tvAdminStationViewStationName);
        TextView tvOpenTime=rowView.findViewById(R.id.tvAdminStaionViewOpeningTime);
        TextView tvCloseTime=rowView.findViewById(R.id.tvAdminStaionViewClosingTime);
        TextView tvConductedBy=rowView.findViewById(R.id.tvAdminStaionViewConductedBy);
        TextView tvNoofCycles=rowView.findViewById(R.id.tvAdminStaionViewNoOfCycle);
        TextView tvAvailableCycles=rowView.findViewById(R.id.tvAdminStaionViewAvailableCycle);
        Button btStadel=rowView.findViewById(R.id.BtAdminStationViewDel);


        tvStationName.setText("STATION NAME : "+list.get(position).stationname);
        tvOpenTime.setText("OPENING TIME : "+list.get(position).openingTime);
        tvCloseTime.setText("CLOSING TIME : "+list.get(position).closingTime);
        tvConductedBy.setText("CONDUCTED BY : "+list.get(position).conductedBy);
        tvNoofCycles.setText("NO OF CYCLES : "+list.get(position).noOfcycle);
        tvAvailableCycles.setText("AVAILABLE CYCLES : "+list.get(position).availableCycle);


        btStadel.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v)
            {
                Toast.makeText(context.getApplicationContext(),"Station Name : "+list.get(position).stationname,Toast.LENGTH_LONG).show();

            }
        });

        return rowView;
    }


}
