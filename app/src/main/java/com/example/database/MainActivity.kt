package com.example.database

import android.content.Intent
import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.widget.ArrayAdapter
import android.widget.SpinnerAdapter
import android.widget.Toast
import com.example.database.DB.ManagerDB
import com.example.database.databinding.ActivityMainBinding
import com.example.database.models.Sexo

class MainActivity : AppCompatActivity() {
    lateinit var binding: ActivityMainBinding
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        Thread.sleep(5000)
        binding = ActivityMainBinding.inflate(layoutInflater)
        setContentView(binding.root)

        Events()
    }

    private fun Events() {
        binding.apply {
            btnSexo.setOnClickListener {
                startActivity(Intent(this@MainActivity, SexoActivity::class.java))
            }
        }
    }
}