package cv.data.repository

import android.content.SharedPreferences
import cv.domain.entities.SettingsEntity
import cv.domain.repositories.ISettingsRepository

class SettingsRepository(
    private val sharedPreferences: SharedPreferences
) : ISettingsRepository {

    // TODO - Make this a callbackFlow and create functions to save individual settings
    override fun getSettings(): SettingsEntity {

        val listener = SharedPreferences.OnSharedPreferenceChangeListener { sharedPreferences, s ->

        }

//        sharedPreferences.registerOnSharedPreferenceChangeListener(listener)

//        sharedPreferences.unregisterOnSharedPreferenceChangeListener(listener)

     return SettingsEntity (
         expandListOnStartUp = sharedPreferences.getBoolean(SHARED_PREF_EXPAND_LIST_ON_STARTUP, false)
     )
    }

    override fun setSettings(settingsEntity: SettingsEntity) {
        sharedPreferences.edit().putBoolean(SHARED_PREF_EXPAND_LIST_ON_STARTUP, settingsEntity.expandListOnStartUp).apply()
    }

    companion object{
        const val SHARED_PREF_EXPAND_LIST_ON_STARTUP = "SHARED_PREF_EXPAND_LIST_ON_STARTUP"
    }

}