package mughalasim.my.cv.ui.screens.list

import androidx.lifecycle.ViewModel
import cv.domain.usecase.DataUseCase
import cv.domain.usecase.SettingsUseCase
import mughalasim.my.cv.services.IServiceNavigation
import mughalasim.my.cv.services.Route

class ListScreenViewModel(
    private val serviceNavigation: IServiceNavigation,
    private val dataUseCase: DataUseCase,
    private val settingsUseCase: SettingsUseCase
) : ViewModel() {

    fun getData() = dataUseCase.getData()

    fun getInitialListExpandedState() = settingsUseCase.getSettings().expandListOnStartUp

    fun openSettings() =
        serviceNavigation.open(
            route = Route.SettingsScreen,
            removeCurrentFromStack = false
        )

}