package com.bruwus.wastetracker.ui.calculate.data

import java.io.Serializable

data class QuestionArgs(
    val text: String,
    val multiplier: Double,
    var number: Int? = 0
): Serializable
