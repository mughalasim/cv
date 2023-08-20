package mughalasim.my.cv.ui.screens.main

import androidx.lifecycle.ViewModel
import androidx.navigation.NavHostController
import cv.domain.usecase.DataUseCase
import mughalasim.my.cv.di.DI
import mughalasim.my.cv.services.IServiceNavigation

class MainScreenViewModel(
    private val dataUseCase: DataUseCase
): ViewModel() {

    fun getLanguage() = dataUseCase.getLanguage()

    fun setNavController(navController: NavHostController) =
        DI.serviceNavigation.setNavController(navController)

    fun getServiceNavigation(): IServiceNavigation =
        DI.serviceNavigation
}