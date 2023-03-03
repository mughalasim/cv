package cv.domain.entities

@Suppress("unused")
data class LanguageEntity(
    val singleTexts: HashMap<String, CharSequence>,
    val pluralTexts: HashMap<String, Array<CharSequence>>,
    val locale: String
){
    constructor(): this(
        hashMapOf(),
        hashMapOf(),
        ""
    )
}