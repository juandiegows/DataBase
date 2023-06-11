package com.example.database.models

data class Sexo(var id: Int, var name: String) {
    override fun toString(): String {
        return name
    }
}
