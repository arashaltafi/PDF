package com.arash.altafi.pdf;

import androidx.appcompat.app.AppCompatActivity;

import android.content.Intent;
import android.os.Bundle;
import android.view.View;

import com.arash.altafi.pdf.pdf1.Pdf1Activity;
import com.arash.altafi.pdf.pdf2.Pdf2Activity;
import com.google.android.material.button.MaterialButton;

public class MainActivity extends AppCompatActivity {

    private MaterialButton btnPdf1;
    private MaterialButton btnPdf2;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        init();
    }

    private void init() {
        bindViews();

        btnPdf1.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                startActivity(new Intent(MainActivity.this , Pdf1Activity.class));
            }
        });

        btnPdf2.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                startActivity(new Intent(MainActivity.this , Pdf2Activity.class));
            }
        });

    }

    private void bindViews() {
        btnPdf1 = findViewById(R.id.btnPdf1);
        btnPdf2 = findViewById(R.id.btnPdf2);
    }
}