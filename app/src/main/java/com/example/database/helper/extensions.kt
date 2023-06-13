package com.example.database.helper

import android.content.Context
import android.view.View
import com.example.database.DB.DBConexion
import com.google.android.material.textfield.TextInputEditText
import com.google.android.material.textfield.TextInputLayout

fun TextInputEditText.Required(container: TextInputLayout) {

    this.setOnFocusChangeListener { v, hasFocus ->
        if (!hasFocus) {
            if (this.text!!.isEmpty()) {
                container.error = "Este campo es requerido"
            } else {
                container.clearFocus()
                container.error = ""
            }
        }
    }
}

var TextInputEditText.Text: String
    get() {
        return this.text.toString()
    }
    set(value) {
        this.setText(value)
    }

fun MutableList<View>.IsValid(): Boolean {
    var isvalid = true
    this.forEach {
        if (it is TextInputEditText) {
            it.requestFocus()
            it.clearFocus()

        }
        if (it is TextInputLayout)
            if (!it.error.isNullOrEmpty()) {
                isvalid = false
            }
    }
    return isvalid
}

fun MutableList<View>.ClearControls() {

    this.forEach {
        if (it is TextInputEditText) {
            it.setText("")
            if (this.indexOf(it) == 0) {
                it.requestFocus()
            }

        }
        if (it is TextInputLayout) {
            it.error = ""

        }

    }
}