package com.example.database

import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.view.View
import android.widget.Toast
import com.example.database.DB.ManagerDB
import com.example.database.databinding.ActivityCreateSexoBinding
import com.example.database.helper.ClearControls
import com.example.database.helper.IsValid
import com.example.database.helper.Required
import com.example.database.models.Sexo

class CreateSexoActivity : AppCompatActivity() {

    lateinit var binding: ActivityCreateSexoBinding
    lateinit var views: MutableList<View>
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        binding = ActivityCreateSexoBinding.inflate(layoutInflater)
        setContentView(binding.root)

        binding.apply {
            txtName.Required(txtCSexo)
            views = mutableListOf(txtName, txtCSexo)
            btnCrear.setOnClickListener {

                if (views.IsValid()) {
                    if (ManagerDB.getInstance(this@CreateSexoActivity).TABLE_SEXO.create(
                            Sexo(
                                0,
                                txtName.text.toString()
                            )
                        ) > 0
                    ) {
                        views.ClearControls()
                        Toast.makeText(
                            this@CreateSexoActivity,
                            "Se ha agregado correctamente",
                            Toast.LENGTH_SHORT
                        ).show()
                    } else {
                        Toast.makeText(
                            this@CreateSexoActivity,
                            "Error al guardar",
                            Toast.LENGTH_SHORT
                        ).show()
                    }

                }
            }

        }
    }
}