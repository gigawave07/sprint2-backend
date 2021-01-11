package com.example.sprin2_mobile.activity;

import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import androidx.appcompat.app.AppCompatActivity;
import com.example.sprin2_mobile.R;


public class MainActivity extends AppCompatActivity {
    Button btnMainLogin;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
        mapping();

        controlBtnMainLogin();
    }

    // Set sự kiện khi nhấn login sẽ qua activity login
    private void controlBtnMainLogin() {
        btnMainLogin.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                Intent intent = new Intent(MainActivity.this, LoginActivity.class);
                startActivity(intent);
            }
        });
    }

    private void mapping() {
        btnMainLogin = findViewById(R.id.btnMainLogin);
    }
}
