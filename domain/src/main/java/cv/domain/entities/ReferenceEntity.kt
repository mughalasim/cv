package cv.domain.entities

data class ReferenceEntity(
    val name: String,
    val company: String,
    val contact: String
){
    constructor() : this("", "", "") {}
}