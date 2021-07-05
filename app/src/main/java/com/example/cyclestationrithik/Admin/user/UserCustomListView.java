package com.example.cyclestationrithik.Admin.user;

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

public class UserCustomListView extends ArrayAdapter<String>
{
    Activity context;
    List<User> list = new ArrayList<User>();

    UserCustomListView(Activity context, List list)
    {
        super(context,R.layout.activity_admin_user_view,list);
        this.context=context;
        this.list=list;
    }

    @Override
    public View getView(final int position, View view, ViewGroup parent)
    {
        LayoutInflater inflater=context.getLayoutInflater();

        View rowView=inflater.inflate(R.layout.activity_user_custom_list_view,null,false);


        TextView tvName=rowView.findViewById(R.id.tvAdminUserViewName);
        TextView tvEmail=rowView.findViewById(R.id.tvAdminUserViewEmail);
        TextView tvPhone=rowView.findViewById(R.id.tvAdminUserViewPhone);
        TextView tvRoll=rowView.findViewById(R.id.tvAdminUserViewRole);
        Button btdel=rowView.findViewById(R.id.BtAdminUserViewDel);


        tvName.setText("NAME : "+list.get(position).name);
        tvEmail.setText("EMAIL : "+list.get(position).emailId);
        tvPhone.setText("PHONE : "+list.get(position).phone);
        tvRoll.setText("ROLE : "+list.get(position).pass);

        btdel.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v)
            {
                Toast.makeText(context.getApplicationContext(),"Name : "+list.get(position).name,Toast.LENGTH_LONG).show();

            }
        });
/*
        tvName.setText("NAME : ");
        tvEmail.setText("EMAIL : ");
        tvPhone.setText("PHONE : ");
        tvRoll.setText("ROLE : ");
*/
        return rowView;
    }


}
