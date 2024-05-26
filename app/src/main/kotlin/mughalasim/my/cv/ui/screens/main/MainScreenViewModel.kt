package mughalasim.my.cv.ui.screens.main

import androidx.lifecycle.ViewModel
import androidx.navigation.NavHostController
import mughalasim.my.cv.navigation.NavigationService

class MainScreenViewModel(
    private val navigationService: NavigationService,
) : ViewModel() {

    fun setNavController(navController: NavHostController) = navigationService.setNavController(navController)

    fun getNavigationService(): NavigationService = navigationService

}
