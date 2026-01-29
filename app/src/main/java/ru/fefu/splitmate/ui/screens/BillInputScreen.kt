package ru.fefu.splitmate.ui.screens

import androidx.compose.runtime.Composable
import androidx.compose.runtime.collectAsState
import androidx.compose.runtime.getValue
import ru.fefu.splitmate.model.UiState
import ru.fefu.splitmate.ui.components.BillInputForm
import ru.fefu.splitmate.viewmodel.SplitMateViewModel

@Composable
fun BillInputScreen(
    viewModel: SplitMateViewModel,
    onCalculate: () -> Unit
) {
    val uiState by viewModel.uiState.collectAsState()
    val state = uiState as? UiState.Input ?: return

    BillInputForm(
        totalAmount = state.totalAmount,
        peopleCount = state.peopleCount,
        tipPercentage = state.tipPercentage,
        isCalculateEnabled = viewModel.isCalculateEnabled(state),
        onTotalAmountChange = viewModel::updateTotalAmount,
        onPeopleCountChange = viewModel::updatePeopleCount,
        onTipPercentageChange = viewModel::updateTipPercentage,
        onCalculateClick = onCalculate
    )
}
