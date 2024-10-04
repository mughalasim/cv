package mughalasim.my.cv.ui.screens.main

import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.padding
import androidx.compose.material3.Scaffold
import androidx.compose.runtime.Composable
import androidx.compose.ui.Modifier
import androidx.navigation.compose.rememberNavController
import mughalasim.my.cv.navigation.NavigationHost
import mughalasim.my.cv.ui.theme.AppTheme
import mughalasim.my.cv.ui.widgets.BottomNavigationWidget
import org.koin.androidx.compose.koinViewModel

@Composable
fun MainScreen() {
    val navController = rememberNavController()
    val viewModel = koinViewModel<MainScreenViewModel>()
    viewModel.setNavController(navController)

    Scaffold(
        containerColor = AppTheme.colors.backgroundScreen,
        bottomBar = { BottomNavigationWidget(navController) }
    ) { innerPadding ->
        Column(
            modifier = Modifier.padding(innerPadding)
        ) {
            NavigationHost(
                navController = navController,
                navigationService = viewModel.getNavigationService(),
            )
        }
    }
}
