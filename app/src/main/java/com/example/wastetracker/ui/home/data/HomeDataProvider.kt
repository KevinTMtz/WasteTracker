package com.example.wastetracker.ui.home.data

object HomeDataProvider {
    private val list = mutableListOf<String>()

    init {
        for (i in 1..5) {
            list.add(
                "Lorem ipsum dolor sit amet, consectetur adipiscing elit ${i}"
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