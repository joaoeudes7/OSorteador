package com.jedev.osorteador.viewmodel

import androidx.lifecycle.MutableLiveData
import androidx.lifecycle.ViewModel

class WordsViewModel: ViewModel() {
    val words = MutableLiveData(mutableListOf<String>())
    val historicWords = MutableLiveData(mutableListOf<String>())

    fun addWord(item: String) {
        this.words.value?.add(item)
    }

    fun removeWord(item: String) {
        this.words.value?.remove(item)
    }

    fun addInHistoric(item: String) {
        this.historicWords.value?.add(item)
    }
}