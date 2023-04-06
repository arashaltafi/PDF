package com.arash.altafi.pdf.pdf3

import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import com.arash.altafi.pdf.databinding.ActivityPdf3Binding

class Pdf3Activity : AppCompatActivity() {

    private val binding by lazy {
        ActivityPdf3Binding.inflate(layoutInflater)
    }

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(binding.root)

        init()
    }

    private fun init() = binding.apply {

    }

}