package com.kotlin.basic.trpl.recycler

import android.os.Bundle
import androidx.activity.enableEdgeToEdge
import androidx.appcompat.app.AppCompatActivity
import androidx.core.view.ViewCompat
import androidx.core.view.WindowInsetsCompat
import androidx.recyclerview.widget.RecyclerView
import com.kotlin.basic.trpl.R

class AksesorisActivity : AppCompatActivity() {
    private lateinit var rvAksesoris: RecyclerView
    private lateinit var rvFlash: RecyclerView
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        enableEdgeToEdge()
        setContentView(R.layout.activity_aksesoris)
        ViewCompat.setOnApplyWindowInsetsListener(findViewById(R.id.main)) { v, insets ->
            val systemBars = insets.getInsets(WindowInsetsCompat.Type.systemBars())
            v.setPadding(systemBars.left, systemBars.top, systemBars.right, systemBars.bottom)
            insets
        }
        rvAksesoris = findViewById(R.id.rvAksesoris)
        rvFlash = findViewById(R.id.rvFlash)

    }

    override fun onStart() {
        super.onStart()
        showAksesoris()
        showFlash()
    }

    private fun showFlash() {
        val listFlash = listOf<FlashModel>(
            FlashModel(
                R.drawable.img_flash1,
                "Rp28.100",
                "[100% ORI | Beli 2 Save More] Glad2Glow Make Up Remover Cleansing Water-300ml Cherry Blossom Betaine Micellar Water /Mugwort",
                4.5f,
                "Lorem ipsum dolor sit amet, consetetur sadipscing elitr, sed diam nonumy eirmod tempor invidunt ut labore et dolore magna aliquyam erat, sed diam voluptua. At vero eos et accusam et justo duo dolores et ea rebum. Stet clita kasd gubergren, no sea"
            ),
            FlashModel(
                R.drawable.img_flash2,
                "Rp29.800",
                "gelang clover e mas muda asli 3gram kadar 875 cantik elegan mewah free surat dari toko Bracelets",
                4.5f,
                "Lorem ipsum dolor sit amet, consetetur sadipscing elitr, sed diam nonumy eirmod tempor invidunt ut labore et dolore magna aliquyam erat, sed diam voluptua. At vero eos et accusam et justo duo dolores et ea rebum. Stet clita kasd gubergren"
            ),
            FlashModel(
                R.drawable.img_flash3,
                "Rp28.100",
                "LABORE Serum SynbioticBiome All Series Dermalab Revolutionaire Sensitive Skin Care - Brightening Serum 20ml or 7ml/Intensive Acne",
                4.5f,
                "Lorem ipsum dolor sit amet, consetetur sad"
            ),
            FlashModel(
                R.drawable.img_flash4,
                "Rp80.800",
                "I13 Pro TWS Headset Bluetooth Bass 9D HiFi Stereo Sound Earphone Wireless Waterproof Headphone Sport",
                4f,
                "Lorem ipsum dolor sit amet, consetetur sadipscing elitr, sed diam nonumy eirmod tempor invidunt ut labore et dolore magna aliquyam erat, sed diam voluptua. At vero eos et accusam et justo duo dolores et ea rebum. Stet clita kasd gubergren"
            )
        )
        rvFlash.adapter = FlashAdapter(listFlash)
    }

    private fun showAksesoris() {
        val listAksesoris = arrayListOf<AksesorisModel>(
            AksesorisModel(
                R.drawable.img_aksesoris1,
                "I13 Pro TWS Headset Bluetooth Bass 9D HiFi Stereo Sound Earphone Wireless Waterproof Headphone Sport",
                "Rp28.100",
                4.5f,
                "KOTA JAKARTA UTARA",
                "Lorem ipsum dolor sit amet, consetetur sadipscing elitr, sed diam"
            ),
            AksesorisModel(
                R.drawable.img_aksesoris2,
                "Pro 4 TWS Headset Bluetooth TWS with Mic Smart Touch Control Earphone HiFi Stereo Headset Wireless",
                "Rp29.800",
                4f,
                "KOTA JAKARTA UTARA",
                "Lorem ipsum dolor sit amet, consetetur sadipscing elitr, sed diam nonumy eir"
            )
        )
        rvAksesoris.adapter = AksesorisAdapter(listAksesoris)

    }
}