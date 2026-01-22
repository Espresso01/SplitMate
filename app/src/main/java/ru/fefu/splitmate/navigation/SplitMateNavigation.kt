package ru.fefu.splitmate.navigation

import androidx.navigation.NavController
import androidx.navigation.NavGraphBuilder
import androidx.navigation.NavType
import androidx.navigation.compose.composable
import androidx.navigation.navArgument
import androidx.navigation.navigation
import ru.fefu.splitmate.viewmodel.SplitMateViewModel
import ru.fefu.splitmate.ui.screens.HomeScreen
import ru.fefu.splitmate.ui.screens.InputScreen
import ru.fefu.splitmate.ui.screens.ResultScreen

sealed class Screen(val route: String) {
    object Home : Screen("home")
    object Input : Screen("input")
    object Result : Screen("result/{calcId}") {
        fun createRoute(calcId: String) = "result/$calcId"
    }
}

fun NavGraphBuilder.splitMateGraph(
    navController: NavController,
    viewModel: SplitMateViewModel
) {
    navigation(
        startDestination = Screen.Home.route,
        route = "root"
    ) {
        composable(Screen.Home.route) {
            HomeScreen(
                onNavigateToInput = {
                    navController.navigate(Screen.Input.route)
                }
            )
        }

        composable(Screen.Input.route) {
            InputScreen(
                viewModel = viewModel,
                onCalculateClicked = { calcId ->
                    navController.navigate(Screen.Result.createRoute(calcId))
                }
            )
        }

        composable(
            route = Screen.Result.route,
            arguments = listOf(
                navArgument("calcId") {
                    type = NavType.StringType
                }
            )
        ) { backStackEntry ->
            val calcId = backStackEntry.arguments?.getString("calcId") ?: ""

            ResultScreen(
                calculationId = calcId,
                viewModel = viewModel,
                onBackToEdit = {
                    navController.popBackStack()
                },
                onNewCalculation = {
                    navController.navigate(Screen.Input.route) {
                        popUpTo(Screen.Home.route) { inclusive = false }
                    }
                }
            )
        }
    }
}