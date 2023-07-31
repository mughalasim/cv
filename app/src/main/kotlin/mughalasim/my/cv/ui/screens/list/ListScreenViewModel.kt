package mughalasim.my.cv.ui.screens.list

import androidx.lifecycle.ViewModel
import cv.domain.usecase.DataUseCase
import mughalasim.my.cv.services.IServiceNavigation
import mughalasim.my.cv.services.Route

class ListScreenViewModel(
    private val serviceNavigation: IServiceNavigation,
    private val useCase: DataUseCase
) : ViewModel() {

    fun getData() = useCase.getData()

    fun openSettings() =
        serviceNavigation.open(Route.SettingsScreen, removeCurrentFromStack = false)

}