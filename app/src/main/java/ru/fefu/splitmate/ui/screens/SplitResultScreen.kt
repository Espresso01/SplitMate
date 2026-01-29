package ru.fefu.splitmate.ui.screens

import androidx.compose.runtime.Composable
import androidx.compose.runtime.collectAsState
import androidx.compose.runtime.getValue
import ru.fefu.splitmate.model.UiState
import ru.fefu.splitmate.ui.components.SplitResultCard
import ru.fefu.splitmate.viewmodel.SplitMateViewModel

@Composable
fun SplitResultScreen(
    viewModel: SplitMateViewModel,
    onNewCalculation: () -> Unit
) {
    val uiState by viewModel.uiState.collectAsState()
    val state = uiState as? UiState.Result ?: return

    SplitResultCard(
        calculation = state.calculation,
        onNewCalculationClick = onNewCalculation
    )
}
