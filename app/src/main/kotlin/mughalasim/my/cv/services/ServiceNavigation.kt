package mughalasim.my.cv.services

import androidx.navigation.NavController
import mughalasim.my.cv.ui.utils.toRoute

sealed class Route(val routeName: String, val isInitialRoute: Boolean = false) {
    object ListScreen : Route("ListScreen", true)
    object SettingsScreen : Route("SettingsScreen")

    companion object {
        fun getInitialRoute(): Route =
            Route::class.sealedSubclasses
                .firstOrNull { it.objectInstance?.isInitialRoute == true }
                ?.objectInstance
                ?: ListScreen
    }
}

class ServiceNavigation : IServiceNavigation {

    private lateinit var navController: NavController
    private lateinit var initialRoute: Route

    override fun setNavController(navController: NavController) {
        this.navController = navController
        val initialRoute = Route.getInitialRoute()
        this.initialRoute = initialRoute
    }

    override fun open(route: Route, removeCurrentFromStack: Boolean) {
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
    }

    override fun popBack() {
        val previous = navController.backQueue[navController.backQueue.size - 2].destination.route
        if (previous == null || previous == getCurrentRoute().routeName) {
            // preventing double tab
            return
        }

        navController.popBackStack()
    }

    override fun getCurrentRoute(): Route = navController.currentDestination?.route?.toRoute() ?: Route.ListScreen

    override fun getInitialRoute(): Route = initialRoute


}