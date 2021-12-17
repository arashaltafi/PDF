package com.arash.altafi.pdf.pdf1;

import androidx.appcompat.app.AppCompatActivity;

import android.content.Intent;
import android.net.Uri;
import android.os.Bundle;
import android.print.PDFPrint;

import com.tejpratapsingh.pdfcreator.utils.FileManager;
import com.tejpratapsingh.pdfcreator.utils.PDFUtil;

import java.io.File;

public class PdfCreateHtmlActivity extends AppCompatActivity {

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);


        FileManager.getInstance().cleanTempFolder(getApplicationContext());
        // Create Temp File to save Pdf To
        final File savedPDFFile = FileManager.getInstance().createTempFile(getApplicationContext(), "pdf", false);
        // Generate Pdf From Html
        PDFUtil.generatePDFFromHTML(getApplicationContext(), savedPDFFile, "<!DOCTYPE html>\n" +
                "<html>\n" +
                "<body>\n" +
                "\n" +
                "<h1>آرش الطافی</h1>\n" +
                "<p>My first paragraph.</p>\n" +
                "<p>!1 @2 #3 $4 %5 ^6 &7 *8 (9 )0</p>\n" +
                "<p>@#&=*+-_.,:!?()/~'%</p>\n" +
                "<a href='https://www.arashaltafi.ir'>آرش الطافی</a>" +
                "\n" +
                "</body>\n" +
                "</html>",
                new PDFPrint.OnPDFPrintListener() {
            @Override
            public void onSuccess(File file) {
                Uri pdfUri = Uri.fromFile(savedPDFFile);
                Intent intentPdfViewer = new Intent(PdfCreateHtmlActivity.this, PdfViewActivity.class);
                intentPdfViewer.putExtra(PdfViewActivity.PDF_FILE_URI, pdfUri);
                startActivity(intentPdfViewer);
            }

            @Override
            public void onError(Exception exception) {
                exception.printStackTrace();
            }
        });


    }
}