package com.kotlin.basic.trpl.intent

import android.os.Bundle
import android.widget.TextView
import androidx.activity.enableEdgeToEdge
import androidx.appcompat.app.AppCompatActivity
import androidx.core.view.ViewCompat
import androidx.core.view.WindowInsetsCompat
import com.kotlin.basic.trpl.R

class NilaiAkhirActivity : AppCompatActivity() {

    private lateinit var tvNim: TextView
    private lateinit var tvNama: TextView
    private lateinit var tvProdi: TextView
    private lateinit var tvJK: TextView
    private lateinit var tvKehadiran: TextView
    private lateinit var tvTgs: TextView
    private lateinit var tvUts: TextView
    private lateinit var tvUas: TextView
    private lateinit var tvNilaiAkhir: TextView
    private lateinit var tvNilaiHuruf: TextView
    private lateinit var tvWaktuInput: TextView

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        enableEdgeToEdge()
        setContentView(R.layout.activity_nilai_akhir)
        ViewCompat.setOnApplyWindowInsetsListener(findViewById(R.id.main)) { v, insets ->
            val systemBars = insets.getInsets(WindowInsetsCompat.Type.systemBars())
            v.setPadding(systemBars.left, systemBars.top, systemBars.right, systemBars.bottom)
            insets
        }
        tvNim = findViewById(R.id.tvNim)
        tvNama = findViewById(R.id.tvNama)
        tvProdi = findViewById(R.id.tvProdi)
        tvJK = findViewById(R.id.tvJK)
        tvKehadiran = findViewById(R.id.tvKehadiran)
        tvTgs = findViewById(R.id.tvTgs)
        tvUts = findViewById(R.id.tvUts)
        tvUas = findViewById(R.id.tvUas)
        tvNilaiAkhir = findViewById(R.id.tvNilaiAkhir)
        tvNilaiHuruf = findViewById(R.id.tvNilaiHuruf)
        tvWaktuInput = findViewById(R.id.tvWaktuInput)
    }

    override fun onStart() {
        super.onStart()
        nilai()
    }

    private fun nilai() {
        val nim = intent.getStringExtra("NIM")
        val nama = intent.getStringExtra("Nama")
        val prodi = intent.getStringExtra("Program Studi")
        val jenisKelamin = intent.getStringExtra("JenisKelamin")
        val kehadiran = intent.getDoubleExtra("kehadiran", 0.0)
        val tugas = intent.getDoubleExtra("Tugas", 0.0)
        val uts = intent.getDoubleExtra("UTS", 0.0)
        val uas = intent.getDoubleExtra("UAS", 0.0)
        val nilaiAkhir = intent.getDoubleExtra("NilaiAkhir",0.0)
        val nilaiHuruf = intent.getStringExtra("NilaiHuruf")
        val waktuInput = intent.getStringExtra("WaktuInput")

        tvNim.text = "NIM : $nim"
        tvNama.text = "Nama : $nama"
        tvProdi.text = "Program Studi : $prodi"
        tvJK.text = "Jenis Kelamin : $jenisKelamin"
        tvKehadiran.text = "Kehadiran : $kehadiran"
        tvTgs.text = "Tugas : $tugas"
        tvUts.text = "UTS : $uts"
        tvUas.text = "UAS : $uas"
        tvNilaiAkhir.text = "Nilai Akhir : $nilaiAkhir"
        tvNilaiHuruf.text = "Nilai Huruf : $nilaiHuruf"
        tvWaktuInput.text = "Waktu Input : $waktuInput"
    }
}