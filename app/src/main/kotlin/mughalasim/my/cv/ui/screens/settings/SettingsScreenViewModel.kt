package mughalasim.my.cv.ui.screens.settings

import androidx.lifecycle.LiveData
import androidx.lifecycle.MutableLiveData
import androidx.lifecycle.ViewModel
import cv.data.repository.AnalyticsRepository
import cv.domain.entities.SettingsEntity
import cv.domain.usecase.SettingsUseCase
import mughalasim.my.cv.di.DI

class SettingsScreenViewModel(
    private val settingsUseCase: SettingsUseCase
): ViewModel() {

    private val _settings: MutableLiveData<SettingsEntity> = MutableLiveData(getState())
    val settings: LiveData<SettingsEntity> = _settings

    fun getState() = SettingsEntity(
        expandListOnStartUp = settingsUseCase.getExpandListOnStartUp(),
        isVerticalScreen =  settingsUseCase.getListOrientation()
    )

    fun navigateBack(){
        DI.serviceNavigation.popBack()
    }

    fun setExpandListOnStartUp(isEnabled: Boolean){
        settingsUseCase.setExpandListOnStartUp(isEnabled)
        _settings.value = getState()
    }

    fun setListOrientation(isVertical: Boolean){
        settingsUseCase.setListOrientation(isVertical)
        _settings.value = getState()
    }

}