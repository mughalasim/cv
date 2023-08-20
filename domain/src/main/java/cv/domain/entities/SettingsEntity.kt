package cv.domain.entities

data class SettingsEntity (
    val expandListOnStartUp: Boolean,
)

fun getFakeSettingsEntity() =
    SettingsEntity(
        expandListOnStartUp = false
    )
