package com.jedev.osorteador.utils

import android.content.Context
import android.text.TextWatcher
import android.widget.SeekBar
import android.widget.TextView
import androidx.core.content.ContextCompat
import androidx.recyclerview.widget.LinearLayoutManager
import androidx.recyclerview.widget.RecyclerView

object ComponentsUtils {

    fun setupRecyclerView(context: Context, recyclerView: RecyclerView, totalCache: Int = 20) {
        recyclerView.setHasFixedSize(true)
        recyclerView.layoutManager = LinearLayoutManager(context)
        recyclerView.setItemViewCacheSize(totalCache)
    }

    fun changeColorTextView(context: Context, textView: TextView, color: Int) {
        textView.setTextColor(ContextCompat.getColor(context, color))
    }

    fun onSeekBarChangeListener(function: (s: Int) -> Unit = {}): SeekBar.OnSeekBarChangeListener {
        return object : SeekBar.OnSeekBarChangeListener {
            override fun onProgressChanged(seekBar: SeekBar?, progress: Int, fromUser: Boolean) {
                function.invoke(progress)
            }

            override fun onStartTrackingTouch(seekBar: SeekBar?) {
                return
            }

            override fun onStopTrackingTouch(seekBar: SeekBar?) {
                return
            }
        }
    }
}