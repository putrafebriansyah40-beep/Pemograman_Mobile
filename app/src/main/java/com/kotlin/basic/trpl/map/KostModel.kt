package com.kotlin.basic.trpl.map

import com.google.android.gms.maps.model.LatLng

data class KostModel(
    val Koordinat: LatLng,
    val title: String,
    val snippet: String,
    val gambar: Int
)
