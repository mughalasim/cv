package cv.domain.entities

@Suppress("unused")
data class LanguageEntity(
    val txt_education: String,
    val txt_references: String,
    val txt_present: String,
    val txt_address: String,
    val txt_contact_info: String,
    val txt_work_experience: String,
    val txt_links: String,
    val txt_skills: String,
    val txt_telephone: String,
    val txt_email: String,
    val txt_version: String,

    val error_internet_connection: String,
    val error_invalid_link: String,

    val plural_year: List<String>,
    val plural_month: List<String>
){
    constructor(): this(
        "",
        "",
        "",
        "",
        "",
        "",
        "",
        "",
        "",
        "",
        "",
        "",
        "",
        listOf(),
        listOf(),
    )
}

fun getDefaultLanguage() = LanguageEntity(

)