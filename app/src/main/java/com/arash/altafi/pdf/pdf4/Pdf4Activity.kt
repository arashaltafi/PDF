package com.arash.altafi.pdf.pdf4

import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import com.arash.altafi.pdf.databinding.ActivityPdf4Binding

class Pdf4Activity : AppCompatActivity() {

    private val binding by lazy {
        ActivityPdf4Binding.inflate(layoutInflater)
    }

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(binding.root)

        init()
    }

    private fun init() = binding.apply {

    }

}