package com.example.database.controller

import android.content.Context
import com.example.database.DB.DBConexion

class SexoController(context: Context): DBConexion(context) {

    override var TABLE: String = SexoController.TABLE
    override var CREATE_TABLE: String = SexoController.CREATE_TABLE

    companion object {
        val TABLE = "sexo"
        val ID = "id"
        val NAME = "Name"
        private val CREATE_TABLE =  "CREATE TABLE $TABLE ($ID INTEGER PRIMARY KEY AUTOINCREMENT, $NAME TEXT UNIQUE)"

    }
}