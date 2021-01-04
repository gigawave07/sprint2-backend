package com.sprint2.activity;

import androidx.appcompat.app.AppCompatActivity;
import android.os.Bundle;
import com.google.zxing.integration.android.IntentIntegrator;
import com.sprint2.R;

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
