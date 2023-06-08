package com.example.database

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
        ManagerDB.getInstance(this).TABLE_SEXO.create(Sexo(0, "Femenino"))
        binding = ActivityMainBinding.inflate(layoutInflater)
        setContentView(binding.root)

        binding.spGenero.adapter = ArrayAdapter<Sexo>(this, android.R.layout.simple_spinner_dropdown_item, ManagerDB.getInstance().TABLE_SEXO.read().toList())
        ManagerDB.getInstance().TABLE_SEXO.read().forEach {
            Toast.makeText(this, it.name, Toast.LENGTH_SHORT).show()
        }
    }
}