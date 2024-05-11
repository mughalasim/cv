package mughalasim.my.cv.ui.screens.list

import androidx.lifecycle.ViewModel
import cv.domain.usecase.DataUseCase
import cv.domain.usecase.SettingsUseCase
import mughalasim.my.cv.di.DI
import mughalasim.my.cv.services.Route

class ListScreenViewModel(
    private val dataUseCase: DataUseCase,
    private val settingsUseCase: SettingsUseCase,
) : ViewModel() {
    fun getData() = dataUseCase.getData()

    fun getExpandListOnStartUp() = settingsUseCase.getExpandListOnStartUp()

    fun isVerticalOrientation() = settingsUseCase.getListOrientation()

    fun openSettings() = DI.serviceNavigation.open(route = Route.SettingsScreen)

    fun onBannerTapped(bannerName: String) = dataUseCase.onBannerTapped(bannerName)
}
