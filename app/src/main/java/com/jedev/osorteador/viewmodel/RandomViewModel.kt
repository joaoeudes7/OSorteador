package com.jedev.osorteador.viewmodel

import androidx.lifecycle.ViewModel

class RandomViewModel: ViewModel()  {
    val historicNumbers = mutableListOf<Int>()

    fun clearHistoric() {
        historicNumbers.clear()
    }
}