package mughalasim.my.cv.ui.screens.main

import androidx.compose.foundation.background
import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.padding
import androidx.compose.material.Scaffold
import androidx.compose.runtime.Composable
import androidx.compose.ui.Modifier
import androidx.compose.ui.res.stringResource
import androidx.navigation.compose.rememberNavController
import mughalasim.my.cv.R
import mughalasim.my.cv.navigation.NavigationHost
import mughalasim.my.cv.ui.theme.AppTheme
import mughalasim.my.cv.ui.theme.padding_screen
import mughalasim.my.cv.ui.widgets.BottomNavItem
import mughalasim.my.cv.ui.widgets.BottomNavigationWidget
import mughalasim.my.cv.ui.widgets.ToolBarWidget
import org.koin.androidx.compose.koinViewModel

@Composable
fun MainScreen() {
    val navController = rememberNavController()
    val viewModel = koinViewModel<MainScreenViewModel>()
    viewModel.setNavController(navController)

    Scaffold(
        topBar = {
            ToolBarWidget(stringResource(id = R.string.app_name))
        },
        bottomBar = {
            BottomNavigationWidget(navController)
        },
        floatingActionButton = {}
    ) { innerPadding ->
        Column(
            modifier = Modifier
                .padding(innerPadding).background(color = AppTheme.colors.backgroundScreen),
            verticalArrangement = Arrangement.spacedBy(padding_screen),
        ) {
            NavigationHost(
                navController = navController,
                navigationService = viewModel.getNavigationService(),
            )
        }
    }
}
