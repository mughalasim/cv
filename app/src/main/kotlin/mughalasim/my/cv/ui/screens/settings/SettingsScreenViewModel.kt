package mughalasim.my.cv.ui.screens.settings

import androidx.lifecycle.ViewModel
import cv.domain.entities.SettingsEntity
import cv.domain.usecase.SettingsUseCase

class SettingsScreenViewModel(
    private val settingsUseCase: SettingsUseCase
): ViewModel() {

    fun getSettings() = settingsUseCase.getSettings()

    fun setSettings(settingsEntity: SettingsEntity) = settingsUseCase.setSettings(settingsEntity)

}