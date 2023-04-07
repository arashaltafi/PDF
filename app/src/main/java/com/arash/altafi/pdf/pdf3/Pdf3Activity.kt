package com.arash.altafi.pdf.pdf3

import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.view.View
import com.arash.altafi.pdf.databinding.ActivityPdf3Binding
import com.github.barteksc.pdfviewer.listener.OnLoadCompleteListener
import com.github.barteksc.pdfviewer.listener.OnPageChangeListener
import com.github.barteksc.pdfviewer.listener.OnPageErrorListener
import com.github.barteksc.pdfviewer.scroll.DefaultScrollHandle
import com.github.barteksc.pdfviewer.util.FitPolicy
import com.krishna.fileloader.FileLoader
import com.krishna.fileloader.listener.FileRequestListener
import com.krishna.fileloader.pojo.FileResponse
import com.krishna.fileloader.request.FileLoadRequest
import java.io.File

class Pdf3Activity : AppCompatActivity(), OnLoadCompleteListener, OnPageChangeListener,
    OnPageErrorListener {

    private val binding by lazy {
        ActivityPdf3Binding.inflate(layoutInflater)
    }

    private val pdfUrl = "https://arashaltafi.ir/url_sample/pdf2.pdf"

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(binding.root)

        init()
    }

    private fun init() = binding.apply {
        progressbar.visibility = View.VISIBLE

        smNightMode.setOnCheckedChangeListener { _, isChecked ->
            if (isChecked) {
                pdfView.setNightMode(true)
                pdfView.loadPages()
            } else {
                pdfView.setNightMode(false)
                pdfView.loadPages()
            }
        }

        FileLoader.with(this@Pdf3Activity)
            .load(pdfUrl, false)
            .fromDirectory("test3", FileLoader.DIR_INTERNAL)
            .asFile(object : FileRequestListener<File?> {
                override fun onLoad(request: FileLoadRequest, response: FileResponse<File?>) {
                    val url: File? = response.body
                    pdfView.fromFile(url)
                        .defaultPage(0)
                        .enableSwipe(true)
                        .enableAnnotationRendering(true)
                        .onLoad(this@Pdf3Activity)
                        .onPageChange(this@Pdf3Activity)
                        .scrollHandle(DefaultScrollHandle(this@Pdf3Activity))
                        .enableDoubletap(true)
                        .onPageError(this@Pdf3Activity)
                        .swipeHorizontal(true)
                        .spacing(0)
                        .fitEachPage(false)
                        .nightMode(true)
                        .pageFitPolicy(FitPolicy.WIDTH)
                        .pageSnap(true)
                        .autoSpacing(false)
                        .pageFling(true)
                        .load()
                }

                override fun onError(request: FileLoadRequest, t: Throwable) {}
            })

    }

    override fun loadComplete(nbPages: Int) {
        binding.progressbar.visibility = View.GONE
    }

    override fun onPageChanged(page: Int, pageCount: Int) {
        binding.progressbar.visibility = View.GONE
    }

    override fun onPageError(page: Int, t: Throwable?) {
        binding.progressbar.visibility = View.GONE
    }

}