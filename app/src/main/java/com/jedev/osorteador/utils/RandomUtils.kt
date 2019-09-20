package com.jedev.osorteador.utils

object RandomUtils {
    fun randomNumbers(start: Int, end: Int): Int {
        return if (start > end) {
            (end..start).random()
        } else {
            (start..end).random()
        }
    }

    fun randomWords(words: MutableList<String>): String {
        return words.random()
    }

    fun randomGroup(quantityGroups: Int, words: MutableList<String>): MutableList<MutableList<String>> {
        val groups = mutableListOf<MutableList<String>>()
        val itemsPerGroup = words.size/quantityGroups

        repeat(quantityGroups) {
            groups.add(mutableListOf())
        }

        words.forEach {
            var randomGroup = randomNumbers(0, quantityGroups - 1)

            while (groups[randomGroup].size >= itemsPerGroup) {
               randomGroup = randomNumbers(0, quantityGroups - 1)
            }

            groups[randomGroup].add(it)
        }

        return groups
    }
}