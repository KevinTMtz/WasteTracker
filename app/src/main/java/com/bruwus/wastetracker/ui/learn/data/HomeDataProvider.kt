package com.bruwus.wastetracker.ui.learn.data

object LearnDataProvider {
    private val list = mutableListOf<String>()

    init {
        for (i in 1..5) {
            list.add(
                "Data $i"
            )
        }
    }

    fun getData(): List<String> {
        return list
    }

    fun addData(text: String) {
        list.add(text)
    }
}