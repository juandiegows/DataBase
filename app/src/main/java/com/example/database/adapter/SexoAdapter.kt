package com.example.database.adapter

import android.app.Dialog
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.Toast
import androidx.appcompat.app.AlertDialog
import androidx.core.view.isVisible
import androidx.recyclerview.widget.RecyclerView
import com.example.database.DB.ManagerDB
import com.example.database.R
import com.example.database.databinding.ActivityCreateSexoBinding
import com.example.database.databinding.ItemSexoBinding
import com.example.database.helper.ClearControls
import com.example.database.helper.IsValid
import com.example.database.helper.Required
import com.example.database.helper.Text
import com.example.database.models.Sexo

class SexoAdapter(var sexos: MutableList<Sexo>, var editar: (sexo: Sexo) -> Unit) :
    RecyclerView.Adapter<SexoAdapter.SexoHolder>() {

    inner class SexoHolder(var binding: ItemSexoBinding) : RecyclerView.ViewHolder(binding.root)


    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): SexoHolder {


        return SexoHolder(
            ItemSexoBinding.bind(
                LayoutInflater.from(parent.context).inflate(R.layout.item_sexo, parent, false)
            )
        )
    }


    override fun onBindViewHolder(holder: SexoHolder, position: Int) {
        //Sexo seleccionado
        var sexo: Sexo = sexos.get(position)
        sexo.apply {
            holder.binding.apply {
                txtSexo.text = name
                btnDelete.setOnClickListener {
                    AlertDialog.Builder(btnDelete.context)
                        .setMessage("¿Desea eliminar el sexo ${sexo.name}?")
                        .setNegativeButton("No") { _, _ ->
                        }.setPositiveButton("Si") { _, _ ->
                            ManagerDB.getInstance(this.root.context).TABLE_SEXO.delete(sexo)
                            sexos.remove(sexo)
                            notifyItemRemoved(position)
                        }.create().show()
                }
                btnEdit.setOnClickListener {
                    editar.invoke(sexo)
                }
                btnEditDialog.setOnClickListener {

                    // para que esto funcione el layout de activity_create_sexo debe esta en wrap_content el alto
                    var bindingUpdateSexo = ActivityCreateSexoBinding.bind(
                        LayoutInflater.from(root.context)
                            .inflate(R.layout.activity_create_sexo, null)
                    )
                    var alert = AlertDialog.Builder(root.context).setView(bindingUpdateSexo.root)
                        .create()
                    var views: MutableList<View>
                    bindingUpdateSexo.apply {
                        textView2.text = "Actualizar"
                        txtName.Required(txtCSexo)
                        btnCrear.text = "Actualizar"
                        txtName.Text = sexo.name
                        views = mutableListOf(txtName, txtCSexo)
                        btnCrear.setOnClickListener {
                            if (views.IsValid()) {
                                if (ManagerDB.getInstance(root.context).TABLE_SEXO.update(
                                        Sexo(
                                            sexo.id,
                                            txtName.Text
                                        )
                                    ) > 0
                                ) {
                                    Toast.makeText(
                                        root.context,
                                        "se ha actualizado correctamente",
                                        Toast.LENGTH_SHORT
                                    ).show()

                                    var sexo_add =  Sexo(
                                        sexo.id,
                                        txtName.Text
                                    )
                                    sexos.set(position,sexo_add )

                                    notifyItemChanged(position)
                                    //ocultamos la alerta
                                    alert.dismiss()




                                } else {
                                    Toast.makeText(
                                        root.context,
                                        "Error al actualizar",
                                        Toast.LENGTH_SHORT
                                    ).show()
                                }
                            }

                        }
                    }
//mostramos la alert
                    alert.show()


                }
            }
        }

    }

    override fun getItemCount(): Int {
        return sexos.size
    }
}