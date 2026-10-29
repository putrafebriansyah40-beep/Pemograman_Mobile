package com.kotlin.basic.trpl.map

import android.Manifest
import android.content.pm.PackageManager
import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.view.Menu
import android.view.MenuItem
import android.view.View
import androidx.activity.enableEdgeToEdge
import androidx.core.app.ActivityCompat
import androidx.core.content.ContextCompat
import androidx.core.view.ViewCompat
import androidx.core.view.WindowInsetsCompat
import com.google.android.gms.common.GoogleApiAvailabilityLight

import com.google.android.gms.maps.CameraUpdateFactory
import com.google.android.gms.maps.GoogleMap
import com.google.android.gms.maps.OnMapReadyCallback
import com.google.android.gms.maps.SupportMapFragment
import com.google.android.gms.maps.model.LatLng
import com.google.android.gms.maps.model.MapStyleOptions
import com.google.android.gms.maps.model.MarkerOptions
import com.kotlin.basic.trpl.R
import com.kotlin.basic.trpl.databinding.ActivityMapsCustomeBinding

class MapsCustomeActivity : AppCompatActivity(), OnMapReadyCallback {

    private lateinit var mMap: GoogleMap
    private lateinit var binding: ActivityMapsCustomeBinding

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        enableEdgeToEdge()

        binding = ActivityMapsCustomeBinding.inflate(layoutInflater)
        setContentView(binding.root)
        ViewCompat.setOnApplyWindowInsetsListener(findViewById(R.id.main)) { v, insets ->
            val systemBars = insets.getInsets(WindowInsetsCompat.Type.systemBars())
            v.setPadding(systemBars.left, systemBars.top, systemBars.right, systemBars.bottom)
            insets
        }

        setSupportActionBar(binding.tbThemeMap)

        //menyebunyikan title pada toolbar
        supportActionBar?.setDisplayShowTitleEnabled(false)

        // Obtain the SupportMapFragment and get notified when the map is ready to be used.
        val mapFragment = supportFragmentManager
            .findFragmentById(R.id.map) as SupportMapFragment
        mapFragment.getMapAsync(this)
    }

    override fun onCreateOptionsMenu(menu: Menu?): Boolean {
        menuInflater.inflate(R.menu.theme_menu, menu)
        return super.onCreateOptionsMenu(menu)
    }

    override fun onOptionsItemSelected(item: MenuItem): Boolean {
        return when (item.itemId) {
            R.id.themeRetro -> {
                mMap.mapType = GoogleMap.MAP_TYPE_NORMAL
                setThemeMap(R.raw.retro_map)
                true
            }

            R.id.themeDark -> {
                mMap.mapType = GoogleMap.MAP_TYPE_NORMAL
                setThemeMap(R.raw.dark_map)
                true
            }

            R.id.themeNight -> {
                mMap.mapType = GoogleMap.MAP_TYPE_NORMAL
                setThemeMap(R.raw.night_map)
                true
            }

            R.id.themeAubergine -> {
                mMap.mapType = GoogleMap.MAP_TYPE_NORMAL
                setThemeMap(R.raw.aubergine)
                true
            }

            else -> super.onOptionsItemSelected(item)
        }

    }

    fun setThemeMap(fileJsonTheme: Int) {
        mMap.setMapStyle(MapStyleOptions.loadRawResourceStyle(this, fileJsonTheme))
    }

    fun tipeMapNormal() {
        binding.fabNormal.setOnClickListener {
            mMap.mapType = GoogleMap.MAP_TYPE_NORMAL
        }
    }

    fun tipeMapSatellite() {
        binding.fabSatellite.setOnClickListener {
            mMap.mapType = GoogleMap.MAP_TYPE_SATELLITE
        }
    }

    fun tipeMapTerrain() {
        binding.fabTerrain.setOnClickListener {
            mMap.mapType = GoogleMap.MAP_TYPE_TERRAIN
        }
    }

    fun myLocation() {
        if (ContextCompat.checkSelfPermission(
                this, Manifest.permission.ACCESS_FINE_LOCATION
            ) == PackageManager.PERMISSION_GRANTED
        ){
            mMap.isMyLocationEnabled = true
        }else{
            ActivityCompat.requestPermissions(
                this, arrayOf(Manifest.permission.ACCESS_FINE_LOCATION),
                1
            )
        }
    }

    /**
     * Manipulates the map once available.
     * This callback is triggered when the map is ready to be used.
     * This is where we can add markers or lines, add listeners or move the camera. In this case,
     * we just add a marker near Sydney, Australia.
     * If Google Play services is not installed on the device, the user will be prompted to install
     * it inside the SupportMapFragment. This method will only be triggered once the user has
     * installed Google Play services and returned to the app.
     */
    override fun onMapReady(googleMap: GoogleMap) {
        mMap = googleMap

        myLocation()

        val listHotel = listOf<HotelModel>(
            HotelModel(
                LatLng(-0.9441216669308189, 100.36200118496953),
                "HOTEL IMAM BONJOL",
                "Jl. Jend. Sudirman No.15, Kp. Jao, Kec. Padang Bar., Kota Padang, Sumatera Barat 25153",
                3.7f,
                "Rp. 400000",
                R.drawable.img_custome1
            ),
            HotelModel(
                LatLng(-0.9433707502982154, 100.36182952360019),
                "Hotel Amaris Padang",
                "Jl. Jend. Sudirman No.19, Jati Baru, Kec. Padang Tim., Kota Padang, Sumatera Barat 25129",
                4.5f,
                "Rp. 544000",
                R.drawable.img_custome2
            )
        )

        val titikTengah = LatLng(-0.9450442214288309, 100.36228013469467)
        for (i in listHotel) {
            val marker = mMap.addMarker(MarkerOptions().position(i.koordinat))
            //simpan data marker dalam tag
            marker?.tag = i
        }
        //saat marker diklik
        mMap.setOnMarkerClickListener { marker ->
            val itemHotel = marker.tag as? HotelModel
            if (itemHotel != null) {
                showMarkerDetail(itemHotel)
            }
            true
        }
        mMap.setOnMapClickListener {
            binding.cvDetail.visibility = View.GONE
        }
        mMap.moveCamera(CameraUpdateFactory.newLatLngZoom(titikTengah, 12f))

        tipeMapNormal()
        tipeMapSatellite()
        tipeMapTerrain()
    }

    //buat fungsi untuk menampilkan card view saat marker di klik
    fun showMarkerDetail(hotelModel: HotelModel) {
        binding.tvNamaTempat.text = hotelModel.namaTempat
        binding.tvAlamat.text = hotelModel.alamat
        binding.rbHotel.rating = hotelModel.rating
        binding.tvHarga.text = hotelModel.harga
        binding.ivHotel.setImageResource(hotelModel.gambar)
        ///tampilkan card view
        binding.cvDetail.visibility = View.VISIBLE
    }
}