package com.kotlin.basic.trpl.recycler

import android.content.Intent
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.ImageView
import android.widget.TextView
import android.widget.Toast
import androidx.recyclerview.widget.RecyclerView
import com.kotlin.basic.trpl.R

class FlashAdapter(
    val listFlash: List<FlashModel>
) : RecyclerView.Adapter<FlashAdapter.ViewFlash>() {

    override fun onCreateViewHolder(
        parent: ViewGroup,
        viewType: Int
    ): ViewFlash {
        return ViewFlash(
            LayoutInflater.from(parent.context).inflate(R.layout.flash_layout, parent, false)
        )
    }

    override fun onBindViewHolder(holder: ViewFlash, position: Int) {
        val itemFlash = listFlash[position]
        
        holder.ivFlash.setImageResource(itemFlash.gambarFlash)
        holder.tvHargaFlash.text = itemFlash.harga
        holder.tvNamaFlash.text = itemFlash.namaFlash
        holder.tvRatingFlash.text = "⭐ ${itemFlash.rating}"

        // Menambahkan listener klik
        holder.itemView.setOnClickListener {
            val intent = Intent(holder.itemView.context, DetailFlashActivity::class.java)
            intent.putExtra("gambar", itemFlash.gambarFlash)
            intent.putExtra("harga", itemFlash.harga)
            intent.putExtra("nama", itemFlash.namaFlash)
            intent.putExtra("rating", itemFlash.rating)
            intent.putExtra("deskripsi", itemFlash.deskripsi)
            holder.itemView.context.startActivity(intent)
        }
    }

    override fun getItemCount(): Int = listFlash.size

    class ViewFlash(view: View) : RecyclerView.ViewHolder(view) {
        val ivFlash = view.findViewById<ImageView>(R.id.ivFlash)
        val tvHargaFlash = view.findViewById<TextView>(R.id.tvHargaFlash)
        val tvNamaFlash = view.findViewById<TextView>(R.id.tvNamaFlash)
        val tvRatingFlash = view.findViewById<TextView>(R.id.tvRatingFlash)
    }
}