package com.example.database

import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import androidx.recyclerview.widget.LinearLayoutManager
import com.example.database.DB.ManagerDB
import com.example.database.adapter.SexoAdapter
import com.example.database.databinding.ActivitySexoBinding

class SexoActivity : AppCompatActivity() {
    lateinit var binding:ActivitySexoBinding
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        binding = ActivitySexoBinding.inflate(layoutInflater)
        setContentView(binding.root)
        binding.apply {
            recySexo.layoutManager = LinearLayoutManager(this@SexoActivity)
            recySexo.adapter = SexoAdapter(ManagerDB.getInstance(this@SexoActivity).TABLE_SEXO.read())
        }
    }
}