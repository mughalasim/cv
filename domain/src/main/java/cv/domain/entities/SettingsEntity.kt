package cv.domain.entities

import androidx.annotation.Keep

@Keep
@Suppress("unused")
data class SettingsEntity (
    val expandListOnStartUp: Boolean,
    val isVerticalScreen: Boolean
){
    constructor() : this(false, false)
}

fun getFakeSettingsEntity() =
    SettingsEntity(
        expandListOnStartUp = true,
        isVerticalScreen = false
    )
