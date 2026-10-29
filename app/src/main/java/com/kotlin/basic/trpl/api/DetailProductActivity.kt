package com.kotlin.basic.trpl.api

import android.os.Bundle
import android.widget.ImageView
import android.widget.TextView
import androidx.activity.enableEdgeToEdge
import androidx.appcompat.app.AppCompatActivity
import androidx.core.view.ViewCompat
import androidx.core.view.WindowInsetsCompat
import com.kotlin.basic.trpl.R
import com.squareup.picasso.Picasso

class DetailProductActivity : AppCompatActivity() {

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        enableEdgeToEdge()
        setContentView(R.layout.activity_detail_product)

        ViewCompat.setOnApplyWindowInsetsListener(findViewById(R.id.main)) { v, insets ->
            val systemBars = insets.getInsets(WindowInsetsCompat.Type.systemBars())
            v.setPadding(systemBars.left, systemBars.top, systemBars.right, systemBars.bottom)
            insets
        }

        val ivThumbnail = findViewById<ImageView>(R.id.ivDetailProduct)
        val tvTitle = findViewById<TextView>(R.id.tvDetailProductTitle)
        val tvPrice = findViewById<TextView>(R.id.tvDetailProductPrice)
        val tvRating = findViewById<TextView>(R.id.tvDetailProductRating)
        val tvDescription = findViewById<TextView>(R.id.tvDetailProductDescription)

        // Mengambil data yang dikirim dari ProductAdapter melalui Intent
        val title = intent.getStringExtra("title")
        val description = intent.getStringExtra("description")
        val price = intent.getDoubleExtra("price", 0.0)
        val rating = intent.getDoubleExtra("rating", 0.0)
        val thumbnail = intent.getStringExtra("thumbnail")

        // Memasang data ke komponen UI
        tvTitle.text = title
        tvDescription.text = description
        tvPrice.text = "$ $price"
        tvRating.text = rating.toString()
        
        if (!thumbnail.isNullOrEmpty()) {
            Picasso.get().load(thumbnail).into(ivThumbnail)
        }
    }
}