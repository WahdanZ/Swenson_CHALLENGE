package com.wahdanz.fixer.extensions

import android.util.Log
import android.widget.EditText

inline fun <reified T : Any> T.debugLog(message: String?) =
    Log.d(T::class.java.simpleName, message ?: "")

inline fun <reified T : Any> T.errorLog(message: String?, error: Throwable? = null) =
    Log.e(T::class.java.simpleName, message ?: "", error)

fun EditText.updateText(text: String) {
    val focused = hasFocus()
    if (focused) {
        clearFocus()
    }
    setText(text)
    if (focused) {
        requestFocus()
    }
}