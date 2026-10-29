package com.kotlin.basic.trpl.api.adapters

import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.ImageView
import android.widget.TextView
import androidx.recyclerview.widget.RecyclerView
import com.kotlin.basic.trpl.R
import com.kotlin.basic.trpl.api.models.ProductModel
import com.squareup.picasso.Picasso

class ProductAdapter(
    val listProduct: List<ProductModel.Product>
) : RecyclerView.Adapter<ProductAdapter.ProductViewHolder>() {

    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): ProductViewHolder {
        val view = LayoutInflater.from(parent.context).inflate(R.layout.product_layout, parent, false)
        return ProductViewHolder(view)
    }

    override fun onBindViewHolder(holder: ProductViewHolder, position: Int) {
        val item = listProduct[position]
        holder.tvTitle.text = item.title
        holder.tvDescription.text = item.description
        holder.tvPrice.text = "$ ${item.price}"
        holder.tvRating.text = item.rating.toString()
        Picasso.get().load(item.thumbnail).into(holder.ivThumbnail)
        
        // Listener klik untuk menuju ke DetailProductActivity
        holder.itemView.setOnClickListener {
            val intent = android.content.Intent(holder.itemView.context, com.kotlin.basic.trpl.api.DetailProductActivity::class.java)
            intent.putExtra("title", item.title)
            intent.putExtra("description", item.description)
            intent.putExtra("price", item.price)
            intent.putExtra("rating", item.rating)
            intent.putExtra("thumbnail", item.thumbnail)
            holder.itemView.context.startActivity(intent)
        }
    }

    override fun getItemCount(): Int = listProduct.size

    class ProductViewHolder(view: View) : RecyclerView.ViewHolder(view) {
        val ivThumbnail: ImageView = view.findViewById(R.id.ivProduct)
        val tvTitle: TextView = view.findViewById(R.id.tvProductTitle)
        val tvDescription: TextView = view.findViewById(R.id.tvProductDescription)
        val tvPrice: TextView = view.findViewById(R.id.tvProductPrice)
        val tvRating: TextView = view.findViewById(R.id.tvProductRating)
    }
}