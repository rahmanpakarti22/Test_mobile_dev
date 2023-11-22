package com.example.code.View.Home

import android.content.Intent
import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import com.example.code.View.Data_mobil.Add_data_mobil
import com.example.code.databinding.ActivityHomeBinding

class Home : AppCompatActivity()
{
    private lateinit var binding: ActivityHomeBinding

    override fun onCreate(savedInstanceState: Bundle?)
    {
        super.onCreate(savedInstanceState)
        binding = ActivityHomeBinding.inflate(layoutInflater)
        setContentView(binding.root)

        binding.addDataMobilHomeLy.setOnClickListener {
            val intent = Intent(this, Add_data_mobil::class.java)
            startActivity(intent)
        }
    }
}