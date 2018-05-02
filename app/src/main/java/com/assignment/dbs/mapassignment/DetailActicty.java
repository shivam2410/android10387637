package com.assignment.dbs.mapassignment;

import android.content.Intent;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.util.Log;
import android.widget.TextView;

import java.util.ArrayList;

public class DetailActicty extends AppCompatActivity {
    TextView  number,name,address,banking, bonus,
            status,contract_name,bike_stands,
            available_bike_stands,available_bikes,last_update;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_detail_acticty);
        Initview();
    }

    private void Initview() {
        number=(TextView)findViewById(R.id.number);
        name=(TextView)findViewById(R.id.name);
        address=(TextView)findViewById(R.id.address);
        banking=(TextView)findViewById(R.id.banking);
        bonus=(TextView)findViewById(R.id.bonus);
        status=(TextView)findViewById(R.id.status);
        contract_name=(TextView)findViewById(R.id.contract_name);
        bike_stands=(TextView)findViewById(R.id.bike_stands);
        available_bike_stands=(TextView)findViewById(R.id.available_bike_stands);
        available_bikes=(TextView)findViewById(R.id.available_bikes);
        last_update=(TextView)findViewById(R.id.last_update);



        Intent i=getIntent();
        number.setText("number : "+i.getStringExtra("number"));
        name.setText("name : "+i.getStringExtra("name"));
        address.setText("address : "+i.getStringExtra("address"));
        banking.setText("banking : "+i.getStringExtra("banking"));
        bonus.setText("bonus : "+i.getStringExtra("bonus"));
        status.setText("status : "+i.getStringExtra("status"));
        contract_name.setText("contract name : "+i.getStringExtra("contract_name"));
        bike_stands.setText("bike_stands : "+i.getStringExtra("bike_stands"));
        available_bike_stands.setText("available bike stands : "+i.getStringExtra("available_bike_stands"));
        available_bikes.setText("available bikes : "+i.getStringExtra("available_bikes"));
        last_update.setText("last update : "+i.getStringExtra("last_update"));
    }
}
