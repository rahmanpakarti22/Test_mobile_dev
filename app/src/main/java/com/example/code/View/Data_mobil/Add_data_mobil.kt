package com.example.code.View.Data_mobil

import android.content.Intent
import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import androidx.recyclerview.widget.LinearLayoutManager
import com.example.code.View.Home.Home
import com.example.code.View.Adapter_mobil.MobilAdapter
import com.example.code.databinding.ActivityAddDataMobilBinding

class Add_data_mobil : AppCompatActivity()
{
    private lateinit var binding: ActivityAddDataMobilBinding

    private lateinit var db: MobilDatabaseHelper
    private lateinit var mobilAdapter: MobilAdapter

    override fun onCreate(savedInstanceState: Bundle?)
    {
        super.onCreate(savedInstanceState)
        binding = ActivityAddDataMobilBinding.inflate(layoutInflater)
        setContentView(binding.root)

        db = MobilDatabaseHelper(this)
        mobilAdapter = MobilAdapter(db.getAllDataMobil(), this)

        binding.rvDataMobil.layoutManager = LinearLayoutManager(this)
        binding.rvDataMobil.adapter = mobilAdapter

        binding.backLy.setOnClickListener {
            val intent = Intent(this, Home::class.java)
            startActivity(intent)
            finish()
        }

        binding.tambahDataMobilHomeLy.setOnClickListener {
            val intent = Intent(this, Mobil_data::class.java)
            startActivity(intent)
            finish()
        }
    }

    override fun onResume() {
        super.onResume()
        mobilAdapter.refreshData(db.getAllDataMobil())
    }
}