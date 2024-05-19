package cv.data.models

import androidx.annotation.Keep
import cv.domain.entities.ResponseEntity
import kotlinx.serialization.Serializable

@Keep
@Serializable
data class ResponseModel(
    val description: DescriptionModel,
    val skills: List<SkillModel>,
    val works: List<ExperienceModel>,
    val educations: List<ExperienceModel>,
    val references: List<ReferenceModel>,
)

fun ResponseModel.toResponseEntity() =
    ResponseEntity(
        description = this.description.toDescriptionEntity(),
        skills = this.skills.map { it.toSkillEntity() },
        works = this.works.map { it.toExperienceEntity() },
        educations = this.educations.map { it.toExperienceEntity() },
        references = this.references.map { it.toReferenceEntity() },
    )
