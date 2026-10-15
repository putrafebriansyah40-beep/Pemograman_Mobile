package com.kotlin.basic.trpl.recycler

import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.ImageView
import android.widget.TextView
import androidx.recyclerview.widget.RecyclerView
import com.kotlin.basic.trpl.R

class BukuAdapter(
    private val listBuku: List<BukuModel>
) : RecyclerView.Adapter<BukuAdapter.BukuViewHolder>() {

    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): BukuViewHolder {
        val view = LayoutInflater.from(parent.context).inflate(R.layout.buku_layout, parent, false)
        return BukuViewHolder(view)
    }

    override fun onBindViewHolder(holder: BukuViewHolder, position: Int) {
        val buku = listBuku[position]
        holder.ivBuku.setImageResource(buku.gambarBuku)
        holder.tvJudulBuku.text = buku.judul
        holder.tvPenulis.text = buku.penulis
        holder.tvHargaBuku.text = buku.harga
        holder.tvStok.text = "Stok: ${buku.stok}"
    }

    override fun getItemCount(): Int = listBuku.size

    class BukuViewHolder(view: View) : RecyclerView.ViewHolder(view) {
        val ivBuku: ImageView = view.findViewById(R.id.ivBuku)
        val tvJudulBuku: TextView = view.findViewById(R.id.tvJudulBuku)
        val tvPenulis: TextView = view.findViewById(R.id.tvPenulis)
        val tvHargaBuku: TextView = view.findViewById(R.id.tvHargaBuku)
        val tvStok: TextView = view.findViewById(R.id.tvStok)
    }
}