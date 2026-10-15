package com.kotlin.basic.trpl.recycler

import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.ImageView
import android.widget.TextView
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
    }

    override fun getItemCount(): Int = listFlash.size

    class ViewFlash(view: View) : RecyclerView.ViewHolder(view) {
        val ivFlash = view.findViewById<ImageView>(R.id.ivFlash)
        val tvHargaFlash = view.findViewById<TextView>(R.id.tvHargaFlash)
    }
}