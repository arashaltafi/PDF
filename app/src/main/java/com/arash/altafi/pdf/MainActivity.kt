package com.arash.altafi.pdf

import android.content.Intent
import android.os.Bundle
import androidx.appcompat.app.AppCompatActivity
import com.arash.altafi.pdf.databinding.ActivityMainBinding
import com.arash.altafi.pdf.pdf1.Pdf1Activity
import com.arash.altafi.pdf.pdf2.Pdf2Activity

class MainActivity : AppCompatActivity() {

    private val binding by lazy {
        ActivityMainBinding.inflate(layoutInflater)
    }

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(binding.root)

        init()
    }

    private fun init() = binding.apply {
        btnPdf1.setOnClickListener {
            startActivity(Intent(this@MainActivity, Pdf1Activity::class.java))
        }

        btnPdf2.setOnClickListener {
            startActivity(Intent(this@MainActivity, Pdf2Activity::class.java))
        }
    }

}