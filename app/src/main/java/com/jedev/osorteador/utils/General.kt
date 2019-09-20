package com.jedev.osorteador.utils

import android.view.View
import com.google.android.material.snackbar.Snackbar

object General {
    fun alert(view: View, msg: String): Snackbar {
        return Snackbar.make(view, msg, Snackbar.LENGTH_SHORT)
    }
}