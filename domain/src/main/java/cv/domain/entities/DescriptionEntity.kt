package cv.domain.entities

data class DescriptionEntity (
    val address: String,
    val full_name: String,
    val links: List<LinksEntity>,
    val area_code: Int,
    val email: String,
    val phone: Long,
    val position_title: String
){
    constructor() : this("", "", listOf(), 0, "", 0, "") {}
}