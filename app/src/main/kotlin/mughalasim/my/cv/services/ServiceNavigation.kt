package mughalasim.my.cv.services

import androidx.navigation.NavController

interface ServiceNavigation {
    fun setNavController(navController: NavController)

    fun open(
        route: Route,
        removeCurrentFromStack: Boolean = false,
    )

    fun popBack()

    fun getCurrentRoute(): Route

    fun getInitialRoute(): Route
}
