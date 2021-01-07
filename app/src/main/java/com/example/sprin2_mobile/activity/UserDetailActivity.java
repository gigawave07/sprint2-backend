package com.example.sprin2_mobile.activity;

import android.content.Intent;
import android.os.Bundle;
import android.util.Log;
import android.view.View;
import android.widget.Button;
import android.widget.TextView;
import androidx.appcompat.app.AppCompatActivity;
import com.android.volley.Request;
import com.android.volley.RequestQueue;
import com.android.volley.Response;
import com.android.volley.VolleyError;
import com.android.volley.toolbox.JsonObjectRequest;
import com.android.volley.toolbox.Volley;
import com.example.sprin2_mobile.R;
import com.example.sprin2_mobile.entity.Customer;
import com.google.gson.Gson;
import org.json.JSONObject;

public class UserDetailActivity extends AppCompatActivity {

    TextView cusDetailName, cusDetailIdentify, cusDetailBirthDay, cusDetailNumCar;
    Button cusDetailBack, cusDetailCarList;
    String URL1 = "http://10.0.2.2:8080/customer/get-customer-detail/";
    String URL2 = "http://10.0.2.2:8080/car/amount-of-car/";


    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_user_detail);
        mapping();
        getCustomerInformation();
        controlBack();
        controlCarList();
    }

    private void mapping() {
        cusDetailName = findViewById(R.id.cusDetailName);
        cusDetailIdentify = findViewById(R.id.cusDetailIdentify);
        cusDetailBirthDay = findViewById(R.id.cusDetailBirthDay);
        cusDetailNumCar = findViewById(R.id.cusDetailNumCar);
        cusDetailBack = findViewById(R.id.cusDetailBack);
        cusDetailCarList = findViewById(R.id.cusDetailCarList);
    }

    private String getAccountId() {
        Intent intent = getIntent();
        return intent.getStringExtra("accountId");
    }

    private void getCustomerInformation() {
        URL1 += getAccountId();
        RequestQueue requestQueue = Volley.newRequestQueue(this);
        JsonObjectRequest objectRequestCustomer = new JsonObjectRequest(
                Request.Method.GET, URL1, null, new Response.Listener<JSONObject>() {
            @Override
            public void onResponse(JSONObject response) {
                setInformation(response);
            }
        }, new Response.ErrorListener() {
            @Override
            public void onErrorResponse(VolleyError error) {
            }
        });
        URL1 = "http://10.0.2.2:8080/customer/get-customer-detail/";
        requestQueue.add(objectRequestCustomer);

    }

    private void setInformation(JSONObject response) {
        Gson gson = new Gson();
        Customer customer = gson.fromJson(response.toString(), Customer.class);
        URL2 += customer.getId();
        getNumCar();
        if (customer.getFullName() != null) {
            cusDetailName.setText(customer.getFullName());
        } else {
            cusDetailName.setText("");
        }
        if (customer.getIdentityNumber() != null) {
            cusDetailIdentify.setText(customer.getIdentityNumber());
        } else {
            cusDetailIdentify.setText("");
        }
        if (customer.getBirthday() != null) {
            cusDetailBirthDay.setText(customer.getBirthday());
        } else {
            cusDetailBirthDay.setText("");
        }

    }

    private void getNumCar() {
        RequestQueue requestQueue = Volley.newRequestQueue(this);
        JsonObjectRequest objectRequestCar = new JsonObjectRequest(
                Request.Method.GET, URL2, null, new Response.Listener<JSONObject>() {
            @Override
            public void onResponse(JSONObject response) {
                Log.e("","asdddddddddddddddddddd");
                cusDetailNumCar.setText(response.toString());
            }
        }, new Response.ErrorListener() {
            @Override
            public void onErrorResponse(VolleyError error) {
            }
        }
        );
        URL2 = "http://10.0.2.2:8080/car/amount-of-car/";
        requestQueue.add(objectRequestCar);
    }

    private void controlCarList() {
        cusDetailCarList.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent intent = new Intent(UserDetailActivity.this, CarList.class);
                startActivity(intent);
            }
        });
    }

    private void controlBack() {
        cusDetailBack.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent intent = new Intent(UserDetailActivity.this, LoginActivity.class);
                startActivity(intent);
            }
        });
    }


}
