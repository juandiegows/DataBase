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
import com.example.database.helper.Singleton
import com.example.database.helper.Text
import com.example.database.models.Sexo

class CreateSexoActivity : AppCompatActivity() {

    lateinit var binding: ActivityCreateSexoBinding
    lateinit var views: MutableList<View>
    var sexo: Sexo? = null
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        binding = ActivityCreateSexoBinding.inflate(layoutInflater)
        setContentView(binding.root)
        Init()
    }

    private fun Init() {
        sexo = Singleton.sexo
        if (sexo != null) {
            binding.apply {
                txtName.Text = sexo!!.name
                btnCrear.text = "Actualizar"
                textView2.text = "Actualizar Sexo ${sexo!!.name}"
            }
        }
        binding.apply {
            txtName.Required(txtCSexo)
            views = mutableListOf(txtName, txtCSexo)
            btnCrear.setOnClickListener {

                if (views.IsValid()) {
                    if (sexo == null) {
                        Save()
                    } else {
                        update()
                    }

                }
            }

        }
    }

    private fun update() {
        binding.apply {

            if (ManagerDB.getInstance(this@CreateSexoActivity).TABLE_SEXO.update(
                    Sexo(
                        sexo!!.id,
                        txtName.text.toString()
                    )
                ) > 0
            ) {
                views.ClearControls()
                Toast.makeText(
                    this@CreateSexoActivity,
                    "Se ha actualizado correctamente",
                    Toast.LENGTH_SHORT
                ).show()
                finish()
            } else {
                Toast.makeText(
                    this@CreateSexoActivity,
                    "Error al actualizar",
                    Toast.LENGTH_SHORT
                ).show()
            }
        }
    }

    private fun Save() {
        binding.apply {

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