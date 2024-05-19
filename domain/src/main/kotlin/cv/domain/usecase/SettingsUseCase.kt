package cv.domain.usecase

import cv.domain.Variables.EVENT_NAME_SETTING
import cv.domain.Variables.PARAM_EXPAND_LIST_ON_START_UP
import cv.domain.Variables.PARAM_MAIN_SCREEN_ORIENTATION
import cv.domain.Variables.SHARED_PREF_EXPAND_LIST_ON_STARTUP
import cv.domain.Variables.SHARED_PREF_LIST_ORIENTATION
import cv.domain.repositories.AnalyticsRepository
import cv.domain.repositories.SettingsRepository

class SettingsUseCase(
    private val settingsRepository: SettingsRepository,
    private val analyticsRepository: AnalyticsRepository,
) {
    fun getExpandListOnStartUp() = settingsRepository.getBool(SHARED_PREF_EXPAND_LIST_ON_STARTUP)

    fun setExpandListOnStartUp(isEnabled: Boolean) {
        analyticsRepository.logEvent(
            EVENT_NAME_SETTING,
            listOf(Pair(PARAM_EXPAND_LIST_ON_START_UP, isEnabled.toString())),
        )
        settingsRepository.setBool(SHARED_PREF_EXPAND_LIST_ON_STARTUP, isEnabled)
    }

    fun getListOrientation() = settingsRepository.getBool(SHARED_PREF_LIST_ORIENTATION)

    fun setListOrientation(isVertical: Boolean) {
        analyticsRepository.logEvent(
            EVENT_NAME_SETTING,
            listOf(Pair(PARAM_MAIN_SCREEN_ORIENTATION, isVertical.toString())),
        )
        settingsRepository.setBool(SHARED_PREF_LIST_ORIENTATION, isVertical)
    }
}
