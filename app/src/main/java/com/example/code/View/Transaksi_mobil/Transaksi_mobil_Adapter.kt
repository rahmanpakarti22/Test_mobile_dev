package com.example.code.View.Transaksi_mobil

import android.content.Context
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.TextView
import androidx.recyclerview.widget.RecyclerView
import com.example.code.R
import com.example.code.View.Data_mobil.MobilDatabaseHelper

class Transaksi_mobil_Adapter(private var transaksi: List<Transaksi>, context: Context) : RecyclerView.Adapter<Transaksi_mobil_Adapter.TransaksiMobilViewHolder>() {

    private val db: MobilDatabaseHelper = MobilDatabaseHelper(context)
    class TransaksiMobilViewHolder(itemmView: View) : RecyclerView.ViewHolder(itemmView)
    {
        val pembeliMobilTextView: TextView = itemView.findViewById(R.id.pembeli_mobil_tv)
        val kontakMobilTextView: TextView  = itemView.findViewById(R.id.kontak_mobil_tv)
        val alamatMobilTextView: TextView  = itemView.findViewById(R.id.alamat_mobil_tv)
    }

    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): TransaksiMobilViewHolder {
        val view = LayoutInflater.from(parent.context).inflate(R.layout.item_transaksi_mobil, parent, false)
        return TransaksiMobilViewHolder(view)
    }

    override fun getItemCount(): Int = transaksi.size

    override fun onBindViewHolder(holder: TransaksiMobilViewHolder, position: Int) {
        val trans = transaksi[position]
        holder.pembeliMobilTextView.text = trans.pembelimobil
        holder.kontakMobilTextView.text  = trans.kontakmobil
        holder.alamatMobilTextView.text  = trans.alamatmobil
    }
}