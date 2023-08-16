package com.example.database.DB

import android.content.Context
import android.database.sqlite.SQLiteDatabase
import android.database.sqlite.SQLiteOpenHelper
import android.util.Log
import com.example.database.controller.SexoController
import com.example.database.controller.UsuarioController

open class DBConexion(context: Context) :
    SQLiteOpenHelper(context, ManagerDB.DB_NAME, null, ManagerDB.DB_VERSION_NOW) {


    override fun onCreate(db: SQLiteDatabase?) {
        var tablas = listOf(
            SexoController.CREATE_TABLE,
            UsuarioController.CREATE_TABLE
        )
        tablas.forEach {
            db!!.execSQL(it)
        }
        Log.d("Nueva", "Se ha creado las tablas")
    }

    override fun onUpgrade(db: SQLiteDatabase?, oldVersion: Int, newVersion: Int) {
        var tablas = listOf(
            SexoController.TABLE,
            UsuarioController.TABLE
        )
        tablas.forEach {
            db?.execSQL("DROP TABLE IF EXISTS $it")
        }

    }
}