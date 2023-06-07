package com.example.database.controller

import android.content.ContentValues
import android.content.Context
import com.example.database.DB.CRUD
import com.example.database.DB.DBConexion
import com.example.database.models.Usuario

class UsuarioController(context: Context) : DBConexion(context), CRUD<Usuario> {

    override var TABLE: String = UsuarioController.TABLE
    override var CREATE_TABLE: String = UsuarioController.CREATE_TABLE

    companion object {
        val TABLE = "sexo"
        val ID = "id"
        val NAME = "Name"
        val EMAIL = "email"
        val SEXO_ID = "sexo_id"
        private val CREATE_TABLE =
            "CREATE TABLE $TABLE ($ID INTEGER PRIMARY KEY AUTOINCREMENT, $NAME" +
                    "$EMAIL TEXT UNIQUE, $SEXO_ID Int,  FOREIGN KEY($SEXO_ID) REFERENCES ${SexoController.TABLE}(${SexoController.ID}) )"

    }

    override fun create(data: Usuario): Long {
        return this.writableDatabase.insert(UsuarioController.TABLE, null, ContentValues().apply {
            put(ID, data.id)
            put(NAME, data.name)
            put(EMAIL, data.email)
            put(SEXO_ID, data.id)
        })
    }

    override fun read(): MutableList<Usuario> {
        val usuarios: MutableList<Usuario> = mutableListOf()
        val cursor = this.readableDatabase.query(
            UsuarioController.TABLE,
            arrayOf(ID, NAME, EMAIL, SEXO_ID),
            null,
            null,
            null,
            null,
            null
        )
        cursor?.use {
            while (cursor.moveToNext()) {
                val id = cursor.getInt(cursor.getColumnIndexOrThrow(ID))
                val name = cursor.getString(cursor.getColumnIndexOrThrow(NAME))
                val email = cursor.getString(cursor.getColumnIndexOrThrow(EMAIL))
                val sexoId = cursor.getInt(cursor.getColumnIndexOrThrow(SEXO_ID))
                usuarios.add(Usuario(id, name, email, sexoId))
            }
        }
        return usuarios
    }

    override fun update(data: Usuario): Long {
        val values = ContentValues().apply {
            put(NAME, data.name)
            put(EMAIL, data.email)
            put(SEXO_ID, data.id)
        }
        val selection = "$ID = ?"
        val selectionArgs = arrayOf(data.id.toString())
        return this.writableDatabase.update(UsuarioController.TABLE, values, selection, selectionArgs).toLong()
    }

    override fun delete(data: Usuario): Long {
        val selection = "$ID = ?"
        val selectionArgs = arrayOf(data.id.toString())
        return this.writableDatabase.delete(UsuarioController.TABLE, selection, selectionArgs).toLong()
    }

}