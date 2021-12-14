package mughalasim.my.cv.data.models

data class ResponseModel (
    val description: DescriptionModel,
    val skills: List<SkillModel>,
    val works: List<ExperienceModel>,
    val educations: List<ExperienceModel>,
    val references: List<ExperienceModel>,
    ){
    constructor() : this(DescriptionModel(), listOf(), listOf(), listOf(), listOf()) {}
}