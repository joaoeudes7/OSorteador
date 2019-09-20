package com.jedev.osorteador.ui


import android.os.Bundle
import androidx.fragment.app.Fragment
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup

import com.jedev.osorteador.R
import kotlinx.android.synthetic.main.fragment_coin.*
import kotlinx.coroutines.GlobalScope
import kotlinx.coroutines.delay
import kotlinx.coroutines.launch
import java.sql.Time
import java.util.*
import kotlin.concurrent.schedule

class CoinFragment : Fragment() {

    private val coins = listOf("Cara", "Coroa")

    override fun onCreateView(inflater: LayoutInflater, container: ViewGroup?,  savedInstanceState: Bundle?): View? {
        return inflater.inflate(R.layout.fragment_coin, container, false)
    }

    override fun onActivityCreated(savedInstanceState: Bundle?) {
        super.onActivityCreated(savedInstanceState)

        randomCoin()

        imageView3.setOnClickListener {
            randomCoin()
        }

        sortedCoin.setOnClickListener {
            randomCoin()
        }
    }

    private fun randomCoin() {
        sortedCoin.text = coins.random()
    }
}
