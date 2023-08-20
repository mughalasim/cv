package cv.domain.entities

import androidx.annotation.Keep

@Keep
@Suppress("unused")
data class ResponseEntity (
    val description: DescriptionEntity,
    val skills: List<SkillEntity>,
    val works: List<ExperienceEntity>,
    val educations: List<ExperienceEntity>,
    val references: List<ReferenceEntity>,
    ){
    constructor() : this(DescriptionEntity(), listOf(), listOf(), listOf(), listOf())

    fun getOrderedWork(isAscending: Boolean): List<ExperienceEntity>{
        return if(isAscending){
            works.sortedBy { it.start_date }
        } else {
            works.sortedByDescending { it.start_date }
        }
    }
}

fun getFakeResponse() = ResponseEntity(
    description = getFakeDescription(),
    skills = getFakeSkills(),
    works = getFakeExperience(),
    educations = getFakeExperience(),
    references = getFakeReferences()
)