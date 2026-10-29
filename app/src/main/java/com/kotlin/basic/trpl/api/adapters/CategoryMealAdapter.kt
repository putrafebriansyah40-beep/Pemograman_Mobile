package com.kotlin.basic.trpl.api.adapters

import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.ImageView
import android.widget.TextView
import androidx.recyclerview.widget.RecyclerView
import com.kotlin.basic.trpl.R
import com.kotlin.basic.trpl.api.models.CategoryModel
import com.squareup.picasso.Picasso

class CategoryMealAdapter(
    val listCategory: List<CategoryModel.Category>
) : RecyclerView.Adapter<CategoryMealAdapter.ViewCateogry>() {

    override fun onCreateViewHolder(
        parent: ViewGroup,
        viewType: Int
    ): ViewCateogry {
        return ViewCateogry(
            LayoutInflater.from(parent.context)
                .inflate(R.layout.category_meal_layout, parent, false)
        )
    }

    override fun onBindViewHolder(holder: ViewCateogry, position: Int) {
        val itemCategory = listCategory[position]
        Picasso.get().load(itemCategory.strCategoryThumb).into(holder.ivCateogry)
        holder.tvNamaCategory.text = itemCategory.strCategory
    }

    override fun getItemCount(): Int = listCategory.size

    class ViewCateogry(view: View) : RecyclerView.ViewHolder(view) {
        val ivCateogry = view.findViewById<ImageView>(R.id.ivCategory)
        val tvNamaCategory = view.findViewById<TextView>(R.id.tvNamaCategory)
    }
}