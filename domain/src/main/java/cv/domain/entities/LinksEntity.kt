package cv.domain.entities

data class LinksEntity(
    val text: String,
    val url: String
){
    constructor() : this("", "") {}
}