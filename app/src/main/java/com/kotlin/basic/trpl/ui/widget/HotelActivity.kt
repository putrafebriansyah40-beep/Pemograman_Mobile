package com.kotlin.basic.trpl.ui.widget

import android.app.DatePickerDialog
import android.app.TimePickerDialog
import android.icu.util.Calendar
import android.os.Bundle
import android.text.Editable
import android.text.TextWatcher
import android.widget.*
import androidx.activity.enableEdgeToEdge
import androidx.appcompat.app.AppCompatActivity
import androidx.core.view.ViewCompat
import androidx.core.view.WindowInsetsCompat
import com.kotlin.basic.trpl.R
import java.text.SimpleDateFormat
import java.util.Locale

class HotelActivity : AppCompatActivity() {

    private lateinit var etRegis: EditText
    private lateinit var etNama: EditText
    private lateinit var etNik: EditText
    private lateinit var etTelepon: EditText
    private lateinit var tvTanggal: TextView
    private lateinit var tvTime: TextView
    private lateinit var spnKelasKamar: Spinner
    private lateinit var rgKategori: RadioGroup
    private lateinit var etJmlHari: EditText

    private lateinit var cbBedSofa: CheckBox
    private lateinit var cbSofa: CheckBox
    private lateinit var cbDispenser: CheckBox
    private lateinit var cbMakanan: CheckBox

    private lateinit var etBedSofa: EditText
    private lateinit var etSofa: EditText
    private lateinit var etDispenser: EditText
    private lateinit var etMakanan: EditText

    private lateinit var btnSimpan: Button
    private lateinit var tvLabelBayar: TextView
    private lateinit var tvHasil: TextView

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        enableEdgeToEdge()
        setContentView(R.layout.activity_hotel)
        ViewCompat.setOnApplyWindowInsetsListener(findViewById(R.id.main)) { v, insets ->
            val systemBars = insets.getInsets(WindowInsetsCompat.Type.systemBars())
            v.setPadding(systemBars.left, systemBars.top, systemBars.right, systemBars.bottom)
            insets
        }

        // Inisialisasi View
        etRegis = findViewById(R.id.etIsiRegis)
        etNama = findViewById(R.id.etIsiNama)
        etNik = findViewById(R.id.etIsiNik)
        etTelepon = findViewById(R.id.etIsiTpln)
        tvTanggal = findViewById(R.id.tvTanggal)
        tvTime = findViewById(R.id.tvTime)
        spnKelasKamar = findViewById(R.id.spnKelasKamar)
        rgKategori = findViewById(R.id.rgkategori)
        etJmlHari = findViewById(R.id.etIsiJlmhHari)

        cbBedSofa = findViewById(R.id.cbBedSofa)
        cbSofa = findViewById(R.id.cbSofa)
        cbDispenser = findViewById(R.id.cbDispenser)
        cbMakanan = findViewById(R.id.cbMakanan)

        etBedSofa = findViewById(R.id.etBedSofa)
        etSofa = findViewById(R.id.etSofa)
        etDispenser = findViewById(R.id.etDispenser)
        etMakanan = findViewById(R.id.etMakanan)

