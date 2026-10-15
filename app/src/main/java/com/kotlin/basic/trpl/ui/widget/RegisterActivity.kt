package com.kotlin.basic.trpl.ui.widget

import android.app.DatePickerDialog
import android.icu.util.Calendar
import android.os.Bundle
import android.widget.ArrayAdapter
import android.widget.Button
import android.widget.CheckBox
import android.widget.EditText
import android.widget.RadioButton
import android.widget.RadioGroup
import android.widget.Spinner
import android.widget.TableLayout
import android.widget.TextView
import android.widget.Toast
import androidx.activity.enableEdgeToEdge
import androidx.appcompat.app.AppCompatActivity
import androidx.core.view.ViewCompat
import androidx.core.view.WindowInsetsCompat
import com.kotlin.basic.trpl.R
import java.text.SimpleDateFormat
import java.util.Locale

class RegisterActivity : AppCompatActivity() {
    private lateinit var email: EditText
    private lateinit var nama: EditText
    private lateinit var pw: EditText
    private lateinit var tlpn: EditText
    private lateinit var tamatan: RadioGroup
    private lateinit var Program: CheckBox
    private lateinit var AI: CheckBox
    private lateinit var Net: CheckBox
    private lateinit var Cyber: CheckBox
    private lateinit var btnProses: Button
    private lateinit var tvHasil: TextView
    private lateinit var spnProdi: Spinner
    private lateinit var tvTanggal: TextView
    private lateinit var datePicker: DatePickerDialog.OnDateSetListener


    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        enableEdgeToEdge()
        setContentView(R.layout.activity_register)
        ViewCompat.setOnApplyWindowInsetsListener(findViewById(R.id.main)) { v, insets ->
            val systemBars = insets.getInsets(WindowInsetsCompat.Type.systemBars())
            v.setPadding(systemBars.left, systemBars.top, systemBars.right, systemBars.bottom)
            insets
        }
        email = findViewById(R.id.etIsiEmail)
        nama = findViewById(R.id.etIsiNama)
        pw = findViewById(R.id.etIsiPw)
        tlpn = findViewById(R.id.etIsiTpln)
        tamatan = findViewById(R.id.rgtamat)
        Program = findViewById(R.id.cbProgram)
        AI = findViewById(R.id.cbAI)
        Net = findViewById(R.id.cbNet)
        Cyber = findViewById(R.id.cbCyber)
        btnProses = findViewById(R.id.btnProses)
        tvHasil = findViewById(R.id.tvHasil)
        spnProdi = findViewById(R.id.spnProdi)
        tvTanggal = findViewById(R.id.tvTanggal)
    }
    override fun onStart() {
        super.onStart()
        prodi()
        tanggal()
        btnProses.setOnClickListener {
            //buat sebuah variabel untuk menampung nilai dari edit text
            val email = email.text
            val nama = nama.text
            val pw = pw.text
            val tlpn = tlpn.text

            if (tamatan.checkedRadioButtonId!=-1) {
                //buat sebuah variabel untuk menampung nilai dari radio button
                var ahli = ""
                val pilAhli = arrayOf(Program, AI, Net, Cyber)
                for (i in pilAhli.indices) {
                    if (pilAhli[i].isChecked)
                        ahli += "${pilAhli[i].text}, "
                }
                val tamatan = findViewById<RadioButton>(tamatan.checkedRadioButtonId)
                tvHasil.text = "Email: $email\nNama: $nama\nPassword: $pw\nNomor Telepon: $tlpn\nTamatan: ${tamatan.text}\nKeahlian : $ahli\nProgram Studi : ${spnProdi.selectedItem}\nTanggal: ${tvTanggal.text}"
            }
            else{
                Toast.makeText(this, "Silahkan pilih Tamatan Anda",Toast.LENGTH_SHORT).show()
            }
        }
    }

    private fun tanggal() {
        val myCalendar = Calendar.getInstance()
        datePicker = DatePickerDialog.OnDateSetListener{_, year, month, dayOfMonth ->
            myCalendar[Calendar.YEAR] = year
            myCalendar[Calendar.MONTH] = month
            myCalendar[Calendar.DAY_OF_MONTH] = dayOfMonth
            val formatIndo = SimpleDateFormat("dd-MM-yyyy", Locale.UK)
            tvTanggal.text = formatIndo.format(myCalendar.time)
        }
        tvTanggal.setOnClickListener {
            DatePickerDialog(
                this,
                datePicker,
                myCalendar[Calendar.YEAR],
                myCalendar[Calendar.MONTH],
                myCalendar[Calendar.DAY_OF_MONTH]
            ).show()
        }
    }

    private fun prodi() {
        val listProdi = arrayOf("TRPL", "Animasi", "MI", "Tekkom", "SI")
        spnProdi.adapter = ArrayAdapter(this,R.layout.spinner_style,listProdi)
    }
}