package mughalasim.my.cv.data.models

data class LinksModel(
    val text: String,
    val url: String
){
    constructor() : this("", "") {}
}