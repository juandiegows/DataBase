package com.example.database.adapter

import android.view.LayoutInflater
import android.view.ViewGroup
import androidx.recyclerview.widget.RecyclerView
import com.example.database.R
import com.example.database.databinding.ItemSexoBinding
import com.example.database.models.Sexo

class SexoAdapter(var sexos: MutableList<Sexo>) : RecyclerView.Adapter<SexoAdapter.SexoHolder>() {

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
        var sexo:Sexo = sexos.get(position)
        sexo.apply {
            holder.binding.apply {
                txtSexo.text = name
            }
        }

    }

    override fun getItemCount(): Int {
        return sexos.size
    }
}