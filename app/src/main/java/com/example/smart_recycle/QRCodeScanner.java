package com.example.smart_recycle;

import androidx.annotation.NonNull;
import androidx.appcompat.app.AppCompatActivity;

import android.os.Bundle;
import android.widget.TextView;

import com.budiyev.android.codescanner.CodeScanner;
import com.budiyev.android.codescanner.CodeScannerView;
import com.budiyev.android.codescanner.DecodeCallback;
import com.google.zxing.Result;

public class QRCodeScanner extends AppCompatActivity {
    CodeScanner codeScanner;
    CodeScannerView scannView;
    TextView resultData;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_qrcode_scanner);
        scannView= findViewById(R.id.scanner_view);
        codeScanner= new CodeScanner(this, scannView);
        resultData = findViewById(R.id.resultdata);

        codeScanner.setDecodeCallback(new DecodeCallback() {
            @Override
            public void onDecoded(@NonNull Result result) {
                runOnUiThread(new Runnable() {
                    @Override
                    public void run() {
                        resultData.setText(result.getText());
                    }
                });
            }
        });
    }

    @Override
    protected void onResume() {
        super.onResume();
        codeScanner.startPreview();
    }
}