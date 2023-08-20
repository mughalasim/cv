package cv.domain.usecase

import cv.domain.repositories.ISettingsRepository

class SettingsUseCase(
    private val settingsRepository: ISettingsRepository
) {
    fun getExpandListOnStartUp() = settingsRepository.getExpandListOnStartUp()

    fun setExpandListOnStartUp(isEnabled: Boolean) = settingsRepository.setExpandListOnStartUp(isEnabled)
}