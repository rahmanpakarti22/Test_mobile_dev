package com.example.code.View.Transaksi_motor

import android.content.Intent
import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import androidx.recyclerview.widget.LinearLayoutManager
import com.example.code.R
import com.example.code.View.Data_motor.Add_data_motor
import com.example.code.View.Data_motor.MotorDatabaseHelper
import com.example.code.databinding.ActivityTransaksiMotorListBinding
import com.google.android.material.textfield.TextInputEditText

class Transaksi_motor_list : AppCompatActivity()
{
    private lateinit var binding: ActivityTransaksiMotorListBinding

    private lateinit var tahun_motor_tr_detail_tv: TextInputEditText
    private lateinit var warna_motor_tr_detail_tv: TextInputEditText
    private lateinit var mesin_motor_tr_detail_tv: TextInputEditText
    private lateinit var harga_motor_tr_detail_tv: TextInputEditText
    private lateinit var sus_motor_tr_detail_tv: TextInputEditText
    private lateinit var trans_motor_tr_detail_tv: TextInputEditText
    private lateinit var stok_motor_tr_detail_tv: TextInputEditText

    private lateinit var transaksi_motor_adapter: Transaksi_motor_Adapter

    private lateinit var db: MotorDatabaseHelper
    private var motorId: Int = -1

    override fun onCreate(savedInstanceState: Bundle?)
    {
        super.onCreate(savedInstanceState)
        binding = ActivityTransaksiMotorListBinding.inflate(layoutInflater)
        setContentView(binding.root)

        tahun_motor_tr_detail_tv = findViewById(R.id.tahun_motor_tr_detail_tv)
        warna_motor_tr_detail_tv = findViewById(R.id.warna_motor_tr_detail_tv)
        mesin_motor_tr_detail_tv = findViewById(R.id.mesin_motor_tr_detail_tv)
        harga_motor_tr_detail_tv = findViewById(R.id.harga_motor_tr_detail_tv)
        sus_motor_tr_detail_tv   = findViewById(R.id.sus_motor_tr_detail_tv)
        trans_motor_tr_detail_tv = findViewById(R.id.trans_motor_tr_detail_tv)
        stok_motor_tr_detail_tv  = findViewById(R.id.stok_motor_tr_detail_tv)

        binding.backLy.setOnClickListener {
            val intent = Intent(this, Add_data_motor::class.java)
            startActivity(intent)
        }

        db = MotorDatabaseHelper(this)
        motorId = intent.getIntExtra("id_motor", -1)
        transaksi_motor_adapter = Transaksi_motor_Adapter(db.getTransaksiMotorById(motorId), this)
        binding.rvListTrMotor.layoutManager = LinearLayoutManager(this)
        binding.rvListTrMotor.adapter = transaksi_motor_adapter

        showDataMotor()
    }

    private fun showDataMotor()
    {
        val motorData = db.getDataMotorById(motorId)
        binding.tahunMotorTrDetailTv.setText(motorData.tahun_motor)
        binding.warnaMotorTrDetailTv.setText(motorData.warna_motor)
        binding.hargaMotorTrDetailTv.setText(motorData.harga_motor)
        binding.mesinMotorTrDetailTv.setText(motorData.mesin_motor)
        binding.susMotorTrDetailTv.setText(motorData.suspensi_motor)
        binding.transMotorTrDetailTv.setText(motorData.transmisi_motor)
        binding.stokMotorTrDetailTv.setText(motorData.stok_motor)
    }

    override fun onBackPressed()
    {
        super.onBackPressed()
        val intent = Intent(this, Add_data_motor::class.java)
        startActivity(intent)
    }
}