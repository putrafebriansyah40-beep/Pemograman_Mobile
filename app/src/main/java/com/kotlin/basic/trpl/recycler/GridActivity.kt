package com.kotlin.basic.trpl.recycler

import android.os.Bundle
import androidx.activity.enableEdgeToEdge
import androidx.appcompat.app.AppCompatActivity
import androidx.core.view.ViewCompat
import androidx.core.view.WindowInsetsCompat
import androidx.recyclerview.widget.RecyclerView
import com.kotlin.basic.trpl.R

class GridActivity : AppCompatActivity() {
    private lateinit var rvGrid: RecyclerView
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        enableEdgeToEdge()
        setContentView(R.layout.activity_grid)
        ViewCompat.setOnApplyWindowInsetsListener(findViewById(R.id.main)) { v, insets ->
            val systemBars = insets.getInsets(WindowInsetsCompat.Type.systemBars())
            v.setPadding(systemBars.left, systemBars.top, systemBars.right, systemBars.bottom)
            insets
        }
        rvGrid = findViewById(R.id.rvGrid)
    }

    override fun onStart() {
        super.onStart()
        showGrid()
    }

    private fun showGrid() {
        val listGrid = listOf<GridModel>(
            GridModel(
                R.drawable.img_grid1,
                "INFINIX SMART 7 SMART 9 9 HD SMART 10 10 PLUS 10 HD NEW ANTI GORES KERAMIK SPY ANTI PECAH - BD",
                "Rp28.100",
                4.5f,
                "10rb",
                "Lorem ipsum dolor sit amet, consetetur sadipscing elitr, sed diam"
            ),
            GridModel(
                R.drawable.img_grid2,
                "I13 Pro TWS Headset Bluetooth Bass 9D HiFi Stereo Sound Earphone Wireless Waterproof Headphone Sport",
                "Rp29.800",
                4f,
                "1jt",
                "Lorem ipsum dolor sit amet, consetetur sadipscing elitr, sed diam nonumy eirmod tempor invidunt ut labore et dolore magna aliquyam erat, sed diam voluptua. At vero eos et accusam et justo duo dolores et ea rebum. Stet clita kasd gubergren, no sea takimata"
            ),
            GridModel(
                R.drawable.img_grid3,
                "Pro 4 TWS Headset Bluetooth TWS with Mic Smart Touch Control Earphone HiFi Stereo Headset Wireless",
                "Rp28.100",
                4.5f,
                "123rb",
                "Lorem ipsum dolor sit amet, consetetur sadipscing elitr, sed diam nonumy eirmod tempor invidunt ut labore et dolore magna aliquyam erat, sed diam voluptua. At vero eos et accusam et justo duo dolores et ea rebum. Stet clita kasd gubergren, no sea takimata"
            ),
            GridModel(
                R.drawable.img_grid4,
                "I13 Pro TWS Headset Bluetooth Bass 9D HiFi Stereo Sound Earphone Wireless Waterproof Headphone Sport",
                "Rp29.800",
                4f,
                "1jt",
                "Lorem ipsum dolor sit amet, consetetur sadi"
            )
        )
        rvGrid.adapter = GridAdapter(listGrid)

    }
}