package com.jedev.osorteador.utils

import android.app.Activity
import android.view.View
import android.widget.Toast
import androidx.appcompat.app.AppCompatActivity
import com.google.android.material.snackbar.Snackbar

object ActivityHelper {
    val Activity.contentView: View
        get() = this.window.decorView


    fun Activity.toast(message: String) {
        Toast.makeText(this, message, Toast.LENGTH_LONG).show()
    }

    fun Activity.snackbar(message: String) {
        Snackbar.make(contentView, message, Snackbar.LENGTH_LONG).show()
    }
}