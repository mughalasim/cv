package mughalasim.my.cv.data.models

data class ReferenceModel(
    val name: String,
    val company: String,
    val contact: String
){
    constructor() : this("", "", "") {}
}