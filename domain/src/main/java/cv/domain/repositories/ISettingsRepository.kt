package cv.domain.repositories

import cv.domain.entities.SettingsEntity

interface ISettingsRepository {
    fun getSettings(): SettingsEntity
    fun setSettings(settingsEntity: SettingsEntity)
}