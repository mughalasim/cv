package mughalasim.my.cv.ui.screens.horizontalPager

import androidx.lifecycle.ViewModel
import cv.data.repository.AnalyticsRepository
import cv.domain.usecase.DataUseCase
import cv.domain.usecase.SettingsUseCase
import mughalasim.my.cv.di.DI
import mughalasim.my.cv.services.Route

class HorizontalPagerScreenViewModel(
    private val dataUseCase: DataUseCase,
    private val settingsUseCase: SettingsUseCase,
) : ViewModel() {

    fun getData() = dataUseCase.getData()

    fun getExpandListOnStartUp() = settingsUseCase.getExpandListOnStartUp()

    fun openSettings() = DI.serviceNavigation.open(route = Route.SettingsScreen)

    fun onBannerTapped(bannerName: String){
        DI.analytics.logEvent(
            AnalyticsRepository.EVENT_NAME_BANNER,
            listOf(Pair(AnalyticsRepository.PARAM_BANNER_NAME, bannerName))
        )
    }

}