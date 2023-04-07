package com.arash.altafi.pdf.pdf4

import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.view.View
import android.widget.LinearLayout
import android.widget.Toast
import com.arash.altafi.pdf.databinding.ActivityPdf4Binding
import es.voghdev.pdfviewpager.library.RemotePDFViewPager
import es.voghdev.pdfviewpager.library.adapter.PDFPagerAdapter
import es.voghdev.pdfviewpager.library.remote.DownloadFile
import es.voghdev.pdfviewpager.library.util.FileUtil

class Pdf4Activity : AppCompatActivity(), DownloadFile.Listener {

    private val binding by lazy {
        ActivityPdf4Binding.inflate(layoutInflater)
    }
    private val pdfUrl = "https://arashaltafi.ir/url_sample/pdf.pdf"
    private lateinit var remotePdf: RemotePDFViewPager
    private lateinit var adapter: PDFPagerAdapter

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(binding.root)

        init()
    }

    private fun init() = binding.apply {
        val listener: DownloadFile.Listener = this@Pdf4Activity

        btnDownload.setOnClickListener {
            remotePdf = RemotePDFViewPager(this@Pdf4Activity, pdfUrl, listener)
            progressbar.visibility = View.VISIBLE
        }
    }

    private fun updateLayout() = binding.apply {
        progressbar.visibility = View.GONE
        root.removeAllViewsInLayout()
        root.addView(
            btnDownload,
            LinearLayout.LayoutParams.MATCH_PARENT, LinearLayout.LayoutParams.WRAP_CONTENT
        )
        root.addView(
            remotePdf,
            LinearLayout.LayoutParams.MATCH_PARENT, LinearLayout.LayoutParams.WRAP_CONTENT
        )
    }

    override fun onSuccess(url: String?, destinationPath: String?) {
        Toast.makeText(applicationContext, "Download Success!", Toast.LENGTH_SHORT).show()
        adapter = PDFPagerAdapter(this, FileUtil.extractFileNameFromURL(url))
        remotePdf.adapter = adapter
        updateLayout()
    }

    override fun onFailure(e: Exception?) {
        e?.printStackTrace()
        binding.progressbar.visibility = View.GONE
    }

    override fun onProgressUpdate(progress: Int, total: Int) {
    }

}