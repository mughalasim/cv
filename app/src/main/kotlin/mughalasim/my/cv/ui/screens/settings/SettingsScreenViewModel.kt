package mughalasim.my.cv.ui.screens.settings

import androidx.lifecycle.LiveData
import androidx.lifecycle.MutableLiveData
import androidx.lifecycle.ViewModel
import cv.domain.entities.SettingsEntity
import cv.domain.usecase.SettingsUseCase
import mughalasim.my.cv.services.IServiceNavigation

class SettingsScreenViewModel(
    private val settingsUseCase: SettingsUseCase,
    private val serviceNavigation: IServiceNavigation
): ViewModel() {

    private val _settings: MutableLiveData<SettingsEntity> = MutableLiveData(getState())
    val settings: LiveData<SettingsEntity> = _settings

    fun getState() = SettingsEntity(
        expandListOnStartUp = settingsUseCase.getExpandListOnStartUp()
    )

    fun navigateBack(){
        serviceNavigation.popBack()
    }

    fun setExpandListOnStartUp(isEnabled: Boolean){
        settingsUseCase.setExpandListOnStartUp(isEnabled)
        _settings.value = getState()
    }

}