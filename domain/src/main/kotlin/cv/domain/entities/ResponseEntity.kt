package cv.domain.entities

data class ResponseEntity(
    val description: DescriptionEntity,
    val skills: List<SkillEntity>,
    val works: List<ExperienceEntity>,
    val educations: List<ExperienceEntity>,
    val references: List<ReferenceEntity>,
) {
    fun getOrderedWork(isAscending: Boolean): List<ExperienceEntity> {
        return if (isAscending)
            {
                works.sortedBy { it.startDate }
            } else {
            works.sortedByDescending { it.startDate }
        }
    }
}

fun getFakeResponse() =
    ResponseEntity(
        description = getFakeDescription(),
        skills = getFakeSkills(),
        works = getFakeExperience(),
        educations = getFakeExperience(),
        references = getFakeReferences(),
    )
