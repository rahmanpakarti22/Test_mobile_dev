package com.example.code.View

import android.content.Intent
import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import com.example.code.R
import com.example.code.databinding.ActivityAddDataMotorBinding

class Add_data_motor : AppCompatActivity()
{
    public lateinit var binding: ActivityAddDataMotorBinding
    override fun onCreate(savedInstanceState: Bundle?)
    {
        super.onCreate(savedInstanceState)
        binding = ActivityAddDataMotorBinding.inflate(layoutInflater)
        setContentView(binding.root)

        binding.tambahDataMotorHomeLy.setOnClickListener {
            val intent = Intent(this, Motor_data::class.java)
            startActivity(intent)
        }
    }
}