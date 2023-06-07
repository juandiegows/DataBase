package com.example.database.DB

import android.content.Context
import android.database.sqlite.SQLiteDatabase
import android.database.sqlite.SQLiteOpenHelper

abstract  class DBConexion(context: Context) : SQLiteOpenHelper(context,ManagerDB.DB_NAME, null, ManagerDB.DB_VERSION_NOW )
{

    abstract var CREATE_TABLE: String
    abstract var TABLE: String


    override fun onCreate(db: SQLiteDatabase?) {
        db?.execSQL(CREATE_TABLE)
    }

    override fun onUpgrade(db: SQLiteDatabase?, oldVersion: Int, newVersion: Int) {
        db?.execSQL("DROP TABLE IF EXISTS $TABLE")
    }
}