package com.example.code.View.Data_mobil

import android.content.Intent
import android.os.Bundle
import android.util.Log
import android.widget.Button
import android.widget.Toast
import androidx.appcompat.app.AppCompatActivity
import com.example.code.R
import com.example.code.databinding.ActivityMobilDataBinding
import com.google.android.material.textfield.TextInputEditText

class Mobil_data : AppCompatActivity()
{
    private lateinit var binding: ActivityMobilDataBinding
    private lateinit var tahun_mobil_add_data_Tl: TextInputEditText
    private lateinit var warna_mobil_add_data_Tl: TextInputEditText
    private lateinit var harga_mobil_add_data_Tl: TextInputEditText
    private lateinit var mesin_mobil_add_data_Tl: TextInputEditText
    private lateinit var kapasitas_mobil_add_data_Tl: TextInputEditText
    private lateinit var tipe_mobil_add_data_Tl: TextInputEditText
    private lateinit var stok_mobil_add_data_Tl: TextInputEditText

    private lateinit var db: MobilDatabaseHelper

    override fun onCreate(savedInstanceState: Bundle?)
    {
        super.onCreate(savedInstanceState)
        binding = ActivityMobilDataBinding.inflate(layoutInflater)
        setContentView(binding.root)

        tahun_mobil_add_data_Tl = findViewById(R.id.tahun_mobil_add_data_Tl)
        warna_mobil_add_data_Tl = findViewById(R.id.warna_mobil_add_data_Tl)
        harga_mobil_add_data_Tl = findViewById(R.id.harga_mobil_add_data_Tl)
        mesin_mobil_add_data_Tl = findViewById(R.id.mesin_mobil_add_data_Tl)
        kapasitas_mobil_add_data_Tl = findViewById(R.id.kapasitas_mobil_add_data_Tl)
        tipe_mobil_add_data_Tl = findViewById(R.id.tipe_mobil_add_data_Tl)
        stok_mobil_add_data_Tl = findViewById(R.id.stok_mobil_add_data_Tl)

        db = MobilDatabaseHelper(this)


        binding.backLy.setOnClickListener {
            val intent = Intent(this, Add_data_mobil::class.java)
            startActivity(intent)
        }


        binding.simpanDataMobilLy.setOnClickListener {
            validasi()
        }

    }

    private fun validasi()
    {
        var boolean = true
        val tahun_mobil = tahun_mobil_add_data_Tl.text.toString()
        val warna_mobil = warna_mobil_add_data_Tl.text.toString()
        val harga_mobil = harga_mobil_add_data_Tl.text.toString()
        val mesin_mobil = mesin_mobil_add_data_Tl.text.toString()
        val kapasitas_mobil = kapasitas_mobil_add_data_Tl.text.toString()
        val tipe_mobil = tipe_mobil_add_data_Tl.text.toString()
        val stok_mobil = stok_mobil_add_data_Tl.text.toString()

        if (tahun_mobil.isEmpty())
        {
            tahun_mobil_add_data_Tl.setError("Tahun Harus Diisi!!")
            boolean = false
        }
        else if (warna_mobil.isEmpty())
        {
            warna_mobil_add_data_Tl.setError("Warna Harus Diisi!!")
            boolean = false
        }
        else if (harga_mobil.isEmpty())
        {
            harga_mobil_add_data_Tl.setError("Harga Harus Diisi!!")
            boolean = false
        }
        else if (mesin_mobil.isEmpty())
        {
            mesin_mobil_add_data_Tl.setError("Mesin Harus Diisi!!")
            boolean = false
        }
        else if (kapasitas_mobil.isEmpty())
        {
            kapasitas_mobil_add_data_Tl.setError("Kapasitas Harus Diisi!!")
            boolean = false
        }
        else if (tipe_mobil.isEmpty())
        {
            tipe_mobil_add_data_Tl.setError("Tipe Harus Diisi!!")
            boolean = false
        }
        else if (stok_mobil.isEmpty())
        {
            stok_mobil_add_data_Tl.setError("Stok Harus Diisi!!")
            boolean = false
        }
        else
        {
            val mobil = Mobil(0, tahun_mobil, warna_mobil, harga_mobil, mesin_mobil, kapasitas_mobil, tipe_mobil,stok_mobil)
            db.InsertDataMobil(mobil)
            val intent = Intent(this, Add_data_mobil::class.java)
            startActivity(intent)

            Toast.makeText(this, "Data Tersimpan", Toast.LENGTH_SHORT).show()
        }
    }
}