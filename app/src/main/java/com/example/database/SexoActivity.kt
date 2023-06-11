package com.example.database

import android.content.Intent
import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.provider.Contacts.Intents
import androidx.recyclerview.widget.LinearLayoutManager
import com.example.database.DB.ManagerDB
import com.example.database.adapter.SexoAdapter
import com.example.database.databinding.ActivitySexoBinding
import com.example.database.helper.Singleton

class SexoActivity : AppCompatActivity() {
    lateinit var binding: ActivitySexoBinding
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        binding = ActivitySexoBinding.inflate(layoutInflater)
        setContentView(binding.root)

        LlenarRecySexo()


        binding.apply {
            btnSexo.setOnClickListener {
                Singleton.sexo = null
                startActivity(Intent(this@SexoActivity, CreateSexoActivity::class.java))
            }
        }

    }

    private fun LlenarRecySexo() {
        binding.apply {
            recySexo.layoutManager = LinearLayoutManager(this@SexoActivity)
            recySexo.adapter =
                SexoAdapter(ManagerDB.getInstance(this@SexoActivity).TABLE_SEXO.read()) { sexo ->
                    Singleton.sexo = sexo
                    startActivity(Intent(this@SexoActivity, CreateSexoActivity::class.java))

                }
        }

    }

    override fun onResume() {
        LlenarRecySexo()
        super.onResume()

    }
}