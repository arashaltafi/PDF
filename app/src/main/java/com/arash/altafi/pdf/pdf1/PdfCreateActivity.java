package com.arash.altafi.pdf.pdf1;

import android.content.Intent;
import android.graphics.Color;
import android.graphics.Typeface;
import android.net.Uri;
import android.os.Bundle;
import android.text.Spannable;
import android.text.SpannableString;
import android.text.Spanned;
import android.text.style.ForegroundColorSpan;
import android.view.Gravity;
import android.view.ViewGroup;
import android.widget.FrameLayout;
import android.widget.ImageView;
import android.widget.LinearLayout;
import android.widget.Toast;

import androidx.annotation.Nullable;
import androidx.core.text.HtmlCompat;

import com.arash.altafi.pdf.R;
import com.tejpratapsingh.pdfcreator.activity.PDFCreatorActivity;
import com.tejpratapsingh.pdfcreator.utils.PDFUtil;
import com.tejpratapsingh.pdfcreator.views.PDFBody;
import com.tejpratapsingh.pdfcreator.views.PDFFooterView;
import com.tejpratapsingh.pdfcreator.views.PDFHeaderView;
import com.tejpratapsingh.pdfcreator.views.PDFTableView;
import com.tejpratapsingh.pdfcreator.views.basic.PDFHorizontalView;
import com.tejpratapsingh.pdfcreator.views.basic.PDFImageView;
import com.tejpratapsingh.pdfcreator.views.basic.PDFLineSeparatorView;
import com.tejpratapsingh.pdfcreator.views.basic.PDFPageBreakView;
import com.tejpratapsingh.pdfcreator.views.basic.PDFTextView;

import java.io.File;
import java.util.Locale;

