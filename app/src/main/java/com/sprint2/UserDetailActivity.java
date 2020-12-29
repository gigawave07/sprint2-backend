package com.sprint2;

import android.graphics.Bitmap;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.ImageView;
import androidx.appcompat.app.AppCompatActivity;
import android.os.Bundle;
import com.google.zxing.BarcodeFormat;
import com.google.zxing.MultiFormatWriter;
import com.google.zxing.common.BitMatrix;
import com.journeyapps.barcodescanner.BarcodeEncoder;

public class UserDetailActivity extends AppCompatActivity {

    EditText edtUserInfor;
    Button btnGenerateQrCode;
    ImageView imgQrCode;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_user_detail);
        mapping();
        generateQrCode();
    }

// Tạo QR code với dữ liệu được nhập vào
    private void generateQrCode() {
        btnGenerateQrCode.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                MultiFormatWriter multiFormatWriter = new MultiFormatWriter();
                try {
                    BitMatrix bitMatrix = multiFormatWriter.encode(edtUserInfor.getText().toString(), BarcodeFormat.QR_CODE, 200, 200);
                    BarcodeEncoder barcodeEncoder = new BarcodeEncoder();
                    Bitmap bitmap = barcodeEncoder.createBitmap(bitMatrix);
                    imgQrCode.setImageBitmap(bitmap);
                } catch (Exception e) {
                    e.printStackTrace();
                }
            }
        });
    }

    private void mapping() {
        edtUserInfor = findViewById(R.id.edtUserInfor);
        btnGenerateQrCode = findViewById(R.id.btnGenerateQrCode);
        imgQrCode = findViewById(R.id.imgQrCode);
    }
}
