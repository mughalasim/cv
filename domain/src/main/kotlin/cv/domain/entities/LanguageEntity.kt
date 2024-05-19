package cv.domain.entities

data class LanguageEntity(
    val singleTexts: HashMap<String, CharSequence>,
    val pluralTexts: HashMap<String, Array<CharSequence>>,
    val locale: String,
)
