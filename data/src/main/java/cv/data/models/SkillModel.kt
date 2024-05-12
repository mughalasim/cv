package cv.data.models

import androidx.annotation.Keep
import cv.domain.entities.SkillEntity
import kotlinx.serialization.Serializable

@Keep
@Serializable
data class SkillModel(
    val title: String,
    val description: String,
)

fun SkillModel.toSkillEntity() =
    SkillEntity(
        title = this.title,
        description = this.description,
    )
