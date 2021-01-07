package com.example.sprin2_mobile.activity;

import android.os.Bundle;
import androidx.appcompat.app.AppCompatActivity;
import com.example.sprin2_mobile.R;
import com.google.zxing.integration.android.IntentIntegrator;


public class EmployeeActivity extends AppCompatActivity {


    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_employee);
        IntentIntegrator intentIntegrator = new IntentIntegrator(this);
        scanQRCode();
    }

    private void scanQRCode() {
        
    }
}
