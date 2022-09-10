package cv.domain.entities

data class ResponseEntity (
    val description: DescriptionEntity,
    val skills: List<SkillEntity>,
    val works: List<ExperienceEntity>,
    val educations: List<ExperienceEntity>,
    val references: List<ReferenceEntity>,
    ){
    constructor() : this(DescriptionEntity(), listOf(), listOf(), listOf(), listOf()) {}
}

fun getFakeResponse() = ResponseEntity(
    description = getFakeDescription(),
    skills = getFakeSkills(),
    works = getFakeExperience(),
    educations = listOf(),
    references = listOf()
)