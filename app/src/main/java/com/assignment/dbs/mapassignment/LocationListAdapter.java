package com.assignment.dbs.mapassignment;

import android.content.Context;
import android.content.Intent;
import android.support.v7.widget.RecyclerView;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.RelativeLayout;
import android.widget.TextView;


import java.io.Serializable;
import java.text.SimpleDateFormat;
import java.util.ArrayList;
import java.util.Calendar;
import java.util.Date;


public class LocationListAdapter extends RecyclerView.Adapter<LocationListAdapter.MyViewHolder> implements View.OnClickListener {
    public ArrayList<RootObject> arrayList = new ArrayList<>();
    public Context context;
    private RootObject model;

    public LocationListAdapter(Context context, ArrayList<RootObject> arrayList) {
        this.arrayList.clear();
        this.arrayList = arrayList;
        this.context = context;

    }

    @Override
    public LocationListAdapter.MyViewHolder onCreateViewHolder(ViewGroup parent, int viewType) {
        View view = LayoutInflater.from(context).inflate(R.layout.list_item_view, parent, false);

        MyViewHolder holder = new MyViewHolder(view);
        return holder;
    }

    @Override
    public void onBindViewHolder(MyViewHolder holder, int position) {
        final MyViewHolder contactViewHolder = (MyViewHolder) holder;
        model = arrayList.get(position);


        contactViewHolder.TV_title.setText(model.getName());
        contactViewHolder.Status.setText("Status : "+model.getStatus());
        contactViewHolder.Contract_name.setText("Contract_name : "+model.getContractName());
        contactViewHolder.RL_row.setTag(position);
        contactViewHolder.RL_row.setOnClickListener(this);

    }

    @Override
    public int getItemCount() {
        return arrayList.size();
    }

    @Override
    public void onClick(View v) {
        Integer position = (Integer) v.getTag();

        Intent intent= new Intent(context, DetailActicty.class);
        intent.putExtra("number",String.valueOf(arrayList.get(position).getNumber()));
        intent.putExtra("name",arrayList.get(position).getName());
        intent.putExtra("address",arrayList.get(position).getAddress());
        intent.putExtra("banking",String.valueOf(arrayList.get(position).getBanking()));
        intent.putExtra("bonus",String.valueOf(arrayList.get(position).getBonus()));
        intent.putExtra("status",String.valueOf(arrayList.get(position).getStatus()));
        intent.putExtra("contract_name",arrayList.get(position).getContractName());
        intent.putExtra("bike_stands",String.valueOf(arrayList.get(position).getBikeStands()));
        intent.putExtra("available_bike_stands",String.valueOf(arrayList.get(position).getAvailableBikeStands()));
        intent.putExtra("available_bikes",String.valueOf(arrayList.get(position).getAvailableBikes()));


        try {
            long ms = arrayList.get(position).getLastUpdate();
            // Date date = df.parse(parseDateTime);
            Calendar calendar = Calendar.getInstance();
            calendar.setTimeInMillis(ms);

            SimpleDateFormat formatter = new SimpleDateFormat("dd MMM yyyy hh:mm a");
            intent.putExtra("last_update", formatter.format(calendar.getTime()));

        } catch (Exception ex) {
        }



        context.startActivity(intent);
    }


    public class MyViewHolder extends RecyclerView.ViewHolder {
        public TextView TV_title, Status, Contract_name;
        RelativeLayout RL_row;

        public MyViewHolder(View itemView) {
            super(itemView);
            TV_title = (TextView) itemView.findViewById(R.id.TV_title);
            Status = (TextView) itemView.findViewById(R.id.Status);
            Contract_name = (TextView) itemView.findViewById(R.id.Contract_name);
            RL_row = (RelativeLayout) itemView.findViewById(R.id.RL_row);
        }
    }
}
