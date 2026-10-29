package com.kotlin.basic.trpl.api

import android.os.Bundle
import android.view.View
import androidx.activity.enableEdgeToEdge
import androidx.appcompat.app.AppCompatActivity
import androidx.core.view.ViewCompat
import androidx.core.view.WindowInsetsCompat
import com.kotlin.basic.trpl.R
import com.kotlin.basic.trpl.api.adapters.CategoryMealAdapter
import com.kotlin.basic.trpl.api.config.ApiClient
import com.kotlin.basic.trpl.api.models.CategoryModel
import com.kotlin.basic.trpl.databinding.ActivityMealCategoryBinding
import retrofit2.Call
import retrofit2.Callback
import retrofit2.Response

class MealCategoryActivity : AppCompatActivity() {

    private lateinit var binding: ActivityMealCategoryBinding

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        enableEdgeToEdge()
        binding = ActivityMealCategoryBinding.inflate(layoutInflater)
        setContentView(binding.root)
        ViewCompat.setOnApplyWindowInsetsListener(findViewById(R.id.main)) { v, insets ->
            val systemBars = insets.getInsets(WindowInsetsCompat.Type.systemBars())
            v.setPadding(systemBars.left, systemBars.top, systemBars.right, systemBars.bottom)
            insets
        }
    }

    override fun onStart() {
        super.onStart()
        showCategory()
    }

    private fun showCategory() {
        binding.pbCategory.visibility = View.VISIBLE
        binding.linearWarning.visibility = View.GONE
        ApiClient.apiService.getCategory().enqueue(object : Callback<CategoryModel>{
            override fun onResponse(
                call: Call<CategoryModel?>,
                response: Response<CategoryModel?>
            ) {
                if(response.isSuccessful){
                    val listCategory = response.body()!!.categories
                    binding.rvCategory.adapter = CategoryMealAdapter(listCategory)
                }else{
                    binding.linearWarning.visibility = View.VISIBLE
                }
                binding.pbCategory.visibility = View.GONE
            }

            override fun onFailure(
                call: Call<CategoryModel?>,
                t: Throwable
            ) {
                binding.linearWarning.visibility = View.VISIBLE
                binding.pbCategory.visibility = View.GONE
            }
        })
    }
}