package com.jedev.osorteador.utils

import android.content.Context
import android.view.LayoutInflater
import android.view.View
import androidx.appcompat.app.AlertDialog

object ViewUtils {
    fun createAlertDialog(context: Context, title: String): AlertDialog.Builder {
        return AlertDialog.Builder(context).setTitle(title)
    }

    fun createView(context: Context, layout: Int): View {
        return LayoutInflater.from(context).inflate(layout, null)
    }

    fun customAlertDialog(context: Context, view: View, title: String): AlertDialog.Builder {
        return AlertDialog.Builder(context).apply {
            setView(view)
            setTitle(title)
        }
    }
}