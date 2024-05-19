package cv.domain.repositories

interface SettingsRepository {
    fun setBool(
        settingName: String,
        value: Boolean,
    )

    fun getBool(settingName: String): Boolean
}
