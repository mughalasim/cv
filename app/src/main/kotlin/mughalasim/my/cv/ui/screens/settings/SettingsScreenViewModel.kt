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
        expandListOnStartUp = settingsUseCase.getExpandListOnStartUp()
    )

    fun navigateBack(){
        DI.serviceNavigation.popBack()
    }

    fun setExpandListOnStartUp(isEnabled: Boolean){
        DI.analytics.logEvent(
            AnalyticsRepository.EVENT_NAME_SETTING,
            listOf(Pair(AnalyticsRepository.PARAM_EXPAND_LIST_ON_START_UP, isEnabled.toString()))
        )
        settingsUseCase.setExpandListOnStartUp(isEnabled)
        _settings.value = getState()
    }

}