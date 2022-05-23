package com.bruwus.wastetracker.ui.calculate.questions

import androidx.fragment.app.Fragment

open class QuestionFragment(private var multiplicator: Double): Fragment() {

    protected var answer: Double? = null

    fun getScore(): Double {
        return answer!! * multiplicator
    }

    fun isAnswered(): Boolean {
        return answer != null
    }
}