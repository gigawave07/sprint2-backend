package com.example.sprin2_mobile.activity;

import android.content.DialogInterface;
import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.Toast;
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

    public static final String BACK_MESSAGE = "Bạn có chắc muốn thoát không?";
    public static final String OPTION_BACK = "Hãy chọn lựa chọn Có hoặc Không";
    public static final String USERNAME_NOT_EMPTY = "Không được bỏ trống tên tài khoản";
    public static final String PASSWORD_NOT_EMPTY = "Không được bỏ trống mật khẩu";
    public static final String ACCOUNT_NOT_EMPTY = "Không được bỏ trống tên tài khoản và mật khẩu";
    public static final String BASE_URL = "http://10.0.2.2:8080/account/check-login-mobile/";
    public static final String WRONG_ACCOUNT = "Tên tài khoản hoặc mật khẩu không chính xác, vui lòng nhập lại";
    public static final String YES_OPTION = "Có";
    public static final String NO_OPTION = "Không";
    EditText edtEmail, edtPassword;
    Button btnLogin, btnBack;


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
                String url = BASE_URL;
                if (edtEmail.getText().length() != 0 && edtPassword.getText().length() != 0) {
                    url += edtEmail.getText().toString() + "/" + edtPassword.getText().toString();
                    getAccount(url);
                } else if (edtEmail.getText().length() == 0) {
                    Toast.makeText(LoginActivity.this, USERNAME_NOT_EMPTY,
                            Toast.LENGTH_LONG).show();
                } else if (edtPassword.getText().length() == 0) {
                    Toast.makeText(LoginActivity.this, PASSWORD_NOT_EMPTY,
                            Toast.LENGTH_LONG).show();
                } else if (edtEmail.getText().length() == 0 && edtPassword.getText().length() == 0) {
                    Toast.makeText(LoginActivity.this, ACCOUNT_NOT_EMPTY,
                            Toast.LENGTH_LONG).show();
                }
            }
        });
    }

    private void getAccount(String url) {
        RequestQueue requestQueue = Volley.newRequestQueue(this);
        JsonObjectRequest objectRequest = new JsonObjectRequest(
                Request.Method.GET, url, null, new Response.Listener<JSONObject>() {
            @SneakyThrows
            @Override
            public void onResponse(JSONObject response) {
                if (response.toString().contains("not found")) {
                    Toast.makeText(LoginActivity.this, WRONG_ACCOUNT, Toast.LENGTH_LONG).show();
                    edtEmail.setText("");
                    edtPassword.setText("");
                } else {
                    Gson gson = new Gson();
                    AppAccount appAccount = gson.fromJson(response.toString(), AppAccount.class);
                    switch (appAccount.getAppRole().getId().toString()) {
                        case "2":
                            Intent intent = new Intent(LoginActivity.this, EmployeeActivity.class);
                            startActivity(intent);
                            break;
                        case "3":
                            intent = new Intent(LoginActivity.this, UserDetailActivity.class);
                            intent.putExtra("accountId", appAccount.getId().toString());
                            startActivity(intent);
                            break;
                    }
                }
            }
        }, new Response.ErrorListener() {
            @Override
            public void onErrorResponse(VolleyError error) {
            }
        });
        requestQueue.add(objectRequest);

    }

    private void controlBtnBack() {
        btnBack.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                AlertDialog.Builder builder = new AlertDialog.Builder(LoginActivity.this,
                        android.R.style.Theme_DeviceDefault_Light_Dialog);
                builder.setTitle(BACK_MESSAGE);
                builder.setMessage(OPTION_BACK);
                builder.setIcon(android.R.drawable.ic_dialog_alert);
                builder.setPositiveButton(YES_OPTION, new DialogInterface.OnClickListener() {
                    @Override
                    public void onClick(DialogInterface dialog, int which) {
                        onBackPressed();
                    }
                });
                builder.setNegativeButton(NO_OPTION, new DialogInterface.OnClickListener() {
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
