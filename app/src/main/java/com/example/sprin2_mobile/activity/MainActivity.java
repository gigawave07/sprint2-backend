package com.example.sprin2_mobile.activity;

import android.content.Intent;
import android.net.ConnectivityManager;
import android.net.NetworkInfo;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.Toast;
import androidx.appcompat.app.AppCompatActivity;
import com.example.sprin2_mobile.R;


public class MainActivity extends AppCompatActivity {

    Button btnMainLogin;
    ConnectivityManager connectivityManager;
    NetworkInfo myWifi, my3G;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
        mapping();
        checkNetWork();
        controlBtnMainLogin();
    }


    // Kiểm tra kết nối mạng 3G ho ặc wifi
    private void checkNetWork() {
        connectivityManager = (ConnectivityManager) getSystemService(CONNECTIVITY_SERVICE);
        myWifi = connectivityManager.getNetworkInfo(ConnectivityManager.TYPE_WIFI);
        my3G = connectivityManager.getNetworkInfo(ConnectivityManager.TYPE_MOBILE);
        if (myWifi == null || my3G == null) {
            // Hiển thị ra màn hình thông báo
            Toast.makeText(getApplicationContext(), "Hãy kiểm tra kết nối mạng", Toast.LENGTH_LONG).show();
        }

    }

    // Set sự kiện khi nhấn login sẽ qua activity login
    private void controlBtnMainLogin() {
        btnMainLogin.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent intent = new Intent(MainActivity.this, LoginActivity.class);
                startActivity(intent);
            }
        });
    }

    private void mapping() {
        btnMainLogin = findViewById(R.id.btnMainLogin);
    }
}
