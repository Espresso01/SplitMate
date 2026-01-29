package ru.fefu.splitmate

import android.os.Bundle
import androidx.activity.ComponentActivity
import androidx.activity.compose.setContent
import androidx.lifecycle.viewmodel.compose.viewModel
import ru.fefu.splitmate.navigation.SplitMateNavigation
import ru.fefu.splitmate.ui.theme.SplitMateTheme
import ru.fefu.splitmate.viewmodel.SplitMateViewModel

class MainActivity : ComponentActivity() {

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)

        setContent {
            SplitMateTheme {
                val viewModel: SplitMateViewModel = viewModel()
                SplitMateNavigation(viewModel)
            }
        }
    }
}
