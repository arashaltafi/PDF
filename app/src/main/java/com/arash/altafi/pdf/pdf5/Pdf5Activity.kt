package com.arash.altafi.pdf.pdf5

import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import com.arash.altafi.pdf.R
import com.arash.altafi.pdf.databinding.ActivityPdf5Binding

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

    }

}