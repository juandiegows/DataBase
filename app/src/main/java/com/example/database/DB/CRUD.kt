package com.example.database.DB

interface CRUD<E> {
    fun create(data: E) : Long
    fun read(): MutableList<E>
    fun update(data: E) : Long
    fun delete(data: E) : Long
}
