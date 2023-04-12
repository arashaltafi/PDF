package com.arash.altafi.pdf

import android.content.Intent
import android.os.Bundle
import androidx.appcompat.app.AppCompatActivity
import com.arash.altafi.pdf.databinding.ActivityMainBinding
import com.arash.altafi.pdf.pdf1.Pdf1Activity
import com.arash.altafi.pdf.pdf2.Pdf2Activity
import com.arash.altafi.pdf.pdf3.Pdf3Activity
import com.arash.altafi.pdf.pdf4.Pdf4Activity
import com.arash.altafi.pdf.pdf5.Pdf5Activity

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

        btnPdf3.setOnClickListener {
            startActivity(Intent(this@MainActivity, Pdf3Activity::class.java))
        }

        btnPdf4.setOnClickListener {
            startActivity(Intent(this@MainActivity, Pdf4Activity::class.java))
        }

        btnPdf5.setOnClickListener {
            startActivity(Intent(this@MainActivity, Pdf5Activity::class.java))
        }
    }

}