package com.example.code.View.Transaksi_mobil

import android.content.Intent
import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.util.Log
import android.widget.Toast
import com.example.code.R
import com.example.code.View.Data_mobil.Add_data_mobil
import com.example.code.View.Data_mobil.MobilDatabaseHelper
import com.example.code.databinding.ActivityTransaksiMobilBinding
import com.google.android.material.textfield.TextInputEditText

class Transaksi_mobil : AppCompatActivity()
{
    private lateinit var binding: ActivityTransaksiMobilBinding

    private lateinit var tahun_mobil_tr_tv: TextInputEditText
    private lateinit var warna_mobil_tr_tv: TextInputEditText
    private lateinit var mesin_mobil_tr_tv: TextInputEditText
    private lateinit var harga_mobil_tr_tv: TextInputEditText
    private lateinit var kapasitas_mobil_tr_tv: TextInputEditText
    private lateinit var tipe_mobil_tr_tv: TextInputEditText
    private lateinit var stok_mobil_tr_tv: TextInputEditText

    private lateinit var pembeli_mobilTl: TextInputEditText
    private lateinit var alamat_pembeli_Tl: TextInputEditText
    private lateinit var kontak_pembeli_Tl: TextInputEditText

    private lateinit var db: MobilDatabaseHelper
    private var mobilId: Int = -1

    override fun onCreate(savedInstanceState: Bundle?)
    {
        super.onCreate(savedInstanceState)
        binding = ActivityTransaksiMobilBinding.inflate(layoutInflater)
        setContentView(binding.root)

        tahun_mobil_tr_tv = findViewById(R.id.tahun_mobil_tr_tv)
        warna_mobil_tr_tv = findViewById(R.id.warna_mobil_tr_tv)
        mesin_mobil_tr_tv = findViewById(R.id.mesin_mobil_tr_tv)
        harga_mobil_tr_tv = findViewById(R.id.harga_mobil_tr_tv)
        kapasitas_mobil_tr_tv = findViewById(R.id.kapasitas_mobil_tr_tv)
        tipe_mobil_tr_tv = findViewById(R.id.tipe_mobil_tr_tv)
        stok_mobil_tr_tv = findViewById(R.id.stok_mobil_tr_tv)

        pembeli_mobilTl   = findViewById(R.id.pembeli_mobilTl)
        alamat_pembeli_Tl = findViewById(R.id.alamat_pembeli_Tl)
        kontak_pembeli_Tl = findViewById(R.id.kontak_pembeli_Tl)

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


        binding.simpanEdataTrMobilLy.setOnClickListener {
            Validasi()
        }

    }

    private fun showDataMobil()
    {
        val mobilData = db.getDataMobilByid(mobilId)
        binding.tahunMobilTrTv.setText(mobilData.tahun_mobil)
        binding.warnaMobilTrTv.setText(mobilData.warna_mobil)
        binding.hargaMobilTrTv.setText(mobilData.harga_mobil)
        binding.mesinMobilTrTv.setText(mobilData.mesin_mobil)
        binding.kapasitasMobilTrTv.setText(mobilData.kapasitas_mobil)
        binding.tipeMobilTrTv.setText(mobilData.tipe_mobil)
        binding.stokMobilTrTv.setText(mobilData.stok_mobil)
    }

    private fun Validasi()
    {
        var boolean = true
        val pembeli_mobil_tr = pembeli_mobilTl.text.toString()
        val kontak_mobil_tr  = kontak_pembeli_Tl.text.toString()
        val alamat_mobil_tr  = alamat_pembeli_Tl.text.toString()

        if (pembeli_mobil_tr.isEmpty())
        {
            pembeli_mobilTl.setError("Pembeli Harus Diisi!!")
            boolean = false
        }
        else if (kontak_mobil_tr.isEmpty())
        {
            kontak_pembeli_Tl.setError("Kontak Harus Diisi!!")
            boolean = false
        }
        else if (alamat_mobil_tr.isEmpty())
        {
            alamat_pembeli_Tl.setError("Alamat Harus Diisi!!")
            boolean = false
        }
        else
        {
            val transaksi = Transaksi(0, idMobilFk = mobilId,pembeli_mobil_tr, kontak_mobil_tr, alamat_mobil_tr)
            Log.d("Database Insert", "idMobilFk: ${mobilId.toString()}, pembeli_mobil_tr: $pembeli_mobil_tr, kontak_mobil_tr: $kontak_mobil_tr, alamat_mobil_tr: $alamat_mobil_tr")
            db.insertDataTransaksi(transaksi)

            val intent = Intent(this, Add_data_mobil::class.java)
            startActivity(intent)

            Toast.makeText(this, "Data Transaksi Tersimpan", Toast.LENGTH_SHORT).show()
        }
    }

    override fun onBackPressed()
    {
        super.onBackPressed()
        val intent = Intent(this, Add_data_mobil::class.java)
        startActivity(intent)
    }
}