package ru.fefu.splitmate.ui.screens

import androidx.compose.runtime.Composable
import androidx.compose.runtime.collectAsState
import androidx.compose.runtime.getValue
import ru.fefu.splitmate.viewmodel.SplitMateViewModel
import ru.fefu.splitmate.model.SplitMateUiState
import ru.fefu.splitmate.ui.components.InputScreen
import ru.fefu.splitmate.ui.components.InputScreenState

@Composable
fun InputScreen(
    viewModel: SplitMateViewModel,
    onCalculateClicked: (String) -> Unit
) {
    val uiState by viewModel.uiState.collectAsState()

    when (uiState) {
        is SplitMateUiState.Input -> {
            val inputState = uiState as SplitMateUiState.Input

            val totalAmount = inputState.totalAmount
            val peopleCount = inputState.peopleCount
            val tipPercentage = inputState.tipPercentage

            val isCalculateEnabled = totalAmount.toDoubleOrNull()?.let { it > 0 } == true &&
                    peopleCount.toIntOrNull()?.let { it > 0 } == true &&
                    tipPercentage.toDoubleOrNull()?.let { it >= 0 } == true

            InputScreen(
                state = InputScreenState(
                    totalAmount = totalAmount,
                    peopleCount = peopleCount,
                    tipPercentage = tipPercentage,
                    isCalculateEnabled = isCalculateEnabled
                ),
                onTotalAmountChanged = { viewModel.updateTotalAmount(it) },
                onPeopleCountChanged = { viewModel.updatePeopleCount(it) },
                onTipPercentageChanged = { viewModel.updateTipPercentage(it) },
                onCalculateClicked = {
                    val calcId = viewModel.calculate()
                    calcId?.let { onCalculateClicked(it) }
                }
            )
        }
        SplitMateUiState.Loading -> {
        }
        is SplitMateUiState.Result -> {
        }
    }
}