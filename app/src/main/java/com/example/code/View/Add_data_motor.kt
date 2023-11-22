package com.example.code.View

import android.content.Intent
import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import androidx.recyclerview.widget.LinearLayoutManager
import com.example.code.R
import com.example.code.databinding.ActivityAddDataMotorBinding

class Add_data_motor : AppCompatActivity()
{
    private lateinit var binding: ActivityAddDataMotorBinding

    private lateinit var db: MotorDatabaseHelper
    private lateinit var motorAdapter: MotorAdapter

    override fun onCreate(savedInstanceState: Bundle?)
    {
        super.onCreate(savedInstanceState)
        binding = ActivityAddDataMotorBinding.inflate(layoutInflater)
        setContentView(binding.root)

        db = MotorDatabaseHelper(this)
        motorAdapter = MotorAdapter(db.getAllDataMotor(), this)

        binding.rvDataMotor.layoutManager = LinearLayoutManager(this)
        binding.rvDataMotor.adapter = motorAdapter

        binding.tambahDataMotorHomeLy.setOnClickListener {
            val intent = Intent(this, Motor_data::class.java)
            startActivity(intent)
        }
    }

    override fun onResume() {
        super.onResume()
        motorAdapter.refreshData(db.getAllDataMotor())
    }
}