package com.example.code.View.Adapter_motor

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
import com.example.code.View.Data_motor.Motor
import com.example.code.View.Data_motor.MotorDatabaseHelper
import com.example.code.View.Data_motor.Update_data_motor
import com.example.code.View.Transaksi_motor.Transaksi_motor
import com.example.code.View.Transaksi_motor.Transaksi_motor_list

class MotorAdapter(private var motor: List<Motor>, context: Context) : RecyclerView.Adapter<MotorAdapter.MotorViewHolder>(){

    private val db: MotorDatabaseHelper = MotorDatabaseHelper(context)
    class MotorViewHolder(itemView: View) : RecyclerView.ViewHolder(itemView)
    {
        val stokTextView: TextView  = itemView.findViewById(R.id.jumah_stok_motor_tv)
        val susTextView: TextView   = itemView.findViewById(R.id.suspensi_motor_tv)
        val tahunTextView: TextView = itemView.findViewById(R.id.tahun_motor_tv)
        val hargaTextView: TextView = itemView.findViewById(R.id.harga_motor_tv)
        val editDataMotor: LinearLayout  = itemView.findViewById(R.id.edit_data_motor)
        val hapusDataMotor: LinearLayout = itemView.findViewById(R.id.hapus_data_motor)
        val jualDataMotor: LinearLayout  = itemView.findViewById(R.id.jual_data_motor)
        val listDataTransaksimotor: LinearLayout = itemView.findViewById(R.id.list_tr_motor_ly)
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

        holder.editDataMotor.setOnClickListener {
            val intent = Intent(holder.itemView.context, Update_data_motor::class.java).apply {
                putExtra("id_motor", motor.id)
            }
            holder.itemView.context.startActivity(intent)
        }

        holder.jualDataMotor.setOnClickListener {
            val intent = Intent(holder.itemView.context, Transaksi_motor::class.java).apply {
                putExtra("id_motor", motor.id)
            }
            holder.itemView.context.startActivity(intent)
        }

        holder.listDataTransaksimotor.setOnClickListener {
            val intent = Intent(holder.itemView.context, Transaksi_motor_list::class.java).apply {
                putExtra("id_motor", motor.id)
            }
            holder.itemView.context.startActivity(intent)
        }

        holder.hapusDataMotor.setOnClickListener {
            db.deleteDataMotor(motor.id)
            refreshData(db.getAllDataMotor())
            Toast.makeText(holder.itemView.context, "Data Terhapus", Toast.LENGTH_SHORT).show()
        }
    }

    fun refreshData(newDataMotor: List<Motor>)
    {
        motor = newDataMotor
        notifyDataSetChanged()
    }
}