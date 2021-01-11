package com.example.sprin2_mobile.activity;

import android.content.Intent;
import android.graphics.Bitmap;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.ImageView;
import android.widget.TextView;
import androidx.appcompat.app.AppCompatActivity;
import com.example.sprin2_mobile.R;
import com.example.sprin2_mobile.entity.Car;
import com.google.firebase.database.DatabaseReference;
import com.google.firebase.database.FirebaseDatabase;
import com.google.gson.Gson;
import com.google.zxing.BarcodeFormat;
import com.google.zxing.MultiFormatWriter;
import com.google.zxing.common.BitMatrix;
import com.journeyapps.barcodescanner.BarcodeEncoder;

import java.time.LocalDate;


public class CarDetailActivity extends AppCompatActivity {

    ImageView imgQrCode;
    TextView licensePlates;
    TextView typeCar;
    TextView floor;
    TextView numberSlot;
    TextView typeCard;
    TextView retireDay;
    Button carBack;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_car_detail);
        mapping();
        generateQrCode();
        controlBack();
    }

    private void controlBack() {
        carBack.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                onBackPressed();
            }
        });
    }

    private String getInformation() {
        Gson gson = new Gson();
        Intent intent = getIntent();
        Car car = new Car();
        car.setId(intent.getStringExtra("id"));
        car.setLicensePlate(intent.getStringExtra("licensePlate"));
        car.setCarType(intent.getStringExtra("carType"));
        car.setBeginDate(intent.getStringExtra("beginDate"));
        car.setEndDate(intent.getStringExtra("endDate"));
        car.setTypeCard(intent.getStringExtra("typeCard"));
        car.setFloor(intent.getStringExtra("floor"));
        car.setSlotNum(intent.getStringExtra("slotNum"));
        displayInfomation(car);
        return gson.toJson(car);
    }

    private void displayInfomation(Car car) {
        licensePlates.setText(car.getLicensePlate());
        typeCar.setText(car.getCarType());
        floor.setText(car.getFloor());
        numberSlot.setText(car.getSlotNum());
        typeCard.setText(car.getTypeCard());
        retireDay.setText(car.getEndDate());
    }

    private Boolean setTextColor(String str) {
        LocalDate endDate = LocalDate.parse(str);
        LocalDate toDay = LocalDate.now();
        return (endDate.isAfter(toDay)) ? true : false;
    }

    private void generateQrCode() {
        MultiFormatWriter multiFormatWriter = new MultiFormatWriter();
        try {
            String carDetail = getInformation();
            BitMatrix bitMatrix = multiFormatWriter.encode(carDetail, BarcodeFormat.QR_CODE, 200, 200);
            BarcodeEncoder barcodeEncoder = new BarcodeEncoder();
            Bitmap bitmap = barcodeEncoder.createBitmap(bitMatrix);
            imgQrCode.setImageBitmap(bitmap);
            sendToFirebase(carDetail);
        } catch (Exception e) {
            e.printStackTrace();
        }
    }

    private void sendToFirebase(String detail) {
        Gson gson = new Gson();
        Car car = gson.fromJson(detail, Car.class);
        DatabaseReference mDatabase;
        mDatabase = FirebaseDatabase.getInstance().getReference();
        mDatabase.child("cars").child(car.getLicensePlate()).setValue(car);
    }

    private void mapping() {
        imgQrCode = findViewById(R.id.imgQrCode);
        licensePlates = findViewById(R.id.licensePlates);
        typeCar = findViewById(R.id.typeCar);
        floor = findViewById(R.id.floor);
        numberSlot = findViewById(R.id.numberSlot);
        carBack = findViewById(R.id.carBack);
        typeCard = findViewById(R.id.typeCard);
        retireDay = findViewById(R.id.retireDay);
    }
}
