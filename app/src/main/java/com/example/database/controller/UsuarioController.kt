package com.example.database.controller

import android.content.Context
import com.example.database.DB.DBConexion

class UsuarioController(context: Context): DBConexion(context) {

    override var TABLE: String = UsuarioController.TABLE
    override var CREATE_TABLE: String = UsuarioController.CREATE_TABLE

    companion object {
        val TABLE = "sexo"
        val ID = "id"
        val NAME = "Name"
        val EMAIL= "email"
        val SEXO_ID = "sexo_id"
        private val CREATE_TABLE =  "CREATE TABLE $TABLE ($ID INTEGER PRIMARY KEY AUTOINCREMENT, $NAME" +
                "$EMAIL TEXT UNIQUE, $SEXO_ID Int,  FOREIGN KEY($SEXO_ID) REFERENCES ${SexoController.TABLE}(${SexoController.ID}) )"

    }

}