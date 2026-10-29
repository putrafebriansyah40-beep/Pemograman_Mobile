package com.kotlin.basic.trpl.api.models

data class ProductModel(
    val products: List<Product>
) {
    data class Product(
        val id: Int,
        val title: String,
        val description: String,
        val price: Double,
        val rating: Double,
        val thumbnail: String
    )
}