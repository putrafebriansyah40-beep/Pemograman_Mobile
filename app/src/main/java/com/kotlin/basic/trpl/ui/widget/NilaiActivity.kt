package com.kotlin.basic.trpl.ui.widget

import android.os.Bundle
import android.widget.ArrayAdapter
import android.widget.Button
import android.widget.EditText
import android.widget.RadioButton
import android.widget.RadioGroup
import android.widget.Spinner
import android.widget.TextView
import android.widget.Toast
import androidx.activity.enableEdgeToEdge
import androidx.appcompat.app.AppCompatActivity
import androidx.core.view.ViewCompat
import androidx.core.view.WindowInsetsCompat
import com.kotlin.basic.trpl.R

class NilaiActivity : AppCompatActivity() {
    private lateinit var etTgs: EditText
    private lateinit var etKhd: EditText
    private lateinit var etUts: EditText
    private lateinit var etUas: EditText
    private lateinit var btnSubmit: Button
    private lateinit var tvNilai: TextView
    private lateinit var etNim: EditText
    private lateinit var etNama: EditText
    private lateinit var spnJurusan: Spinner
    private lateinit var rgJK: RadioGroup

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        enableEdgeToEdge()
        setContentView(R.layout.activity_nilai)
        ViewCompat.setOnApplyWindowInsetsListener(findViewById(R.id.main)) { v, insets ->
            val systemBars = insets.getInsets(WindowInsetsCompat.Type.systemBars())
            v.setPadding(systemBars.left, systemBars.top, systemBars.right, systemBars.bottom)
            insets
        }

        etTgs = findViewById(R.id.etTgs)
        etKhd = findViewById(R.id.etKhd)
        etUts = findViewById(R.id.etUts)
        etUas = findViewById(R.id.etUas)

        btnSubmit = findViewById(R.id.btnSubmit)
        tvNilai = findViewById(R.id.tvNilai)
        etNim = findViewById(R.id.etIsiNim)
        etNama = findViewById(R.id.etIsiNama)
        spnJurusan = findViewById(R.id.spnJurusan)
        rgJK = findViewById(R.id.rgJK)
    }

    override fun onStart() {
        super.onStart()
        jurusan()
        nilaiAkhir()
    }

    private fun jurusan() {
        val listJurusan = arrayOf("TRPL", "Animasi", "MI", "Tekkom", "SI")
        spnJurusan.adapter = ArrayAdapter(this, R.layout.spinner_style, listJurusan)
    }

    private fun nilaiAkhir() {
        btnSubmit.setOnClickListener {
            val nim = etNim.text.toString()
            val nama = etNama.text.toString()
            val jurusan = spnJurusan.selectedItem.toString()
            
            // Validasi Jenis Kelamin
            val selectedJKId = rgJK.checkedRadioButtonId
            if (selectedJKId == -1) {
                Toast.makeText(this, "Silakan pilih jenis kelamin!", Toast.LENGTH_SHORT).show()
                return@setOnClickListener
            }
            val rbJK = findViewById<RadioButton>(selectedJKId)
            val jenisKelamin = rbJK.text.toString()

            // Memberikan nilai default 0 jika input kosong atau bukan angka
            val tugas = etTgs.text.toString().toDoubleOrNull() ?: 0.0
            val kehadiran = etKhd.text.toString().toDoubleOrNull() ?: 0.0
            val uts = etUts.text.toString().toDoubleOrNull() ?: 0.0
            val uas = etUas.text.toString().toDoubleOrNull() ?: 0.0

            // Melakukan perhitungan
            val nilaiAngka = (kehadiran * 0.1) + (tugas * 0.2) + (((uts + uas) / 2) * 0.7)


            val nilaiHuruf = when (nilaiAngka) {
                in 90.0..100.0 -> "A"
                in 81.0..89.0 -> "A-"
                in 76.0..80.0 -> "B+"
                in 71.0..75.0 -> "B"
                in 66.0..70.0 -> "B-"
                in 61.0..65.0 -> "C+"
                in 51.0..60.0 -> "C"
                in 46.0..50.0 -> "D"
                else -> "E"
            }
            val hasil =
                "NIM: $nim\nNama: $nama\nJurusan: $jurusan\nJenis Kelamin: $jenisKelamin\nNilai Kehadiran: $kehadiran\nNilai Tugas: $tugas\nNilai UTS: $uts\nNilai UAS: $uas\nNilai Akhir: $nilaiAngka\nNilai Huruf: $nilaiHuruf"
            tvNilai.text = hasil
        }
    }
}