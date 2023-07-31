package mughalasim.my.cv.ui.screens.main

import androidx.lifecycle.ViewModel
import androidx.navigation.NavHostController
import cv.domain.usecase.LanguageUseCase
import mughalasim.my.cv.services.IServiceNavigation

class MainScreenViewModel(
    private val serviceNavigation: IServiceNavigation,
    private val useCase: LanguageUseCase
): ViewModel() {

    fun getLanguage() = useCase.getLanguage()

    fun setNavController(navController: NavHostController) =
        serviceNavigation.setNavController(navController)

    fun getServiceNavigation(): IServiceNavigation =
        serviceNavigation
}