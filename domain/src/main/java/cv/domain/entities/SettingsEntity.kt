package cv.domain.entities

import androidx.annotation.Keep

@Keep
data class SettingsEntity(
    val expandListOnStartUp: Boolean,
    val isVerticalScreen: Boolean,
)

fun getFakeSettingsEntity() =
    SettingsEntity(
        expandListOnStartUp = true,
        isVerticalScreen = false,
    )
