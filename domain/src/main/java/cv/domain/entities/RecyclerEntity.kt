package cv.domain.entities

data class RecyclerEntity (
    val title: String? = null,
    val description: DescriptionEntity? = null,
    val skill : SkillEntity? = null,
    val work : ExperienceEntity? = null,
    val education : ExperienceEntity? = null,
    val reference : ReferenceEntity? = null,
    val type: AdapterType
    )

enum class AdapterType {
    TITLE, DESCRIPTION, SKILL, WORK, EDUCATION, REFERENCE
}

