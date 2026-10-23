package com.kotlin.basic.trpl.map

import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import androidx.activity.enableEdgeToEdge

import com.google.android.gms.maps.CameraUpdateFactory
import com.google.android.gms.maps.GoogleMap
import com.google.android.gms.maps.OnMapReadyCallback
import com.google.android.gms.maps.SupportMapFragment
import com.google.android.gms.maps.model.BitmapDescriptorFactory
import com.google.android.gms.maps.model.LatLng
import com.google.android.gms.maps.model.MarkerOptions
import com.kotlin.basic.trpl.R
import com.kotlin.basic.trpl.databinding.ActivityMapsMultiMarkerBinding

class MapsMultiMarkerActivity : AppCompatActivity(), OnMapReadyCallback {

    private lateinit var mMap: GoogleMap
    private lateinit var binding: ActivityMapsMultiMarkerBinding

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        enableEdgeToEdge()

        binding = ActivityMapsMultiMarkerBinding.inflate(layoutInflater)
        setContentView(binding.root)

        // Obtain the SupportMapFragment and get notified when the map is ready to be used.
        val mapFragment = supportFragmentManager
            .findFragmentById(R.id.map) as SupportMapFragment
        mapFragment.getMapAsync(this)
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

        val listKos = listOf<KostModel>(
            KostModel(LatLng(-0.9203041216404281, 100.46601451907553),
                "Kost putra AR-RAZZAQ",
                "Jl. Puncak 2, Limau Manis, Kec. Pauh, Kota Padang, Sumatera Barat 25176",
                R.drawable.marker_kos
                ),
            KostModel(LatLng(-0.9198428411802833, 100.46622909579283),
                "Kost RAHMA",
                "Jl. Puncak No.I, Limau Manis, Kec. Pauh, Kota Padang, Sumatera Barat 25175",
                R.drawable.marker_restoran
                ),
            KostModel(
                LatLng(-0.9200252078809557, 100.46593941722448),
                "Kost Meisy",
                "Jl. Puncak, Limau Manis, Kec. Pauh, Kota Padang, Sumatera Barat 25175",
                R.drawable.marker_masjid
            ),
            KostModel(
                LatLng(-0.9201861196679285, 100.46716250451307),
                "Kost 6 Saudara",
                "Padang, Kec. Pauh, Kota Padang, Sumatera Barat 25175",
                R.drawable.marker_sekolah
                )
        )

        // Add a marker in Sydney and move the camera
        val titikTengah = LatLng(-0.9202649821441862, 100.46685861037217)
        for(i in listKos){
            mMap.addMarker(
                MarkerOptions()
                    .position(i.Koordinat)
                    .title(i.title)
                    .snippet(i.snippet)
                    .icon(BitmapDescriptorFactory.fromResource(i.gambar))
            )
        }
        mMap.moveCamera(CameraUpdateFactory.newLatLngZoom(titikTengah,12f))
    }
}