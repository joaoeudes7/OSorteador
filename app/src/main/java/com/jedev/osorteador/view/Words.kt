package com.jedev.osorteador.view

import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import com.jedev.osorteador.R
import com.jedev.osorteador.utils.RandomUtils
import kotlinx.android.synthetic.main.content_main.*

class Words : AppCompatActivity() {

    val words = mutableListOf<String>()
    val historic_words = mutableListOf<String>()

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_words)

        btnRandom.setOnClickListener {
            val word = RandomUtils.randomWords(words)

            historic_words.add(word)
            num.text = word
        }
    }
}
