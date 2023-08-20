package cv.domain.repositories

interface ISettingsRepository {

    fun getExpandListOnStartUp(): Boolean

    fun setExpandListOnStartUp(isEnabled: Boolean)

}