package com.kotlin.basic.trpl.api

import android.os.Bundle
import android.util.Log
import android.view.View
import android.widget.Toast
import androidx.activity.enableEdgeToEdge
import androidx.appcompat.app.AppCompatActivity
import androidx.core.view.ViewCompat
import androidx.core.view.WindowInsetsCompat
import com.kotlin.basic.trpl.R
import com.kotlin.basic.trpl.api.adapters.ProductAdapter
import com.kotlin.basic.trpl.api.config.DummyApiClient
import com.kotlin.basic.trpl.api.models.ProductModel
import com.kotlin.basic.trpl.databinding.ActivityProductBinding
import retrofit2.Call
import retrofit2.Callback
import retrofit2.Response

class ProductActivity : AppCompatActivity() {

    private lateinit var binding: ActivityProductBinding

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        enableEdgeToEdge()
        binding = ActivityProductBinding.inflate(layoutInflater)
        setContentView(binding.root)

        ViewCompat.setOnApplyWindowInsetsListener(findViewById(R.id.main)) { v, insets ->
            val systemBars = insets.getInsets(WindowInsetsCompat.Type.systemBars())
            v.setPadding(systemBars.left, systemBars.top, systemBars.right, systemBars.bottom)
            insets
        }
    }

    override fun onStart() {
        super.onStart()
        getProducts()
    }

    private fun getProducts() {
        binding.pbProduct.visibility = View.VISIBLE
        binding.linearWarning.visibility = View.GONE

        DummyApiClient.apiService.getProducts().enqueue(object : Callback<ProductModel> {
            override fun onResponse(
                call: Call<ProductModel>,
                response: Response<ProductModel>
            ) {
                if (response.isSuccessful && response.body() != null) {
                    val listProduct = response.body()!!.products
                    binding.rvProduct.adapter = ProductAdapter(listProduct)
                } else {
                    binding.linearWarning.visibility = View.VISIBLE
                }
                binding.pbProduct.visibility = View.GONE
            }

            override fun onFailure(call: Call<ProductModel>, t: Throwable) {
                binding.linearWarning.visibility = View.VISIBLE
                binding.pbProduct.visibility = View.GONE
            }
        })
    }
}