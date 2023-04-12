package com.arash.altafi.pdf.pdf5

import android.net.Uri
import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import com.arash.altafi.pdf.R
import com.arash.altafi.pdf.databinding.ActivityPdf5Binding
import com.pspdfkit.configuration.activity.PdfActivityConfiguration
import com.pspdfkit.ui.PdfActivity
import com.pspdfkit.ui.PdfActivityIntentBuilder

class Pdf5Activity : AppCompatActivity() {

    private val binding by lazy {
        ActivityPdf5Binding.inflate(layoutInflater)
    }

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_pdf5)

        init()
    }

    private fun init() = binding.apply {
        val uri = Uri.parse("file:///android_asset/pdf.pdf")
//        val config = PdfActivityConfiguration.Builder(this@Pdf5Activity).build()
//        PdfActivity.showDocument(this@Pdf5Activity, uri, config)

        val intent = PdfActivityIntentBuilder.fromUri(this@Pdf5Activity, uri).build()
        startActivity(intent)
    }

}