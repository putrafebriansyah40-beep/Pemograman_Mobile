package com.kotlin.basic.trpl.recycler

import android.os.Bundle
import android.widget.ImageView
import android.widget.TextView
import androidx.activity.enableEdgeToEdge
import androidx.appcompat.app.AppCompatActivity
import androidx.core.view.ViewCompat
import androidx.core.view.WindowInsetsCompat
import com.kotlin.basic.trpl.R

class DetailAksesorisActivity : AppCompatActivity() {
    private lateinit var ivAksesorisDetail: ImageView
    private lateinit var tvHargaAksesoris: TextView
    private lateinit var tbNamaDetailAksesoris: TextView
    private lateinit var tvRatingDetailAksesoris: TextView
    private lateinit var tvDeskripsiAksesoris: TextView

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        enableEdgeToEdge()
        setContentView(R.layout.activity_detail_aksesoris)
        ViewCompat.setOnApplyWindowInsetsListener(findViewById(R.id.main)) { v, insets ->
            val systemBars = insets.getInsets(WindowInsetsCompat.Type.systemBars())
            v.setPadding(systemBars.left, systemBars.top, systemBars.right, systemBars.bottom)
            insets
        }
        ivAksesorisDetail = findViewById(R.id.ivAksesorisDetail)
        tvHargaAksesoris = findViewById(R.id.tvHargaAksesoris)
        tbNamaDetailAksesoris = findViewById(R.id.tbNamaDetailAksesoris)
        tvRatingDetailAksesoris = findViewById(R.id.tvRatingDetailAksesoris)
        tvDeskripsiAksesoris = findViewById(R.id.tvDeskripsiAksesoris)
    }

    override fun onStart() {
        super.onStart()
        showDetailAksesoris()
    }

    private fun showDetailAksesoris() {
        val gambar = intent.getIntExtra(
            "gambar",0)
        val nama = intent.getStringExtra("nama")
        val harga = intent.getStringExtra("harga")
        val rating = intent.getFloatExtra("rating",0f)
        val alamat = intent.getStringExtra("alamat")
        val deskripsi = intent.getStringExtra("deskripsi")
        ivAksesorisDetail.setImageResource(gambar)
        tvHargaAksesoris.text = harga
        tbNamaDetailAksesoris.text = nama
        tvRatingDetailAksesoris.text = rating.toString()
        tvDeskripsiAksesoris.text = deskripsi
    }
}