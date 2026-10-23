package com.kotlin.basic.trpl.recycler

import android.os.Bundle
import android.widget.ImageView
import android.widget.TextView
import androidx.activity.enableEdgeToEdge
import androidx.appcompat.app.AppCompatActivity
import androidx.core.view.ViewCompat
import androidx.core.view.WindowInsetsCompat
import com.kotlin.basic.trpl.R

class DetailGridActivity : AppCompatActivity() {

    private lateinit var ivGridDetail: ImageView
    private lateinit var tvHargaGridDetail: TextView
    private lateinit var tvNamaGridDetail: TextView
    private lateinit var tvRatingGridDetail: TextView
    private lateinit var tvDeskripsiGridDetail: TextView

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        enableEdgeToEdge()
        setContentView(R.layout.activity_detail_grid)
        ViewCompat.setOnApplyWindowInsetsListener(findViewById(R.id.main)) { v, insets ->
            val systemBars = insets.getInsets(WindowInsetsCompat.Type.systemBars())
            v.setPadding(systemBars.left, systemBars.top, systemBars.right, systemBars.bottom)
            insets
        }

        ivGridDetail = findViewById(R.id.ivGridDetail)
        tvHargaGridDetail = findViewById(R.id.tvHargaGridDetail)
        tvNamaGridDetail = findViewById(R.id.tvNamaGridDetail)
        tvRatingGridDetail = findViewById(R.id.tvRatingGridDetail)
        tvDeskripsiGridDetail = findViewById(R.id.tvDeskripsiGridDetail)
    }

    override fun onStart() {
        super.onStart()
        showDetailGrid()
    }

    private fun showDetailGrid() {
        val gambar = intent.getIntExtra("gambar", 0)
        val nama = intent.getStringExtra("nama")
        val harga = intent.getStringExtra("harga")
        val rating = intent.getFloatExtra("rating", 0f)
        val deskripsi = intent.getStringExtra("deskripsi")

        if (gambar != 0) {
            ivGridDetail.setImageResource(gambar)
        }
        tvNamaGridDetail.text = nama
        tvHargaGridDetail.text = harga
        tvRatingGridDetail.text = rating.toString()
        tvDeskripsiGridDetail.text = deskripsi
    }
}