package com.example.code.View

import android.content.Intent
import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import androidx.recyclerview.widget.LinearLayoutManager
import com.example.code.R
import com.example.code.View.Data_mobil.Add_data_mobil
import com.example.code.View.Data_mobil.MobilDatabaseHelper
import com.example.code.View.Home.Home
import com.example.code.databinding.ActivityTransaksiMobilListBinding
import com.google.android.material.textfield.TextInputEditText

class Transaksi_mobil_list : AppCompatActivity()
{
    private lateinit var binding: ActivityTransaksiMobilListBinding

    private lateinit var tahun_mobil_tr_detaiL_tv: TextInputEditText
    private lateinit var warna_mobil_tr_detaiL_tv: TextInputEditText
    private lateinit var mesin_mobil_tr_detaiL_tv: TextInputEditText
    private lateinit var harga_mobil_tr_detaiL_tv: TextInputEditText
    private lateinit var kapasitas_mobil_tr_detaiL_tv: TextInputEditText
    private lateinit var tipe_mobil_tr_detaiL_tv: TextInputEditText
    private lateinit var stok_mobil_tr_detaiL_tv: TextInputEditText

    private lateinit var transaksi_mobil_Adapter: Transaksi_mobil_Adapter

    private lateinit var db: MobilDatabaseHelper
    private var mobilId: Int = -1
    override fun onCreate(savedInstanceState: Bundle?)
    {
        super.onCreate(savedInstanceState)
        binding = ActivityTransaksiMobilListBinding.inflate(layoutInflater)
        setContentView(binding.root)

        tahun_mobil_tr_detaiL_tv = findViewById(R.id.tahun_mobil_tr_detaiL_tv)
        warna_mobil_tr_detaiL_tv = findViewById(R.id.warna_mobil_tr_detaiL_tv)
        mesin_mobil_tr_detaiL_tv = findViewById(R.id.mesin_mobil_tr_detaiL_tv)
        harga_mobil_tr_detaiL_tv = findViewById(R.id.harga_mobil_tr_detaiL_tv)
        kapasitas_mobil_tr_detaiL_tv = findViewById(R.id.kapasitas_mobil_tr_detaiL_tv)
        tipe_mobil_tr_detaiL_tv = findViewById(R.id.tipe_mobil_tr_detaiL_tv)
        stok_mobil_tr_detaiL_tv = findViewById(R.id.stok_mobil_tr_detaiL_tv)

        binding.backLy.setOnClickListener {
            val intent = Intent(this, Add_data_mobil::class.java)
            startActivity(intent)
        }

        db = MobilDatabaseHelper(this)

        mobilId = intent.getIntExtra("id_mobil", -1)
        transaksi_mobil_Adapter = Transaksi_mobil_Adapter(db.getTransaksiByMobilId(mobilId), this)
        binding.rvListTrMobil.layoutManager = LinearLayoutManager(this)
        binding.rvListTrMobil.adapter = transaksi_mobil_Adapter

        showDataMobil()
    }

    private fun showDataMobil()
    {
        val mobilData = db.getDataMobilByid(mobilId)
        binding.tahunMobilTrDetaiLTv.setText(mobilData.tahun_mobil)
        binding.warnaMobilTrDetaiLTv.setText(mobilData.warna_mobil)
        binding.hargaMobilTrDetaiLTv.setText(mobilData.harga_mobil)
        binding.mesinMobilTrDetaiLTv.setText(mobilData.mesin_mobil)
        binding.kapasitasMobilTrDetaiLTv.setText(mobilData.kapasitas_mobil)
        binding.tipeMobilTrDetaiLTv.setText(mobilData.tipe_mobil)
        binding.stokMobilTrDetaiLTv.setText(mobilData.stok_mobil)
    }

    override fun onBackPressed()
    {
        super.onBackPressed()
        val intent = Intent(this, Home::class.java)
        startActivity(intent)
        finish()
    }
}