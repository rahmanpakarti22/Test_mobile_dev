package com.example.code.View

import android.content.Intent
import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.util.Log
import android.widget.Toast
import com.example.code.R
import com.example.code.View.Data_mobil.Add_data_mobil
import com.example.code.databinding.ActivityMotorDataBinding
import com.google.android.material.textfield.TextInputEditText

class Motor_data : AppCompatActivity()
{
    private lateinit var binding: ActivityMotorDataBinding

    private lateinit var tahun_motor_add_data_Tl: TextInputEditText
    private lateinit var warna_motor_add_data_Tl: TextInputEditText
    private lateinit var harga_motor_add_data_Tl: TextInputEditText
    private lateinit var mesin_motor_add_data_Tl: TextInputEditText
    private lateinit var suspensi_motor_add_data_Tl: TextInputEditText
    private lateinit var transmisi_motor_add_data_Tl: TextInputEditText
    private lateinit var stok_motor_add_data_Tl: TextInputEditText

    private lateinit var db: MotorDatabaseHelper
    override fun onCreate(savedInstanceState: Bundle?)
    {
        super.onCreate(savedInstanceState)
        binding = ActivityMotorDataBinding.inflate(layoutInflater)
        setContentView(binding.root)

        tahun_motor_add_data_Tl = findViewById(R.id.tahun_motor_add_data_Tl)
        warna_motor_add_data_Tl = findViewById(R.id.warna_motor_add_data_Tl)
        harga_motor_add_data_Tl = findViewById(R.id.harga_motor_add_data_Tl)
        mesin_motor_add_data_Tl = findViewById(R.id.mesin_motor_add_data_Tl)
        suspensi_motor_add_data_Tl  = findViewById(R.id.suspensi_motor_add_data_Tl)
        transmisi_motor_add_data_Tl = findViewById(R.id.transmisi_motor_add_data_Tl)
        stok_motor_add_data_Tl      = findViewById(R.id.stok_motor_add_data_Tl)

        db = MotorDatabaseHelper(this)

        binding.backLy.setOnClickListener {
            val intent = Intent(this, Add_data_motor::class.java)
            startActivity(intent)
        }

        binding.simpanDataMotorLy.setOnClickListener {
            validasi()
        }

    }

    private fun validasi()
    {
        var boolean = true
        val tahun_motor = tahun_motor_add_data_Tl.text.toString()
        val warna_motor = warna_motor_add_data_Tl.text.toString()
        val harga_motor = harga_motor_add_data_Tl.text.toString()
        val mesin_motor = mesin_motor_add_data_Tl.text.toString()
        val suspensi_motor  = suspensi_motor_add_data_Tl.text.toString()
        val transmisi_motor = transmisi_motor_add_data_Tl.text.toString()
        val stok_motor = stok_motor_add_data_Tl.text.toString()

        if (tahun_motor.isEmpty())
        {
            tahun_motor_add_data_Tl.setError("Tahun Harus Diisi!!")
            boolean = false
        }
        else if (warna_motor.isEmpty())
        {
            warna_motor_add_data_Tl.setError("Warna Harus Diisi!!")
            boolean = false
        }
        else if (harga_motor.isEmpty())
        {
            harga_motor_add_data_Tl.setError("Harga Harus Diisi!!")
            boolean = false
        }
        else if (mesin_motor.isEmpty())
        {
            mesin_motor_add_data_Tl.setError("Mesin Harus Diisi!!")
            boolean = false
        }
        else if (suspensi_motor.isEmpty())
        {
            suspensi_motor_add_data_Tl.setError("Suspensi Harus Diisi!!")
            boolean = false
        }
        else if (transmisi_motor.isEmpty())
        {
            transmisi_motor_add_data_Tl.setError("Transmisi Harus Diisi!!")
            boolean = false
        }
        else if (stok_motor.isEmpty())
        {
            stok_motor_add_data_Tl.setError("Stok Harus Diisi!!")
            boolean = false
        }
        else
        {
            val motor = Motor(0, tahun_motor, warna_motor, harga_motor, mesin_motor, suspensi_motor, transmisi_motor, stok_motor)
            db.InsertDataMotor(motor)
            Log.d("DataMotor", "Data motor tersimpan di database:")
            Log.d("DataMotor", "Tahun Motor: $tahun_motor")
            Log.d("DataMotor", "Warna Motor: $warna_motor")
            Log.d("DataMotor", "Harga Motor: $harga_motor")
            Log.d("DataMotor", "Mesin Motor: $mesin_motor")
            Log.d("DataMotor", "Suspensi Motor: $suspensi_motor")
            Log.d("DataMotor", "Transmisi Motor: $transmisi_motor")
            Log.d("DataMotor", "Stok Motor: $stok_motor")
            val intent = Intent(this, Add_data_motor::class.java)
            startActivity(intent)

            Toast.makeText(this, "Data Tersimpan", Toast.LENGTH_SHORT).show()
        }

    }
}