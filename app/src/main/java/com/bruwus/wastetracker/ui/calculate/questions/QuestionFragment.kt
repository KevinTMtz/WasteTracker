package com.bruwus.wastetracker.ui.calculate.questions

import androidx.fragment.app.Fragment

open class QuestionFragment(private var multiplier: Double): Fragment() {

    protected var answer: Double? = null

    fun getScore(): Double {
        return answer!! * multiplier
    }

    fun isAnswered(): Boolean {
        return answer != null
    }
}