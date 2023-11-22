package com.example.code.View.Data_mobil

import android.content.Intent
import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.widget.Toast
import com.example.code.R
import com.example.code.databinding.ActivityUpdateDataMobilBinding
import com.google.android.material.textfield.TextInputEditText

class Update_data_mobil : AppCompatActivity()
{
    private lateinit var binding: ActivityUpdateDataMobilBinding

    private lateinit var tahun_mobil_update_data_Tl: TextInputEditText
    private lateinit var warna_mobil_update_data_Tl: TextInputEditText
    private lateinit var harga_mobil_update_data_Tl: TextInputEditText
    private lateinit var mesin_mobil_update_data_Tl: TextInputEditText
    private lateinit var kapasitas_mobil_update_data_Tl: TextInputEditText
    private lateinit var tipe_mobil_update_data_Tl: TextInputEditText
    private lateinit var stok_mobil_update_data_Tl: TextInputEditText

    private lateinit var db: MobilDatabaseHelper
    private var mobilId: Int = -1

    override fun onCreate(savedInstanceState: Bundle?)
    {
        super.onCreate(savedInstanceState)
        binding = ActivityUpdateDataMobilBinding.inflate(layoutInflater)
        setContentView(binding.root)

        tahun_mobil_update_data_Tl = findViewById(R.id.tahun_mobil_update_data_Tl)
        warna_mobil_update_data_Tl = findViewById(R.id.warna_mobil_update_data_Tl)
        harga_mobil_update_data_Tl = findViewById(R.id.harga_mobil_update_data_Tl)
        mesin_mobil_update_data_Tl = findViewById(R.id.mesin_mobil_update_data_Tl)
        kapasitas_mobil_update_data_Tl = findViewById(R.id.kapasitas_mobil_update_data_Tl)
        tipe_mobil_update_data_Tl = findViewById(R.id.tipe_mobil_update_data_Tl)
        stok_mobil_update_data_Tl = findViewById(R.id.stok_mobil_update_data_Tl)

        binding.backLy.setOnClickListener {
            val intent = Intent(this, Add_data_mobil::class.java)
            startActivity(intent)
        }

        db = MobilDatabaseHelper(this)
        mobilId = intent.getIntExtra("id_mobil", -1)
        if (mobilId == -1)
        {
            finish()
            return
        }

        showDataMobil()

        binding.simpanUpdatedataMobilLy.setOnClickListener {
            data()
        }
    }

    private fun showDataMobil()
    {
        val mobilData = db.getDataMobilByid(mobilId)
        binding.tahunMobilUpdateDataTl.setText(mobilData.tahun_mobil)
        binding.warnaMobilUpdateDataTl.setText(mobilData.warna_mobil)
        binding.hargaMobilUpdateDataTl.setText(mobilData.harga_mobil)
        binding.mesinMobilUpdateDataTl.setText(mobilData.mesin_mobil)
        binding.kapasitasMobilUpdateDataTl.setText(mobilData.kapasitas_mobil)
        binding.tipeMobilUpdateDataTl.setText(mobilData.tipe_mobil)
        binding.stokMobilUpdateDataTl.setText(mobilData.stok_mobil)
    }

    private fun data()
    {
        val tahun_mobil_edit = tahun_mobil_update_data_Tl.text.toString()
        val warna_mobil_edit = warna_mobil_update_data_Tl.text.toString()
        val harga_mobil_edit = harga_mobil_update_data_Tl.text.toString()
        val mesin_mobil_edit = mesin_mobil_update_data_Tl.text.toString()
        val kapasitas_mobil_edit = kapasitas_mobil_update_data_Tl.text.toString()
        val tipe_mobil_edit = tipe_mobil_update_data_Tl.text.toString()
        val stok_mobil_edit = stok_mobil_update_data_Tl.text.toString()

        val UpdateDataMobil = Mobil(mobilId, tahun_mobil_edit, warna_mobil_edit, harga_mobil_edit, mesin_mobil_edit, kapasitas_mobil_edit, tipe_mobil_edit, stok_mobil_edit)
        db.UpdateMobil(UpdateDataMobil)
        val intent = Intent(this, Add_data_mobil::class.java)
        startActivity(intent)
        finish()
        Toast.makeText(this, "Data Terupdate", Toast.LENGTH_SHORT).show()
    }
}