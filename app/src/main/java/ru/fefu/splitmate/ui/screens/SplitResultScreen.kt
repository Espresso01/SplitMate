package ru.fefu.splitmate.ui.screens

import androidx.compose.runtime.Composable
import androidx.compose.runtime.remember
import ru.fefu.splitmate.viewmodel.SplitMateViewModel
import ru.fefu.splitmate.ui.components.ResultScreen
import ru.fefu.splitmate.ui.components.ResultScreenState

@Composable
fun ResultScreen(
    calculationId: String,
    viewModel: SplitMateViewModel,
    onBackToEdit: () -> Unit,
    onNewCalculation: () -> Unit
) {
    val calculation = remember(calculationId) {
        viewModel.getCalculationById(calculationId)
    }

    ResultScreen(
        state = ResultScreenState(calculation = calculation),
        onBackToEditClicked = {
            viewModel.navigateToInput()
            onBackToEdit()
        },
        onNewCalculationClicked = {
            viewModel.startNewCalculation()
            onNewCalculation()
        }
    )
}