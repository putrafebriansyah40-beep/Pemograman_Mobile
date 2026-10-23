package com.kotlin.basic.trpl.recycler

import android.content.Intent
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.ImageView
import android.widget.TextView
import androidx.recyclerview.widget.RecyclerView
import com.kotlin.basic.trpl.R

class GridAdapter(
    val listGrid: List<GridModel>
) : RecyclerView.Adapter<GridAdapter.ViewGrid>() {

    override fun onCreateViewHolder(
        parent: ViewGroup,
        viewType: Int
    ): ViewGrid {
        return ViewGrid(
            LayoutInflater.from(parent.context)
                .inflate(R.layout.grid_layout, parent, false)
        )
    }

    override fun onBindViewHolder(holder: ViewGrid, position: Int) {
        val itemGrid = listGrid[position]

        holder.ivGrid.setImageResource(itemGrid.gambar)
        holder.tvNamaProduk.text = itemGrid.nama
        holder.tvHarga.text = itemGrid.harga
        holder.tvRating.text = itemGrid.rating.toString()
        holder.tvTerjual.text = "${itemGrid.terjual} Terjual"

        // Listener klik untuk menuju ke DetailGridActivity
        holder.itemView.setOnClickListener {
            val intent = Intent(holder.itemView.context, DetailGridActivity::class.java)
            intent.putExtra("gambar", itemGrid.gambar)
            intent.putExtra("nama", itemGrid.nama)
            intent.putExtra("harga", itemGrid.harga)
            intent.putExtra("rating", itemGrid.rating)
            intent.putExtra("terjual", itemGrid.terjual)
            intent.putExtra("deskripsi", itemGrid.deskripsi)
            holder.itemView.context.startActivity(intent)
        }
    }

    override fun getItemCount(): Int = listGrid.size

    class ViewGrid(view: View) : RecyclerView.ViewHolder(view) {
        val ivGrid = view.findViewById<ImageView>(R.id.ivGrid)
        val tvNamaProduk = view.findViewById<TextView>(R.id.tvNamaProduk)
        val tvHarga = view.findViewById<TextView>(R.id.tvHarga)
        val tvRating = view.findViewById<TextView>(R.id.tvRating)
        val tvTerjual = view.findViewById<TextView>(R.id.tvTerjual)
    }
}