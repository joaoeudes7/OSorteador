package com.jedev.osorteador.utils

import android.widget.Toast
import androidx.core.content.ContextCompat
import androidx.fragment.app.Fragment
import com.google.android.material.snackbar.Snackbar

object FragmentHelper {
    val Fragment.contentView
        get() = this.view!!

    fun Fragment.snackBar(message: String) {
        Snackbar.make(contentView, message, Snackbar.LENGTH_LONG).show()
    }

    fun Fragment.toast(message: String) {
        Toast.makeText(this.activity, message, Toast.LENGTH_LONG).show()
    }

    fun Fragment.getColor(colorId: Int): Int {
        return ContextCompat.getColor(this.context!!, colorId)
    }
}