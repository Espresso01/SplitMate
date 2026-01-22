package ru.fefu.splitmate

import androidx.compose.runtime.Composable
import androidx.lifecycle.viewmodel.compose.viewModel
import androidx.navigation.compose.NavHost
import androidx.navigation.compose.rememberNavController
import ru.fefu.splitmate.navigation.splitMateGraph
import ru.fefu.splitmate.viewmodel.SplitMateViewModel

@Composable
fun SplitMateApp(
    viewModel: SplitMateViewModel = viewModel()
) {
    val navController = rememberNavController()

    NavHost(
        navController = navController,
        startDestination = "root"
    ) {
        splitMateGraph(
            navController = navController,
            viewModel = viewModel
        )
    }
}