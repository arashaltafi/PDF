package com.arash.altafi.pdf.pdf2;

import static com.arash.altafi.pdf.pdf2.LogUtils.LOGE;
import static com.arash.altafi.pdf.pdf2.permission.PermissionsActivity.PERMISSION_REQUEST_CODE;
import static com.arash.altafi.pdf.pdf2.permission.PermissionsChecker.REQUIRED_PERMISSION;

import androidx.appcompat.app.AppCompatActivity;

import android.content.ActivityNotFoundException;
import android.content.Context;
import android.content.Intent;
import android.os.Bundle;
import android.os.Handler;
import android.view.View;
import android.widget.Toast;

import com.arash.altafi.pdf.R;
import com.arash.altafi.pdf.pdf2.permission.PermissionsActivity;
import com.arash.altafi.pdf.pdf2.permission.PermissionsChecker;
import com.google.android.material.button.MaterialButton;
import com.itextpdf.kernel.colors.Color;
import com.itextpdf.kernel.colors.DeviceRgb;
import com.itextpdf.kernel.font.PdfFont;
import com.itextpdf.kernel.font.PdfFontFactory;
import com.itextpdf.kernel.geom.PageSize;
import com.itextpdf.kernel.pdf.PdfDocument;
import com.itextpdf.kernel.pdf.PdfDocumentInfo;
import com.itextpdf.kernel.pdf.PdfWriter;
import com.itextpdf.kernel.pdf.canvas.draw.DottedLine;
import com.itextpdf.layout.Document;
import com.itextpdf.layout.element.LineSeparator;
import com.itextpdf.layout.element.Paragraph;
import com.itextpdf.layout.element.Text;
import com.itextpdf.layout.property.TextAlignment;

import java.io.File;
import java.io.FileOutputStream;
import java.io.IOException;

public class Pdf2Activity extends AppCompatActivity {

    private MaterialButton btnCreatePdf2;
    private MaterialButton btnOpenPdf2;

