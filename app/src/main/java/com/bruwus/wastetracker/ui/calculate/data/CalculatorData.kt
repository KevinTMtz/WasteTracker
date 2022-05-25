package com.bruwus.wastetracker.ui.calculate.data

import java.io.Serializable
import kotlin.math.floor
import kotlin.math.roundToInt

open class CalculatorData(
    val result: Double,
    // TODO: Define proper ranges or more types of consumer
    val consumerType: Int =
        when(floor(result / 10).roundToInt()){
            0,10 -> 0
            11,20 -> 1
            else -> 2
        },
) : Serializable