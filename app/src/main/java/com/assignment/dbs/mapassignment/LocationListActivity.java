package com.assignment.dbs.mapassignment;

import android.content.Intent;
import android.os.Bundle;
import android.support.v7.app.AppCompatActivity;
import android.support.v7.widget.RecyclerView;
import android.util.Log;
import android.view.View;
import android.widget.TextView;

import com.android.volley.DefaultRetryPolicy;
import com.android.volley.Request;
import com.android.volley.RequestQueue;
import com.android.volley.Response;
import com.android.volley.VolleyError;
import com.android.volley.toolbox.StringRequest;
import com.android.volley.toolbox.Volley;

import org.json.JSONArray;
import org.json.JSONException;
import org.json.JSONObject;

import java.util.ArrayList;

public class LocationListActivity extends AppCompatActivity {
     ArrayList<RootObject> arraylist = new ArrayList<>();
     RecyclerView recyclerView;
     TextView TV_loading;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_location_list);
            recyclerView = (RecyclerView) findViewById(R.id.RV_items);
            TV_loading = (TextView) findViewById(R.id.TV_loading);
        API_CALL();
    }
    private void API_CALL() {


        try {
            final String murl="https://api.jcdecaux.com/vls/v1/stations?contract=Dublin&apiKey=0b7bb315465152e2ea91cbe894fe27fbbea5c69d";
            final RequestQueue requestQueue = Volley.newRequestQueue(this);
            final StringRequest stringRequest = new StringRequest(Request.Method.GET, murl,
                    new Response.Listener<String>() {
                        @Override
                        public void onResponse(String response) {
                            Log.e("-->Response-->", response);
                            DublinResponse(response) ;
                        }
                    },
                    new Response.ErrorListener() {
                        @Override
                        public void onErrorResponse(VolleyError error) {
                            Log.e("-->Response", "---->Response On failed--" + error);
                        }
                    }) {

            };
            requestQueue.add(stringRequest);
            stringRequest.setRetryPolicy(new DefaultRetryPolicy(5000,
                    DefaultRetryPolicy.DEFAULT_MAX_RETRIES,
                    DefaultRetryPolicy.DEFAULT_BACKOFF_MULT));
        } catch (Exception ex) {
        }

    }

    private void DublinResponse(String response) {
        try {


            JSONArray jsonArray = new JSONArray(response);
            if (jsonArray.length() > 0) {
                for (int i = 0; i < jsonArray.length(); i++) {
                    JSONObject jsonObject1 = jsonArray.optJSONObject(i);
                    //-------------------------------------
                    int available_bikes=jsonObject1.getInt("available_bikes");
                    int available_bike_stands=jsonObject1.getInt("available_bike_stands");
                    boolean banking=jsonObject1.getBoolean("banking");
                    int bike_stands=jsonObject1.getInt("bike_stands");
                    boolean bonus=jsonObject1.getBoolean("bonus");
                    String contract_name=jsonObject1.getString("contract_name");
                    int last_update=jsonObject1.getInt("last_update");
                    int number=jsonObject1.getInt("number");
                    String status=jsonObject1.getString("status");
                    String name=jsonObject1.getString("name");
                    String address=jsonObject1.getString("address");

                    //------------------------------------
                    RootObject model=new RootObject();
                    model.setNumber(number);
                    model.setName(name);
                    model.setAddress(address);
                    model.setBanking(banking);
                    model.setBonus(bonus);
                    model.setStatus(status);
                    model.setContractName(contract_name);
                    model.setBikeStands(bike_stands);
                    model.setAvailableBikeStands(available_bike_stands);
                    model.setAvailableBikes(available_bikes);
                    model.setLastUpdate(last_update);


                    arraylist.add(model);

                }
                LocationListAdapter adapter = new LocationListAdapter(this, arraylist);
                recyclerView.setAdapter(adapter);
                recyclerView.setVisibility(View.VISIBLE);
                TV_loading.setVisibility(View.GONE);

            }
        } catch (JSONException e) {
            e.printStackTrace();
        }
    }

    public void Click_gotoMap(View view) {
        startActivity(new Intent(LocationListActivity.this, MapsActivity.class));
        finish();
    }
}
