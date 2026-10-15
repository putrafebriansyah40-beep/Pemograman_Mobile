package com.kotlin.basic.trpl.recycler

import android.os.Bundle
import androidx.activity.enableEdgeToEdge
import androidx.appcompat.app.AppCompatActivity
import androidx.core.view.ViewCompat
import androidx.core.view.WindowInsetsCompat
import androidx.recyclerview.widget.RecyclerView
import com.kotlin.basic.trpl.R

class BukuActivity : AppCompatActivity() {

    private lateinit var rvBuku: RecyclerView

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        enableEdgeToEdge()
        setContentView(R.layout.activity_buku)
        ViewCompat.setOnApplyWindowInsetsListener(findViewById(R.id.main)) { v, insets ->
            val systemBars = insets.getInsets(WindowInsetsCompat.Type.systemBars())
            v.setPadding(systemBars.left, systemBars.top, systemBars.right, systemBars.bottom)
            insets
        }

        rvBuku = findViewById(R.id.rvBuku)
        setupRecyclerView()
    }

    private fun setupRecyclerView() {
        val listBuku = listOf(
            BukuModel(
                R.drawable.img_atomichabbit,
                "Atomic Habits",
                "James Clear",
                "Rp105.000",
                8
            ),
            BukuModel(
                R.drawable.img_laskarpelangi,
                "Laskar Pelangi",
                "Andrea Hirata",
                "Rp85.000",
                12
            ),
            BukuModel(
                R.drawable.img_bumi,
                "Bumi",
                "Tere Liye",
                "Rp90.000",
                15
            ),
            BukuModel(
                R.drawable.img_sapiens,
                "Sapiens",
                "Yuval Noah Harari",
                "Rp120.000",
                7
            )
        )

        rvBuku.adapter = BukuAdapter(listBuku)
    }
}