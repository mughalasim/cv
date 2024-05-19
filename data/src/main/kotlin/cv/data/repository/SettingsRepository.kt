package cv.data.repository

import android.content.SharedPreferences
import cv.domain.repositories.ISettingsRepository

class SettingsRepository(
    private val sharedPreferences: SharedPreferences,
) : ISettingsRepository {
    override fun setBool(
        settingName: String,
        value: Boolean,
    ) {
        sharedPreferences.edit().putBoolean(settingName, value).apply()
    }

    override fun getBool(settingName: String): Boolean {
        return sharedPreferences.getBoolean(settingName, false)
    }
}
