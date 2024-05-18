package mughalasim.my.cv.navigation

import androidx.navigation.NavController
import cv.domain.Variables.EVENT_NAME_NAVIGATE
import cv.domain.Variables.PARAM_SCREEN_NAME
import mughalasim.my.cv.di.DI
import mughalasim.my.cv.ui.utils.toRoute

class NavigationServiceImp : NavigationService {
    private lateinit var navController: NavController
    private lateinit var initialRoute: Route

    override fun setNavController(navController: NavController) {
        this.navController = navController
        val initialRoute = Route.getInitialRoute()
        this.initialRoute = initialRoute
    }

    override fun open(
        route: Route,
        removeCurrentFromStack: Boolean,
    ) {
        if (route == getCurrentRoute()) {
            // preventing double tab
            return
        }
        navController.navigate(route.routeName) {
            if (removeCurrentFromStack) {
                popUpTo(getCurrentRoute().routeName) {
                    inclusive = true
                }
            }
        }
        DI.analytics.logEvent(
            EVENT_NAME_NAVIGATE,
            listOf(
                Pair(PARAM_SCREEN_NAME, route.routeName),
            ),
        )
    }

    override fun popBack() {
        navController.popBackStack()
        DI.analytics.logEvent(
            EVENT_NAME_NAVIGATE,
            listOf(
                Pair(PARAM_SCREEN_NAME, getCurrentRoute().routeName),
            ),
        )
    }

    override fun getCurrentRoute(): Route = navController.currentDestination?.route?.toRoute() ?: Route.ListScreen

    override fun getInitialRoute(): Route = initialRoute
}

sealed class Route(val routeName: String, val isInitialRoute: Boolean = false) {
    data object ListScreen : Route("ListScreen")

    data object SettingsScreen : Route("SettingsScreen")

    companion object {
        fun getInitialRoute(): Route =
            Route::class.sealedSubclasses
                .firstOrNull { it.objectInstance?.isInitialRoute == true }
                ?.objectInstance
                ?: ListScreen
    }
}
