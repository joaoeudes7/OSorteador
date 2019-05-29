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
        return words[randomNumbers(0, words.size)]
    }

    fun randomGroup(quantity_groups: Int, words: MutableList<String>): MutableList<MutableList<String>> {
        val groups = mutableListOf<MutableList<String>>()
        val itemsPerGroup = words.size/quantity_groups

        repeat(quantity_groups) {
            groups.add(mutableListOf())
        }

        words.forEach {
            var randomGroup = randomNumbers(0, quantity_groups - 1)

            while (groups[randomGroup].size >= itemsPerGroup) {

               randomGroup = randomNumbers(0, quantity_groups - 1)
            }
            groups[randomGroup].add(it)
        }

        return groups
    }
}