    Context mContext;
    PermissionsChecker checker;
    String dest;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_pdf2);

        init();
    }

    private void init() {

        bindViews();

        mContext = getApplicationContext();
        checker = new PermissionsChecker(this);
        String name = "test111";
        dest = FileUtils.getAppPath(mContext) + name + ".pdf";

        btnCreatePdf2.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                if (checker.lacksPermissions(REQUIRED_PERMISSION)) {
                    PermissionsActivity.startActivityForResult(Pdf2Activity.this, PERMISSION_REQUEST_CODE, REQUIRED_PERMISSION);
                } else {
                    createPdf(dest);
                }
            }
        });

        btnOpenPdf2.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                new Handler().postDelayed(new Runnable() {
                    @Override
                    public void run() {
                        try {
                            FileUtils.openFile(mContext, new File(dest));
                        }
                        catch (Exception e) {
                            e.getMessage();
                        }
                    }
                }, 1000);
            }
        });

    }

    public void createPdf(String dest) {

        if (new File(dest).exists()) {
            new File(dest).delete();
        }

        try {
            /**
             * Creating Document
             */
            PdfWriter pdfWriter = new PdfWriter(new FileOutputStream(dest));
            PdfDocument pdfDocument = new PdfDocument(pdfWriter);
            PdfDocumentInfo info = pdfDocument.getDocumentInfo();

            info.setTitle("Example of iText7 by Pratik Butani");
            info.setAuthor("Pratik Butani");
            info.setSubject("iText7 PDF Demo");
            info.setKeywords("iText, PDF, Pratik Butani");
            info.setCreator("A simple tutorial example");

            Document document = new Document(pdfDocument, PageSize.A4, true);

            /***
             * Variables for further use....
             */
            Color mColorAccent = new DeviceRgb(153, 204, 255);
            Color mColorBlack = new DeviceRgb(0, 0, 0);
            float mHeadingFontSize = 20.0f;
            float mValueFontSize = 26.0f;

            /**
             * How to USE FONT....
             */
            PdfFont font = PdfFontFactory.createFont("assets/fonts/brandon_medium.otf", "UTF-8", true);

            // LINE SEPARATOR
            LineSeparator lineSeparator = new LineSeparator(new DottedLine());
            lineSeparator.setStrokeColor(new DeviceRgb(0, 0, 68));

            // Title Order Details...
            // Adding Title....
            Text mOrderDetailsTitleChunk = new Text("Order Details").setFont(font).setFontSize(36.0f).setFontColor(mColorBlack);
            Paragraph mOrderDetailsTitleParagraph = new Paragraph(mOrderDetailsTitleChunk)
                    .setTextAlignment(TextAlignment.CENTER);
            document.add(mOrderDetailsTitleParagraph);

            // Fields of Order Details...
            // Adding Chunks for Title and value
            Text mOrderIdChunk = new Text("Order No:").setFont(font).setFontSize(mHeadingFontSize).setFontColor(mColorAccent);
            Paragraph mOrderIdParagraph = new Paragraph(mOrderIdChunk);
            document.add(mOrderIdParagraph);

            Text mOrderIdValueChunk = new Text("#123123").setFont(font).setFontSize(mValueFontSize).setFontColor(mColorBlack);
            Paragraph mOrderIdValueParagraph = new Paragraph(mOrderIdValueChunk);
            document.add(mOrderIdValueParagraph);

            // Adding Line Breakable Space....
            document.add(new Paragraph(""));
            // Adding Horizontal Line...
            document.add(lineSeparator);
            // Adding Line Breakable Space....
            document.add(new Paragraph(""));

            // Fields of Order Details...
            Text mOrderDateChunk = new Text("Order Date:").setFont(font).setFontSize(mHeadingFontSize).setFontColor(mColorAccent);
            Paragraph mOrderDateParagraph = new Paragraph(mOrderDateChunk);
            document.add(mOrderDateParagraph);

            Text mOrderDateValueChunk = new Text("06/07/2017").setFont(font).setFontSize(mValueFontSize).setFontColor(mColorBlack);
            Paragraph mOrderDateValueParagraph = new Paragraph(mOrderDateValueChunk);
            document.add(mOrderDateValueParagraph);

            document.add(new Paragraph(""));
            document.add(lineSeparator);
            document.add(new Paragraph(""));

            // Fields of Order Details...
            Text mOrderAcNameChunk = new Text("Account Name:").setFont(font).setFontSize(mHeadingFontSize).setFontColor(mColorAccent);
            Paragraph mOrderAcNameParagraph = new Paragraph(mOrderAcNameChunk);
            document.add(mOrderAcNameParagraph);

            Text mOrderAcNameValueChunk = new Text("Pratik Butani").setFont(font).setFontSize(mValueFontSize).setFontColor(mColorBlack);
            Paragraph mOrderAcNameValueParagraph = new Paragraph(mOrderAcNameValueChunk);
            document.add(mOrderAcNameValueParagraph);

            document.add(new Paragraph(""));
            document.add(lineSeparator);
            document.add(new Paragraph(""));

            document.close();

            Toast.makeText(mContext, "Created... :)", Toast.LENGTH_SHORT).show();


        } catch (IOException e) {
            LOGE("createPdf: Error " + e.getLocalizedMessage());
        } catch (ActivityNotFoundException ae) {
            Toast.makeText(mContext, "No application found to open this file.", Toast.LENGTH_SHORT).show();
        }
    }


    @Override
    protected void onActivityResult(int requestCode, int resultCode, Intent data) {
        super.onActivityResult(requestCode, resultCode, data);
        if (resultCode == PermissionsActivity.PERMISSIONS_GRANTED) {
            Toast.makeText(mContext, "Permission Granted to Save", Toast.LENGTH_SHORT).show();
        } else {
            Toast.makeText(mContext, "Permission not granted, Try again!", Toast.LENGTH_SHORT).show();
        }
    }

    private void bindViews() {
        btnCreatePdf2 = findViewById(R.id.btnCreatePdf2);
        btnOpenPdf2 = findViewById(R.id.btnOpenPdf2);
    }

}