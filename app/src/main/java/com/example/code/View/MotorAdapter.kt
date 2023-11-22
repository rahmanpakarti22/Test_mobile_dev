package com.example.code.View

import android.content.Context
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.TextView
import androidx.recyclerview.widget.RecyclerView
import com.example.code.R
import com.example.code.View.Adapter_mobil.MobilAdapter

class MotorAdapter(private var motor: List<Motor>, context: Context) : RecyclerView.Adapter<MotorAdapter.MotorViewHolder>(){

    class MotorViewHolder(itemView: View) : RecyclerView.ViewHolder(itemView)
    {
        val stokTextView: TextView  = itemView.findViewById(R.id.jumah_stok_motor_tv)
        val susTextView: TextView   = itemView.findViewById(R.id.suspensi_motor_tv)
        val tahunTextView: TextView = itemView.findViewById(R.id.tahun_motor_tv)
        val hargaTextView: TextView = itemView.findViewById(R.id.harga_motor_tv)
    }

    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): MotorViewHolder {
        val view = LayoutInflater.from(parent.context).inflate(R.layout.item_data_motor, parent, false)
        return MotorViewHolder(view)
    }

    override fun getItemCount(): Int = motor.size

    override fun onBindViewHolder(holder: MotorViewHolder, position: Int) {
        val motor = motor[position]
        holder.stokTextView.text  = motor.stok_motor
        holder.susTextView.text   = motor.suspensi_motor
        holder.tahunTextView.text = motor.tahun_motor
        holder.hargaTextView.text = motor.harga_motor
    }

    fun refreshData(newDataMotor: List<Motor>)
    {
        motor = newDataMotor
        notifyDataSetChanged()
    }
}