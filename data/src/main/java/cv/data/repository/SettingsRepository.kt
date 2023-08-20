package cv.data.repository

import android.content.SharedPreferences
import cv.domain.repositories.ISettingsRepository

class SettingsRepository(
    private val sharedPreferences: SharedPreferences
) : ISettingsRepository {

    override fun getExpandListOnStartUp(): Boolean {
     return sharedPreferences.getBoolean(SHARED_PREF_EXPAND_LIST_ON_STARTUP, false)
    }

    override fun setExpandListOnStartUp(isEnabled: Boolean) {
        sharedPreferences.edit().putBoolean(SHARED_PREF_EXPAND_LIST_ON_STARTUP, isEnabled).apply()
    }

    companion object{
        const val SHARED_PREF_EXPAND_LIST_ON_STARTUP = "SHARED_PREF_EXPAND_LIST_ON_STARTUP"
    }

}