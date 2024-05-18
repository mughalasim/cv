package mughalasim.my.cv.navigation

import androidx.compose.runtime.Composable
import androidx.navigation.NavHostController
import androidx.navigation.compose.NavHost
import androidx.navigation.compose.composable
import mughalasim.my.cv.ui.screens.list.ListScreen
import mughalasim.my.cv.ui.screens.settings.SettingsScreen

@Composable
fun NavigationHost(
    navController: NavHostController,
    navigationService: NavigationService,
) {
    NavHost(
        navController = navController,
        startDestination = navigationService.getInitialRoute().routeName,
    ) {
        composable(route = Route.ListScreen.routeName) { ListScreen() }
        composable(route = Route.SettingsScreen.routeName) { SettingsScreen() }
    }
}
