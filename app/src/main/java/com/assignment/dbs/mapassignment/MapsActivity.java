package com.assignment.dbs.mapassignment;

import android.app.Activity;
import android.content.Context;
import android.content.Intent;
import android.graphics.Bitmap;
import android.graphics.Canvas;
import android.os.Bundle;
import android.support.v4.app.FragmentActivity;
import android.util.DisplayMetrics;
import android.util.Log;
import android.view.View;

import com.android.volley.DefaultRetryPolicy;
import com.android.volley.Request;
import com.android.volley.RequestQueue;
import com.android.volley.Response;
import com.android.volley.VolleyError;
import com.android.volley.toolbox.StringRequest;
import com.android.volley.toolbox.Volley;
import com.google.android.gms.maps.CameraUpdateFactory;
import com.google.android.gms.maps.GoogleMap;
import com.google.android.gms.maps.OnMapReadyCallback;
import com.google.android.gms.maps.SupportMapFragment;
import com.google.android.gms.maps.model.BitmapDescriptor;
import com.google.android.gms.maps.model.BitmapDescriptorFactory;
import com.google.android.gms.maps.model.LatLng;
import com.google.android.gms.maps.model.Marker;
import com.google.android.gms.maps.model.MarkerOptions;

import org.json.JSONArray;
import org.json.JSONException;
import org.json.JSONObject;

public class MapsActivity extends FragmentActivity implements OnMapReadyCallback {


    private GoogleMap GM_info;


    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_maps);
        // Obtain the SupportMapFragment and get notified when the map is ready to be used.
        SupportMapFragment mapFragment = (SupportMapFragment) getSupportFragmentManager()
                .findFragmentById(R.id.map);
        mapFragment.getMapAsync(this);
        API_CALL();
    }


    /**
     * Manipulates the map once available.
     * This callback is triggered when the map is ready to be used.
     * This is where we can add markers or lines, add listeners or move the camera. In this case,
     * we just add a marker near Sydney, Australia.
     * If Google Play services is not installed on the device, the user will be prompted to install
     * it inside the SupportMapFragment. This method will only be triggered once the user has
     * installed Google Play services and returned to the app.
     */
    @Override
    public void onMapReady(GoogleMap googleMap) {
        GM_info = googleMap;

    }

    //---------------------------------------
    protected void createMarker(LatLng ltlng, String title, String snippet) {

        final Marker mrkr = GM_info.addMarker(new MarkerOptions().position(ltlng).title(title).snippet(snippet).visible(true));
        mrkr.showInfoWindow();
    }


    public static BitmapDescriptor createDrawableFromView(Context context, View view) {
        DisplayMetrics displayMetrics = new DisplayMetrics();
        ((Activity) context).getWindowManager().getDefaultDisplay()
                .getMetrics(displayMetrics);

        view.measure(displayMetrics.widthPixels, displayMetrics.heightPixels);
        view.layout(0, 0, displayMetrics.widthPixels,displayMetrics.heightPixels);
        view.buildDrawingCache();
        Bitmap bitmap = Bitmap.createBitmap(view.getMeasuredWidth(),
                view.getMeasuredHeight(), Bitmap.Config.ARGB_8888);
        Canvas canvas = new Canvas(bitmap);
        view.draw(canvas);
        return BitmapDescriptorFactory.fromBitmap(bitmap);
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
                            DublinResponee(response) ;
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

    private void DublinResponee(String response) {
        try {

            JSONArray jsonArray = new JSONArray(response);
            if (jsonArray.length() > 0) {
                for (int i = 0; i < jsonArray.length(); i++) {
                    JSONObject jsonObject1 = jsonArray.optJSONObject(i);
                    //-------------------------------------
          /*          String available_bikes=jsonObject1.getString("available_bikes");
                    String available_bike_stands=jsonObject1.getString("available_bike_stands");
                    String banking=jsonObject1.getString("banking");
                    String bike_stands=jsonObject1.getString("bike_stands");
                    String bonus=jsonObject1.getString("bonus");
                    String contract_name=jsonObject1.getString("contract_name");
                    String last_update=jsonObject1.getString("last_update");

                    String number=jsonObject1.getString("number");
                    String status=jsonObject1.getString("status");*/

                    String name=jsonObject1.getString("name");
                    String address=jsonObject1.getString("address");

                    JSONObject possitionObject=jsonObject1.getJSONObject("position");
                    double lat=possitionObject.getDouble("lat");
                    double lng=possitionObject.getDouble("lng");
                    //------------------------------------
                    /*RootObject model=new RootObject();
                    model.setAddress("");

                    arraylist.add(model);*/

                    LatLng position = new LatLng(lat, lng);
                    createMarker(position,name , address);
                    if(i==0){
                        GM_info.animateCamera(CameraUpdateFactory.newLatLngZoom(position, 15));
                    }
                }

            }
        } catch (JSONException e) {
            e.printStackTrace();
        }
    }

    public void onclick_btist(View view) {
        startActivity(new Intent(MapsActivity.this, LocationListActivity.class));
        finish();
    }
    //----------------------------------CLOSED SCOPE--------------
}
