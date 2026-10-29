package com.kotlin.basic.trpl.api.config

import com.kotlin.basic.trpl.api.models.CategoryModel
import retrofit2.Call
import retrofit2.http.GET

interface ApiService {
    //mengambil category dari the meal
    @GET("api/json/v1/1/categories.php")
    fun getCategory(): Call<CategoryModel>
}