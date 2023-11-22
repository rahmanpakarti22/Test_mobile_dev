package com.example.code.View.Data_motor

import android.content.Intent
import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.widget.Toast
import com.example.code.R
import com.example.code.View.Data_mobil.Add_data_mobil
import com.example.code.databinding.ActivityUpdateDataMotorBinding
import com.google.android.material.textfield.TextInputEditText

class Update_data_motor : AppCompatActivity()
{
    private lateinit var binding: ActivityUpdateDataMotorBinding

    private lateinit var tahun_motor_update_data_Tl: TextInputEditText
    private lateinit var warna_motor_update_data_Tl: TextInputEditText
    private lateinit var harga_mobil_update_data_Tl: TextInputEditText
    private lateinit var mesin_motor_update_data_Tl: TextInputEditText
    private lateinit var sus_motor_update_data_Tl: TextInputEditText
    private lateinit var trans_motor_update_data_Tl: TextInputEditText
    private lateinit var stok_motor_update_data_Tl: TextInputEditText

    private lateinit var db: MotorDatabaseHelper
    private var motorId: Int = -1
    override fun onCreate(savedInstanceState: Bundle?)
    {
        super.onCreate(savedInstanceState)
        binding = ActivityUpdateDataMotorBinding.inflate(layoutInflater)
        setContentView(binding.root)

        tahun_motor_update_data_Tl = findViewById(R.id.tahun_motor_update_data_Tl)
        warna_motor_update_data_Tl = findViewById(R.id.warna_motor_update_data_Tl)
        harga_mobil_update_data_Tl = findViewById(R.id.harga_mobil_update_data_Tl)
        mesin_motor_update_data_Tl = findViewById(R.id.mesin_motor_update_data_Tl)
        sus_motor_update_data_Tl   = findViewById(R.id.sus_motor_update_data_Tl)
        trans_motor_update_data_Tl = findViewById(R.id.trans_motor_update_data_Tl)
        stok_motor_update_data_Tl  = findViewById(R.id.stok_motor_update_data_Tl)

        binding.backLy.setOnClickListener {
            val intent = Intent(this, Add_data_motor::class.java)
            startActivity(intent)
            finish()
        }

        db = MotorDatabaseHelper(this)
        motorId = intent.getIntExtra("id_motor", -1)
        if (motorId == -1)
        {
            finish()
            return
        }

        showDataMotor()

        binding.simpanUpdatedataMotorLy.setOnClickListener {
            data()
        }
    }

    private fun showDataMotor()
    {
        val motorData = db.getDataMotorById(motorId)
        binding.tahunMotorUpdateDataTl.setText(motorData.tahun_motor)
        binding.warnaMotorUpdateDataTl.setText(motorData.warna_motor)
        binding.hargaMobilUpdateDataTl.setText(motorData.harga_motor)
        binding.mesinMotorUpdateDataTl.setText(motorData.mesin_motor)
        binding.susMotorUpdateDataTl.setText(motorData.suspensi_motor)
        binding.transMotorUpdateDataTl.setText(motorData.transmisi_motor)
        binding.stokMotorUpdateDataTl.setText(motorData.stok_motor)
    }

    private fun data()
    {
        val tahun_motor_edit = tahun_motor_update_data_Tl.text.toString()
        val warna_motor_edit = warna_motor_update_data_Tl.text.toString()
        val harga_motor_edit = harga_mobil_update_data_Tl.text.toString()
        val mesin_motor_edit = mesin_motor_update_data_Tl.text.toString()
        val suspensi_motor_edit  = sus_motor_update_data_Tl.text.toString()
        val transmisi_motor_edit = trans_motor_update_data_Tl.text.toString()
        val stok_motor_edit = stok_motor_update_data_Tl.text.toString()

        val updateDataMotor = Motor(motorId, tahun_motor_edit, warna_motor_edit, harga_motor_edit, mesin_motor_edit, suspensi_motor_edit, transmisi_motor_edit, stok_motor_edit)
        db.updateDataMotor(updateDataMotor)
        val intent = Intent(this, Add_data_motor::class.java)
        startActivity(intent)
        finish()
        Toast.makeText(this, "Data Terupdate", Toast.LENGTH_SHORT).show()
    }
}