package com.example.database.DB

import android.app.Application
import android.content.Context
import com.example.database.controller.SexoController
import com.example.database.controller.UsuarioController

class ManagerDB {
    companion object {
        var DB_NAME = "db"
        val DB_VERSION_NOW = 1
        private var connection : ManagerDB? = null

        fun getInstance(context: Context? = null) : ManagerDB{
            if(connection == null){
                connection = ManagerDB(context!!)

            }

            return connection!!
        }

    }

    var TABLE_USUARIO: UsuarioController

    var TABLE_SEXO: SexoController
    constructor(context: Context){
        var conection = DBConexion(context)
        TABLE_USUARIO = UsuarioController(conection)
        TABLE_SEXO = SexoController(conection)
    }
}