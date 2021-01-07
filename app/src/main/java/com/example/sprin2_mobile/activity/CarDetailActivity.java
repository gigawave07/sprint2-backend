package com.example.sprin2_mobile.activity;

import android.graphics.Bitmap;
import android.os.Bundle;
import android.widget.Button;
import android.widget.ImageView;
import android.widget.TextView;
import androidx.appcompat.app.AppCompatActivity;
import com.example.sprin2_mobile.R;
import com.example.sprin2_mobile.entity.Customer;
import com.google.gson.Gson;
import com.google.zxing.BarcodeFormat;
import com.google.zxing.MultiFormatWriter;
import com.google.zxing.common.BitMatrix;
import com.journeyapps.barcodescanner.BarcodeEncoder;


public class CarDetailActivity extends AppCompatActivity {

    ImageView imgQrCode;
    TextView licensePlates;
    TextView typeCar;
    TextView floor;
    TextView numberSlot;
    Button carBack;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_car_detail);
        generateQrCode();
        mapping();
    }

    private String getInformation() {
        Gson gson = new Gson();
        return gson.toJson(new Customer());
    }

    private void generateQrCode() {
        MultiFormatWriter multiFormatWriter = new MultiFormatWriter();
        try {
            String customer = getInformation();
            BitMatrix bitMatrix = multiFormatWriter.encode(customer, BarcodeFormat.QR_CODE, 200, 200);
            BarcodeEncoder barcodeEncoder = new BarcodeEncoder();
            Bitmap bitmap = barcodeEncoder.createBitmap(bitMatrix);
            imgQrCode.setImageBitmap(bitmap);
        } catch (Exception e) {
            e.printStackTrace();
        }
    }

    private void mapping() {
        imgQrCode = findViewById(R.id.imgQrCode);
        licensePlates = findViewById(R.id.licensePlates);
        typeCar = findViewById(R.id.typeCar);
        floor = findViewById(R.id.floor);
        numberSlot = findViewById(R.id.numberSlot);
        carBack = findViewById(R.id.carBack);
    }
}
