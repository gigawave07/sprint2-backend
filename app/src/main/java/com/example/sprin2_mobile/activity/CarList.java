package com.example.sprin2_mobile.activity;

import android.content.Intent;
import android.os.Bundle;
import android.util.Log;
import android.widget.ListView;
import androidx.appcompat.app.AppCompatActivity;
import com.android.volley.Request;
import com.android.volley.RequestQueue;
import com.android.volley.Response;
import com.android.volley.VolleyError;
import com.android.volley.toolbox.JsonObjectRequest;
import com.android.volley.toolbox.Volley;
import com.example.sprin2_mobile.R;
import org.json.JSONObject;

public class CarList extends AppCompatActivity {

    private ListView listViewCar;
    private static final String URL_GET_LIST_CAR = "http://10.0.2.2:8080/car/get-list-car/";

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_car_list);
        mapping();
        getListCar();
    }

    private void getListCar() {
        String url = URL_GET_LIST_CAR;
        url += getCustomerId();
        RequestQueue requestQueue = Volley.newRequestQueue(this);
        JsonObjectRequest jsonObjectRequest = new JsonObjectRequest(
                Request.Method.GET, url, null, new Response.Listener<JSONObject>() {
            @Override
            public void onResponse(JSONObject response) {

                Log.e("", response.toString());
            }
        }, new Response.ErrorListener() {
            @Override
            public void onErrorResponse(VolleyError error) {
            }
        });
        requestQueue.add(jsonObjectRequest);
    }


    private String getCustomerId() {
        Intent intent = getIntent();
        return intent.getStringExtra("customerId");
    }

    private void mapping() {
        listViewCar = findViewById(R.id.listViewCar);
    }
}
