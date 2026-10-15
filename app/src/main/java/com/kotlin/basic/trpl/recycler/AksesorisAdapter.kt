package com.kotlin.basic.trpl.recycler

import android.content.Intent
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.ImageView
import android.widget.RatingBar
import android.widget.TextView
import androidx.recyclerview.widget.RecyclerView
import com.kotlin.basic.trpl.R

class AksesorisAdapter(
    val listAksesoris: ArrayList<AksesorisModel>
): RecyclerView.Adapter<AksesorisAdapter.ViewAksesoris>() {


    override fun onCreateViewHolder(
        parent: ViewGroup,
        viewType: Int
    ): AksesorisAdapter.ViewAksesoris {
        return ViewAksesoris(LayoutInflater.from(parent.context).inflate(R.layout.aksesoris_layout,parent,false))
    }

    override fun onBindViewHolder(holder: AksesorisAdapter.ViewAksesoris, position: Int) {
        val itemAksesoris = listAksesoris[position]
        holder.ivAksesoris.setImageResource(itemAksesoris.gambarAksesoris)
        holder.tvNamaAksesoris.text = itemAksesoris.namaAksesoris
        holder.tvHarga.text = itemAksesoris.harga
        holder.rbAksesoris.rating = itemAksesoris.rating
        holder.tvAlamat.text = itemAksesoris.alamat
        //klik detail salah satu item
        holder.itemView.setOnClickListener {
            holder.itemView.context.startActivity(
                Intent(holder.itemView.context, DetailAksesorisActivity::class.java)
                    .putExtra("gambar", itemAksesoris.gambarAksesoris)
                .putExtra("nama", itemAksesoris.namaAksesoris)
                .putExtra("harga", itemAksesoris.harga)
                .putExtra("rating", itemAksesoris.rating)
                .putExtra("alamat", itemAksesoris.alamat)
                .putExtra("deskripsi", itemAksesoris.deskripsi)
            )
        }
    }

    override fun getItemCount(): Int = listAksesoris.size

    class ViewAksesoris(view: View):
        RecyclerView.ViewHolder(view) {
            val ivAksesoris = view.findViewById<ImageView>(R.id.ivAksesoris)
            val tvNamaAksesoris = view.findViewById<TextView>(R.id.tvNamaAksesoris)
            val tvHarga = view.findViewById<TextView>(R.id.tvHarga)
            val rbAksesoris = view.findViewById<RatingBar>(R.id.rbAksesoris)
            val tvAlamat = view.findViewById<TextView>(R.id.tvAlamat)
    }
}