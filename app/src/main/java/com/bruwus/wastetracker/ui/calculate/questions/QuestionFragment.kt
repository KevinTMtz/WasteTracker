package com.bruwus.wastetracker.ui.calculate.questions

import androidx.fragment.app.Fragment
import com.bruwus.wastetracker.ui.calculate.data.QuestionArgs

open class QuestionFragment: Fragment() {
    protected var answer: Double? = null
    protected var multiplier: Double? = null

    protected var questionArgs: QuestionArgs? = null

    fun getScore(): Double {
        return answer!! * (multiplier ?: 0.0)
    }

    fun isAnswered(): Boolean {
        return answer != null
    }
}