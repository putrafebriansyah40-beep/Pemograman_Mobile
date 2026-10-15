package com.kotlin.basic.trpl.ui.widget

import android.os.Bundle
import android.text.Editable
import android.text.TextWatcher
import android.widget.ArrayAdapter
import android.widget.Button
import android.widget.CheckBox
import android.widget.EditText
import android.widget.Spinner
import android.widget.TextView
import androidx.activity.enableEdgeToEdge
import androidx.appcompat.app.AppCompatActivity
import androidx.core.view.ViewCompat
import androidx.core.view.WindowInsetsCompat
import com.kotlin.basic.trpl.R

class PembayaranActivity : AppCompatActivity() {

    private lateinit var cbNasgor: CheckBox
    private lateinit var cbMiegor: CheckBox
    private lateinit var cbSoto: CheckBox
    private lateinit var cbLontong: CheckBox
    private lateinit var cbAygep: CheckBox

    private lateinit var etNasgor: EditText
    private lateinit var etMiegor: EditText
    private lateinit var etSoto: EditText
    private lateinit var etLontong: EditText
    private lateinit var etAygep: EditText

    private lateinit var btnBayar: Button
    private lateinit var tvTotal: TextView
    private lateinit var spnJnsPelanggan: Spinner


    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        enableEdgeToEdge()
        setContentView(R.layout.activity_pembayaran)
        ViewCompat.setOnApplyWindowInsetsListener(findViewById(R.id.main)) { v, insets ->
            val systemBars = insets.getInsets(WindowInsetsCompat.Type.systemBars())
            v.setPadding(systemBars.left, systemBars.top, systemBars.right, systemBars.bottom)
            insets
        }
        cbNasgor = findViewById(R.id.cbNasgor)
        cbMiegor = findViewById(R.id.cbMiegor)
        cbSoto = findViewById(R.id.cbSoto)
        cbLontong = findViewById(R.id.cbLontong)
        cbAygep = findViewById(R.id.cbAygep)
        etNasgor = findViewById(R.id.etNasgor)
        etMiegor = findViewById(R.id.etMiegor)
        etSoto = findViewById(R.id.etSoto)
        etLontong = findViewById(R.id.etLontong)
        etAygep = findViewById(R.id.etAygep)
        btnBayar = findViewById(R.id.btnBayar)
        tvTotal = findViewById(R.id.tvTotal)
        spnJnsPelanggan = findViewById(R.id.spnJnsPelanggan)

    }
    override fun onStart() {
        super.onStart()
        jnsPelanggan()
        bayar()
    }

    private fun jnsPelanggan() {
        val listJnsPelanggan = arrayOf("VIP","Member","Reguler")
        spnJnsPelanggan.adapter = ArrayAdapter(this,R.layout.spinner_style,listJnsPelanggan)
    }

    private fun bayar() {
        // Buat variable untuk list makanan
        val listMakanan = arrayOf(cbNasgor, cbMiegor, cbSoto, cbLontong, cbAygep)


        // Variable harga makanan
        val hargaMakanan = intArrayOf(12_000, 10_000, 12_000, 8_000, 15_000)

        // Buat variable jumlah makanan
        val jlhMakanan = arrayOf(etNasgor, etMiegor, etSoto, etLontong, etAygep)

        for (i in jlhMakanan.indices) {
            jlhMakanan[i].addTextChangedListener(object : TextWatcher {
                override fun afterTextChanged(s: Editable?) {
                }

                override fun beforeTextChanged(
                    s: CharSequence?,
                    start: Int,
                    count: Int,
                    after: Int
                ) {
                }

                override fun onTextChanged(
                    s: CharSequence?,
                    start: Int,
                    before: Int,
                    count: Int
                ) {
                    listMakanan[i].isChecked = !s.isNullOrEmpty() && s.toString() != "0"
                }

            })
        }

        btnBayar.setOnClickListener {
            var totalBayar = 0
            var pilMakanan = ""
            val pelanggan = spnJnsPelanggan.selectedItem

            val diskon = when(pelanggan){
                "VIP" -> 0.02
                "Member" -> 0.01
                else -> 0.0
            }

            for (i in listMakanan.indices) {
                // Cek apakah sudah dicentang atau belum dari checkbox
                if (listMakanan[i].isChecked) {
                    val jlhMkn = jlhMakanan[i].text.toString().toIntOrNull() ?: 0
                    totalBayar += jlhMkn * hargaMakanan[i]
                    pilMakanan += "${listMakanan[i].text} x ${jlhMakanan[i].text} = Rp${totalBayar}\n"
                }
            }

            val potongaHarga = if (totalBayar >= 150_000) 1_000 else 0

            val totalDiskon = (totalBayar * diskon).toInt()
            totalBayar -= totalDiskon
            totalBayar -= potongaHarga

            val pembayaran = "$pilMakanan\nTotal Diskon: Rp$totalDiskon\nTotal Potongan: Rp$potongaHarga\nTotal Bayar: Rp$totalBayar"

            tvTotal.text = pembayaran
        }
    }
}