package com.bruwus.wastetracker.ui.calculate.questions

import androidx.fragment.app.Fragment

open class QuestionFragment(private val number: Int,
                            private val question: String,
                            private var multiplicator: Double = 1.0): Fragment() {

    protected var answer: Double? = null

    fun getScore(): Double {
        return answer!! * multiplicator
    }
}