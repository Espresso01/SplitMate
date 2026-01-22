package ru.fefu.splitmate.ui.screens

import androidx.compose.runtime.Composable
import ru.fefu.splitmate.ui.components.HomeScreen

@Composable
fun HomeScreen(
    onNavigateToInput: () -> Unit
) {
    HomeScreen(
        onStartClicked = onNavigateToInput
    )
}