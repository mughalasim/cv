package mughalasim.my.cv.data.models

data class SkillModel(
    val title: String,
    val description: String
){
    constructor() : this("", "") {}
}