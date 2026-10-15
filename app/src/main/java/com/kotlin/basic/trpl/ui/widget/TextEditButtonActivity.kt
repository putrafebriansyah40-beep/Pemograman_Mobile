package com.kotlin.basic.trpl.ui.widget

import android.annotation.SuppressLint
import android.os.Bundle
import android.widget.Button
import android.widget.EditText
import android.widget.TextView
import androidx.activity.enableEdgeToEdge
import androidx.appcompat.app.AppCompatActivity
import androidx.core.view.ViewCompat
import androidx.core.view.WindowInsetsCompat
import com.kotlin.basic.trpl.R

class TextEditButtonActivity : AppCompatActivity() {
    //Variable Global
    private lateinit var etNim : EditText
    private lateinit var etNama : EditText
    private lateinit var btnProses : Button
    private lateinit var tvHasil : TextView

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        enableEdgeToEdge()
        setContentView(R.layout.activity_text_edit_button)
        ViewCompat.setOnApplyWindowInsetsListener(findViewById(R.id.main)) { v, insets ->
            val systemBars = insets.getInsets(WindowInsetsCompat.Type.systemBars())
            v.setPadding(systemBars.left, systemBars.top, systemBars.right, systemBars.bottom)
            insets
        }

        etNim = findViewById(R.id.etIsiNim)
        etNama = findViewById(R.id.etIsiNama)
        btnProses = findViewById(R.id.btnProses)
        tvHasil = findViewById(R.id.tvHasil)
    }

    override fun onStart() {
        super.onStart()
        btnProses.setOnClickListener {
            //buat sebuah variabel untuk menampung nilai dari edit text
            val nim = etNim.text
            val nama = etNama.text
            
            //Tampilkan input ke tvHasil
            tvHasil.text = "NIM: $nim\nNama: $nama"
        }
    }
}