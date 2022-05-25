package com.bruwus.wastetracker.ui.calculate.data

import java.io.Serializable
import kotlin.math.floor
import kotlin.math.roundToInt

open class CalculatorData(
    val result: Double,
    val consumerType: Int = floor(result / 10).roundToInt()
) : Serializable