package ru.fefu.splitmate.viewmodel

import androidx.lifecycle.ViewModel
import kotlinx.coroutines.flow.MutableStateFlow
import kotlinx.coroutines.flow.StateFlow
import ru.fefu.splitmate.model.SplitCalculation
import ru.fefu.splitmate.model.UiState

class SplitMateViewModel : ViewModel() {

    private val _uiState = MutableStateFlow<UiState>(UiState.Input())
    val uiState: StateFlow<UiState> = _uiState

    fun updateTotalAmount(value: String) {
        val state = _uiState.value as? UiState.Input ?: return
        _uiState.value = state.copy(totalAmount = value)
    }

    fun updatePeopleCount(value: String) {
        val state = _uiState.value as? UiState.Input ?: return
        _uiState.value = state.copy(peopleCount = value)
    }

    fun updateTipPercentage(value: String) {
        val state = _uiState.value as? UiState.Input ?: return
        _uiState.value = state.copy(tipPercentage = value)
    }

    fun isCalculateEnabled(state: UiState.Input): Boolean {
        return state.totalAmount.toDoubleOrNull()?.let { it > 0 } == true &&
                state.peopleCount.toIntOrNull()?.let { it > 0 } == true &&
                state.tipPercentage.toDoubleOrNull()?.let { it >= 0 } == true
    }

    fun calculate() {
        val state = _uiState.value as? UiState.Input ?: return
        if (!isCalculateEnabled(state)) return

        val calculation = SplitCalculation(
            totalAmount = state.totalAmount.toDouble(),
            peopleCount = state.peopleCount.toInt(),
            tipPercentage = state.tipPercentage.toDouble()
        )

        _uiState.value = UiState.Result(calculation)
    }

    fun reset() {
        _uiState.value = UiState.Input()
    }
}
