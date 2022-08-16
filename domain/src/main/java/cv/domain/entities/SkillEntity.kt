package cv.domain.entities

data class SkillEntity(
    val title: String,
    val description: String
){
    constructor() : this("", "") {}
}