public class PdfCreateActivity extends PDFCreatorActivity {

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);

        // hide action bar (full screen)
        if (getSupportActionBar() != null) {
            getSupportActionBar().hide();
        }


        String name = "Altafi";

        createPDF(name , new PDFUtil.PDFUtilListener() {
            @Override
            public void pdfGenerationSuccess(File savedPDFFile) {
                Toast.makeText(PdfCreateActivity.this, "فایل پی ای اف شما ساخته شد", Toast.LENGTH_SHORT).show();
            }

            @Override
            public void pdfGenerationFailure(Exception exception) {
                Toast.makeText(PdfCreateActivity.this, "خطایی رخ داده است", Toast.LENGTH_SHORT).show();
            }
        });

    }

    @Override
    protected PDFHeaderView getHeaderView(int forPage) {
        // initialize pdf header
        PDFHeaderView headerView = new PDFHeaderView(getApplicationContext());

        // set direction header
        PDFHorizontalView horizontalView = new PDFHorizontalView(getApplicationContext());

        // initialize pdf textview
        PDFTextView pdfTextView = new PDFTextView(getApplicationContext(), PDFTextView.PDF_TEXT_SIZE.HEADER);

        // insert title of header
        SpannableString word = new SpannableString("آرش الطافی");

        // customize text header (color , size)
        word.setSpan(new ForegroundColorSpan(Color.DKGRAY), 0, word.length(), Spannable.SPAN_EXCLUSIVE_EXCLUSIVE);

        // insert text to textview pdf
        pdfTextView.setText(word);

        // set layout for header
        pdfTextView.setLayout(new LinearLayout.LayoutParams(0, LinearLayout.LayoutParams.MATCH_PARENT, 1));
        pdfTextView.getView().setGravity(Gravity.CENTER_VERTICAL);
        pdfTextView.getView().setTypeface(pdfTextView.getView().getTypeface(), Typeface.BOLD);

        // insert text view pdf to header
        horizontalView.addView(pdfTextView);

        // initialize pdf imageview
        PDFImageView imageView = new PDFImageView(getApplicationContext());

        // set layout for header
        LinearLayout.LayoutParams imageLayoutParam = new LinearLayout.LayoutParams(60, 60, 0);
        imageView.setImageScale(ImageView.ScaleType.CENTER_INSIDE);
        imageView.setImageResource(R.mipmap.ic_launcher);
        imageLayoutParam.setMargins(0, 0, 10, 0);
        imageView.setLayout(imageLayoutParam);

        // insert image view pdf to header
        horizontalView.addView(imageView);

        // insert text and image to pdf header
        headerView.addView(horizontalView);

        // set horizontal line to divide header from body
        PDFLineSeparatorView lineSeparatorView1 = new PDFLineSeparatorView(getApplicationContext()).setBackgroundColor(Color.BLACK);
        headerView.addView(lineSeparatorView1);

        return headerView;
    }

    @Override
    protected PDFBody getBodyViews() {

        // initialize pdf body
        PDFBody pdfBody = new PDFBody();

        // initialize text view pdf
        PDFTextView pdfCompanyNameView = new PDFTextView(getApplicationContext(), PDFTextView.PDF_TEXT_SIZE.H3);

        // add text to text view pdf
        pdfCompanyNameView.setText("سر تیتر");

        // insert textview pdf to pdf body
        pdfBody.addView(pdfCompanyNameView);

        // set horizontal line to divide
        PDFLineSeparatorView lineSeparatorView1 = new PDFLineSeparatorView(getApplicationContext()).setBackgroundColor(Color.RED);

        // insert divider line to pdf body
        pdfBody.addView(lineSeparatorView1);

        // initialize text view pdf
        PDFTextView pdfAddressView = new PDFTextView(getApplicationContext(), PDFTextView.PDF_TEXT_SIZE.P);

        // add text to text view pdf
        pdfAddressView.setText("تست\nتست 2 \n تست 3");

        // insert textview pdf to pdf body
        pdfBody.addView(pdfAddressView);

        // set horizontal line to divide
        PDFLineSeparatorView lineSeparatorView2 = new PDFLineSeparatorView(getApplicationContext()).setBackgroundColor(Color.BLUE);

        // customize divider line
        lineSeparatorView2.setLayout(new LinearLayout.LayoutParams(LinearLayout.LayoutParams.MATCH_PARENT, 4, 0));

        // insert divider line to pdf body
        pdfBody.addView(lineSeparatorView2);

        // initialize text view pdf
        PDFTextView pdfTableTitleView = new PDFTextView(getApplicationContext(), PDFTextView.PDF_TEXT_SIZE.H1);

        // add text to text view pdf
        pdfTableTitleView.setText("آرش الطافی تست");

        // insert textview pdf to pdf body
        pdfBody.addView(pdfTableTitleView);

        // page 2

        int[] widthPercent = {20, 20, 20, 40}; // Sum should be equal to 100%
        String[] textInTable = {"1", "2", "3", "4"};

        final PDFPageBreakView pdfPageBreakView = new PDFPageBreakView(getApplicationContext());
        pdfBody.addView(pdfPageBreakView);

        PDFTableView.PDFTableRowView tableHeader = new PDFTableView.PDFTableRowView(getApplicationContext());
        for (String s : textInTable) {
            PDFTextView pdfTextView = new PDFTextView(getApplicationContext(), PDFTextView.PDF_TEXT_SIZE.P);
            pdfTextView.setText("Header Title: " + s);
            tableHeader.addToRow(pdfTextView);
        }

        PDFTableView.PDFTableRowView tableRowView1 = new PDFTableView.PDFTableRowView(getApplicationContext());
        for (String s : textInTable) {
            PDFTextView pdfTextView = new PDFTextView(getApplicationContext(), PDFTextView.PDF_TEXT_SIZE.P);
            pdfTextView.setText("Row 1 : " + s);
            tableRowView1.addToRow(pdfTextView);
        }


        // page 3

        PDFTableView tableView = new PDFTableView(getApplicationContext(), tableHeader, tableRowView1);

        for (int i = 0; i < 40; i++) {
            // Create 10 rows
            PDFTableView.PDFTableRowView tableRowView = new PDFTableView.PDFTableRowView(getApplicationContext());
            for (String s : textInTable) {
                PDFTextView pdfTextView = new PDFTextView(getApplicationContext(), PDFTextView.PDF_TEXT_SIZE.P);
                pdfTextView.setText("سطر " + (i + 2) + ": " + s);
                tableRowView.addToRow(pdfTextView);
            }
            tableView.addRow(tableRowView);
        }

        tableView.setColumnWidth(widthPercent);
        pdfBody.addView(tableView);

        PDFLineSeparatorView lineSeparatorView4 = new PDFLineSeparatorView(getApplicationContext()).setBackgroundColor(Color.BLACK);
        pdfBody.addView(lineSeparatorView4);

        PDFTextView pdfIconLicenseView = new PDFTextView(getApplicationContext(), PDFTextView.PDF_TEXT_SIZE.H3);
        Spanned icon8Link = HtmlCompat.fromHtml("شخصی سازی شده توسط : <a href='https://arashaltafi.ir'>آرش الطافی</a>", HtmlCompat.FROM_HTML_MODE_LEGACY);
        pdfIconLicenseView.getView().setText(icon8Link);
        pdfBody.addView(pdfIconLicenseView);

        return pdfBody;
    }

    @Override
    protected PDFFooterView getFooterView(int forPage) {

        // initialize pdf footer
        PDFFooterView footerView = new PDFFooterView(getApplicationContext());

        // initialize pdf text view
        PDFTextView pdfTextViewPage = new PDFTextView(getApplicationContext(), PDFTextView.PDF_TEXT_SIZE.SMALL);

        // insert text to textview
        pdfTextViewPage.setText(String.format(Locale.getDefault(), "صفحه: %d", forPage + 1));

        // set layout for footer
        pdfTextViewPage.setLayout(new LinearLayout.LayoutParams(LinearLayout.LayoutParams.MATCH_PARENT, LinearLayout.LayoutParams.MATCH_PARENT, 0));
        pdfTextViewPage.getView().setGravity(Gravity.CENTER_HORIZONTAL);

        // set horizontal line to divide header from body
        PDFLineSeparatorView lineSeparatorView1 = new PDFLineSeparatorView(getApplicationContext()).setBackgroundColor(Color.BLACK);
        footerView.addView(lineSeparatorView1);

        // insert (Add) layout to footer
        footerView.addView(pdfTextViewPage);

        return footerView;
    }

    @Nullable
    @Override
    protected PDFImageView getWatermarkView(int forPage) {
        // initialize image view pdf
        PDFImageView pdfImageView = new PDFImageView(getApplicationContext());

        // create frame layout
        FrameLayout.LayoutParams childLayoutParams = new FrameLayout.LayoutParams(ViewGroup.LayoutParams.MATCH_PARENT, 200, Gravity.CENTER);

        // insert image to layout
        pdfImageView.setLayout(childLayoutParams);

        // customize image view pdf
        pdfImageView.setImageResource(R.drawable.ic_pdf);
        pdfImageView.setImageScale(ImageView.ScaleType.FIT_CENTER);
        pdfImageView.getView().setAlpha(0.3F);

        return pdfImageView;
    }

    @Override
    protected void onNextClicked(File savedPDFFile) {
        Uri pdfUri = Uri.fromFile(savedPDFFile);
        Intent intentPdfViewer = new Intent(PdfCreateActivity.this, PdfViewActivity.class);
        intentPdfViewer.putExtra(PdfViewActivity.PDF_FILE_URI, pdfUri);
        startActivity(intentPdfViewer);
    }

}