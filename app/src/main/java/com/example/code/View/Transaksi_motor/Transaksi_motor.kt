package com.example.code.View.Transaksi_motor

import android.content.Intent
import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.util.Log
import android.widget.Toast
import com.example.code.R
import com.example.code.View.Data_motor.Add_data_motor
import com.example.code.View.Data_motor.MotorDatabaseHelper
import com.example.code.databinding.ActivityTransaksiMotorBinding
import com.google.android.material.textfield.TextInputEditText

class Transaksi_motor : AppCompatActivity()
{
    private lateinit var binding: ActivityTransaksiMotorBinding

    private lateinit var tahun_motor_tr_tv: TextInputEditText
    private lateinit var warna_motor_tr_tv: TextInputEditText
    private lateinit var mesin_motor_tr_tv: TextInputEditText
    private lateinit var harga_motor_tr_tv: TextInputEditText
    private lateinit var sus_motor_tr_tv: TextInputEditText
    private lateinit var trans_motor_tr_tv: TextInputEditText
    private lateinit var stok_motor_tr_tv: TextInputEditText

    private lateinit var pembeli_motor_Tl: TextInputEditText
    private lateinit var alamat_pembeli_motor_Tl: TextInputEditText
    private lateinit var kontak_pembeli_motor_Tl: TextInputEditText

    private lateinit var db: MotorDatabaseHelper
    private var motorId: Int = -1
    override fun onCreate(savedInstanceState: Bundle?)
    {
        super.onCreate(savedInstanceState)
        binding = ActivityTransaksiMotorBinding.inflate(layoutInflater)
        setContentView(binding.root)

        tahun_motor_tr_tv = findViewById(R.id.tahun_motor_tr_tv)
        warna_motor_tr_tv = findViewById(R.id.warna_motor_tr_tv)
        mesin_motor_tr_tv = findViewById(R.id.mesin_motor_tr_tv)
        harga_motor_tr_tv = findViewById(R.id.harga_motor_tr_tv)
        sus_motor_tr_tv   = findViewById(R.id.sus_motor_tr_tv)
        trans_motor_tr_tv = findViewById(R.id.trans_motor_tr_tv)
        stok_motor_tr_tv  = findViewById(R.id.stok_motor_tr_tv)

        pembeli_motor_Tl        = findViewById(R.id.pembeli_motor_Tl)
        alamat_pembeli_motor_Tl = findViewById(R.id.alamat_pembeli_motor_Tl)
        kontak_pembeli_motor_Tl = findViewById(R.id.kontak_pembeli_motor_Tl)

        binding.backLy.setOnClickListener {
            val intent = Intent(this, Add_data_motor::class.java)
            startActivity(intent)
        }

        db = MotorDatabaseHelper(this)
        motorId = intent.getIntExtra("id_motor", -1)
        if (motorId == -1)
        {
            finish()
            return
        }

        showDataMotor()

        binding.simpanEdataTrMotorLy.setOnClickListener {
            Validasi()
        }

    }

    private fun showDataMotor()
    {
        val motorData = db.getDataMotorById(motorId)
        binding.tahunMotorTrTv.setText(motorData.tahun_motor)
        binding.warnaMotorTrTv.setText(motorData.warna_motor)
        binding.hargaMotorTrTv.setText(motorData.harga_motor)
        binding.mesinMotorTrTv.setText(motorData.mesin_motor)
        binding.susMotorTrTv.setText(motorData.suspensi_motor)
        binding.transMotorTrTv.setText(motorData.transmisi_motor)
        binding.stokMotorTrTv.setText(motorData.stok_motor)
    }

    private fun Validasi()
    {
        var boolean = true
        val pembeli_motor_tr = pembeli_motor_Tl.text.toString()
        val kontak_motor_tr  = alamat_pembeli_motor_Tl.text.toString()
        val alamat_motor_tr  = kontak_pembeli_motor_Tl.text.toString()

        if (pembeli_motor_tr.isEmpty())
        {
            pembeli_motor_Tl.setError("Pembeli Harus Diisi!!")
            boolean = false
        }
        else if (kontak_motor_tr.isEmpty())
        {
            alamat_pembeli_motor_Tl.setError("Kontak Harus Diisi!!")
            boolean = false
        }
        else if (alamat_motor_tr.isEmpty())
        {
            kontak_pembeli_motor_Tl.setError("Alamat Harus Diisi!!")
            boolean = false
        }
        else
        {
            val transMotor = Transaksi_motor_data(0, idMotorFk = motorId, pembeli_motor_tr, alamat_motor_tr, kontak_motor_tr)
            Log.d("Database Insert", "idMotorFk: ${motorId.toString()}, pembeli_motor_tr: $pembeli_motor_tr, alamat_motor_tr: $alamat_motor_tr, alamat_mobil_tr: $kontak_motor_tr")
            db.InsertDataTransaksiMotor(transMotor)
            val intent = Intent(this, Add_data_motor::class.java)
            startActivity(intent)

            Toast.makeText(this, "Data Transaksi Tersimpan", Toast.LENGTH_SHORT).show()
        }
    }

    override fun onBackPressed()
    {
        super.onBackPressed()
        val intent = Intent(this, Add_data_motor::class.java)
        startActivity(intent)
    }
}