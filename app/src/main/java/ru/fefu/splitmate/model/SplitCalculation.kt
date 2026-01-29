package ru.fefu.splitmate.model

data class SplitCalculation(
    val totalAmount: Double,
    val peopleCount: Int,
    val tipPercentage: Double
) {
    val tipAmount: Double =
        totalAmount * tipPercentage / 100.0

    val totalWithTip: Double =
        totalAmount + tipAmount

    val perPerson: Double =
        totalWithTip / peopleCount
}
