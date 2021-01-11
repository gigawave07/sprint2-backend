package com.example.sprin2_mobile.activity;

import android.content.Intent;
import android.os.Bundle;
import android.util.Log;
import android.view.View;
import android.widget.AdapterView;
import android.widget.ArrayAdapter;
import android.widget.ListView;
import android.widget.Toast;
import androidx.appcompat.app.AppCompatActivity;
import com.android.volley.RequestQueue;
import com.android.volley.Response;
import com.android.volley.VolleyError;
import com.android.volley.toolbox.JsonArrayRequest;
import com.android.volley.toolbox.Volley;
import com.example.sprin2_mobile.R;
import com.example.sprin2_mobile.adapter.CarAdapter;
import com.example.sprin2_mobile.entity.Car;
import com.google.gson.Gson;
import org.json.JSONArray;
import org.json.JSONException;
import org.json.JSONObject;

import java.util.ArrayList;
import java.util.List;

public class CarList extends AppCompatActivity {

    private ListView listViewCar;
    private static final String URL_GET_LIST_CAR = "http://10.0.2.2:8080/car/get-list-car/";
    List<Car> carList;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_car_list);
        mapping();
        getListCar();
    }


    private void getListCar() {
        carList = new ArrayList<>();
        String url = URL_GET_LIST_CAR;
        url += getCustomerId();
        RequestQueue requestQueue = Volley.newRequestQueue(this);
        JsonArrayRequest jsonArrayRequest = new JsonArrayRequest(url, new Response.Listener<JSONArray>() {
            @Override
            public void onResponse(JSONArray response) {
                try {
                    Car car;
                    Gson gson = new Gson();
                    for (int i = 0; i < response.length(); i++) {
                        JSONObject temp = response.getJSONObject(i);
                        car = gson.fromJson(temp.toString(), Car.class);
                        carList.add(car);
                    }
                    displayCarList(carList);
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

    private void displayCarList(List<Car> carList) {
        CarAdapter carAdapter = new CarAdapter(carList, CarList.this);
        listViewCar.setAdapter(carAdapter);
        listViewCar.setOnItemClickListener(new AdapterView.OnItemClickListener() {
            @Override
            public void onItemClick(AdapterView<?> parent, View view, int position, long id) {
                Intent intent = new Intent(CarList.this, CarDetailActivity.class);
                intent.putExtra("id", carList.get(position).getId());
                intent.putExtra("licensePlate", carList.get(position).getLicensePlate());
                intent.putExtra("carType", carList.get(position).getCarType());
                intent.putExtra("beginDate", carList.get(position).getBeginDate());
                intent.putExtra("endDate", carList.get(position).getEndDate());
                intent.putExtra("typeCard", carList.get(position).getTypeCard());
                intent.putExtra("floor", carList.get(position).getFloor());
                intent.putExtra("slotNum", carList.get(position).getSlotNum());
                startActivity(intent);
            }
        });
    }


    private String getCustomerId() {
        Intent intent = getIntent();
        return intent.getStringExtra("customerId");
    }

    private void mapping() {
        listViewCar = findViewById(R.id.listViewCar);
    }
}
