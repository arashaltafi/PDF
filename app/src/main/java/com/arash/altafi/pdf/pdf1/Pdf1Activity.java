package com.arash.altafi.pdf.pdf1;

import androidx.appcompat.app.AppCompatActivity;

import android.content.Intent;
import android.os.Bundle;
import android.view.View;

import com.arash.altafi.pdf.R;
import com.google.android.material.button.MaterialButton;

public class Pdf1Activity extends AppCompatActivity {

    private MaterialButton btnPdfCreator;
    private MaterialButton btnPdfCreatorHtml;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_pdf1);

        init();
    }

    private void init() {
        bindViews();

        btnPdfCreator.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                startActivity(new Intent(Pdf1Activity.this , PdfCreateActivity.class));
            }
        });

        btnPdfCreatorHtml.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                startActivity(new Intent(Pdf1Activity.this , PdfCreateHtmlActivity.class));
            }
        });
    }

    private void bindViews() {
        btnPdfCreator = findViewById(R.id.btn_pdf_creator);
        btnPdfCreatorHtml = findViewById(R.id.btn_pdf_creator_html);
    }

}