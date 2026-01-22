package ru.fefu.splitmate.model

import java.util.UUID

data class SplitCalculation(
    val id: String = UUID.randomUUID().toString(),
    val totalAmount: Double,
    val peopleCount: Int,
    val tipPercentage: Double
) {
    val tipAmount: Double
        get() = totalAmount * (tipPercentage / 100)

    val totalWithTip: Double
        get() = totalAmount + tipAmount

    val perPerson: Double
        get() = if (peopleCount > 0) totalWithTip / peopleCount else 0.0
}