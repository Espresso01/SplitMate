package ru.fefu.splitmate.navigation

import androidx.compose.runtime.Composable
import androidx.navigation.compose.NavHost
import androidx.navigation.compose.composable
import androidx.navigation.compose.rememberNavController
import ru.fefu.splitmate.ui.screens.BillInputScreen
import ru.fefu.splitmate.ui.screens.SplitResultScreen
import ru.fefu.splitmate.ui.screens.WelcomeScreen
import ru.fefu.splitmate.viewmodel.SplitMateViewModel

private object Routes {
    const val WELCOME = "welcome"
    const val INPUT = "input"
    const val RESULT = "result"
}

@Composable
fun SplitMateNavigation(viewModel: SplitMateViewModel) {
    val navController = rememberNavController()

    NavHost(
        navController = navController,
        startDestination = Routes.WELCOME
    ) {

        composable(Routes.WELCOME) {
            WelcomeScreen {
                navController.navigate(Routes.INPUT)
            }
        }

        composable(Routes.INPUT) {
            BillInputScreen(
                viewModel = viewModel,
                onCalculate = {
                    viewModel.calculate()
                    navController.navigate(Routes.RESULT)
                }
            )
        }

        composable(Routes.RESULT) {
            SplitResultScreen(
                viewModel = viewModel,
                onNewCalculation = {
                    viewModel.reset()
                    navController.popBackStack(
                        Routes.INPUT,
                        inclusive = false
                    )
                }
            )
        }
    }
}