        btnSimpan = findViewById(R.id.btnSimpan)
        tvLabelBayar = findViewById(R.id.tvLabelBayar)
        tvHasil = findViewById(R.id.tvHasil)
    }

    override fun onStart() {
        super.onStart()
        setupSpinner()
        setupDateTimePickers()
        setupCheckboxLogic()
        prosesSimpan()
    }

    private fun setupSpinner() {
        val listKelas = arrayOf(
            "Kelas Super VIP (Rp 750.000)", 
            "Kelas VIP (Rp 600.000)", 
            "Kelas Utama (Rp 400.000)", 
            "Kelas I (Rp 255.000)",
            "Kelas II (Rp 170.000)",
            "Kelas III (Rp 160.000)"
        )
        spnKelasKamar.adapter = ArrayAdapter(this, android.R.layout.simple_spinner_dropdown_item, listKelas)
    }

    private fun setupDateTimePickers() {
        // Tanggal
        val calendar = Calendar.getInstance()
        val dateSetListener = DatePickerDialog.OnDateSetListener { _, year, month, dayOfMonth ->
            calendar.set(Calendar.YEAR, year)
            calendar.set(Calendar.MONTH, month)
            calendar.set(Calendar.DAY_OF_MONTH, dayOfMonth)
            val formatIndo = SimpleDateFormat("dd MMMM yyyy", Locale.Builder().setLanguage("id").setRegion("ID").build())
            tvTanggal.text = formatIndo.format(calendar.time)
        }
        tvTanggal.setOnClickListener {
            DatePickerDialog(
                this, dateSetListener,
                calendar.get(Calendar.YEAR),
                calendar.get(Calendar.MONTH),
                calendar.get(Calendar.DAY_OF_MONTH)
            ).show()
        }

        // Waktu
        val timeSetListener = TimePickerDialog.OnTimeSetListener { _, hourOfDay, minute ->
            calendar.set(Calendar.HOUR_OF_DAY, hourOfDay)
            calendar.set(Calendar.MINUTE, minute)
            val formatWaktu = SimpleDateFormat("HH:mm", Locale.getDefault())
            tvTime.text = formatWaktu.format(calendar.time)
        }
        tvTime.setOnClickListener {
            TimePickerDialog(
                this, timeSetListener,
                calendar.get(Calendar.HOUR_OF_DAY),
                calendar.get(Calendar.MINUTE),
                true
            ).show()
        }
    }

    private fun setupCheckboxLogic() {
        val checkBoxes = arrayOf(cbBedSofa, cbSofa, cbDispenser, cbMakanan)
        val editTexts = arrayOf(etBedSofa, etSofa, etDispenser, etMakanan)

        for (i in editTexts.indices) {
            // Logika 1: Jika EditText diisi angka, otomatis centang Checkbox
            editTexts[i].addTextChangedListener(object : TextWatcher {
                override fun afterTextChanged(s: Editable?) {}
                override fun beforeTextChanged(s: CharSequence?, start: Int, count: Int, after: Int) {}
                override fun onTextChanged(s: CharSequence?, start: Int, before: Int, count: Int) {
                    // Mencegah infinite loop dengan listener Checkbox
                    checkBoxes[i].setOnCheckedChangeListener(null)
                    checkBoxes[i].isChecked = !s.isNullOrEmpty() && s.toString() != "0"
                    setCheckboxListener(checkBoxes[i], editTexts[i])
                }
            })

            // Set listener awal
            setCheckboxListener(checkBoxes[i], editTexts[i])
        }
    }

    private fun setCheckboxListener(checkBox: CheckBox, editText: EditText) {
        checkBox.setOnCheckedChangeListener { _, isChecked ->
            if (isChecked) {
                // Jika dicentang tapi teks masih kosong, otomatis isi dengan "1"
                if (editText.text.isNullOrEmpty() || editText.text.toString() == "0") {
                    editText.setText("1")
                }
            } else {
                // Jika tidak dicentang, kosongkan EditText
                editText.setText("")
            }
        }
    }

    private fun prosesSimpan() {
        btnSimpan.setOnClickListener {
            val regis = etRegis.text.toString()
            val nama = etNama.text.toString()
            val nik = etNik.text.toString()
            val telepon = etTelepon.text.toString()
            val tanggal = tvTanggal.text.toString()
            val waktu = tvTime.text.toString()
            val kelasKamar = spnKelasKamar.selectedItem.toString()
            val jmlHari = etJmlHari.text.toString().toIntOrNull() ?: 0

            // Validasi Kategori Pasien
            val selectedKategoriId = rgKategori.checkedRadioButtonId
            if (selectedKategoriId == -1) {
                Toast.makeText(this, "Silakan pilih Kategori Pasien!", Toast.LENGTH_SHORT).show()
                return@setOnClickListener
            }
            val rbKategori = findViewById<RadioButton>(selectedKategoriId)
            val kategoriPasien = rbKategori.text.toString()

            // Harga Kamar Dasar
            val hargaKamar = when (spnKelasKamar.selectedItemPosition) {
                0 -> 750000 // Kelas Super VIP
                1 -> 600000 // Kelas VIP
                2 -> 400000 // Kelas Utama
                3 -> 255000 // Kelas I
                4 -> 170000 // Kelas II
                5 -> 160000 // Kelas III
                else -> 0
            }
            val totalHargaKamar = hargaKamar * jmlHari

            // Hitung Biaya Tambahan
            var totalTambahan = 0
            var detailTambahan = ""

            if (cbBedSofa.isChecked) {
                val hari = etBedSofa.text.toString().toIntOrNull() ?: 0
                val sub = 75000 * hari
                totalTambahan += sub
                detailTambahan += "- Bed Sofa ($hari hari) = Rp $sub\n"
            }
            if (cbSofa.isChecked) {
                val hari = etSofa.text.toString().toIntOrNull() ?: 0
                val sub = 65000 * hari
                totalTambahan += sub
                detailTambahan += "- Sofa ($hari hari) = Rp $sub\n"
            }
            if (cbDispenser.isChecked) {
                val hari = etDispenser.text.toString().toIntOrNull() ?: 0
                val sub = 25000 * hari
                totalTambahan += sub
                detailTambahan += "- Dispenser ($hari hari) = Rp $sub\n"
            }
            if (cbMakanan.isChecked) {
                val hari = etMakanan.text.toString().toIntOrNull() ?: 0
                val sub = 45000 * hari
                totalTambahan += sub
                detailTambahan += "- Makanan ($hari hari) = Rp $sub\n"
            }

            var multiplierDiskon = 0.0
            if (kategoriPasien == "BPJS") {
                multiplierDiskon = 0.1
            } else if (kategoriPasien == "Non BPJS") {
                 multiplierDiskon = 0.05
            } else if (kategoriPasien == "Mandiri") {
                multiplierDiskon = 0.03
            }
            
            val diskon = (multiplierDiskon * totalHargaKamar).toInt()

            val potongan = if (jmlHari > 3) 50000 else 0
            
            val totalBiaya = (totalHargaKamar + totalTambahan) - diskon - potongan
            val baris1 = "= harga kamar * jumlah hari + biaya tambahan - (diskon * harga kamar * jumlah hari) - Potongan"
            val baris2 = "= (Rp. $hargaKamar * $jmlHari) + Rp. $totalTambahan - Rp. $diskon - Rp. $potongan"
            val baris3 = "= Rp. ${totalHargaKamar + totalTambahan} - Rp. ${diskon + potongan}"
            val baris4 = "= Rp. $totalBiaya"

            val rumusPerhitungan = "$baris1\n$baris2\n$baris3\n$baris4"

            val tambahanText = if (detailTambahan.isEmpty()) "- Tidak Ada" else detailTambahan.trimEnd()

            val hasil = "No Reg : $regis\nNama Lengkap : $nama\nNIK : $nik\nHP : $telepon\nTanggal Masuk : $tanggal\nJam Masuk : $waktu\nKelas Kamar : $kelasKamar\nKategori Pasien : $kategoriPasien\nJumlah Hari : $jmlHari\nBiaya Tambahan:\n$tambahanText\nTotal Bayar : $totalBiaya\n\n\nTotal biaya\n$rumusPerhitungan\n"
        }
    }
}