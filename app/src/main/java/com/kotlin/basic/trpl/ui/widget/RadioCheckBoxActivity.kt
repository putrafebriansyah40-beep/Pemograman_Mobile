package com.kotlin.basic.trpl.ui.widget

import android.os.Bundle
import android.widget.Button
import android.widget.CheckBox
import android.widget.RadioButton
import android.widget.RadioGroup
import android.widget.TextView
import android.widget.Toast
import androidx.activity.enableEdgeToEdge
import androidx.appcompat.app.AppCompatActivity
import androidx.core.view.ViewCompat
import androidx.core.view.WindowInsetsCompat
import com.kotlin.basic.trpl.R

class RadioCheckBoxActivity : AppCompatActivity() {
    private lateinit var rgJK: RadioGroup
    private lateinit var cbBaca: CheckBox
    private lateinit var cbTulis: CheckBox
    private lateinit var cbNonton: CheckBox
    private lateinit var cbGame: CheckBox
    private lateinit var btnProses: Button
    private lateinit var tvHasil: TextView

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        enableEdgeToEdge()
        setContentView(R.layout.activity_radio_check_box)
        ViewCompat.setOnApplyWindowInsetsListener(findViewById(R.id.main)) { v, insets ->
            val systemBars = insets.getInsets(WindowInsetsCompat.Type.systemBars())
            v.setPadding(systemBars.left, systemBars.top, systemBars.right, systemBars.bottom)
            insets
        }
        rgJK = findViewById(R.id.rgJK)
        cbBaca = findViewById(R.id.cbBaca)
        cbTulis = findViewById(R.id.cbTulis)
        cbNonton = findViewById(R.id.cbNonton)
        cbGame = findViewById(R.id.cbGame)
        btnProses = findViewById(R.id.btnProses)
        tvHasil = findViewById(R.id.tvHasil)
    }
    override fun onStart() {
        super.onStart()
        btnProses.setOnClickListener {
            if (rgJK.checkedRadioButtonId!=-1) {
                //buat sebuah variabel untuk menampung nilai dari radio button
                var hobi = ""
                val pilHobi = arrayOf(cbBaca, cbTulis, cbNonton, cbGame)
                for (i in pilHobi.indices){
                    //i[o] => cbBaca, i[1] => cbTulis, i[2] => cbNonton, i[3] => cbGame
                    if(pilHobi[i].isChecked)
                        hobi += "${pilHobi[i].text}, "
                }
                val jk = findViewById<RadioButton>(rgJK.checkedRadioButtonId)
                tvHasil.text = "Jenis Kelamin : ${jk.text}\nHobi : $hobi"
            }
            else{
                Toast.makeText(this, "Silahkan pilih jenis kelamin",Toast.LENGTH_SHORT).show()
            }
        }
    }
}