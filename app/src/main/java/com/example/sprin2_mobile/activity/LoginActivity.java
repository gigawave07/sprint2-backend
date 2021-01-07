package com.example.sprin2_mobile.activity;

import android.content.DialogInterface;
import android.content.Intent;
import android.os.Bundle;
import android.util.Log;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import androidx.appcompat.app.AlertDialog;
import androidx.appcompat.app.AppCompatActivity;
import com.android.volley.Request;
import com.android.volley.RequestQueue;
import com.android.volley.Response;
import com.android.volley.VolleyError;
import com.android.volley.toolbox.JsonObjectRequest;
import com.android.volley.toolbox.Volley;
import com.example.sprin2_mobile.R;
import com.example.sprin2_mobile.entity.AppAccount;
import com.google.gson.Gson;
import lombok.SneakyThrows;
import org.json.JSONObject;


public class LoginActivity extends AppCompatActivity {

    EditText edtEmail, edtPassword;
    Button btnLogin, btnBack;
    String URL = "http://10.0.2.2:8080/account/checkLogin-mobile/";


    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_login);
        mapping();

        controlBtnBack();
        controlBtnLogin();
    }


    private void controlBtnLogin() {
        btnLogin.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                if (edtEmail.getText().length() != 0 && edtPassword.getText().length() != 0) {
                    URL += edtEmail.getText().toString() + "/" + edtPassword.getText().toString();
                    getAccount();
                    URL = "http://10.0.2.2:8080/account/checkLogin-mobile/";
                }
            }
        });
    }

    private void getAccount() {
        RequestQueue requestQueue = Volley.newRequestQueue(this);
        JsonObjectRequest objectRequest = new JsonObjectRequest(
                Request.Method.GET, URL, null, new Response.Listener<JSONObject>() {
            @SneakyThrows
            @Override
            public void onResponse(JSONObject response) {
                Gson gson = new Gson();
                AppAccount appAccount = gson.fromJson(response.toString(), AppAccount.class);
                if (appAccount.getAppRole().getId().toString().equals("1")) {

                } else if (appAccount.getAppRole().getId().toString().equals("2")) {
                    Intent intent = new Intent(LoginActivity.this, EmployeeActivity.class);
                    startActivity(intent);
                } else if (appAccount.getAppRole().getId().toString().equals("3")) {
                    Intent intent = new Intent(LoginActivity.this, UserDetailActivity.class);
                    intent.putExtra("accountId", appAccount.getId().toString());
                    startActivity(intent);
                }
                ;
            }
        }, new Response.ErrorListener() {
            @Override
            public void onErrorResponse(VolleyError error) {
            }
        }
        );
        requestQueue.add(objectRequest);

    }

    private void controlBtnBack() {
        btnBack.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                AlertDialog.Builder builder = new AlertDialog.Builder(LoginActivity.this, android.R.style.Theme_DeviceDefault_Light_Dialog);
                builder.setTitle("Bạn có chắc muốn thoát không?");
                builder.setMessage("Hãy chọn lựa chọn Có hoặc Không");
                builder.setIcon(android.R.drawable.ic_dialog_alert);
                builder.setPositiveButton("Có", new DialogInterface.OnClickListener() {
                    @Override
                    public void onClick(DialogInterface dialog, int which) {
                        onBackPressed();
                    }
                });
                builder.setNegativeButton("Không", new DialogInterface.OnClickListener() {
                    @Override
                    public void onClick(DialogInterface dialog, int which) {
                    }
                });
                builder.show();
            }
        });
    }

    private void mapping() {
        edtEmail = findViewById(R.id.edtEmail);
        edtPassword = findViewById(R.id.edtPassword);
        btnLogin = findViewById(R.id.btnLogin);
        btnBack = findViewById(R.id.btnBack);
    }
}
