package com.kotlin.basic.trpl.api.config

import com.kotlin.basic.trpl.api.models.ProductModel
import retrofit2.Call
import retrofit2.http.GET

interface DummyApiService {
    @GET("products")
    fun getProducts(): Call<ProductModel>
}