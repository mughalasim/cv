package cv.domain.entities

data class SettingsEntity(
    val expandListOnStartUp: Boolean,
    val isVerticalScreen: Boolean,
)

fun getFakeSettingsEntity() =
    SettingsEntity(
        expandListOnStartUp = true,
        isVerticalScreen = true,
    )
