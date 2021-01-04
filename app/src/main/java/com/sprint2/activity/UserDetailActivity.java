package com.sprint2.activity;

import android.graphics.Bitmap;
import android.os.Bundle;
import android.widget.ImageView;
import android.widget.TextView;
import androidx.appcompat.app.AppCompatActivity;
import com.google.gson.Gson;
import com.google.zxing.BarcodeFormat;
import com.google.zxing.MultiFormatWriter;
import com.google.zxing.common.BitMatrix;
import com.journeyapps.barcodescanner.BarcodeEncoder;
import com.sprint2.R;
import com.sprint2.entity.Customer;

public class UserDetailActivity extends AppCompatActivity {


    ImageView imgQrCode;
    TextView textViewCustomerFullName, textViewLicensePlates, textViewFloor, textViewNumberSlot;


    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_user_detail);
        mapping();
        generateQrCode();
    }

    private String getInformation() {
        Customer customer = new Customer();
        textViewCustomerFullName.setText("Mai Thế Vinh");
        textViewLicensePlates.setText("74G1-12607");
        textViewFloor.setText("1");
        textViewNumberSlot.setText("7");
        customer.setName(textViewCustomerFullName.getText().toString());
        customer.setLicensePlates(textViewLicensePlates.getText().toString());
        customer.setNumberPhone("0334611971");
        customer.setAddress("Đà Nẵng");

        Gson gson = new Gson();
        String json = gson.toJson(customer);
        return json;
    }

    // Tạo QR code với dữ liệu nhận được
    private void generateQrCode() {

        MultiFormatWriter multiFormatWriter = new MultiFormatWriter();
        try {
            String customer = getInformation();
            BitMatrix bitMatrix = multiFormatWriter.encode(customer.toString(), BarcodeFormat.QR_CODE, 200, 200);
            BarcodeEncoder barcodeEncoder = new BarcodeEncoder();
            Bitmap bitmap = barcodeEncoder.createBitmap(bitMatrix);
            imgQrCode.setImageBitmap(bitmap);
        } catch (Exception e) {
            e.printStackTrace();
        }
    }

    private void mapping() {
        imgQrCode = findViewById(R.id.imgQrCode);
        textViewCustomerFullName = findViewById(R.id.customerFullName);
        textViewLicensePlates = findViewById(R.id.licensePlates);
        textViewFloor = findViewById(R.id.floor);
        textViewNumberSlot = findViewById(R.id.numberSlot);
    }
}
