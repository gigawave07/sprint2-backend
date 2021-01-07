package com.example.sprin2_mobile.activity;

import android.content.Intent;
import android.os.Bundle;
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
import com.example.sprin2_mobile.entity.Message;
import com.google.gson.Gson;
import org.json.JSONObject;

public class UserDetailActivity extends AppCompatActivity {

    private static final String URL_GET_CUSTOMER_INFORMATION = "http://10.0.2.2:8080/customer/get-customer-detail/";
    private static final String URL_GET_CAR_QUANTITY = "http://10.0.2.2:8080/car/amount-of-car/";
    TextView customerDetailName;
    TextView customerDetailIdentify;
    TextView customerDetailBirthDay;
    TextView customerDetailNumCar;
    Button customerDetailBack;
    Button customerDetailCarList;
    String customerId;

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
        customerDetailName = findViewById(R.id.customerDetailName);
        customerDetailIdentify = findViewById(R.id.customerDetailIdentify);
        customerDetailBirthDay = findViewById(R.id.customerDetailBirthDay);
        customerDetailNumCar = findViewById(R.id.customerDetailNumCar);
        customerDetailBack = findViewById(R.id.customerDetailBack);
        customerDetailCarList = findViewById(R.id.customerDetailCarList);
    }

    private String getAccountId() {
        Intent intent = getIntent();
        return intent.getStringExtra("accountId");
    }

    private void getCustomerInformation() {
        String url1 = URL_GET_CUSTOMER_INFORMATION;
        url1 += getAccountId();
        RequestQueue requestQueue = Volley.newRequestQueue(this);
        JsonObjectRequest objectRequestCustomer = new JsonObjectRequest(
                Request.Method.GET, url1, null, new Response.Listener<JSONObject>() {
            @Override
            public void onResponse(JSONObject response) {
                setInformation(response);
            }
        }, new Response.ErrorListener() {
            @Override
            public void onErrorResponse(VolleyError error) {
            }
        });
        requestQueue.add(objectRequestCustomer);
    }

    private void setInformation(JSONObject response) {
        String url2 = URL_GET_CAR_QUANTITY;
        Gson gson = new Gson();
        Customer customer = gson.fromJson(response.toString(), Customer.class);
        url2 += customer.getId();
        customerId = customer.getId();
        getNumCar(url2);
        boolean isCustomerNameNull = customer.getFullName() != null;
        if (isCustomerNameNull) {
            customerDetailName.setText(customer.getFullName());
        }
        boolean isCustomerIdentifyNumberNull = customer.getIdentityNumber() != null;
        if (isCustomerIdentifyNumberNull) {
            customerDetailIdentify.setText(customer.getIdentityNumber());
        }
        boolean isCustomerBirthDayNull = customer.getBirthday() != null;
        if (isCustomerBirthDayNull) {
            customerDetailBirthDay.setText(customer.getBirthday());
        }
    }

    private void getNumCar(String url2) {
        RequestQueue requestQueue = Volley.newRequestQueue(this);
        JsonObjectRequest objectRequestCar = new JsonObjectRequest(
                Request.Method.GET, url2, null, new Response.Listener<JSONObject>() {
            @Override
            public void onResponse(JSONObject response) {
                Gson gson = new Gson();
                Message message = gson.fromJson(response.toString(), Message.class);
                customerDetailNumCar.setText(message.getMessage());
            }
        }, new Response.ErrorListener() {
            @Override
            public void onErrorResponse(VolleyError error) {
            }
        });
        requestQueue.add(objectRequestCar);
    }

    private void controlCarList() {
        customerDetailCarList.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                Intent intent = new Intent(UserDetailActivity.this, CarList.class);
                intent.putExtra("customerId", customerId);
                startActivity(intent);
            }
        });
    }

    private void controlBack() {
        customerDetailBack.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                Intent intent = new Intent(UserDetailActivity.this, LoginActivity.class);
                startActivity(intent);
            }
        });
    }


}
