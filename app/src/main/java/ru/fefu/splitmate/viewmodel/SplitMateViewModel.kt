package ru.fefu.splitmate.viewmodel

import androidx.lifecycle.ViewModel
import ru.fefu.splitmate.model.SplitCalculation
import ru.fefu.splitmate.model.SplitMateUiState
import kotlinx.coroutines.flow.MutableStateFlow
import kotlinx.coroutines.flow.StateFlow
import kotlinx.coroutines.flow.asStateFlow
import kotlinx.coroutines.flow.update

class SplitMateViewModel : ViewModel() {

    private val _uiState = MutableStateFlow<SplitMateUiState>(SplitMateUiState.Input())
    val uiState: StateFlow<SplitMateUiState> = _uiState.asStateFlow()

    private val _calculations = MutableStateFlow<List<SplitCalculation>>(emptyList())
    val calculations: StateFlow<List<SplitCalculation>> = _calculations.asStateFlow()

    fun updateTotalAmount(amount: String) {
        when (val currentState = _uiState.value) {
            is SplitMateUiState.Input -> {
                _uiState.update {
                    currentState.copy(totalAmount = amount)
                }
            }
            else -> {}
        }
    }

    fun updatePeopleCount(count: String) {
        when (val currentState = _uiState.value) {
            is SplitMateUiState.Input -> {
                _uiState.update {
                    currentState.copy(peopleCount = count)
                }
            }
            else -> {}
        }
    }

    fun updateTipPercentage(tip: String) {
        when (val currentState = _uiState.value) {
            is SplitMateUiState.Input -> {
                _uiState.update {
                    currentState.copy(tipPercentage = tip)
                }
            }
            else -> {}
        }
    }

    fun calculate(): String? {
        return when (val currentState = _uiState.value) {
            is SplitMateUiState.Input -> {
                try {
                    val total = currentState.totalAmount.toDoubleOrNull() ?: return null
                    val people = currentState.peopleCount.toIntOrNull() ?: return null
                    val tip = currentState.tipPercentage.toDoubleOrNull() ?: return null

                    if (total <= 0 || people <= 0 || tip < 0) return null

                    val calculation = SplitCalculation(
                        totalAmount = total,
                        peopleCount = people,
                        tipPercentage = tip
                    )

                    _calculations.update { currentList ->
                        currentList + calculation
                    }

                    _uiState.update { SplitMateUiState.Result(calculation) }

                    calculation.id
                } catch (e: Exception) {
                    null
                }
            }
            else -> null
        }
    }

    fun navigateToResult(calculation: SplitCalculation) {
        if (!_calculations.value.any { it.id == calculation.id }) {
            _calculations.update { currentList ->
                currentList + calculation
            }
        }

        _uiState.update { SplitMateUiState.Result(calculation) }
    }

    fun startNewCalculation() {
        _uiState.update { SplitMateUiState.Input() }
    }

    fun navigateToInput() {
        when (val currentState = _uiState.value) {
            is SplitMateUiState.Result -> {
                _uiState.update {
                    SplitMateUiState.Input(
                        totalAmount = currentState.calculation.totalAmount.toString(),
                        peopleCount = currentState.calculation.peopleCount.toString(),
                        tipPercentage = currentState.calculation.tipPercentage.toString()
                    )
                }
            }
            else -> {
                _uiState.update { SplitMateUiState.Input() }
            }
        }
    }

    fun getCalculationById(id: String): SplitCalculation? {
        return _calculations.value.find { it.id == id }
    }
}