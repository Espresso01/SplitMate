package ru.fefu.splitmate.ui.screens

import androidx.compose.runtime.Composable
import ru.fefu.splitmate.ui.components.WelcomeScreenContent

@Composable
fun WelcomeScreen(
    onNavigateToInput: () -> Unit
) {
    WelcomeScreenContent(
        onStartClicked = onNavigateToInput
    )
}