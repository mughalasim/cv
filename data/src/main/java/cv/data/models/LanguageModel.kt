package cv.data.models

import androidx.annotation.Keep
import cv.domain.entities.LanguageEntity
import kotlinx.serialization.Serializable

@Keep
@Suppress("unused")
@Serializable
data class LanguageModel(
    val singleTexts: HashMap<String, CharSequence>,
    val pluralTexts: HashMap<String, Array<CharSequence>>,
    val locale: String,
) {
    constructor() : this(
        hashMapOf(),
        hashMapOf(),
        "",
    )
}

fun LanguageModel.toLanguageEntity() =
    LanguageEntity(
        singleTexts = this.singleTexts,
        pluralTexts = this.pluralTexts,
        locale = this.locale,
    )
