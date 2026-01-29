package ru.fefu.splitmate.model

sealed interface UiState {

    data class Input(
        val totalAmount: String = "",
        val peopleCount: String = "",
        val tipPercentage: String = ""
    ) : UiState

    data class Result(
        val calculation: SplitCalculation
    ) : UiState
}
