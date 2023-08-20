package cv.domain.entities

import androidx.annotation.Keep

@Keep
@Suppress("unused")
data class SettingsEntity (
    val expandListOnStartUp: Boolean,
){
    constructor() : this(false)
}

fun getFakeSettingsEntity() =
    SettingsEntity(
        expandListOnStartUp = false
    )
