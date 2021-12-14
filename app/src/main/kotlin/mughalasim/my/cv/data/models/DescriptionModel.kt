package mughalasim.my.cv.data.models

data class DescriptionModel (
    val address: String,
    val full_name: String,
    val links: List<LinksModel>,
    val area_code: Int,
    val email: String,
    val phone: Long,
    val position_title: String
){
    constructor() : this("", "", listOf(), 0, "", 0, "") {}
}