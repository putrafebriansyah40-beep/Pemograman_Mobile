package com.kotlin.basic.trpl.map

import com.google.android.gms.maps.model.LatLng

data class HotelModel(
    val koordinat: LatLng,
    val namaTempat: String,
    val alamat: String,
    val rating: Float,
    val harga: String,
    val gambar: Int
)
