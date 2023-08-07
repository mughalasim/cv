package cv.domain.usecase

import cv.domain.entities.SettingsEntity
import cv.domain.repositories.ISettingsRepository

class SettingsUseCase(
    private val settingsRepository: ISettingsRepository
) {
    fun getSettings() = settingsRepository.getSettings()

    fun setSettings(settingsEntity: SettingsEntity) = settingsRepository.setSettings(settingsEntity)
}