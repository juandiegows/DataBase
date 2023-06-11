package com.example.database.controller

import android.content.ContentValues
import android.content.Context
import com.example.database.DB.CRUD
import com.example.database.DB.DBConexion
import com.example.database.models.Sexo

class SexoController(context: Context) : DBConexion(context), CRUD<Sexo> {

    override var TABLE: String = SexoController.TABLE
    override var CREATE_TABLE: String = SexoController.CREATE_TABLE

    companion object {
        val TABLE = "sexo"
        val ID = "id"
        val NAME = "Name"
        private val CREATE_TABLE = "CREATE TABLE $TABLE ($ID INTEGER PRIMARY KEY AUTOINCREMENT, $NAME TEXT UNIQUE)"
    }

    override fun create(data: Sexo): Long {
        val values = ContentValues().apply {
            put(NAME, data.name)
        }
        return this.writableDatabase.insert(TABLE, null, values)
    }

    override fun read(): MutableList<Sexo> {
        val sexos: MutableList<Sexo> = mutableListOf()
        val cursor = this.readableDatabase.query(
            TABLE,
            arrayOf(ID, NAME),
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
                sexos.add(Sexo(id, name))
            }
        }
        return sexos
    }

    override fun update(data: Sexo): Long {
        val values = ContentValues().apply {
            put(NAME, data.name)
        }
        val selection = "$ID LIKE ?"
        val selectionArgs = arrayOf(data.id.toString())
        return this.writableDatabase.update(TABLE, values, selection, selectionArgs).toLong()
    }

    override fun delete(data: Sexo): Long {
        val selection = "$ID LIKE ?"
        val selectionArgs = arrayOf(data.id.toString())
        return this.writableDatabase.delete(TABLE, selection, selectionArgs).toLong()
    }
}
