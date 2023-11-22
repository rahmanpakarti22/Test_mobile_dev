package com.example.code.View.Adapter_mobil

import android.annotation.SuppressLint
import android.content.Context
import android.content.Intent
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.LinearLayout
import android.widget.TextView
import android.widget.Toast
import androidx.recyclerview.widget.RecyclerView
import com.example.code.R
import com.example.code.View.Data_mobil.Mobil
import com.example.code.View.Data_mobil.MobilDatabaseHelper
import com.example.code.View.Data_mobil.Update_data_mobil
import com.example.code.View.Transaksi_mobil

class MobilAdapter(private var mobil: List<Mobil>, context: Context) :
    RecyclerView.Adapter<MobilAdapter.MobilViewHolder>() {

    private val db: MobilDatabaseHelper = MobilDatabaseHelper(context)

    class MobilViewHolder(itemView: View) : RecyclerView.ViewHolder(itemView)
    {
        val stokTextView: TextView = itemView.findViewById(R.id.jumah_stok_tv)
        val jenisTextView: TextView = itemView.findViewById(R.id.jenis_tv)
        val tahunTextView: TextView = itemView.findViewById(R.id.tahun_tv)
        val hargaTextView: TextView = itemView.findViewById(R.id.harga_tv)
        val editDatamobil: LinearLayout = itemView.findViewById(R.id.edit_data_mobil)
        val hapusDatamobil: LinearLayout = itemView.findViewById(R.id.hapus_data_mobil)
        val jualDatamobil: LinearLayout = itemView.findViewById(R.id.jual_data_mobil)
    }

    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): MobilViewHolder {
        val view = LayoutInflater.from(parent.context).inflate(R.layout.item_data_mobil, parent, false)
        return MobilViewHolder(view)
    }

    override fun getItemCount(): Int = mobil.size

    override fun onBindViewHolder(holder: MobilViewHolder, position: Int) {
        val mobil = mobil[position]
        holder.stokTextView.text  = mobil.stok_mobil
        holder.jenisTextView.text = mobil.tipe_mobil
        holder.tahunTextView.text = mobil.tahun_mobil
        holder.hargaTextView.text = mobil.harga_mobil

        holder.editDatamobil.setOnClickListener {
            val intent = Intent(holder.itemView.context, Update_data_mobil::class.java).apply {
                putExtra("id_mobil", mobil.id)
            }
            holder.itemView.context.startActivity(intent)
        }

        holder.jualDatamobil.setOnClickListener {
            val intent = Intent(holder.itemView.context, Transaksi_mobil::class.java).apply {
                putExtra("id_mobil", mobil.id)
            }
            holder.itemView.context.startActivity(intent)
        }

        holder.hapusDatamobil.setOnClickListener {
            db.deleteDataMobil(mobil.id)
            refreshData(db.getAllDataMobil())
            Toast.makeText(holder.itemView.context, "Data Terhapus", Toast.LENGTH_SHORT).show()
        }
    }

    @SuppressLint("NotifyDataSetChanged")
    fun refreshData(newDataMobil: List<Mobil>)
    {
        mobil = newDataMobil
        notifyDataSetChanged()
    }
}