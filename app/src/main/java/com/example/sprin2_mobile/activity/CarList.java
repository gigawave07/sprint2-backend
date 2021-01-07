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
import com.android.volley.toolbox.JsonArrayRequest;
import com.android.volley.toolbox.JsonObjectRequest;
import com.android.volley.toolbox.Volley;
import com.example.sprin2_mobile.R;
import com.example.sprin2_mobile.entity.Car;
import com.google.gson.Gson;
import com.google.gson.JsonArray;
import com.google.gson.JsonObject;
import org.json.JSONArray;
import org.json.JSONException;
import org.json.JSONObject;

import java.util.ArrayList;
import java.util.List;

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
        Log.e("", " ----------------------------------------------------");
        String url = URL_GET_LIST_CAR;
        url += getCustomerId();
//        url += "4";
        RequestQueue requestQueue = Volley.newRequestQueue(this);
        JsonArrayRequest jsonArrayRequest = new JsonArrayRequest(url, new Response.Listener<JSONArray>() {
            @Override
            public void onResponse(JSONArray response) {
                try {
                    List<Car> carList = new ArrayList<>();
                    Car car;
                    Gson gson = new Gson();
                    for (int i = 0; i < response.length(); i++) {
                        JSONObject temp = response.getJSONObject(i);
                        car = gson.fromJson(temp.toString(), Car.class);
                        carList.add(car);
                    }
                    Log.e("", carList.toString());
                } catch (JSONException e) {
                    e.printStackTrace();
                }
            }
        }, new Response.ErrorListener() {
            @Override
            public void onErrorResponse(VolleyError error) {
            }
        });
        requestQueue.add(jsonArrayRequest);
    }


    private String getCustomerId() {
        Intent intent = getIntent();
        return intent.getStringExtra("customerId");
    }

    private void mapping() {
        listViewCar = findViewById(R.id.listViewCar);
    }
}
