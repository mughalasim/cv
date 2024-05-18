package cv.domain.entities

import androidx.annotation.Keep

@Keep
data class LanguageEntity(
    val singleTexts: HashMap<String, CharSequence>,
    val pluralTexts: HashMap<String, Array<CharSequence>>,
    val locale: String,
)
