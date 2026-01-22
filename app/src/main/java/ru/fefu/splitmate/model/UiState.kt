package ru.fefu.splitmate.model

sealed interface SplitMateUiState {
    data object Loading : SplitMateUiState
    data class Input(
        val totalAmount: String = "",
        val peopleCount: String = "",
        val tipPercentage: String = ""
    ) : SplitMateUiState

    data class Result(
        val calculation: SplitCalculation
    ) : SplitMateUiState
}