package com.example.sprin2_mobile.activity;

import android.content.Intent;
import android.graphics.Color;
import android.os.Bundle;
import android.util.Log;
import android.util.TimeUtils;
import android.view.View;
import android.widget.Button;
import android.widget.ImageView;
import android.widget.TextView;
import android.widget.Toast;
import androidx.annotation.NonNull;
import androidx.appcompat.app.AppCompatActivity;
import com.example.sprin2_mobile.R;
import com.example.sprin2_mobile.entity.Car;
import com.google.firebase.database.*;
import com.google.gson.Gson;
import com.google.zxing.integration.android.IntentIntegrator;
import com.google.zxing.integration.android.IntentResult;

import java.time.LocalDate;
import java.time.LocalDateTime;
import java.util.concurrent.TimeUnit;

public class EmployeeActivity extends AppCompatActivity {

    Button btnQrScanCheckIn;
    Button scannerBack;
    ImageView screenScanner;
    TextView carId;
    TextView carFloor;
    TextView carNumber;
    TextView typeTicket;
    TextView beginDate;
    TextView retireDate;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_employee);
        mapping();
        controlReadQRCode();
        controlBack();
    }

    public void getDataFirebase(String carQR) {
        carId.setText("");
        carFloor.setText("");
        carNumber.setText("");
        typeTicket.setText("");
        beginDate.setText("");
        retireDate.setText("");
        Gson gson = new Gson();
        final boolean[] check = {true};
        final int[] count = {0};
        Car car = gson.fromJson(carQR, Car.class);
        final Car[] carFirebase = {new Car()};
        DatabaseReference mDatabase = FirebaseDatabase.getInstance().getReference().child("cars")
                .child(car.getLicensePlate());
        mDatabase.addValueEventListener(new ValueEventListener() {
            @Override
            public void onDataChange(@NonNull DataSnapshot snapshot) {
                if (snapshot.getValue() != null && check[0]) {
                    carFirebase[0].setId(snapshot.child("id").getValue().toString());
                    carFirebase[0].setSlotNum(snapshot.child("slotNum").getValue().toString());
                    carFirebase[0].setFloor(snapshot.child("floor").getValue().toString());
                    carFirebase[0].setTypeCard(snapshot.child("typeCard").getValue().toString());
                    carFirebase[0].setBeginDate(snapshot.child("beginDate").getValue().toString());
                    carFirebase[0].setEndDate(snapshot.child("endDate").getValue().toString());
                    carFirebase[0].setLicensePlate(snapshot.child("licensePlate").getValue().toString());
                    carFirebase[0].setCarType(snapshot.child("carType").getValue().toString());
                    displayInformation(carFirebase[0], mDatabase);
                    check[0] = false;
                    count[0] = 1;
                    car.setId("");
                } else if (count[0] == 0) {
                    count[0] = 1;
                    Toast.makeText(EmployeeActivity.this, "Không tìm thấy xe", Toast.LENGTH_LONG).show();
                    car.setId("");
                }
            }

            @Override
            public void onCancelled(@NonNull DatabaseError error) {
                Log.e("Error", error.toString());
            }
        });
    }

    private void controlBack() {
        scannerBack.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                Intent intent = new Intent(EmployeeActivity.this, LoginActivity.class);
                startActivity(intent);
            }
        });
    }


    private void controlReadQRCode() {
        final IntentIntegrator intentIntegrator = new IntentIntegrator(this);
        btnQrScanCheckIn.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                intentIntegrator.initiateScan();
            }
        });

    }

    @Override
    protected void onActivityResult(int requestCode, int resultCode, Intent data) {
        IntentResult result = IntentIntegrator.parseActivityResult(requestCode, resultCode, data);
        if (result != null) {
            if (result.getContents() == null) {
                Toast.makeText(this, "Cancelled", Toast.LENGTH_LONG).show();
            } else {

//                Toast.makeText(this, "Scanned: " + result.getContents(), Toast.LENGTH_LONG).show();
                getDataFirebase(result.getContents());
            }
        } else {
            super.onActivityResult(requestCode, resultCode, data);
        }
    }

    private void displayInformation(Car carFirebase, DatabaseReference mDataBase) {
        carId.setText(carFirebase.getLicensePlate());
        carFloor.setText(carFirebase.getFloor());
        carNumber.setText(carFirebase.getSlotNum());
        typeTicket.setText(carFirebase.getTypeCard());
        beginDate.setText(carFirebase.getBeginDate());
        retireDate.setText(carFirebase.getEndDate());
        if (LocalDate.parse(retireDate.getText().toString()).isBefore(LocalDate.now())) {
            retireDate.setTextColor(Color.rgb(255, 0, 0));
        } else {
            retireDate.setTextColor(Color.rgb(0, 0, 0));
        }
        Toast.makeText(this, "Scan thành công", Toast.LENGTH_LONG).show();
        mDataBase.removeValue();
        Intent intent = new Intent(EmployeeActivity.this, EmployeeActivity.class);
        intent.putExtra("carId", carId.getText());
        intent.putExtra("carFloor", carFloor.getText());
        intent.putExtra("carNumber", carNumber.getText());
        intent.putExtra("typeTicket", typeTicket.getText());
        intent.putExtra("beginDate", beginDate.getText());
        intent.putExtra("retireDate", retireDate.getText());
    }

    private void mapping() {
        btnQrScanCheckIn = findViewById(R.id.btnQrScanCheckIn);
        scannerBack = findViewById(R.id.scannerBack);
        screenScanner = findViewById(R.id.screenScanner);
        carId = findViewById(R.id.carId);
        carFloor = findViewById(R.id.carFoor);
        carNumber = findViewById(R.id.carNumber);
        typeTicket = findViewById(R.id.typeTicket);
        beginDate = findViewById(R.id.beginDate);
        retireDate = findViewById(R.id.retireDate);

    }
}
