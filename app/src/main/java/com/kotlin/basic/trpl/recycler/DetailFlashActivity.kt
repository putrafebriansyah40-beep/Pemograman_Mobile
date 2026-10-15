package com.kotlin.basic.trpl.recycler

import android.os.Bundle
import android.widget.ImageView
import android.widget.TextView
import androidx.activity.enableEdgeToEdge
import androidx.appcompat.app.AppCompatActivity
import androidx.core.view.ViewCompat
import androidx.core.view.WindowInsetsCompat
import com.kotlin.basic.trpl.R

class DetailFlashActivity : AppCompatActivity() {

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        enableEdgeToEdge()
        setContentView(R.layout.activity_detail_flash)
        ViewCompat.setOnApplyWindowInsetsListener(findViewById(R.id.main)) { v, insets ->
            val systemBars = insets.getInsets(WindowInsetsCompat.Type.systemBars())
            v.setPadding(systemBars.left, systemBars.top, systemBars.right, systemBars.bottom)
            insets
        }

        val ivFlashDetail = findViewById<ImageView>(R.id.ivFlashDetail)
        val tvHargaFlashDetail = findViewById<TextView>(R.id.tvHargaFlashDetail)
        val tvNamaFlashDetail = findViewById<TextView>(R.id.tvNamaFlashDetail)
        val tvRatingFlashDetail = findViewById<TextView>(R.id.tvRatingFlashDetail)
        val tvDeskripsiFlashDetail = findViewById<TextView>(R.id.tvDeskripsiFlashDetail)

        // Mendapatkan data dari Intent
        val gambar = intent.getIntExtra("gambar", 0)
        val harga = intent.getStringExtra("harga")
        val nama = intent.getStringExtra("nama")
        val rating = intent.getFloatExtra("rating", 0f)
        val deskripsi = intent.getStringExtra("deskripsi")

        // Memasukkan data ke UI
        if (gambar != 0) ivFlashDetail.setImageResource(gambar)
        tvHargaFlashDetail.text = harga
        tvNamaFlashDetail.text = nama
        tvRatingFlashDetail.text = rating.toString()
        tvDeskripsiFlashDetail.text = deskripsi
    }
}