package com.example.code.View

import android.content.Context
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.TextView
import androidx.recyclerview.widget.RecyclerView
import com.example.code.R
import com.example.code.View.Data_motor.MotorDatabaseHelper

class Transaksi_motor_Adapter(private var transaksi_motor: List<Transaksi_motor_data>, context: Context) : RecyclerView.Adapter<Transaksi_motor_Adapter.TransaksiMotorViewHolder>() {

    private val db: MotorDatabaseHelper = MotorDatabaseHelper(context)
    class TransaksiMotorViewHolder(itemmView: View): RecyclerView.ViewHolder(itemmView)
    {
        val pembeliMotorTextView: TextView = itemView.findViewById(R.id.pembeli_motor_tv)
        val kontakMotorTextView: TextView = itemView.findViewById(R.id.kontak_motor_tv)
        val alamatMotorTextView: TextView = itemView.findViewById(R.id.alamat_motor_tv)
    }

    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): TransaksiMotorViewHolder {
        val view = LayoutInflater.from(parent.context).inflate(R.layout.item_transaksi_motor, parent, false)
        return TransaksiMotorViewHolder(view)
    }

    override fun getItemCount(): Int = transaksi_motor.size

    override fun onBindViewHolder(holder: TransaksiMotorViewHolder, position: Int) {
        val trans_motor = transaksi_motor[position]
        holder.pembeliMotorTextView.text = trans_motor.pembelimotor
        holder.kontakMotorTextView.text  = trans_motor.kontakmotor
        holder.alamatMotorTextView.text  = trans_motor.alamatmotor
    }
}