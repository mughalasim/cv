package mughalasim.my.cv.navigation

import androidx.compose.runtime.Composable
import androidx.navigation.NavHostController
import androidx.navigation.compose.NavHost
import androidx.navigation.compose.composable
import mughalasim.my.cv.services.Route
import mughalasim.my.cv.services.ServiceNavigation
import mughalasim.my.cv.ui.screens.list.ListScreen
import mughalasim.my.cv.ui.screens.settings.SettingsScreen

@Composable
fun NavigationHost(
    navController: NavHostController,
    serviceNavigation: ServiceNavigation,
) {
    NavHost(
        navController = navController,
        startDestination = serviceNavigation.getInitialRoute().routeName,
    ) {
        composable(route = Route.ListScreen.routeName) { ListScreen() }
        composable(route = Route.SettingsScreen.routeName) { SettingsScreen() }
    }
}
