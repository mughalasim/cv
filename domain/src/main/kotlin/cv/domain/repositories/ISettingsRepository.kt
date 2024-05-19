package cv.domain.repositories

interface ISettingsRepository {
    fun setBool(
        settingName: String,
        value: Boolean,
    )

    fun getBool(settingName: String): Boolean
}
